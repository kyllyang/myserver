package org.kyll.myserver.base.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2014-12-02 16:27
 */
@Entity
@Table(name = "MS_SYS_CONFIG")
public class Config implements Serializable {
	private Long id;
	private String key;
	private String value;
	private String description;
	private Integer sort;

	public Config() {
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

	@Column(name = "KEY_")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "VALUE_")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
