package com.demo.portal.controllers;

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
    public String index(Principal principal, Model model) throws UnsupportedEncodingException {
        String tokenValue = ((OAuth2AuthenticationDetails)((OAuth2Authentication) principal).getDetails()).getTokenValue();
        String logoutUri = String.format("http://zero.teamcore.cn/ids/connect/endsession?post_logout_redirect_uri=%s&id_token_hint=%s",
                URLEncoder.encode("http://localhost:8082/home", StandardCharsets.UTF_8.toString()), tokenValue);
        model.addAttribute("username", principal == null ? "" : principal.getName());
        model.addAttribute("logoutUri", logoutUri);
        return "index";
    }
}

