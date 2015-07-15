Ext.define('Business.ProductGridPanel', {
	extend: 'Base.ux.GridPanel',

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'productGridPanel',
			url: ctx + '/business/product/dataset.ctrl',
			sortProperty: 'id',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '厂商',
				dataIndex: 'vendorName'
			}, {
				text: '客户名称',
				dataIndex: 'customerCompanyName'
			}, {
				text: '项目名称',
				dataIndex: 'projectName'
			}, {
				text: '产品名称',
				dataIndex: 'name'
			}, {
				text: '型号',
				dataIndex: 'model'
			}, {
				text: '单位',
				dataIndex: 'unit'
			}, {
				text: '数量',
				dataIndex: 'amount'
			}, {
				text: '进货含税单价',
				dataIndex: 'inTaxPrice'
			}, {
				text: '进货含税合计',
				dataIndex: 'inTaxTotal'
			}, {
				text: '出货含税单价',
				dataIndex: 'outTaxPrice'
			}, {
				text: '出货含税合计',
				dataIndex: 'outTaxTotal'
			}, {
				text: '毛利',
				dataIndex: 'grossMargin'
			}],
			listeners: {
				afterRender: function(form, eOpts){
					this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
						enter: function() {
							this.queryData();
						},
						scope: this
					});
				},
				scope: this
			}
		});
		this.callParent();

		this.on('itemdblclick', function(grid, record, item, index, e, eOpts){
			this.viewEventHandler();
		});

		this.store.on('beforeload', function(store, operation, eOpts) {
		}, this);

		this.queryData();
	},
	doAddEvent: function() {
		Ext.create('Business.ProductFormWindow', {
			productGridPanel: this
		}).show();
	},
	doEditEvent: function(id) {
		Ext.create('Business.ProductFormWindow', {
			entityId: id,
			productGridPanel: this
		}).show();
	},
	doViewEvent: function(id) {
		Ext.create('Business.ProductFormWindow', {
			entityId: id,
			productGridPanel: this,
			readOnlyForm: true
		}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/business/product/delete.ctrl',
			params: {
				ids: ids
			},
			success: function(response, opts) {
				Ext.Msg.alert("系统提示", "数据删除成功！");
				this.queryData();
			},
			failure: function(response, opts) {
				Ext.Msg.alert("系统提示", "数据删除失败！");
			},
			scope: this
		});
	},
	queryData: function() {
		this.getStore().load();
	}
});
