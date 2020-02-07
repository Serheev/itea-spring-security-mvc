package com.serheev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AppController {

    @GetMapping(value = {"/", "/homepage**"})
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Home Page");
        model.addObject("message", "Welcome Page!");
        model.addObject("description", "This page is available to all visitors without exception.");
        model.setViewName("index");
        model.setStatus(HttpStatus.OK);
        return model;
    }

    @GetMapping(value = "/opened**")
    public ModelAndView openedPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Opened resource");
        model.addObject("message", "This is opened page - for anonymous users!");
        model.addObject("description", "This page is available to users with the ANONYM role.");
        model.setViewName("opened");
        model.setStatus(HttpStatus.OK);
        return model;
    }

    @GetMapping(value = "/closed**")
    public ModelAndView closedPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Closed resource");
        model.addObject("message", "This is closed page - for anonymous users!");
        model.addObject("description", "This page is available to users with the USER role.");
        model.setViewName("closed");
        model.setStatus(HttpStatus.OK);
        return model;
    }

    @GetMapping(value = "/protected**")
    public ModelAndView protectedPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Protected resource");
        model.addObject("message", "This is protected page - Only for Admin Users!");
        model.addObject("description", "This page is available only to users with the ADMIN role.");
        model.setViewName("protected");
        model.setStatus(HttpStatus.OK);
        return model;
    }

}
