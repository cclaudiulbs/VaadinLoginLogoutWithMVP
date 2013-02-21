package com.cc.vaadin.logindemo.views;

import com.cc.vaadin.logindemo.resources.User;
import com.vaadin.ui.ComponentContainer;

/**
 * @author cclaudiu
 * Hint: work with Views(CustomComponents, and setContent() of the Windows, rather than changin mainWindows!)
 */

public interface LoginView extends ComponentContainer {

    /**
     * This method is implemented in the LoginView Implementer - what should happen when the
     * user is OK, and this method is actually called by the Presenter based on the Implementation
     * this method establishes what View is delegated, next after the login is successfull;
     */
    void setLoginWindow(ComponentContainer toDispatchedView, User user);

    void addListener(LoginViewListener loginViewListener);

    interface LoginViewListener {
        void loginEvent(User user);
    }

}