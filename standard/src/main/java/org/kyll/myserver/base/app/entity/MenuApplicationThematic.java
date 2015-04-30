package org.kyll.myserver.base.app.entity;

import org.kyll.myserver.base.gis.entity.Thematic;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-04-28 19:06
 */
@Entity
@Table(name = "MS_APP_MENU_APPLICATION_THEMATIC")
public class MenuApplicationThematic implements Serializable {
	private Long id;
	private Menu menu;
	private Module application;
	private Thematic thematic;

	public MenuApplicationThematic() {
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
	@JoinColumn(name = "MENU_ID_")
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLICATION_ID_")
	public Module getApplication() {
		return application;
	}

	public void setApplication(Module application) {
		this.application = application;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THEMATIC_ID")
	public Thematic getThematic() {
		return thematic;
	}

	public void setThematic(Thematic thematic) {
		this.thematic = thematic;
	}
}
