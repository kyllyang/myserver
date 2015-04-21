Ext.define('Base.app.module.ModuleTreePanel', {
	extend: 'Ext.tree.Panel',

	currentNodeId: null,

	itemId: 'moduleTreePanel',
	autoScroll: true,

	initComponent: function() {
		Ext.define('DateModel', {
			extend: 'Ext.data.Model',
			fields: ['id', 'text', 'type']
		});

		var store = Ext.create('Ext.data.TreeStore', {
			model: 'DateModel',
			proxy: {
				type: 'ajax',
				url: ctx + '/app/module/tree.ctrl'
			},
			reader: {
				type: 'json'
			},
			root: {
				id: null,
				text: '应用模块'
			}
		});

		Ext.apply(this, {
			store: store,
			useArrows: true
		});
		this.callParent();

		this.on('select', function(treePanel, record, index, eOpts) {
			if ('2' != record.get('type')) {
				this.ownerCt.getComponent('moduleGridPanel').queryData(record.get('id'));
			}
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
