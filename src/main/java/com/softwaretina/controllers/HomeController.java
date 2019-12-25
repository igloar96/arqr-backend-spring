package com.softwaretina.controllers;

import com.softwaretina.services.auth.LogginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HomeController {

    @Autowired
    private LogginService logginService;

    @GetMapping("/login")
    void requestLogin(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setHeader("Location","login.html");
        httpServletResponse.setStatus(302);

    }

    @GetMapping("/")
    void redirectToAuth(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Location","login.html");
        httpServletResponse.setStatus(302);
    }

}
