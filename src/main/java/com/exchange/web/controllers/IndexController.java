package com.exchange.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by greenlucky on 1/14/17.
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String home(){
        return "index";
    }
}
