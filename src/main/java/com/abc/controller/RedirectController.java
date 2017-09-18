package com.abc.controller;

import com.abc.utility.HttpUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * Created by lyh on 17-6-27.
 */
@Controller
public class RedirectController {

    @Autowired
    private Environment env;

    private HashMap<String, String> getTokenMap(String s){
        String[] array = s.split("&");
        HashMap map = new HashMap();
        for(int i = 0; i < array.length; i++){
            int index = array[i].indexOf("=");
            String a = array[i].substring(0, index);
            String b = array[i].substring(index + 1);
            map.put(a, b);
        }

        return map;
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect(@RequestParam(value = "code", required = true) String code, Model model){
        String url = env.getProperty("token_uri");
        String params = "grant_type=authorization_code&client_id=" + env.getProperty("client_id") + "&client_secret=" + env.getProperty("client_secret") +
                "&code=" + code + "&redirect_uri=" + env.getProperty("redirect_uri");

        try {
            String response = HttpUtility.sendPost(url, params);
            HashMap tokenMap = getTokenMap(response);
            String accessToken = (String) tokenMap.get("access_token");

            model.addAttribute("access_token", accessToken);

            url = env.getProperty("profile_url") + "?access_token=" + accessToken;
            String profile = HttpUtility.sendGet(url);

            model.addAttribute("profile", profile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "profile";
    }
}
