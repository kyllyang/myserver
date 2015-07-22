Ext.define('Business.ProjectGridPanel', {
	extend: 'Base.ux.GridPanel',

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'projectGridPanel',
			url: ctx + '/business/project/dataset.ctrl',
			sortProperty: 'id',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '客户名称',
				dataIndex: 'customerCompanyName'
			}, {
				text: '项目名称',
				dataIndex: 'name'
			}, {
				text: '项目联系人',
				dataIndex: 'linkMan'
			}, {
				text: '联系电话',
				dataIndex: 'phone'
			}, {
				text: '资金情况',
				dataIndex: 'fund'
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
		Ext.create('Business.ProjectFormWindow', {
			projectGridPanel: this
		}).show();
	},
	doEditEvent: function(id) {
		Ext.create('Business.ProjectFormWindow', {
			entityId: id,
			projectGridPanel: this
		}).show();
	},
	doViewEvent: function(id) {
		Ext.create('Business.ProjectFormWindow', {
			entityId: id,
			projectGridPanel: this,
			readOnlyForm: true
		}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/business/project/delete.ctrl',
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
			Ext.create('Business.ProjectTraceWindow', {
				projectId: records[0].get('id'),
				projectName: records[0].get('name')
			}).show();
		} else {
			Ext.Msg.alert("系统提示", "请选择一条数据！");
		}
	},
	queryData: function() {
		this.getStore().load();
	}
});
