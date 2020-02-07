package com.serheev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class BaseController{
    @GetMapping("/somepage")
    public ModelAndView getSomePage(){
        return new ModelAndView("somepage", HttpStatus.OK);
    }
    @GetMapping("/somepage/secondpage")
    public ModelAndView getSecondPage(){
        return new ModelAndView("secondpage", HttpStatus.OK);
    }
}
