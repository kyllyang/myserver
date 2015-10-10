package org.kyll.myserver.base.sys.vo;

import org.kyll.myserver.base.sys.entity.Role;

import java.util.Map;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2014-11-07 15:46
 */
public class SessionVo {
	private Long userId;
	private String username;
	private Set<Role> roleSet;
	private Map<String, String> config;

	public SessionVo() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}
}
