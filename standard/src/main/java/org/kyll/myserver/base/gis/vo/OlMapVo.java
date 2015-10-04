package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-10-03 12:17
 */
public class OlMapVo implements Vo<Long> {
	private Long id;
	private String loadTilesWhileAnimating;
	private String loadTilesWhileInteracting;
	private String logo;
	private String renderer;

	public OlMapVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoadTilesWhileAnimating() {
		return loadTilesWhileAnimating;
	}

	public void setLoadTilesWhileAnimating(String loadTilesWhileAnimating) {
		this.loadTilesWhileAnimating = loadTilesWhileAnimating;
	}

	public String getLoadTilesWhileInteracting() {
		return loadTilesWhileInteracting;
	}

	public void setLoadTilesWhileInteracting(String loadTilesWhileInteracting) {
		this.loadTilesWhileInteracting = loadTilesWhileInteracting;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRenderer() {
		return renderer;
	}

	public void setRenderer(String renderer) {
		this.renderer = renderer;
	}
}
