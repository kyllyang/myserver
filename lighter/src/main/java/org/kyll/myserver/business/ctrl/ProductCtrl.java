package org.kyll.myserver.business.ctrl;

import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Customer;
import org.kyll.myserver.business.entity.Product;
import org.kyll.myserver.business.entity.Project;
import org.kyll.myserver.business.entity.Vendor;
import org.kyll.myserver.business.service.CustomerService;
import org.kyll.myserver.business.service.ProductService;
import org.kyll.myserver.business.service.ProjectService;
import org.kyll.myserver.business.service.VendorService;
import org.kyll.myserver.business.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-07-15 20:14
 */
@Controller
@Scope("request")
public class ProductCtrl {
	@Autowired
	private ProductService productService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProjectService projectService;

	@RequestMapping("/business/product/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Product> dataset = productService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ProductVo> voDataset = POJOUtils.convert(dataset, ProductVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/business/product/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Product entity = productService.get(id);
		ProductVo entityVo = POJOUtils.convert(entity, ProductVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/business/product/save.ctrl")
	public void save(ProductVo entityVo, HttpServletResponse response) throws Exception {
		Product product;
		Long id = entityVo.getId();
		if (id == null) {
			product = new Product();
		} else {
			product = productService.get(id);
		}
		Long vendorId = entityVo.getVendorId();
		if (vendorId != null) {
			product.setVendor(vendorService.get(vendorId));
		}
		Long customerId = entityVo.getCustomerId();
		if (customerId != null) {
			product.setCustomer(customerService.get(customerId));
		}
		Long projectId = entityVo.getProjectId();
		if (projectId != null) {
			product.setProject(projectService.get(projectId));
		}
		POJOUtils.copyProperties(product, entityVo);

		productService.save(product);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/business/product/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		productService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<Product, ProductVo> voHandler = (product, productVo) -> {
		Vendor vendor = product.getVendor();
		if (vendor != null) {
			productVo.setVendorId(vendor.getId());
			productVo.setVendorName(vendor.getName());
		}
		Customer customer = product.getCustomer();
		if (customer != null) {
			productVo.setCustomerId(customer.getId());
			productVo.setCustomerCompanyName(customer.getCompanyName());
		}
		Project project = product.getProject();
		if (project != null) {
			productVo.setProjectId(project.getId());
			productVo.setProjectName(project.getName());
		}
	};
}
