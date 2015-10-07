package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-10-07 9:49
 */
@Entity
@Table(name = "MS_GIS_OL_TOOLBAR")
public class OlToolbar implements Serializable {
	private Long id;
	private OlMap olMap;
	private String toolbarClassName;
	private String toolbarEnabled;
	private String style;

	public OlToolbar() {
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
	@JoinColumn(name = "MAP_ID_")
	public OlMap getOlMap() {
		return olMap;
	}

	public void setOlMap(OlMap olMap) {
		this.olMap = olMap;
	}

	@Column(name = "TOOLBAR_CLASS_NAME_")
	public String getToolbarClassName() {
		return toolbarClassName;
	}

	public void setToolbarClassName(String toolbarClassName) {
		this.toolbarClassName = toolbarClassName;
	}

	@Column(name = "TOOLBAR_ENABLED_")
	public String getToolbarEnabled() {
		return toolbarEnabled;
	}

	public void setToolbarEnabled(String toolbarEnabled) {
		this.toolbarEnabled = toolbarEnabled;
	}

	@Column(name = "STYLE_")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
