package com.demo.portal.rabbitmq;

import com.demo.portal.config.ValuedAnnotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Topics {
    Topic[] value();
}
