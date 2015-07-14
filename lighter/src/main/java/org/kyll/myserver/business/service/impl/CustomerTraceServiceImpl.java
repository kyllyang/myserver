package org.kyll.myserver.business.service.impl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.dao.CustomerTraceDao;
import org.kyll.myserver.business.entity.CustomerTrace;
import org.kyll.myserver.business.service.CustomerTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-07-14 16:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerTraceServiceImpl implements CustomerTraceService {
    @Autowired
    private CustomerTraceDao customerTraceDao;

    @Override
    public CustomerTrace get(Long id) {
        return customerTraceDao.get(id);
    }

    @Override
    public Dataset<CustomerTrace> get(QueryCondition qc, Paginated pg) {
        StringBuilder hql = new StringBuilder("from CustomerTrace t where 1 = 1");
        this.appendQueryCondition(hql, qc);
        HqlUtils.appendOrderBy(hql, "t", pg);
        return customerTraceDao.find(hql, pg);
    }

    @Override
    public void save(CustomerTrace customerTrace) {
        customerTraceDao.save(customerTrace);
    }

    @Override
    public void delete(Long[] ids) {
        customerTraceDao.delete(ids);
    }

    private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
        if (qc != null) {
            Long customerId = qc.getCustomerId();
            if (customerId != null) {
                hql.append(" and t.customer.id = '").append(customerId).append("'");
            }
        }
        return hql;
    }
}
