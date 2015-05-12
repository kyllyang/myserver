Ext.define('Base.sys.role.ModuleTreePanel', {
	extend: 'Base.ux.TreePanel',

	initComponent: function() {
		Ext.define('TreeModel', {
			extend: 'Ext.data.Model',
			fields: ['id', 'text']
		});

		Ext.apply(this, {
			itemId: 'moduleTreePanel',
			autoScroll: true,
			useArrows: true,
			buttonAlign: 'center',
			store: Ext.create('Ext.data.TreeStore', {
				model: 'TreeModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/app/module/tree.ctrl',
					extraParams: {
						checked: false
					}
				},
				reader: {
					type: 'json'
				},
				root: {
					id: null,
					text: '应用模块',
					checked: false,
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
				handler: this.saveTree,
				scope: this
			}]
		});
		this.callParent();
	},
	loadTree: function(roleId) {
		this.store.proxy.actionMethods = {read: 'POST'};
		Ext.apply(this.store.proxy.extraParams, {
			roleId: roleId
		});

		this.getStore().load();
	},
	saveTree: function() {
		var record = this.ownerCt.getComponent('roleGridPanel').getSelectedRecord();
		if (record) {
			var records = this.getChecked();
			var moduleIds = [];
			for (var i = 0; i < records.length; i++) {
				moduleIds.push(records[i].get('id'));
			}

			Ext.Ajax.request({
				url: ctx + '/sys/role/saveModule.ctrl',
				params: {
					roleId: record.get('id'),
					moduleIds: moduleIds
				},
				success: function(response, opts) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
				},
				failure: function(response, opts) {
					Ext.Msg.alert("系统提示", "数据删除失败！");
				},
				scope: this
			});
		} else {
			Ext.Msg.alert("系统提示", "请选择一个角色！");
		}
	}
});
