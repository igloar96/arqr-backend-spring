package com.softwaretina.services.auth;

import org.springframework.stereotype.Component;

@Component("authentication")
public class PermissionEvaluator {
    public boolean can(){
        return false;
    }
}
