Ext.define('Business.EmployeeGridPanel', {
	extend: 'Base.ux.GridPanel',

	departmentTreePanel: null,

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'employeeGridPanel',
			url: ctx + '/business/employee/dataset.ctrl',
			sortProperty: 'sort',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '员工姓名',
				dataIndex: 'name'
			}, {
				text: '用户名称',
				dataIndex: 'username'
			}, {
				text: '电子邮箱',
				dataIndex: 'email'
			}, {
				text: '冻结',
				dataIndex: 'freeze',
				renderer : function(value, metaData, record, rowIndex, colIndex, store, view) {
					if ('1' == value) {
						return "<img src=\"" + ctx + "/resource/image/icon/freeze.png\" border=\"0\" title=\"冻结\"/>"
					}
				}
			}],
			dockedItems: [Ext.create('Ext.form.Panel', {
				itemId: 'queryForm',
				dock: 'top',
				autoHeight: true,
				bodyPadding: 5,
				layout: {
					type: 'table',
					columns: 3
				},
				defaults: {
					labelAlign: 'right',
					labelWidth: 80,
					labelSeparator: '：',
					width: 250
				},
				items: [{
					xtype: 'textfield',
					fieldLabel: '员工姓名',
					name: 'name'
				}, {
					xtype: 'textfield',
					fieldLabel: '用户名称',
					name: 'username'
				}, {
					xtype: 'container',
					items: [{
						xtype: 'button',
						text: '查询',
						width: 80,
						margin: '0 0 0 10',
						handler: this.queryData,
						scope: this
					}, {
						xtype: 'button',
						text: '清空',
						width: 80,
						margin: '0 0 0 10',
						handler: this.clear,
						scope: this
					}]
				}]
			})],
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
			var form = this.getComponent('queryForm').getForm();

			this.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.store.proxy.extraParams, {
				'qc.name': form.findField('name').getValue(),
				'qc.username': form.findField('username').getValue()
			});
		}, this);

		this.queryData();
	},
	doAddEvent: function() {
		Ext.create('Business.EmployeeFormWindow', {
			employeeGridPanel: this
		}).show();
	},
	doEditEvent: function(id) {
		Ext.create('Business.EmployeeFormWindow', {
			entityId: id,
			employeeGridPanel: this
		}).show();
	},
	doViewEvent: function(id) {
		Ext.create('Business.EmployeeFormWindow', {
			entityId: id,
			employeeGridPanel: this,
			readOnlyForm: true
		}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/business/employee/delete.ctrl',
			params: {
				ids: ids
			},
			success: function(response, opts) {
				Ext.Msg.alert("系统提示", "数据删除成功！");
				this.queryData();
				myServer.getViewport().down('#employeeAreaTreePanel').queryData();
			},
			failure: function(response, opts) {
				Ext.Msg.alert("系统提示", "数据删除失败！");
			},
			scope: this
		});
	},
	queryData: function() {
		this.getStore().load();
	},
	clear: function() {
		var form = this.getComponent('queryForm').getForm();
		form.findField('name').setValue('');
		form.findField('username').setValue('');
		this.queryData();
	}
});
