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
	private String clickTolerance;
	private String delta;
	private String duration;
	private String kineticDecay;
	private String kineticDelay;
	private String kineticMinVelocity;
	private String multi;
	private String pixelDelta;
	private String pixelTolerance;
	private String projection;
	private String snapTolerance;
	private String threshold;
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

	@Column(name = "CLICK_TOLERANCE_")
	public String getClickTolerance() {
		return clickTolerance;
	}

	public void setClickTolerance(String clickTolerance) {
		this.clickTolerance = clickTolerance;
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

	@Column(name = "KINETIC_DECAY_")
	public String getKineticDecay() {
		return kineticDecay;
	}

	public void setKineticDecay(String kineticDecay) {
		this.kineticDecay = kineticDecay;
	}

	@Column(name = "KINETIC_DELAY_")
	public String getKineticDelay() {
		return kineticDelay;
	}

	public void setKineticDelay(String kineticDelay) {
		this.kineticDelay = kineticDelay;
	}

	@Column(name = "KINETIC_MIN_VELOCITY_")
	public String getKineticMinVelocity() {
		return kineticMinVelocity;
	}

	public void setKineticMinVelocity(String kineticMinVelocity) {
		this.kineticMinVelocity = kineticMinVelocity;
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

	@Column(name = "PIXEL_TOLERANCE_")
	public String getPixelTolerance() {
		return pixelTolerance;
	}

	public void setPixelTolerance(String pixelTolerance) {
		this.pixelTolerance = pixelTolerance;
	}

	@Column(name = "PROJECTION_")
	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	@Column(name = "SNAP_TOLERANCE_")
	public String getSnapTolerance() {
		return snapTolerance;
	}

	public void setSnapTolerance(String snapTolerance) {
		this.snapTolerance = snapTolerance;
	}

	@Column(name = "THRESHOLD_")
	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	@Column(name = "WRAP_X_")
	public String getWrapX() {
		return wrapX;
	}

	public void setWrapX(String wrapX) {
		this.wrapX = wrapX;
	}
}
