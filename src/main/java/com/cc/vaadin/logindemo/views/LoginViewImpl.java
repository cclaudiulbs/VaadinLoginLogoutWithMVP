package com.cc.vaadin.logindemo.views;

import com.cc.vaadin.logindemo.resources.User;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 *
 * Each LoginView LogoutView or WelcomeInViewImpl is a concrete Implementer of its corresponding Interface
 * and also a Window! By Contract for the Presenter to be loosely decoupled, an implementation
 * inherited from the Super Abstract MyView inherited is implemented to return an instance of the
 * to be delegated View
 *
 * Hint: Use Views instead of Windows in Vaadin! (that is extend CUstomComponent and using "setContent()" set
 * the content of the View)
 */

public class LoginViewImpl extends CustomComponent implements LoginView, Button.ClickListener {

    /** Only the Presenter registers ONE Listener */
    private final List<LoginViewListener> listeners = new ArrayList<>();

    private final TextField userNameField = new TextField("Please enter username: ");
    private final PasswordField passwordField = new PasswordField("Please enter password: ");
    private final Button rememberMe = new CheckBox("Remember me");

    private User loggedUser;

    public LoginViewImpl() {
        /** Format the main Layout of the View */
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        /** Format the header of the View */
        VerticalLayout headerLayout = new VerticalLayout();
        Label headerText = new Label("<b>Welcome to Login/Logout Vaadin Application Implemented using MVP Pattern</b>",
                                    Label.CONTENT_XHTML);
        headerText.setSizeUndefined();
        headerLayout.addComponent(headerText);
        headerLayout.setComponentAlignment(headerText, Alignment.MIDDLE_CENTER);

        /** Format the Body of the View */
        Panel bodyLayout = new Panel();
        // By default the Panel has a VerticalLayout, which takes 100 Width, so cannot be centerized, set it to shrink
        bodyLayout.setWidth(30, Sizeable.UNITS_PERCENTAGE);

        // register on clicks events and forward them to Presenter, which handles the UI Interaction
        final Button loginButton = new Button("Log Me In");
        loginButton.addListener(this);

        final FormLayout loginForm = new FormLayout();
        loginForm.setSizeFull();
        loginForm.addComponent(userNameField);
        loginForm.addComponent(passwordField);
        loginForm.addComponent(rememberMe);
        loginForm.addComponent(loginButton);
        loginForm.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);

        bodyLayout.addComponent(loginForm);

        /** Format the Footer of the View */
        VerticalLayout footerLayout = new VerticalLayout();

        Label footerText = new Label("<b>Vaadin is a wonderful UI RIA Framework!</b>", Label.CONTENT_XHTML);
        footerText.setSizeUndefined();

        footerLayout.addComponent(footerText);
        footerLayout.setComponentAlignment(footerText, Alignment.BOTTOM_CENTER);

        // Add all Layouts Components to the mainLayout which setup the Layout of the LoginView Window
        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(bodyLayout);
        mainLayout.addComponent(footerLayout);

        // Align the Login FormLayout to center, before set a Width != 100 for Panel
        mainLayout.setComponentAlignment(bodyLayout, Alignment.TOP_CENTER);

        // The body should shrink to fill in the whole space remained!
        mainLayout.setExpandRatio(bodyLayout, 1.0f);

         // since CustomComponent also has a VerticalLayout which has the Height undefined
        setSizeFull();
        setCompositionRoot(mainLayout);
    }

    /**
     * this is called at runtime when an implementation of the getWindow() is provided based on the instance of the
     * passed in view
     */
    @Override
    public void setLoginWindow(ComponentContainer toDispatchView, User user) {
        Window mainWindow = getApplication().getMainWindow();
        getApplication().setUser(user);
        getApplication().getMainWindow().setContent(toDispatchView);

        mainWindow.showNotification("User " + user.getUserName() + " logged into Application!",
                Window.Notification.TYPE_TRAY_NOTIFICATION);
    }

    /**
     * Only the Presenter registers ONE Listener; (this is called from Presenter to register itself)
     */
    @Override public void addListener(LoginViewListener loginViewListener) {
        listeners.add(loginViewListener);
    }

    /** Delegate each buttonClick Event to the Presenter to handle the UI Interaction
     * The View is implemented in such a way that it is independent of the Presenter Implementation */
    @Override public void buttonClick(Button.ClickEvent event) {

        for(LoginViewListener eachListener : listeners) {

            loggedUser = new User(userNameField.getValue().toString(),
                                  passwordField.getValue().toString(),
                                  Boolean.valueOf(rememberMe.getValue().toString()));

            eachListener.loginEvent(loggedUser);
        }
    }
}