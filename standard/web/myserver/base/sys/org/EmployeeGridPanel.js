Ext.define('Base.sys.org.EmployeeGridPanel', {
	extend: 'Base.ux.GridPanel',

	departmentTreePanel: null,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'employeeGridPanel',
			url: ctx + '/sys/employee/dataset.ctrl',
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
				text: '密码',
				dataIndex: 'password'
			}, {
				text: '电子邮箱',
				dataIndex: 'email'
			}, {
				text: '所属部门',
				dataIndex: 'departmentName'
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
				layout: 'hbox',
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
					xtype: 'textfield',
					fieldLabel: '电子邮箱',
					name: 'email'
				}, {
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
			var record = this.departmentTreePanel.getSelectedRecord();
			var form = this.getComponent('queryForm').getForm();

			this.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.store.proxy.extraParams, {
				'qc.departmentId': Ext.isEmpty(record) ? null : record.get('id'),
				'qc.name': form.findField('name').getValue(),
				'qc.username': form.findField('username').getValue(),
				'qc.email': form.findField('email').getValue()
			});
		}, this);

		this.queryData();
	},
	doAddEvent: function() {
		if (Ext.isEmpty(this.departmentTreePanel.getSelectedRecord().get('id'))) {
			Ext.Msg.alert("系统提示", "请选择一个部门！");
		} else {
			Ext.create('Base.sys.org.EmployeeFormWindow', {
				departmentTreePanel: this.departmentTreePanel,
				employeeGridPanel: this
			}).show();
		}
	},
	doEditEvent: function(id) {
		Ext.create('Base.sys.org.EmployeeFormWindow', {
			entityId: id,
			departmentTreePanel: this.departmentTreePanel,
			employeeGridPanel: this
		}).show();
	},
	doViewEvent: function(id) {
		Ext.create('Base.sys.org.EmployeeFormWindow', {
			entityId: id,
			departmentTreePanel: this.departmentTreePanel,
			employeeGridPanel: this,
			readOnlyForm: true
		}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/sys/employee/delete.ctrl',
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
	getSelectedRecord: function() {
		return this.getSelectionModel().getLastSelected();
	},
	queryData: function() {
		this.getStore().load();
	},
	clear: function() {
		var form = this.getComponent('queryForm').getForm();
		form.findField('username').setValue('');
		form.findField('email').setValue('');
		this.queryData();
	}
});
