package org.kyll.myserver.base.sys.entity;

import org.kyll.myserver.base.app.entity.Module;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2014-11-05 13:04
 */
@Entity
@Table(name = "MS_SYS_ROLE")
public class Role implements Serializable {
	private Long id;
	private String name;
	private String description;
	private Integer sort;
	private Set<Module> functionSet;

	public Role() {
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

	@Column(name = "DESCRIPTION_")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SORT_")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToMany
	@JoinTable(name = "MS_SYS_ROLE_FUNCTION", joinColumns = {@JoinColumn(name = "ROLE_ID_")}, inverseJoinColumns = {@JoinColumn(name = "FUNCTION_ID_")})
	public Set<Module> getFunctionSet() {
		return functionSet;
	}

	public void setFunctionSet(Set<Module> functionSet) {
		this.functionSet = functionSet;
	}
}
