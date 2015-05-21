package org.kyll.myserver.base.sys.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2014-11-07 13:52
 */
public class EmployeeVo implements Vo<Long> {
	private Long id;
	private String name;
	private String username;
	private String password;
	private Integer freeze;
	private String email;
	private Integer sort;
	private Long departmentId;
	private String departmentName;

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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
