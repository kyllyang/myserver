package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-10-21 21:19
 */
@Entity
@Table(name = "MS_GIS_OL_SOURCE")
public class OlSource implements Serializable {
	private Long id;
	private String name;
	private Integer sort;
	private String sourceClassName;// ol.source.BingMaps, ol.source.TileArcGISRest, ol.source.TileWMS, ol.source.XYZ
	private String culture;
	private String gutter;
	private String hidpi;
	private String imagerySet;
	private String key;
	private String logo;
	private String maxZoom;
	private String minZoom;
	private String projection;
	private String serverType;// 'carmentaserver', 'geoserver', 'mapserver', 'qgis'
	private String tilePixelRatio;
	private String tileSize;
	private String url;
	private String wrapX;

	public OlSource() {
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

	@Column(name = "SOURCE_CLASS_NAME_")
	public String getSourceClassName() {
		return sourceClassName;
	}

	public void setSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
	}

	@Column(name = "CULTURE_")
	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	@Column(name = "GUTTER_")
	public String getGutter() {
		return gutter;
	}

	public void setGutter(String gutter) {
		this.gutter = gutter;
	}

	@Column(name = "HIDPI_")
	public String getHidpi() {
		return hidpi;
	}

	public void setHidpi(String hidpi) {
		this.hidpi = hidpi;
	}

	@Column(name = "IMAGERY_SET_")
	public String getImagerySet() {
		return imagerySet;
	}

	public void setImagerySet(String imagerySet) {
		this.imagerySet = imagerySet;
	}

	@Column(name = "KEY_")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "LOGO_")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "MAX_ZOOM_")
	public String getMaxZoom() {
		return maxZoom;
	}

	public void setMaxZoom(String maxZoom) {
		this.maxZoom = maxZoom;
	}

	@Column(name = "MIN_ZOOM_")
	public String getMinZoom() {
		return minZoom;
	}

	public void setMinZoom(String minZoom) {
		this.minZoom = minZoom;
	}

	@Column(name = "PROJECTION_")
	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	@Column(name = "SERVER_TYPE_")
	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	@Column(name = "TILE_PIXEL_RATIO_")
	public String getTilePixelRatio() {
		return tilePixelRatio;
	}

	public void setTilePixelRatio(String tilePixelRatio) {
		this.tilePixelRatio = tilePixelRatio;
	}

	@Column(name = "TILE_SIZE_")
	public String getTileSize() {
		return tileSize;
	}

	public void setTileSize(String tileSize) {
		this.tileSize = tileSize;
	}

	@Column(name = "URL_")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "WRAP_X_")
	public String getWrapX() {
		return wrapX;
	}

	public void setWrapX(String wrapX) {
		this.wrapX = wrapX;
	}
}
