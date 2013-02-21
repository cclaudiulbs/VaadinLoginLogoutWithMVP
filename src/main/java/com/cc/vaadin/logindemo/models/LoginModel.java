package com.cc.vaadin.logindemo.models;

import com.cc.vaadin.logindemo.resources.User;

/**
 * @author cclaudiu
 * This Model Class does NOT have anything in common with Vaadin!
 * It is usually the deepest level of abstraction, where the business logic of the application
 * is defined; can fetch data directly from a GenericDao; call Shiro for authentication of the User
 * and many other operations; In our case there's some basic checks which can be implemented
 * with Shiro for validation and user session persisence;
 */

public class LoginModel {
    // Some business logic t validate the input user against these values
    private static final String userNameShouldBe = "cclaudiu";
    private static final String passwordShouldBe = "1234";

    public boolean validateUser(User user) {
        if(userNameShouldBe.equals(user.getUserName()) && passwordShouldBe.equals(user.getPassword())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}