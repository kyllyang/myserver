package org.kyll.myserver.base.app.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.app.entity.Menu;

/**
 * User: Kyll
 * Date: 2015-04-20 11:26
 */
public interface MenuService extends BaseService<Menu, Long> {
	JSONArray getTreeJson(Long parentId);

	void save(Menu menu, Long parentId, Long functionId, String mats);

	void delete(Long[] ids);
}
