package org.kyll.myserver.business.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-07-15 19:50
 */
@Entity
@Table(name = "MS_LI_VERDOR")
public class Vendor implements Serializable {
	private Long id;
	private String name;

	public Vendor() {
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
}
