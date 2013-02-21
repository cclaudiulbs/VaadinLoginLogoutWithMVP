package com.cc.vaadin.logindemo.presenters;

import com.cc.vaadin.logindemo.models.LoginModel;
import com.cc.vaadin.logindemo.resources.User;
import com.cc.vaadin.logindemo.views.LoginView;
import com.cc.vaadin.logindemo.views.WelcomeInView;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 *
 * Define a presenter foreach of the Views
 * Each Presenter implement the Inner Interface provided in the LoginViewListener
 */

public class LoginPresenter implements LoginView.LoginViewListener {
    private LoginView loginView;
    private LoginModel loginModel;
    private WelcomeInView nextView;

    public LoginPresenter(LoginView loginView, LoginModel loginModel, WelcomeInView nextView) {
        this.loginView = loginView;
        this.loginModel = loginModel;
        this.nextView = nextView;

        // the Presenter register itself through the addListener(), in a independent way that the View doesn't know anything
        // about the Presenter; Note that the View could be implemented to know and call the Presenter directly
        loginView.addListener(this);
    }

    /**
     * If the validation-process of the Presenter passes, the Presenter dispatches to a corresponding View
     */
    @Override
    public void loginEvent(User user) {
        if(loginModel.validateUser(user)) {
            loginView.setLoginWindow(nextView, user);
        } else {
            loginView.getWindow().showNotification("User is NOT authorized to Login!", Window.Notification.TYPE_ERROR_MESSAGE);
        }
    }
}