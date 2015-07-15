Ext.define('Business.ProjectTraceGridPanel', {
	extend: 'Base.ux.GridPanel',

	projectId: null,
	projectName: null,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'projectTraceGridPanel',
			url: ctx + '/business/projecttrace/dataset.ctrl',
			sortProperty: 'id',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				dataIndex: 'projectId',
				hidden: true
			}, {
				text: '项目名称',
				dataIndex: 'projectName'
			}, {
				text: '项目联系人',
				dataIndex: 'linkMan'
			}, {
				text: '联系电话',
				dataIndex: 'phone'
			}, {
				text: '资金情况',
				dataIndex: 'fund'
			}, {
				text: '跟踪成果',
				dataIndex: 'result'
			}, {
				text: '项目成功率',
				dataIndex: 'successRate'
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
				'qc.projectId': this.projectId
			});
		}, this);

		this.queryData();
	},
	doAddEvent: function() {
		Ext.create('Business.ProjectTraceFormWindow', {
			projectId: this.projectId,
			projectName: this.projectName,
			projectTraceGridPanel: this
		}).show();
	},
	doEditEvent: function(id) {
		Ext.create('Business.ProjectTraceFormWindow', {
			entityId: id,
			projectTraceGridPanel: this
		}).show();
	},
	doViewEvent: function(id) {
		Ext.create('Business.ProjectTraceFormWindow', {
			entityId: id,
			projectTraceGridPanel: this,
			readOnlyForm: true
		}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/business/projecttrace/delete.ctrl',
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
