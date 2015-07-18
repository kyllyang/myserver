package org.kyll.myserver.business.vo;

import org.kyll.myserver.base.common.Vo;
import org.kyll.myserver.business.entity.Area;
import org.kyll.myserver.business.entity.Role;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-11-07 13:52
 */
public class EmployeeVo implements Vo<Long> {
	private Long id;
	private String name;
	private String username;
	private String password;
	private String passwordReset;
	private Integer freeze;
	private String email;
	private Integer sort;

	private Long[] areaIds;
	private Long[] roleIds;
	private List<Area> areaList;
	private List<Role> roleList;

	public EmployeeVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordReset() {
		return passwordReset;
	}

	public void setPasswordReset(String passwordReset) {
		this.passwordReset = passwordReset;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getFreeze() {
		return freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long[] getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(Long[] areaIds) {
		this.areaIds = areaIds;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
