Ext.define('Base.app.menu.MenuTreePanel', {
	extend: 'Ext.tree.Panel',

	currentNodeId: null,

	initComponent: function() {
		Ext.define('DataModel', {
			extend: 'Ext.data.Model',
			fields: ['id', 'text']
		});

		Ext.apply(this, {
			itemId: 'menuTreePanel',
			autoScroll: true,
			useArrows: true,
			store: Ext.create('Ext.data.TreeStore', {
				model: 'DataModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/app/menu/tree.ctrl'
				},
				reader: {
					type: 'json'
				},
				root: {
					id: null,
					text: '业务菜单'
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
			}
		});
		this.callParent();

		this.on('itemcontextmenu', function(treePanel, record, item, index, e, eOpts) {
			Ext.create('Base.app.menu.ContextMenu', {
				menuTreePanel: this
			}).showAt(e.getXY());
			e.stopEvent();
		}, this);

		this.on('select', function(treePanel, record, index, eOpts) {
			this.ownerCt.getComponent('menuForm').loadForm();
		}, this);

		this.getStore().on('load', function(store, node, records, successful, eOpts) {
			var selectNode = this.currentNodeId ? store.getNodeById(this.currentNodeId) : store.getRootNode();

			var tempNode = selectNode;
			while (tempNode) {
				this.expandNode(tempNode);
				tempNode = tempNode.parentNode;
			}

			this.getSelectionModel().select(selectNode);
		}, this);

		this.getStore().load();
	},
	loadTreeNode: function(id) {
		this.currentNodeId = id;
		this.getStore().load();
	},
	getSelectedRecord: function() {
		return this.getSelectionModel().getLastSelected();
	}
});
