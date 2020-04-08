package com.demo.portal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "", method = GET)
    public String index(Principal principal, Model model) throws UnsupportedEncodingException, JsonProcessingException {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)((OAuth2Authentication) principal).getDetails();
        String tokenValue = details.getTokenValue();
        String logoutUri = String.format("http://zero.teamcore.cn/ids/connect/endsession?post_logout_redirect_uri=%s&id_token_hint=%s",
                URLEncoder.encode("http://localhost:8082/home", StandardCharsets.UTF_8.toString()), tokenValue);
        model.addAttribute("username", principal == null ? "" : principal.getName());
        model.addAttribute("logoutUri", logoutUri);
        model.addAttribute("details", new ObjectMapper().writeValueAsString(((OAuth2Authentication) principal).getUserAuthentication().getDetails()));
        return "index";
    }
}

