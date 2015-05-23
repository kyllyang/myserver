Ext.define('Base.sys.org.RoleTreePanel', {
	extend: 'Ext.tree.Panel',

	initComponent: function() {
		Ext.define('TreeModel', {
			extend: 'Ext.data.Model',
			fields: ['id', 'text']
		});

		Ext.apply(this, {
			itemId: 'roleTreePanel',
			autoScroll: true,
			useArrows: true,
			buttonAlign: 'center',
			store: Ext.create('Ext.data.TreeStore', {
				model: 'TreeModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/sys/role/tree.ctrl'
				},
				reader: {
					type: 'json'
				},
				root: {
					id: null,
					text: '角色',
					expanded: true
				}
			}),
			header: {
				items: [{
					xtype: 'button',
					icon: ctx + '/resource/image/icon/expandall.png',
					handler: this.expandAll,
					scope: this
				}, {
					xtype: 'button',
					icon: ctx + '/resource/image/icon/collapseall.png',
					handler: this.collapseAll,
					scope: this
				}]
			},
			buttons:[{
				xtype: 'button',
				text: '保存',
				handler: this.saveRole,
				scope:this,
				hidden: this.readOnlyForm
			}]
		});
		this.callParent();
	},
	loadTree: function(employeeId) {
		this.store.proxy.actionMethods = {read: 'POST'};
		Ext.apply(this.store.proxy.extraParams, {
			employeeId: employeeId
		});
		this.getStore().load();
	},
	saveRole: function() {
		var record = this.ownerCt.getComponent('employeeGridPanel').getSelectedRecord();
		if (record) {
			var records = this.getChecked();
			var moduleIds = [];
			for (var i = 0; i < records.length; i++) {
				moduleIds.push(records[i].get('id'));
			}

			Ext.Ajax.request({
				url: ctx + '/sys/employee/saveRole.ctrl',
				params: {
					employeeId: record.get('id'),
					roleIds: moduleIds
				},
				success: function(response, opts) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
				},
				failure: function(response, opts) {
					Ext.Msg.alert("系统提示", "数据保存失败！");
				},
				scope: this
			});
		} else {
			Ext.Msg.alert("系统提示", "请选择一个员工！");
		}
	}
});
