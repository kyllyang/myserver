package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-08-10 16:31
 */
@Entity
@Table(name = "MS_GIS_OL_OVERLAY")
public class OlOverlay implements Serializable {
	private Long id;
	private OlMap olMap;
	private String element;
	private String offset;
	private String position;
	private String positioning;// 'bottom-left', 'bottom-center', 'bottom-right', 'center-left', 'center-center', 'center-right', 'top-left', 'top-center', 'top-right'
	private String stopEvent;
	private String insertFirst;
	private String autoPan;
	private String autoPanAnimation;
	private String autoPanMargin;

	public OlOverlay() {
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

	@Column(name = "ELEMENT_")
	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	@Column(name = "OFFSET_")
	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	@Column(name = "POSITION_")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "POSITIONING_")
	public String getPositioning() {
		return positioning;
	}

	public void setPositioning(String positioning) {
		this.positioning = positioning;
	}

	@Column(name = "STOP_EVENT_")
	public String getStopEvent() {
		return stopEvent;
	}

	public void setStopEvent(String stopEvent) {
		this.stopEvent = stopEvent;
	}

	@Column(name = "INSERT_FIRST_")
	public String getInsertFirst() {
		return insertFirst;
	}

	public void setInsertFirst(String insertFirst) {
		this.insertFirst = insertFirst;
	}

	@Column(name = "AUTO_PAN_")
	public String getAutoPan() {
		return autoPan;
	}

	public void setAutoPan(String autoPan) {
		this.autoPan = autoPan;
	}

	@Column(name = "AUTO_PAN_ANIMATION_")
	public String getAutoPanAnimation() {
		return autoPanAnimation;
	}

	public void setAutoPanAnimation(String autoPanAnimation) {
		this.autoPanAnimation = autoPanAnimation;
	}

	@Column(name = "AUTO_PAN_MARGIN_")
	public String getAutoPanMargin() {
		return autoPanMargin;
	}

	public void setAutoPanMargin(String autoPanMargin) {
		this.autoPanMargin = autoPanMargin;
	}
}
