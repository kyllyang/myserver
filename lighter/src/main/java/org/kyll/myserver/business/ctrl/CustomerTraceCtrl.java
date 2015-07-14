package org.kyll.myserver.business.ctrl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Customer;
import org.kyll.myserver.business.entity.CustomerTrace;
import org.kyll.myserver.business.service.CustomerService;
import org.kyll.myserver.business.service.CustomerTraceService;
import org.kyll.myserver.business.vo.CustomerTraceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-07-14 20:02
 */
@Controller
@Scope("request")
public class CustomerTraceCtrl {
    @Autowired
    private CustomerTraceService customerTraceService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/business/customertrace/dataset.ctrl")
    public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Dataset<CustomerTrace> dataset = customerTraceService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
        Dataset<CustomerTraceVo> voDataset = POJOUtils.convert(dataset, CustomerTraceVo.class, voHandler);

        response.setContentType("text/plain");
        response.getWriter().println(JsonUtils.convert(voDataset));
    }

    @RequestMapping("/business/customertrace/input.ctrl")
    public void input(Long id, HttpServletResponse response) throws Exception {
        CustomerTrace entity = customerTraceService.get(id);
        CustomerTraceVo entityVo = POJOUtils.convert(entity, CustomerTraceVo.class, voHandler);

        response.setContentType("text/plain");
        response.getWriter().println(JsonUtils.convert(entityVo));
    }

    @RequestMapping("/business/customertrace/save.ctrl")
    public void save(CustomerTraceVo entityVo, HttpServletResponse response) throws Exception {
        CustomerTrace customerTrace;
        Long id = entityVo.getId();
        if (id == null) {
            customerTrace = new CustomerTrace();
            Long customerId = entityVo.getCustomerId();
            if (customerId != null) {
                customerTrace.setCustomer(customerService.get(customerId));
            }
        } else {
            customerTrace = customerTraceService.get(id);
        }
        POJOUtils.copyProperties(customerTrace, entityVo);

        customerTraceService.save(customerTrace);

        response.setContentType("text/plain");
        response.getWriter().println(JsonUtils.ajaxResult(true));
    }

    @RequestMapping("/business/customertrace/delete.ctrl")
    public void delete(Long[] ids, HttpServletResponse response) throws Exception {
        customerTraceService.delete(ids);

        response.setContentType("text/plain");
        response.getWriter().println(JsonUtils.ajaxResult(true));
    }

    private POJOUtils.VoHandler<CustomerTrace, CustomerTraceVo> voHandler = (customerTrace, customerTraceVo) -> {
        Customer customer = customerTrace.getCustomer();
        if (customer != null) {
            customerTraceVo.setCustomerId(customer.getId());
            customerTraceVo.setCustomerCompanyName(customer.getCompanyName());
        }
    };
}
