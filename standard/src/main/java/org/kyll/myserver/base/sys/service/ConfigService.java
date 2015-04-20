package org.kyll.myserver.base.sys.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.sys.entity.Config;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-12-02 16:41
 */
public interface ConfigService extends BaseService<Config, Long> {
	List<Config> getAll();
}
