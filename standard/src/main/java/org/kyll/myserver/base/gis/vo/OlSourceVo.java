package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-11-03 20:35
 */
public class OlSourceVo implements Vo<Long> {
	private Long id;
	private String name;
	private Integer sort;
	private String sourceType;
	private String sourceClassName;
	private String crossOrigin;
	private String culture;
	private String dimensions;
	private String displayDpi;
	private String distance;
	private String extent;
	private String format;
	private String gutter;
	private String hidpi;
	private String imageExtent;
	private String imagerySet;
	private String imageSizeHeight;
	private String imageSizeWidth;
	private String key;
	private String layer;
	private String logo;
	private String matrixSet;
	private String maxZoom;
	private String metersPerUnit;
	private String minZoom;
	private String opaque;
	private String operationType;
	private String params;
	private String preemptive;
	private String projection;
	private String ratio;
	private String requestEncoding;
	private String resolutions;
	private String serverType;
	private String sizeHeight;
	private String sizeWidth;
	private String source;
	private String sources;
	private String state;
	private String style;
	private String threads;
	private String tierSizeCalculation;
	private String tilePixelRatio;
	private String tileSize;
	private String url;
	private String urls;
	private String useOverlay;
	private String version;

	public OlSourceVo() {
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

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceClassName() {
		return sourceClassName;
	}

	public void setSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
	}

	public String getCrossOrigin() {
		return crossOrigin;
	}

	public void setCrossOrigin(String crossOrigin) {
		this.crossOrigin = crossOrigin;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getDisplayDpi() {
		return displayDpi;
	}

	public void setDisplayDpi(String displayDpi) {
		this.displayDpi = displayDpi;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getGutter() {
		return gutter;
	}

	public void setGutter(String gutter) {
		this.gutter = gutter;
	}

	public String getHidpi() {
		return hidpi;
	}

	public void setHidpi(String hidpi) {
		this.hidpi = hidpi;
	}

	public String getImageExtent() {
		return imageExtent;
	}

	public void setImageExtent(String imageExtent) {
		this.imageExtent = imageExtent;
	}

	public String getImagerySet() {
		return imagerySet;
	}

	public void setImagerySet(String imagerySet) {
		this.imagerySet = imagerySet;
	}

	public String getImageSizeHeight() {
		return imageSizeHeight;
	}

	public void setImageSizeHeight(String imageSizeHeight) {
		this.imageSizeHeight = imageSizeHeight;
	}

	public String getImageSizeWidth() {
		return imageSizeWidth;
	}

	public void setImageSizeWidth(String imageSizeWidth) {
		this.imageSizeWidth = imageSizeWidth;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMatrixSet() {
		return matrixSet;
	}

	public void setMatrixSet(String matrixSet) {
		this.matrixSet = matrixSet;
	}

	public String getMaxZoom() {
		return maxZoom;
	}

	public void setMaxZoom(String maxZoom) {
		this.maxZoom = maxZoom;
	}

	public String getMetersPerUnit() {
		return metersPerUnit;
	}

	public void setMetersPerUnit(String metersPerUnit) {
		this.metersPerUnit = metersPerUnit;
	}

	public String getMinZoom() {
		return minZoom;
	}

	public void setMinZoom(String minZoom) {
		this.minZoom = minZoom;
	}

	public String getOpaque() {
		return opaque;
	}

	public void setOpaque(String opaque) {
		this.opaque = opaque;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getPreemptive() {
		return preemptive;
	}

	public void setPreemptive(String preemptive) {
		this.preemptive = preemptive;
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getRequestEncoding() {
		return requestEncoding;
	}

	public void setRequestEncoding(String requestEncoding) {
		this.requestEncoding = requestEncoding;
	}

	public String getResolutions() {
		return resolutions;
	}

	public void setResolutions(String resolutions) {
		this.resolutions = resolutions;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getSizeHeight() {
		return sizeHeight;
	}

	public void setSizeHeight(String sizeHeight) {
		this.sizeHeight = sizeHeight;
	}

	public String getSizeWidth() {
		return sizeWidth;
	}

	public void setSizeWidth(String sizeWidth) {
		this.sizeWidth = sizeWidth;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getThreads() {
		return threads;
	}

	public void setThreads(String threads) {
		this.threads = threads;
	}

	public String getTierSizeCalculation() {
		return tierSizeCalculation;
	}

	public void setTierSizeCalculation(String tierSizeCalculation) {
		this.tierSizeCalculation = tierSizeCalculation;
	}

	public String getTilePixelRatio() {
		return tilePixelRatio;
	}

	public void setTilePixelRatio(String tilePixelRatio) {
		this.tilePixelRatio = tilePixelRatio;
	}

	public String getTileSize() {
		return tileSize;
	}

	public void setTileSize(String tileSize) {
		this.tileSize = tileSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getUseOverlay() {
		return useOverlay;
	}

	public void setUseOverlay(String useOverlay) {
		this.useOverlay = useOverlay;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
