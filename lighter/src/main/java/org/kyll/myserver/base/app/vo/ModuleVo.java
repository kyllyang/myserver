package org.kyll.myserver.base.app.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2014-11-07 13:40
 */
public class ModuleVo implements Vo<Long> {
	private Long id;
	private Long parentId;
	private String parentName;
	private String name;
	private String type;
	private String typeText;
	private String funcType;
	private String funcTypeText;
	private String funcCode;
	private String description;
	private Integer sort;

	public ModuleVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeText() {
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	public String getFuncTypeText() {
		return funcTypeText;
	}

	public void setFuncTypeText(String funcTypeText) {
		this.funcTypeText = funcTypeText;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
