package com.cc.vaadin.logindemo.resources;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cclaudiu
 *
 */

public class User {
    private final String userName;
	private final String password;
    private final Boolean rememberMe;
    
    public User(String userName, String password, Boolean rememberMe) {
    	this.userName = userName;
    	this.password = password;
    	this.rememberMe = rememberMe;
    }
    
    @Override public boolean equals(Object that) {
    	return EqualsBuilder.reflectionEquals(this, that);
    }
    
    @Override public int hashCode() {
    	return HashCodeBuilder.reflectionHashCode(this);
    }
    
    public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}


	public Boolean getRememberMe() {
		return rememberMe;
	}
}