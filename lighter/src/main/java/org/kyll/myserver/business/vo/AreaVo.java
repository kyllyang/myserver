package org.kyll.myserver.business.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-07-15 13:52
 */
public class AreaVo implements Vo<Long> {
	private Long id;
	private String name;

	public AreaVo() {
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
}
