package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-04-28 18:31
 */
@Entity
@Table(name = "MS_GIS_THEMATIC")
public class Thematic implements Serializable {
	private Long id;
	private String name;
	private Integer sort;
	private String initializationClass;
	private OlMap olMap;

	public Thematic() {
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

	@Column(name = "SORT_")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "INITIALIZATION_CLASS_")
	public String getInitializationClass() {
		return initializationClass;
	}

	public void setInitializationClass(String initializationClass) {
		this.initializationClass = initializationClass;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAP_ID_")
	public OlMap getOlMap() {
		return olMap;
	}

	public void setOlMap(OlMap olMap) {
		this.olMap = olMap;
	}
}
