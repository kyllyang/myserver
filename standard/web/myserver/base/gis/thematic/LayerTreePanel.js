Ext.define('Base.gis.thematic.LayerTreePanel', {
	extend: 'Ext.tree.Panel',

	mapId: null,

	initComponent: function() {
		Ext.define('DataModel', {
			extend: 'Ext.data.Model',
			fields: ['id', 'text', 'sort']
		});

		Ext.apply(this, {
			itemId: 'layerTreePanel',
			autoScroll: true,
			useArrows: true,
			store: Ext.create('Ext.data.TreeStore', {
				model: 'DataModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/gis/layergroup/tree.ctrl'
				},
				reader: {
					type: 'json'
				},
				root: {
					id: null,
					text: '图层结构'
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
			Ext.create('Base.gis.thematic.ContextMenu', {
				layerTreePanel: this
			}).showAt(e.getXY());
			e.stopEvent();
		}, this);

		this.store.on('beforeload', function(store, operation, eOpts) {
			this.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.store.proxy.extraParams, {
				mapId: this.mapId
			});
		}, this);
	},
	queryData: function() {
		this.getStore().load();
	},
	getJsonObject: function(node) {
		var jo = {
			id: node.get('id'),
			name: node.get('text'),
			sort: node.get('sort'),
			children: []
		};
		for (var i = 0; i < node.childNodes.length; i++) {
			jo.children.push(this.getJsonObject(node.childNodes[i]))
		}
		return jo;
	},
	getSelectedRecord: function() {
		return this.getSelectionModel().getLastSelected();
	}
});
