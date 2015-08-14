package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-08-10 16:29
 */
@Entity
@Table(name = "MS_GIS_OL_INTERACTION")
public class OlInteraction implements Serializable {
	private Long id;
	private OlMap olMap;
	private String interactionClassName;// ol.interaction.DoubleClickZoom, ol.interaction.DragAndDrop, ol.interaction.KeyboardPan, ol.interaction.KeyboardZoom, ol.interaction.MouseWheelZoom, ol.interaction.Pointer, ol.interaction.Select
	private String addCondition;
	private String condition;
	private String delta;
	private String duration;
	private String filter;
	private String formatConstructors;
	private String handleDownEvent;
	private String handleDragEvent;
	private String handleEvent;
	private String handleMoveEvent;
	private String handleUpEvent;
	private String layers;
	private String multi;
	private String pixelDelta;
	private String projection;
	private String removeCondition;
	private String style;
	private String toggleCondition;
	private String wrapX;

	public OlInteraction() {
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

	@Column(name = "INTERACTION_CLASS_NAME_")
	public String getInteractionClassName() {
		return interactionClassName;
	}

	public void setInteractionClassName(String interactionClassName) {
		this.interactionClassName = interactionClassName;
	}

	@Column(name = "ADD_CONDITION_")
	public String getAddCondition() {
		return addCondition;
	}

	public void setAddCondition(String addCondition) {
		this.addCondition = addCondition;
	}

	@Column(name = "CONDITION_")
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "DELTA_")
	public String getDelta() {
		return delta;
	}

	public void setDelta(String delta) {
		this.delta = delta;
	}

	@Column(name = "DURATION_")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name = "FILTER_")
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Column(name = "FORMAT_CONSTRUCTORS_")
	public String getFormatConstructors() {
		return formatConstructors;
	}

	public void setFormatConstructors(String formatConstructors) {
		this.formatConstructors = formatConstructors;
	}

	@Column(name = "HANDLE_DOWN_EVENT_")
	public String getHandleDownEvent() {
		return handleDownEvent;
	}

	public void setHandleDownEvent(String handleDownEvent) {
		this.handleDownEvent = handleDownEvent;
	}

	@Column(name = "HANDLE_DRAG_EVENT_")
	public String getHandleDragEvent() {
		return handleDragEvent;
	}

	public void setHandleDragEvent(String handleDragEvent) {
		this.handleDragEvent = handleDragEvent;
	}

	@Column(name = "HANDLE_EVENT_")
	public String getHandleEvent() {
		return handleEvent;
	}

	public void setHandleEvent(String handleEvent) {
		this.handleEvent = handleEvent;
	}

	@Column(name = "HANDLE_MOVE_EVENT_")
	public String getHandleMoveEvent() {
		return handleMoveEvent;
	}

	public void setHandleMoveEvent(String handleMoveEvent) {
		this.handleMoveEvent = handleMoveEvent;
	}

	@Column(name = "HANDLE_UP_EVENT_")
	public String getHandleUpEvent() {
		return handleUpEvent;
	}

	public void setHandleUpEvent(String handleUpEvent) {
		this.handleUpEvent = handleUpEvent;
	}

	@Column(name = "LAYERS_")
	public String getLayers() {
		return layers;
	}

	public void setLayers(String layers) {
		this.layers = layers;
	}

	@Column(name = "MULTI_")
	public String getMulti() {
		return multi;
	}

	public void setMulti(String multi) {
		this.multi = multi;
	}

	@Column(name = "PIXEL_DELTA_")
	public String getPixelDelta() {
		return pixelDelta;
	}

	public void setPixelDelta(String pixelDelta) {
		this.pixelDelta = pixelDelta;
	}

	@Column(name = "PROJECTION_")
	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	@Column(name = "REMOVE_CONDITION_")
	public String getRemoveCondition() {
		return removeCondition;
	}

	public void setRemoveCondition(String removeCondition) {
		this.removeCondition = removeCondition;
	}

	@Column(name = "STYLE_")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "TOGGLE_CONDITION_")
	public String getToggleCondition() {
		return toggleCondition;
	}

	public void setToggleCondition(String toggleCondition) {
		this.toggleCondition = toggleCondition;
	}

	@Column(name = "WRAP_X_")
	public String getWrapX() {
		return wrapX;
	}

	public void setWrapX(String wrapX) {
		this.wrapX = wrapX;
	}
}
