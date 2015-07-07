package org.kyll.myserver.base.app.entity;

import org.kyll.myserver.base.sys.entity.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2014-11-05 12:54
 */
@Entity
@Table(name = "MS_APP_MODULE")
public class Module implements Serializable {
	private Long id;
	private Module parent;
	private String name;
	private String type;
	private String funcType;
	private String funcCode;
	private String description;
	private Integer sort;

	public Module() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID_")
	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	@Column(name = "NAME_")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE_")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "FUNC_TYPE_")
	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	@Column(name = "FUNC_CODE_")
	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
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
}
