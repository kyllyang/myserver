package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-10-03 12:22
 */
public class OlInteractionVo implements Vo<Long> {
	private Long id;
	private String interactionClassName;
	private String interactionEnabled;
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

	public OlInteractionVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInteractionClassName() {
		return interactionClassName;
	}

	public void setInteractionClassName(String interactionClassName) {
		this.interactionClassName = interactionClassName;
	}

	public String getInteractionEnabled() {
		return interactionEnabled;
	}

	public void setInteractionEnabled(String interactionEnabled) {
		this.interactionEnabled = interactionEnabled;
	}

	public String getClickTolerance() {
		return clickTolerance;
	}

	public void setClickTolerance(String clickTolerance) {
		this.clickTolerance = clickTolerance;
	}

	public String getDelta() {
		return delta;
	}

	public void setDelta(String delta) {
		this.delta = delta;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getKineticDecay() {
		return kineticDecay;
	}

	public void setKineticDecay(String kineticDecay) {
		this.kineticDecay = kineticDecay;
	}

	public String getKineticDelay() {
		return kineticDelay;
	}

	public void setKineticDelay(String kineticDelay) {
		this.kineticDelay = kineticDelay;
	}

	public String getKineticMinVelocity() {
		return kineticMinVelocity;
	}

	public void setKineticMinVelocity(String kineticMinVelocity) {
		this.kineticMinVelocity = kineticMinVelocity;
	}

	public String getMulti() {
		return multi;
	}

	public void setMulti(String multi) {
		this.multi = multi;
	}

	public String getPixelDelta() {
		return pixelDelta;
	}

	public void setPixelDelta(String pixelDelta) {
		this.pixelDelta = pixelDelta;
	}

	public String getPixelTolerance() {
		return pixelTolerance;
	}

	public void setPixelTolerance(String pixelTolerance) {
		this.pixelTolerance = pixelTolerance;
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	public String getSnapTolerance() {
		return snapTolerance;
	}

	public void setSnapTolerance(String snapTolerance) {
		this.snapTolerance = snapTolerance;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getWrapX() {
		return wrapX;
	}

	public void setWrapX(String wrapX) {
		this.wrapX = wrapX;
	}
}
