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
	private String sourceType;// ol.source.Image, ol.source.Tile, ol.source.Vector
	private String sourceClassName;// ol.source.ImageCanvas, ol.source.ImageVector, ol.source.ImageMapGuide, ol.source.ImageStatic, ol.source.ImageWMS, ol.source.Raster, ol.source.BingMaps, ol.source.TileArcGISRest, ol.source.TileJSON, ol.source.TileWMS, ol.source.WMTS, ol.source.MapQuest, ol.source.OSM, ol.source.Stamen, ol.source.XYZ, ol.source.Zoomify, ol.source.TileUTFGrid, ol.source.Vector, ol.source.Cluster, ol.source.TileVector
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
	private String serverType;// 'carmentaserver', 'geoserver', 'mapserver', 'qgis'
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

	@Column(name = "SOURCE_TYPE_")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "SOURCE_CLASS_NAME_")
	public String getSourceClassName() {
		return sourceClassName;
	}

	public void setSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
	}

	@Column(name = "CROSS_ORIGIN_")
	public String getCrossOrigin() {
		return crossOrigin;
	}

	public void setCrossOrigin(String crossOrigin) {
		this.crossOrigin = crossOrigin;
	}

	@Column(name = "CULTURE_")
	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	@Column(name = "DIMENSIONS_")
	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	@Column(name = "DISPLAY_DPI_")
	public String getDisplayDpi() {
		return displayDpi;
	}

	public void setDisplayDpi(String displayDpi) {
		this.displayDpi = displayDpi;
	}

	@Column(name = "DISTANCE_")
	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Column(name = "EXTENT_")
	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	@Column(name = "FORMAT_")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
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

	@Column(name = "IMAGE_EXTENT_")
	public String getImageExtent() {
		return imageExtent;
	}

	public void setImageExtent(String imageExtent) {
		this.imageExtent = imageExtent;
	}

	@Column(name = "IMAGERY_SET_")
	public String getImagerySet() {
		return imagerySet;
	}

	public void setImagerySet(String imagerySet) {
		this.imagerySet = imagerySet;
	}

	@Column(name = "IMAGE_SIZE_HEIGHT_")
	public String getImageSizeHeight() {
		return imageSizeHeight;
	}

	public void setImageSizeHeight(String imageSizeHeight) {
		this.imageSizeHeight = imageSizeHeight;
	}

	@Column(name = "IMAGE_SIZE_WIDTH_")
	public String getImageSizeWidth() {
		return imageSizeWidth;
	}

	public void setImageSizeWidth(String imageSizeWidth) {
		this.imageSizeWidth = imageSizeWidth;
	}

	@Column(name = "KEY_")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "LAYER_")
	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	@Column(name = "LOGO_")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "MATRIX_SET_")
	public String getMatrixSet() {
		return matrixSet;
	}

	public void setMatrixSet(String matrixSet) {
		this.matrixSet = matrixSet;
	}

	@Column(name = "MAX_ZOOM_")
	public String getMaxZoom() {
		return maxZoom;
	}

	public void setMaxZoom(String maxZoom) {
		this.maxZoom = maxZoom;
	}

	@Column(name = "METERS_PER_UNIT_")
	public String getMetersPerUnit() {
		return metersPerUnit;
	}

	public void setMetersPerUnit(String metersPerUnit) {
		this.metersPerUnit = metersPerUnit;
	}

	@Column(name = "MIN_ZOOM_")
	public String getMinZoom() {
		return minZoom;
	}

	public void setMinZoom(String minZoom) {
		this.minZoom = minZoom;
	}

	@Column(name = "OPAQUE_")
	public String getOpaque() {
		return opaque;
	}

	public void setOpaque(String opaque) {
		this.opaque = opaque;
	}

	@Column(name = "OPERATION_TYPE_")
	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	@Column(name = "PARAMS_")
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Column(name = "PREEMPTIVE_")
	public String getPreemptive() {
		return preemptive;
	}

	public void setPreemptive(String preemptive) {
		this.preemptive = preemptive;
	}

	@Column(name = "PROJECTION_")
	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	@Column(name = "RATIO_")
	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	@Column(name = "REQUEST_ENCODING_")
	public String getRequestEncoding() {
		return requestEncoding;
	}

	public void setRequestEncoding(String requestEncoding) {
		this.requestEncoding = requestEncoding;
	}

	@Column(name = "RESOLUTIONS_")
	public String getResolutions() {
		return resolutions;
	}

	public void setResolutions(String resolutions) {
		this.resolutions = resolutions;
	}

	@Column(name = "SERVER_TYPE_")
	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	@Column(name = "SIZE_HEIGHT_")
	public String getSizeHeight() {
		return sizeHeight;
	}

	public void setSizeHeight(String sizeHeight) {
		this.sizeHeight = sizeHeight;
	}

	@Column(name = "SIZE_WIDTH_")
	public String getSizeWidth() {
		return sizeWidth;
	}

	public void setSizeWidth(String sizeWidth) {
		this.sizeWidth = sizeWidth;
	}

	@Column(name = "SOURCE_")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "SOURCES_")
	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	@Column(name = "STATE_")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "STYLE_")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "THREADS_")
	public String getThreads() {
		return threads;
	}

	public void setThreads(String threads) {
		this.threads = threads;
	}

	@Column(name = "TIER_SIZE_CALCULATION_")
	public String getTierSizeCalculation() {
		return tierSizeCalculation;
	}

	public void setTierSizeCalculation(String tierSizeCalculation) {
		this.tierSizeCalculation = tierSizeCalculation;
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

	@Column(name = "URLS_")
	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	@Column(name = "USE_OVERLAY_")
	public String getUseOverlay() {
		return useOverlay;
	}

	public void setUseOverlay(String useOverlay) {
		this.useOverlay = useOverlay;
	}

	@Column(name = "VERSION_")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
