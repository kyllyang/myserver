package org.kyll.myserver.business.service;

import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.CustomerTrace;

/**
 * User: Kyll
 * Date: 2015-07-14 16:39
 */
public interface CustomerTraceService extends BaseService<CustomerTrace, Long> {
    Dataset<CustomerTrace> get(QueryCondition qc, Paginated pg);

    void save(CustomerTrace customerTrace);

    void delete(Long[] ids);
}
