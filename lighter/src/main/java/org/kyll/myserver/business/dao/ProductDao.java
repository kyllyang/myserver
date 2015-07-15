package org.kyll.myserver.business.dao;

import org.kyll.myserver.base.common.BaseHibernateDao;
import org.kyll.myserver.business.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2015-07-15 20:04
 */
@Repository
public class ProductDao extends BaseHibernateDao<Product, Long> {
}
