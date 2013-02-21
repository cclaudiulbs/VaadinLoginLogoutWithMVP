package com.cc.vaadin.logindemo.presenters;

import com.cc.vaadin.logindemo.views.LoginView;
import com.cc.vaadin.logindemo.views.WelcomeInView;

/**
 * @author cclaudiu
 * When the User is logged in there's no need for a Model anymore in this small application
 */

public class WelcomeInPresenter implements WelcomeInView.WelcomInViewListener {

    private WelcomeInView currentView;
    private LoginView nextView;

    public WelcomeInPresenter(WelcomeInView currentView, LoginView nextView) {
        this.currentView = currentView;
        this.nextView = nextView;

        currentView.addListener(this);
    }

    /** The Presenter is in charge of dispatching to the next View, the View only implements this behavior */
    @Override
    public void logoutEvent() {
        currentView.setLogoutPage(nextView);
    }
}