package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-10-07 10:01
 */
public class OlToolbarVo implements Vo<Long> {
	private Long id;
	private String toolbarClassName;
	private String toolbarEnabled;
	private String style;

	public OlToolbarVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToolbarClassName() {
		return toolbarClassName;
	}

	public void setToolbarClassName(String toolbarClassName) {
		this.toolbarClassName = toolbarClassName;
	}

	public String getToolbarEnabled() {
		return toolbarEnabled;
	}

	public void setToolbarEnabled(String toolbarEnabled) {
		this.toolbarEnabled = toolbarEnabled;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
