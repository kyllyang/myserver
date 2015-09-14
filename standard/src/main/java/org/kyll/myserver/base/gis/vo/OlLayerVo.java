package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-09-09 16:06
 */
public class OlLayerVo implements Vo<Long> {
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
	private String visible;
	private String useInterimTilesOnError;
	private String updateWhileAnimating;
	private String updateWhileInteracting;
	private String sourceClassName;
	private String layerClassName;
	private String layerClassNameText;
	private String sourceUrl;
	private String sourceFormat;
	private String sourceStrategy;
	private String sourceTileSize;
	private String sourceTilePixelRatio;
	private String sourceMaxZoom;
	private String sourceUseSpatialIndex;
	private String sourceWrapX;

	public OlLayerVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getBrightness() {
		return brightness;
	}

	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}

	public String getContrast() {
		return contrast;
	}

	public void setContrast(String contrast) {
		this.contrast = contrast;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public String getHue() {
		return hue;
	}

	public void setHue(String hue) {
		this.hue = hue;
	}

	public String getMinResolution() {
		return minResolution;
	}

	public void setMinResolution(String minResolution) {
		this.minResolution = minResolution;
	}

	public String getMaxResolution() {
		return maxResolution;
	}

	public void setMaxResolution(String maxResolution) {
		this.maxResolution = maxResolution;
	}

	public String getOpacity() {
		return opacity;
	}

	public void setOpacity(String opacity) {
		this.opacity = opacity;
	}

	public String getPreload() {
		return preload;
	}

	public void setPreload(String preload) {
		this.preload = preload;
	}

	public String getRenderBuffer() {
		return renderBuffer;
	}

	public void setRenderBuffer(String renderBuffer) {
		this.renderBuffer = renderBuffer;
	}

	public String getSaturation() {
		return saturation;
	}

	public void setSaturation(String saturation) {
		this.saturation = saturation;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getUseInterimTilesOnError() {
		return useInterimTilesOnError;
	}

	public void setUseInterimTilesOnError(String useInterimTilesOnError) {
		this.useInterimTilesOnError = useInterimTilesOnError;
	}

	public String getUpdateWhileAnimating() {
		return updateWhileAnimating;
	}

	public void setUpdateWhileAnimating(String updateWhileAnimating) {
		this.updateWhileAnimating = updateWhileAnimating;
	}

	public String getUpdateWhileInteracting() {
		return updateWhileInteracting;
	}

	public void setUpdateWhileInteracting(String updateWhileInteracting) {
		this.updateWhileInteracting = updateWhileInteracting;
	}

	public String getSourceClassName() {
		return sourceClassName;
	}

	public void setSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
	}

	public String getLayerClassName() {
		return layerClassName;
	}

	public void setLayerClassName(String layerClassName) {
		this.layerClassName = layerClassName;
	}

	public String getLayerClassNameText() {
		return layerClassNameText;
	}

	public void setLayerClassNameText(String layerClassNameText) {
		this.layerClassNameText = layerClassNameText;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceFormat() {
		return sourceFormat;
	}

	public void setSourceFormat(String sourceFormat) {
		this.sourceFormat = sourceFormat;
	}

	public String getSourceStrategy() {
		return sourceStrategy;
	}

	public void setSourceStrategy(String sourceStrategy) {
		this.sourceStrategy = sourceStrategy;
	}

	public String getSourceTileSize() {
		return sourceTileSize;
	}

	public void setSourceTileSize(String sourceTileSize) {
		this.sourceTileSize = sourceTileSize;
	}

	public String getSourceTilePixelRatio() {
		return sourceTilePixelRatio;
	}

	public void setSourceTilePixelRatio(String sourceTilePixelRatio) {
		this.sourceTilePixelRatio = sourceTilePixelRatio;
	}

	public String getSourceMaxZoom() {
		return sourceMaxZoom;
	}

	public void setSourceMaxZoom(String sourceMaxZoom) {
		this.sourceMaxZoom = sourceMaxZoom;
	}

	public String getSourceUseSpatialIndex() {
		return sourceUseSpatialIndex;
	}

	public void setSourceUseSpatialIndex(String sourceUseSpatialIndex) {
		this.sourceUseSpatialIndex = sourceUseSpatialIndex;
	}

	public String getSourceWrapX() {
		return sourceWrapX;
	}

	public void setSourceWrapX(String sourceWrapX) {
		this.sourceWrapX = sourceWrapX;
	}
}
