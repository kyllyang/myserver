Ext.define('Business.ExpenseGridPanel', {
	extend: 'Base.ux.GridPanel',

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'expenseGridPanel',
			url: ctx + '/business/expense/dataset.ctrl',
			sortProperty: 'id',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '区域',
				dataIndex: 'areaName'
			}, {
				text: '客户名称',
				dataIndex: 'customerCompanyName'
			}, {
				text: '项目名称',
				dataIndex: 'projectName'
			}, {
				text: '起始日期',
				dataIndex: 'startDate',
				renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
					return Ext.Date.format(Ext.Date.parse(value, 'Y-m-d H:i:s.u'), 'Y-m-d');
				}
			}, {
				text: '结束日期',
				dataIndex: 'endDate',
				renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
					return Ext.Date.format(Ext.Date.parse(value, 'Y-m-d H:i:s.u'), 'Y-m-d');
				}
			}, {
				text: '天数',
				dataIndex: 'days'
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
		}, this);

		this.queryData();
	},
	doAddEvent: function() {
		Ext.create('Business.ExpenseFormWindow', {
			expenseGridPanel: this
		}).show();
	},
	doEditEvent: function(id) {
		Ext.create('Business.ExpenseFormWindow', {
			entityId: id,
			expenseGridPanel: this
		}).show();
	},
	doViewEvent: function(id) {
		Ext.create('Business.ExpenseFormWindow', {
			entityId: id,
			expenseGridPanel: this,
			readOnlyForm: true
		}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/business/expense/delete.ctrl',
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
