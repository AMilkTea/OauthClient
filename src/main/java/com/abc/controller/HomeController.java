package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lyh on 17-6-26.
 */
@Controller
public class HomeController {

    @Autowired
    private Environment env;

    @RequestMapping(value = {"/", "/test"}, method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public String authenticate(){
        String redirect = "redirect:" + env.getProperty("authorize_url") + "?response_type=code&client_id=" + env.getProperty("client_id") + "&redirect_uri=" + env.getProperty("redirect_uri");
        return redirect;
    }
}
