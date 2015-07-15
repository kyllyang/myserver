package org.kyll.myserver.business.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Product;

/**
 * User: Kyll
 * Date: 2015-07-15 20:07
 */
public interface ProductService extends BaseService<Product, Long> {
	Dataset<Product> get(QueryCondition qc, Paginated pg);

	void save(Product product);

	void delete(Long[] ids);
}
