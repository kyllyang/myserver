package org.kyll.myserver.business.sys.service.impl;

import org.kyll.myserver.business.sys.dao.ConfigDao;
import org.kyll.myserver.business.sys.entity.Config;
import org.kyll.myserver.business.sys.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-12-02 16:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigDao configDao;

	@Override
	public Config get(Long id) {
		return configDao.get(id);
	}

	@Override
	public List<Config> getAll() {
		return configDao.getAll();
	}
}
