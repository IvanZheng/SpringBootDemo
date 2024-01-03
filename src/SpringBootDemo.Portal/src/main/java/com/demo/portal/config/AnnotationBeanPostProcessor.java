package com.demo.portal.config;


import com.demo.portal.rabbitmq.Consumer;
import lombok.var;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class AnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private final Map<String, Object> properties = new HashMap<>();
    public AnnotationBeanPostProcessor(ConfigurableApplicationContext context) {
        var env = context.getEnvironment();
        env.getPropertySources().forEach(propertySource -> {
            if (propertySource instanceof MapPropertySource) {
                MapPropertySource mapPropertySource = (MapPropertySource) propertySource;
                properties.putAll(mapPropertySource.getSource());
            }
        });
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Consumer){
            System.out.println("aaa");
        }
        var classAnnotations = Arrays.stream(bean.getClass().getAnnotations());
        var methodAnnotations = Arrays.stream(bean.getClass().getMethods()).flatMap(m -> Arrays.stream(m.getAnnotations()));
        var fieldAnnotations = Arrays.stream(bean.getClass().getDeclaredFields()).flatMap(f -> Arrays.stream(f.getAnnotations()));
        var annotations = Stream.concat(classAnnotations, methodAnnotations);
        annotations = Stream.concat(annotations, fieldAnnotations)
                            .filter(a -> Arrays.stream(a.annotationType().getAnnotations())
                                               .anyMatch(aa -> aa instanceof ValuedAnnotation));

        annotations.forEach(a -> {
            InvocationHandler h = Proxy.getInvocationHandler(a); // 获取 AnnotationInvocationHandler
            Field hField = null; // 获取 memberValues 字段
            try {
                hField = h.getClass().getDeclaredField("memberValues");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            hField.setAccessible(true); // 因为这个字段事 private final 修饰，所以要打开权限

            try {
                var memberValues = (Map) hField.get(h);
                memberValues.keySet().forEach(k -> {
                    var value = replacePlaceholders(memberValues.get(k).toString());
                    memberValues.put(k, value);
                });

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return bean;
    }

    public String replacePlaceholders(String input) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]+\\}");
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(0);
            var valueObject = properties.get(key.substring(2, key.length()-1));
            if (valueObject != null){
                String value = valueObject.toString();
                if (value != null) {
                    matcher.appendReplacement(sb, value);
                }
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}
