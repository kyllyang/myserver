package org.kyll.myserver.base.app.service.impl;

import org.kyll.myserver.base.app.dao.MenuApplicationThematicDao;
import org.kyll.myserver.base.app.entity.MenuApplicationThematic;
import org.kyll.myserver.base.app.service.MenuApplicationThematicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-05-17 10:02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuApplicationThematicServiceImpl implements MenuApplicationThematicService {
	@Autowired
	private MenuApplicationThematicDao menuApplicationThematicDao;

	@Override
	public MenuApplicationThematic get(Long id) {
		return menuApplicationThematicDao.get(id);
	}

	@Override
	public List<MenuApplicationThematic> getByMenu(Long id) {
		return menuApplicationThematicDao.find("from MenuApplicationThematic t where t.menu.id = '" + id + "'");
	}
}
