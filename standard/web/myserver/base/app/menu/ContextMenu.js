Ext.define('Base.app.menu.ContextMenu', {
	extend: 'Ext.menu.Menu',

	menuTreePanel: null,

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Ext.menu.Item', {
			text: '新增',
			icon: ctx + '/resource/image/icon/add.png',
			listeners: {
				click: this.doAddEvent,
				scope: this
			}
		}));

		if (this.menuTreePanel.getSelectedRecord().get('id')) {
			this.add(Ext.create('Ext.menu.Item', {
				text: '编辑',
				icon: ctx + '/resource/image/icon/edit.png',
				listeners: {
					click: this.doEditEvent,
					scope: this
				}
			}));
			this.add(Ext.create('Ext.menu.Item', {
				text: '删除',
				icon: ctx + '/resource/image/icon/delete.png',
				listeners: {
					click: this.doDeleteEvent,
					scope: this
				}
			}));
		}
	},
	doAddEvent: function(item, e, eOpts) {
		Ext.create('Base.app.menu.MenuFormWindow', {
			menuTreePanel: this.menuTreePanel
		}).show();
	},
	doEditEvent: function(item, e, eOpts) {
		Ext.create('Base.app.menu.MenuFormWindow', {
			menuTreePanel: this.menuTreePanel,
			entityId: this.menuTreePanel.getSelectedRecord().get('id')
		}).show();
	},
	doDeleteEvent: function(item, e, eOpts) {
		Ext.MessageBox.confirm("系统提示", "确定要删除数据吗？", function (btn) {
			if ('yes' == btn) {
				Ext.Ajax.request({
					url: ctx + '/app/menu/delete.ctrl',
					params: {
						ids: [this.menuTreePanel.getSelectedRecord().get('id')]
					},
					success: function(response, opts) {
						Ext.Msg.alert("系统提示", "数据删除成功！");

						var record = this.menuTreePanel.getSelectedRecord();
						this.menuTreePanel.loadTreeNode(record.parentNode && record.parentNode.get('id') ? record.parentNode.get('id') : null);
					},
					failure: function(response, opts) {
						Ext.Msg.alert("系统提示", "数据删除失败！");
					},
					scope: this
				});
			}
		}, this);
	}
});
