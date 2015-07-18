package org.kyll.myserver.business.service.impl;

import org.kyll.myserver.business.dao.RoleDao;
import org.kyll.myserver.business.entity.Role;
import org.kyll.myserver.business.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User: Kyll
 * Date: 2014-11-10 14:36
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role get(Long id) {
		return roleDao.get(id);
	}

	@Override
	public List<Role> get() {
		return roleDao.find("from Role t order by t.sort asc");
	}
}
