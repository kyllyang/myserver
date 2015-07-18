package org.kyll.myserver.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2014-11-05 12:48
 */
@Entity
@Table(name = "MS_LI_EMPLOYEE")
public class Employee implements Serializable {
	private Long id;
	private String name;
	private String username;
	private String password;
	private Integer freeze;
	private String email;
	private Integer sort;
	private Set<Area> areaSet;
	private Set<Role> roleSet;

	public Employee() {
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID_")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME_")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USERNAME_")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD_")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "FREEZE_")
	public Integer getFreeze() {
		return freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	@Column(name = "EMAIL_")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SORT_")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToMany
	@JoinTable(name = "MS_LI_EMPLOYEE_AREA", joinColumns = {@JoinColumn(name = "EMPLOYEE_ID_")}, inverseJoinColumns = {@JoinColumn(name = "AREA_ID_")})
	public Set<Area> getAreaSet() {
		return areaSet;
	}

	public void setAreaSet(Set<Area> areaSet) {
		this.areaSet = areaSet;
	}

	@ManyToMany
	@JoinTable(name = "MS_LI_EMPLOYEE_ROLE", joinColumns = {@JoinColumn(name = "EMPLOYEE_ID_")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID_")})
	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}
}
