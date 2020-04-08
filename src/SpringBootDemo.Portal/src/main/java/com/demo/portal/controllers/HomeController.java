package com.demo.portal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "", method = GET)
    public String index(Principal principal, Model model) throws JsonProcessingException {
//        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)((OAuth2Authentication) principal).getDetails();
//        String tokenValue = details.getTokenValue();
//        String logoutUri = String.format("http://zero.teamcore.cn/ids/connect/endsession?post_logout_redirect_uri=%s&id_token_hint=%s",
//               URLEncoder.encode("http://localhost:8082/home", StandardCharsets.UTF_8.toString()), tokenValue);
        OidcIdToken token = ((DefaultOidcUser)((OAuth2AuthenticationToken) principal).getPrincipal()).getIdToken();
        model.addAttribute("username", principal == null ? "" : ((DefaultOidcUser) ((OAuth2AuthenticationToken) principal).getPrincipal()).getUserInfo().getFullName());
        //model.addAttribute("logoutUri", logoutUri);

        model.addAttribute("tokenValue", new ObjectMapper().writeValueAsString(token.getTokenValue()));
        model.addAttribute("claims", new ObjectMapper().writeValueAsString(token.getClaims()));
        return "home/index";
    }
    @RequestMapping(value = "logout", method = GET)
    public String logout(){
        return "home/logout";
    }
}

