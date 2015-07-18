package org.kyll.myserver.business.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2014-11-05 13:04
 */
@Entity
@Table(name = "MS_LI_ROLE")
public class Role implements Serializable {
	private Long id;
	private String code;
	private String name;
	private String description;
	private Integer sort;

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

	@Column(name = "CODE_")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
}
