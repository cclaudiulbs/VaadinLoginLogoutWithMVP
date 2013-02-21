package com.cc.vaadin.logindemo.views;

import com.vaadin.ui.ComponentContainer;

/**
 * @author cclaudiu
 * Designed by Interface-View - the View is loosely decoupled from the Presenter
 * and the Presenter works via Interface View
 */

public interface WelcomeInView extends ComponentContainer {

    void setLogoutPage(ComponentContainer view);

    void addListener(WelcomInViewListener welcomInViewListener);

    interface WelcomInViewListener {
        void logoutEvent();
    }

}