package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-08-10 16:07
 */
@Entity
@Table(name = "MS_GIS_OL_MAP")
public class OlMap implements Serializable {
	private Long id;
	private String loadTilesWhileAnimating;// boolean 0: false; 1: true
	private String loadTilesWhileInteracting;// boolean 0: false; 1: true
	private String logo;
	private String renderer;// Canvas, DOM, WebGL
	private Thematic thematic;

	public OlMap() {
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

	@Column(name = "LOAD_TILES_WHILE_ANIMATING_")
	public String getLoadTilesWhileAnimating() {
		return loadTilesWhileAnimating;
	}

	public void setLoadTilesWhileAnimating(String loadTilesWhileAnimating) {
		this.loadTilesWhileAnimating = loadTilesWhileAnimating;
	}

	@Column(name = "LOAD_TILES_WHILE_INTERACTING_")
	public String getLoadTilesWhileInteracting() {
		return loadTilesWhileInteracting;
	}

	public void setLoadTilesWhileInteracting(String loadTilesWhileInteracting) {
		this.loadTilesWhileInteracting = loadTilesWhileInteracting;
	}

	@Column(name = "LOGO_")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "RENDERER_")
	public String getRenderer() {
		return renderer;
	}

	public void setRenderer(String renderer) {
		this.renderer = renderer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THEMATIC_ID_")
	public Thematic getThematic() {
		return thematic;
	}

	public void setThematic(Thematic thematic) {
		this.thematic = thematic;
	}
}
