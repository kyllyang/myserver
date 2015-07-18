package org.kyll.myserver.business.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.business.entity.Role;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-11-10 14:35
 */
public interface RoleService extends BaseService<Role, Long> {
	List<Role> get();
}
