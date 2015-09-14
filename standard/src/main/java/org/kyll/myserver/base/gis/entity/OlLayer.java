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
	private String name;
	private Integer sort;
	private String brightness;
	private String contrast;
	private String extent;
	private String hue;
	private String minResolution;
	private String maxResolution;
	private String opacity;
	private String preload;
	private String renderBuffer;
	private String saturation;
//	private String style;// todo 可能会删除
	private String visible;
	private String useInterimTilesOnError;
	private String updateWhileAnimating;
	private String updateWhileInteracting;
	private String sourceClassName;// ol.source.ImageVector, ol.source.ImageMapGuide, ol.source.ImageStatic, ol.source.ImageWMS, ol.source.Raster, ol.source.TileDebug, ol.source.TileImage, ol.source.BingMaps, ol.source.TileArcGISRest, ol.source.TileJSON, ol.source.TileWMS, ol.source.WMTS, ol.source.XYZ, ol.source.MapQuest, ol.source.OSM, ol.source.Stamen, ol.source.Zoomify, ol.source.TileUTFGrid, ol.source.Vector, ol.source.Cluster, ol.source.TileVector
	private String layerClassName;// ol.layer.Image, ol.layer.Tile, ol.layer.Vector, ol.layer.Heatmap
	private String sourceUrl;
	private String sourceFormat;
	private String sourceStrategy;
	private String sourceTileSize;
	private String sourceTilePixelRatio;
	private String sourceMaxZoom;
	private String sourceUseSpatialIndex;
	private String sourceWrapX;

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

	@Column(name = "PRELOAD_")
	public String getPreload() {
		return preload;
	}

	public void setPreload(String preload) {
		this.preload = preload;
	}

	@Column(name = "USE_INTERIM_TILES_ON_ERROR_")
	public String getUseInterimTilesOnError() {
		return useInterimTilesOnError;
	}

	public void setUseInterimTilesOnError(String useInterimTilesOnError) {
		this.useInterimTilesOnError = useInterimTilesOnError;
	}

	@Column(name = "UPDATE_WHILE_ANIMATING_")
	public String getUpdateWhileAnimating() {
		return updateWhileAnimating;
	}

	public void setUpdateWhileAnimating(String updateWhileAnimating) {
		this.updateWhileAnimating = updateWhileAnimating;
	}

	@Column(name = "UPDATE_WHILE_INTERACTING_")
	public String getUpdateWhileInteracting() {
		return updateWhileInteracting;
	}

	public void setUpdateWhileInteracting(String updateWhileInteracting) {
		this.updateWhileInteracting = updateWhileInteracting;
	}

	@Column(name = "RENDER_BUFFER_")
	public String getRenderBuffer() {
		return renderBuffer;
	}

	public void setRenderBuffer(String renderBuffer) {
		this.renderBuffer = renderBuffer;
	}

	@Column(name = "SOURCE_URL_")
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	@Column(name = "SOURCE_FORMAT_")
	public String getSourceFormat() {
		return sourceFormat;
	}

	public void setSourceFormat(String sourceFormat) {
		this.sourceFormat = sourceFormat;
	}

	@Column(name = "SOURCE_STRATEGY_")
	public String getSourceStrategy() {
		return sourceStrategy;
	}

	public void setSourceStrategy(String sourceStrategy) {
		this.sourceStrategy = sourceStrategy;
	}

	@Column(name = "SOURCE_TILE_SIZE_")
	public String getSourceTileSize() {
		return sourceTileSize;
	}

	public void setSourceTileSize(String sourceTileSize) {
		this.sourceTileSize = sourceTileSize;
	}

	@Column(name = "SOURCE_TILE_PIXEL_RATIO_")
	public String getSourceTilePixelRatio() {
		return sourceTilePixelRatio;
	}

	public void setSourceTilePixelRatio(String sourceTilePixelRatio) {
		this.sourceTilePixelRatio = sourceTilePixelRatio;
	}

	@Column(name = "SOURCE_MAX_ZOOM_")
	public String getSourceMaxZoom() {
		return sourceMaxZoom;
	}

	public void setSourceMaxZoom(String sourceMaxZoom) {
		this.sourceMaxZoom = sourceMaxZoom;
	}

	@Column(name = "SOURCE_USE_SPATIAL_INDEX_")
	public String getSourceUseSpatialIndex() {
		return sourceUseSpatialIndex;
	}

	public void setSourceUseSpatialIndex(String sourceUseSpatialIndex) {
		this.sourceUseSpatialIndex = sourceUseSpatialIndex;
	}

	@Column(name = "SOURCE_WRAP_X_")
	public String getSourceWrapX() {
		return sourceWrapX;
	}

	public void setSourceWrapX(String sourceWrapX) {
		this.sourceWrapX = sourceWrapX;
	}
}
