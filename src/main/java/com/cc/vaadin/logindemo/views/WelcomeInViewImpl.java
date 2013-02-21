package com.cc.vaadin.logindemo.views;

import com.cc.vaadin.logindemo.resources.User;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 * Pure Vaadin Implementation of the WelcomeInView Interface
 */

public class WelcomeInViewImpl extends CustomComponent implements WelcomeInView, Button.ClickListener {

    private List<WelcomInViewListener> listeners = new ArrayList<WelcomInViewListener>();

    public WelcomeInViewImpl() {
        VerticalLayout mainLayout = new VerticalLayout();
        setSizeFull();

        VerticalLayout logoutLayout = new VerticalLayout();
        Button logoutButton = new Button("Logout");
        logoutButton.addListener(this);

        logoutLayout.addComponent(logoutButton);
        logoutLayout.setComponentAlignment(logoutButton, Alignment.TOP_RIGHT);

        Embedded imageEmbedded = getImageHelper();

        mainLayout.addComponent(logoutLayout);
        mainLayout.addComponent(imageEmbedded);

        mainLayout.setExpandRatio(imageEmbedded, 1);

        setCompositionRoot(mainLayout);
    }

    /** Called by Presenter to register itself in the Presenter Constructor */
    @Override public void addListener(WelcomInViewListener welcomeInViewListener) {
        listeners.add(welcomeInViewListener);
    }

    /** Each view is decoupled from the Next View, Using Views rather Windows!
     * Hint: call getApplication() no more than ONCE inside, and do not use chain calls using getApplication() */
    @Override
    public void setLogoutPage(ComponentContainer view) {
        Window mainWindow = getApplication().getMainWindow();
        mainWindow.setContent(view);

        mainWindow.showNotification("User " + ((User) mainWindow.getApplication().getUser()).getUserName()
                                  + " just logged out from the System",
                Window.Notification.TYPE_TRAY_NOTIFICATION);
    }

    /** TODO: user should be persisted during UI Interaction, so that it can be displayed */
    @Override
    public void buttonClick(Button.ClickEvent event) {
        for(WelcomInViewListener eachListener : listeners) {
            eachListener.logoutEvent();
        }
    }

    private Embedded getImageHelper() {
        final String urlString = "http://upload.wikimedia.org/wikipedia/en/4/46/Userpage_decoration_for_Redvers_-_Welcome.png";
        final Resource resource = new ExternalResource(urlString);
        final Embedded imageEmbedded = new Embedded("", resource);

        return imageEmbedded;
    }
}