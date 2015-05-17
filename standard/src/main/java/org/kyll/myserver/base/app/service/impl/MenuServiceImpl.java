package org.kyll.myserver.base.app.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kyll.myserver.base.app.dao.MenuApplicationThematicDao;
import org.kyll.myserver.base.app.dao.MenuDao;
import org.kyll.myserver.base.app.dao.ModuleDao;
import org.kyll.myserver.base.app.entity.Menu;
import org.kyll.myserver.base.app.entity.MenuApplicationThematic;
import org.kyll.myserver.base.app.service.MenuService;
import org.kyll.myserver.base.gis.dao.ThematicDao;
import org.kyll.myserver.base.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * User: Kyll
 * Date: 2015-04-20 17:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private ThematicDao thematicDao;
	@Autowired
	private MenuApplicationThematicDao menuApplicationThematicDao;

	@Override
	public Menu get(Long id) {
		return menuDao.get(id);
	}

	@Override
	public JSONArray getTreeJson(Long parentId) {
		List<Menu> list = menuDao.find("from Menu t order by t.sort");
		return this.recursiveTree(parentId, list);
	}

	private JSONArray recursiveTree(Long parentId, List<Menu> list) {
		JSONArray ja = new JSONArray();

		for (Menu menu : list) {
			Menu parent = menu.getParent();
			if (parentId == null ? parent == null : parent != null && Objects.equals(parentId, parent.getId())) {
				Long id = menu.getId();

				JSONObject jo = new JSONObject();
				jo.put("id", id);
				jo.put("text", menu.getName());

				JSONArray children = this.recursiveTree(id, list);
				jo.put("leaf", children.isEmpty());
				jo.put("children", children);

				ja.add(jo);
			}
		}
		return ja;
	}

	@Override
	public void save(Menu menu, Long parentId, Long functionId, String mats) {
		if (parentId != null) {
			menu.setParent(this.get(parentId));
		}
		if (functionId != null) {
			menu.setFunction(moduleDao.get(functionId));
		}
		menuDao.save(menu);

		menuApplicationThematicDao.delete(menuApplicationThematicDao.find("from MenuApplicationThematic t where t.menu.id = '" + menu.getId() + "'"));

		JSONArray ja = JSONArray.fromObject(mats);
		for (int i = 0, size = ja.size(); i < size; i++) {
			JSONObject jo = ja.getJSONObject(i);
			Long moduleId = JsonUtils.getLong(jo, "moduleId");
			Long thematicId = JsonUtils.getLong(jo, "thematicId");

			MenuApplicationThematic menuApplicationThematic = new MenuApplicationThematic();
			menuApplicationThematic.setMenu(menu);
			if (moduleId != null) {
				menuApplicationThematic.setApplication(moduleDao.get(moduleId));
			}
			if (thematicId != null) {
				menuApplicationThematic.setThematic(thematicDao.get(thematicId));
			}
			menuApplicationThematicDao.save(menuApplicationThematic);
		}
	}

	@Override
	public void delete(Long[] ids) {
		menuDao.delete(ids);
	}
}
