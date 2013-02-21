package com.cc.vaadin.logindemo.application;

import com.cc.vaadin.logindemo.models.LoginModel;
import com.cc.vaadin.logindemo.presenters.LoginPresenter;
import com.cc.vaadin.logindemo.presenters.WelcomeInPresenter;
import com.cc.vaadin.logindemo.views.LoginView;
import com.cc.vaadin.logindemo.views.LoginViewImpl;
import com.cc.vaadin.logindemo.views.WelcomeInView;
import com.cc.vaadin.logindemo.views.WelcomeInViewImpl;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 *
 * In this Vaadin-Application we'll demonstrate the Model-View-Presenter
 * aka MVP Pattern
 * It's very good to code on this design, since it provides a clean separation of
 * each concern:
 * - views do not contain any business logic
 * - model is in charge of the domains, and heavy computation which do not have anything
 *   in common with Vaadin framework at all
 * - presenter which acts as the kernel of the application( as controller in MVC )
 *  but the here we have a presenter/view; the presenter takes the view User Interaction
 *  , retrieves data from the domain, and populates the view back with data;
 *  each layer is loose-coupled, and is open to change but closed to any modification;
 *  also each component has it's own responsability, that is highly cohesive;
 *
 *  Steps: We'll start to create our views, as designed by interfaces, where there's NO logic;
 *  Foreach View Interface, there's also a View Implementer concrete class;
 *  THe View implementer is a pure Vaadin implementation of the UI; this should implement and
 *  delegate to Presenter all the UI Interaction; the Presenter process the data with the "help" of the
 *  Model, and dispatches to the next View;
 *
 *  Since Vaadin is a single-interface-page, we work with only the main Window, and use to set its content
 */

public class MainApplication extends Application {

    @Override public void init() {
        final Window mainWindow = new Window("Main Application Window");
        setMainWindow(mainWindow);

        final LoginView loginView = new LoginViewImpl();
        final WelcomeInView welcomeInView = new WelcomeInViewImpl();

        final LoginModel loginModel = new LoginModel();

        mainWindow.setContent(loginView);

        // tie together the View and the Model through the Presenter
        new LoginPresenter(loginView, loginModel, welcomeInView);
        new WelcomeInPresenter(welcomeInView, loginView);
    }
}