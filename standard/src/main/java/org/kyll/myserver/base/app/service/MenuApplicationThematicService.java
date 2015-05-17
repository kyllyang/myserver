package org.kyll.myserver.base.app.service;

import org.kyll.myserver.base.app.entity.MenuApplicationThematic;
import org.kyll.myserver.base.common.BaseService;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-05-17 10:02
 */
public interface MenuApplicationThematicService extends BaseService<MenuApplicationThematic, Long> {
	List<MenuApplicationThematic> getByMenu(Long id);
}
