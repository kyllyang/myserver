Ext.define('Business.CustomerGridPanel', {
	extend: 'Base.ux.GridPanel',

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'customerGridPanel',
			url: ctx + '/business/customer/dataset.ctrl',
			sortProperty: 'id',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '拜访日期',
				dataIndex: 'visitDate',
				renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
					return Ext.Date.format(Ext.Date.parse(value, 'Y-m-d H:i:s.u'), 'Y-m-d');
				}
			}, {
				text: '单位名称',
				dataIndex: 'companyName'
			}, {
				text: '联系人',
				dataIndex: 'linkMan'
			}, {
				text: '职务',
				dataIndex: 'job'
			}, {
				text: '联系电话',
				dataIndex: 'phone'
			}, {
				text: '办公电话',
				dataIndex: 'officePhone'
			}, {
				text: '邮箱',
				dataIndex: 'email'
			}, {
				text: '等级',
				dataIndex: 'level'
			}, {
				text: '拜访成果',
				dataIndex: 'visitResult'
			}],
			tbar: [{
				text: '跟踪',
				icon: ctx + '/resource/image/icon/business/trace.png',
				handler: this.traceList,
				scope: this
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
				'qc.employeeId': myServer.isAdmin() ? myServer.getViewport().down('#employeeAreaTreePanel').getSelectedRecordEmployeeId() : myServer.loginUser.id
			});
		}, this);
	},
	doAddEvent: function() {
		Ext.create('Business.CustomerFormWindow', {
			customerGridPanel: this
		}).show();
	},
	doEditEvent: function(id) {
		Ext.create('Business.CustomerFormWindow', {
			entityId: id,
			customerGridPanel: this
		}).show();
	},
	doViewEvent: function(id) {
		Ext.create('Business.CustomerFormWindow', {
			entityId: id,
			customerGridPanel: this,
			readOnlyForm: true
		}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/business/customer/delete.ctrl',
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
	traceList: function() {
		var records = this.getSelectedRecords();
		if (records.length == 1) {
			Ext.create('Business.CustomerTraceWindow', {
				customerId: records[0].get('id'),
				customerCompanyName: records[0].get('companyName')
			}).show();
		} else {
			Ext.Msg.alert("系统提示", "请选择一条数据！");
		}
	},
	queryData: function() {
		this.getStore().load();
	}
});
