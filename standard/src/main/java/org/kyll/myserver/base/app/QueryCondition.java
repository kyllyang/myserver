package org.kyll.myserver.base.app;

/**
 * User: Kyll
 * Date: 2015-04-20 18:29
 */
public class QueryCondition {
	private Long id;
	private Long parentId;
	private String name;
	private Integer type;
	private Integer funcType;

	public QueryCondition() {
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFuncType() {
		return funcType;
	}

	public void setFuncType(Integer funcType) {
		this.funcType = funcType;
	}
}
