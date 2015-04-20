package org.kyll.myserver.base.app.service.impl;

import org.kyll.myserver.base.app.entity.Menu;
import org.kyll.myserver.base.app.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-04-20 17:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
	@Override
	public Menu get(Long aLong) {
		return null;
	}
}
