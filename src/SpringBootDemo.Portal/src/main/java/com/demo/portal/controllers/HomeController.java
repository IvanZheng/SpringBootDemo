package com.demo.portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "", method = GET)
    public String index(Principal principal, Model model)
    {
        model.addAttribute("username", principal == null ? "" : principal.getName());
        return "index";
    }
}
