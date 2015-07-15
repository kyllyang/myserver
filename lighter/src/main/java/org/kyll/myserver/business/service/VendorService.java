package org.kyll.myserver.business.service;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Vendor;

/**
 * User: Kyll
 * Date: 2015-07-15 20:04
 */
public interface VendorService extends BaseService<Vendor, Long> {
	Dataset<Vendor> get(QueryCondition qc, Paginated pg);

	void save(JSONArray jsonArray);

	void delete(Long[] ids);
}
