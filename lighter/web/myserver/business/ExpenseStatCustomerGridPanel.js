Ext.define('Business.ExpenseStatCustomerGridPanel', {
	extend: 'Base.ux.GridPanel',

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'expenseStatCustomerGridPanel',
			url: ctx + '/business/expense/stat/customer.ctrl',
			sortProperty: 'customerId',
			sortDirection: 'asc',
			idProperty: 'customerId',
			enableCheckbox: false,
			enableAddBtn: false,
			enableEditBtn: false,
			enableViewBtn: false,
			enableDelBtn: false,
			enableColumnEditBtn: false,
			enableColumnViewBtn: false,
			enableColumnDelBtn: false,
			columns: [{
				dataIndex: 'customerId',
				hidden: true
			}, {
				text: '客户名称',
				dataIndex: 'customerCompanyName'
			}, {
				text: '车费',
				dataIndex: 'carExpense'
			}, {
				text: '市内交通费',
				dataIndex: 'cityTrafficExpense'
			}, {
				text: '单位补助',
				dataIndex: 'subsidyExpense'
			}, {
				text: '其他费用',
				dataIndex: 'otherExpense'
			}, {
				text: '本次总计',
				dataIndex: 'thisTimeTotal'
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
			this.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.store.proxy.extraParams, {
				'qc.employeeId': myServer.isAdmin() ? myServer.getViewport().down('#employeeAreaTreePanel').getSelectedRecordEmployeeId() : myServer.loginUser.id,
				'qc.areaId': myServer.getViewport().down('#employeeAreaTreePanel').getSelectedRecordAreaId()
			});
		}, this);
	},
	queryData: function() {
		this.getStore().load();
	}
});
