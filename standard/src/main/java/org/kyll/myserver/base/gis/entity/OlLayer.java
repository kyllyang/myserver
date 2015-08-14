package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-08-10 16:30
 */
@Entity
@Table(name = "MS_GIS_OL_LAYER")
public class OlLayer implements Serializable {
	private Long id;
	private OlMap olMap;
	private String brightness;
	private String contrast;
	private String hue;
	private String opacity;
	private String saturation;
	private String visible;
	private String extent;
	private String minResolution;
	private String maxResolution;
	private String sourceClassName;// ol.source.ImageVector, ol.source.ImageMapGuide, ol.source.ImageStatic, ol.source.ImageWMS, ol.source.Raster, ol.source.TileDebug, ol.source.TileImage, ol.source.BingMaps, ol.source.TileArcGISRest, ol.source.TileJSON, ol.source.TileWMS, ol.source.WMTS, ol.source.XYZ, ol.source.MapQuest, ol.source.OSM, ol.source.Stamen, ol.source.Zoomify, ol.source.TileUTFGrid, ol.source.Vector, ol.source.Cluster, ol.source.TileVector
	private String layerClassName;// ol.layer.Image, ol.layer.Tile, ol.layer.Vector, ol.layer.Heatmap

	public OlLayer() {
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

	@Column(name = "BRIGHTNESS_")
	public String getBrightness() {
		return brightness;
	}

	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}

	@Column(name = "CONTRAST_")
	public String getContrast() {
		return contrast;
	}

	public void setContrast(String contrast) {
		this.contrast = contrast;
	}

	@Column(name = "HUE_")
	public String getHue() {
		return hue;
	}

	public void setHue(String hue) {
		this.hue = hue;
	}

	@Column(name = "OPACITY_")
	public String getOpacity() {
		return opacity;
	}

	public void setOpacity(String opacity) {
		this.opacity = opacity;
	}

	@Column(name = "SATURATION_")
	public String getSaturation() {
		return saturation;
	}

	public void setSaturation(String saturation) {
		this.saturation = saturation;
	}

	@Column(name = "VISIBLE_")
	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	@Column(name = "EXTENT_")
	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	@Column(name = "MIN_RESOLUTION_")
	public String getMinResolution() {
		return minResolution;
	}

	public void setMinResolution(String minResolution) {
		this.minResolution = minResolution;
	}

	@Column(name = "MAX_RESOLUTION_")
	public String getMaxResolution() {
		return maxResolution;
	}

	public void setMaxResolution(String maxResolution) {
		this.maxResolution = maxResolution;
	}

	@Column(name = "SOURCE_CLASS_NAME_")
	public String getSourceClassName() {
		return sourceClassName;
	}

	public void setSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
	}

	@Column(name = "LAYER_CLASS_NAME_")
	public String getLayerClassName() {
		return layerClassName;
	}

	public void setLayerClassName(String layerClassName) {
		this.layerClassName = layerClassName;
	}
}
