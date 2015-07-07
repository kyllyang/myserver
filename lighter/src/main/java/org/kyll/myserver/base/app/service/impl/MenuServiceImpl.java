package org.kyll.myserver.base.app.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.kyll.myserver.base.app.dao.MenuApplicationThematicDao;
import org.kyll.myserver.base.app.dao.MenuDao;
import org.kyll.myserver.base.app.dao.ModuleDao;
import org.kyll.myserver.base.app.entity.Menu;
import org.kyll.myserver.base.app.entity.MenuApplicationThematic;
import org.kyll.myserver.base.app.entity.Module;
import org.kyll.myserver.base.app.service.MenuService;
import org.kyll.myserver.base.gis.dao.ThematicDao;
import org.kyll.myserver.base.sys.dao.EmployeeDao;
import org.kyll.myserver.base.sys.entity.Employee;
import org.kyll.myserver.base.sys.entity.Role;
import org.kyll.myserver.base.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Menu get(Long id) {
		return menuDao.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getFunctionJson(Long applicationId, Long thematicId, Long userId) {
		Set<Menu> topMenuSet = menuApplicationThematicDao.find("from MenuApplicationThematic t where t.application.id = '" + applicationId + "' and t.thematic.id = '" + thematicId + "'").stream().map(MenuApplicationThematic::getMenu).collect(Collectors.toSet());

		Set<Long> functionIdSet = new HashSet<>();
		Employee employee = employeeDao.get(userId);
		for (Role role : employee.getRoleSet()) {
			functionIdSet.addAll(role.getFunctionSet().stream().map(Module::getId).collect(Collectors.toList()));
		}

		Query query = menuDao.createQuery("from Menu t where t.function.id in (:functionIds)");
		query.setParameterList("functionIds", functionIdSet);
		List<Menu> menuList = query.list();
		Set<Menu> menuSet = new HashSet<>();
		for (Menu menu : menuList) {
			menuSet.addAll(this.getParentSet(menu));
		}

		List<Menu> topMenuList = new ArrayList<>(topMenuSet);
		Collections.sort(topMenuList, (menu1, menu2) -> menu1.getSort() - menu2.getSort());

		JSONArray ja = new JSONArray();
		for (Menu menu : topMenuList) {
			Long id = menu.getId();
			JSONArray children = this.getChildSet(id, menuSet);
			if (!children.isEmpty()) {
				JSONObject jo = new JSONObject();
				jo.put("id", id);
				jo.put("text", menu.getName());

				Module function = menu.getFunction();
				if (function != null) {
					jo.put("funcType", function.getFuncType());

					String funcCode = function.getFuncCode();
					if (StringUtils.isNotBlank(funcCode)) {
						jo.put("funcCode", JSONObject.fromObject(funcCode));
					}
				}


				jo.put("leaf", children.isEmpty());
				jo.put("children", children);

				ja.add(jo);
			}
		}

		return ja;
	}

	private Set<Menu> getParentSet(Menu menu) {
		Set<Menu> parentSet = new HashSet<>();
		parentSet.add(menu);

		Menu parent = menu.getParent();
		if (parent != null) {
			parentSet.addAll(this.getParentSet(parent));
		}

		return parentSet;
	}

	private JSONArray getChildSet(Long parentId, Set<Menu> menuSet) {
		Set<Menu> childSet = new HashSet<>();
		for (Menu menu : menuSet) {
			Menu parent = menu.getParent();
			if (parent != null && Objects.equals(parentId, parent.getId())) {
				childSet.add(menu);
			}
		}

		List<Menu> childList = new ArrayList<>(childSet);
		Collections.sort(childList, (menu1, menu2) -> menu1.getSort() - menu2.getSort());

		JSONArray ja = new JSONArray();
		for (Menu menu : childList) {
			Long id = menu.getId();

			JSONObject jo = new JSONObject();
			jo.put("id", id);
			jo.put("text", menu.getName());

			Module function = menu.getFunction();
			if (function != null) {
				jo.put("funcType", function.getFuncType());

				String funcCode = function.getFuncCode();
				if (StringUtils.isNotBlank(funcCode)) {
					jo.put("funcCode", JSONObject.fromObject(funcCode));
				}
			}

			JSONArray children = this.getChildSet(id, menuSet);
			jo.put("leaf", children.isEmpty());
			jo.put("children", children);

			ja.add(jo);
		}

		return ja;
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
