package org.kyll.myserver.business.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.dao.VendorDao;
import org.kyll.myserver.business.entity.Vendor;
import org.kyll.myserver.business.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-07-15 20:05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorDao vendorDao;

	@Override
	public Vendor get(Long id) {
		return vendorDao.get(id);
	}

	@Override
	public Dataset<Vendor> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Vendor t where 1 = 1");
		this.appendQueryCondition(hql, qc);
		HqlUtils.appendOrderBy(hql, "t", pg);
		return vendorDao.find(hql, pg);
	}

	@Override
	public void save(JSONArray jsonArray) {
		for (int i = 0, size = jsonArray.size(); i < size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Long id = JsonUtils.getLong(jsonObject, "id");
			Vendor vendor;
			if (id == null) {
				vendor = new Vendor();
			} else {
				vendor = this.get(id);
			}

			vendor.setName(JsonUtils.getString(jsonObject, "name"));

			vendorDao.save(vendor);
		}
	}

	@Override
	public void delete(Long[] ids) {
		vendorDao.delete(ids);
	}

	private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
		if (qc != null) {
		}
		return hql;
	}
}
