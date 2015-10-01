package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-10-01 10:45
 */
@Entity
@Table(name = "MS_GIS_OL_MAP_LAYERGROUP")
public class OlLayerGroup implements Serializable {
	private Long id;
	private OlLayerGroup parent;
	private OlMap olMap;
	private OlLayer olLayer;
	private String name;
	private Integer sort;

	public OlLayerGroup() {
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
	public OlLayerGroup getParent() {
		return parent;
	}

	public void setParent(OlLayerGroup parent) {
		this.parent = parent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAP_ID_")
	public OlMap getOlMap() {
		return olMap;
	}

	public void setOlMap(OlMap olMap) {
		this.olMap = olMap;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAYER_ID_")
	public OlLayer getOlLayer() {
		return olLayer;
	}

	public void setOlLayer(OlLayer olLayer) {
		this.olLayer = olLayer;
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
}
