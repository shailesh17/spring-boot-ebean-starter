package com.ssp.model;

/**
 * Defines the user roles in the system.
 * @author shailesh.patel
 */
public enum RoleType {
	
	CELEBRITY
	,FAN
	,ADMIN;
	
	public String getCode() {
		return this.name();
	}
}
