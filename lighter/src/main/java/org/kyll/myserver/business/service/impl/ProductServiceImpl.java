package org.kyll.myserver.business.service.impl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.dao.ProductDao;
import org.kyll.myserver.business.entity.Product;
import org.kyll.myserver.business.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-07-15 20:08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public Product get(Long id) {
		return productDao.get(id);
	}

	@Override
	public Dataset<Product> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Product t where 1 = 1");
		this.appendQueryCondition(hql, qc);
		HqlUtils.appendOrderBy(hql, "t", pg);
		return productDao.find(hql, pg);
	}

	@Override
	public void save(Product product) {
		productDao.save(product);
	}

	@Override
	public void delete(Long[] ids) {
		productDao.delete(ids);
	}

	private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
		if (qc != null) {
		}
		return hql;
	}
}
