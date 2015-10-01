Ext.define('Base.gis.thematic.ContextMenu', {
	extend: 'Ext.menu.Menu',

	layerTreePanel: null,

	initComponent: function() {
		this.callParent();

		var id = this.layerTreePanel.getSelectedRecord().get('id');
		if (Ext.isEmpty(id)) {
			this.add(Ext.create('Ext.menu.Item', {
				text: '加入图层',
				icon: ctx + '/resource/image/icon/addLayer.png',
				listeners: {
					click: this.doAddLayerEvent,
					scope: this
				}
			}));
			this.add(Ext.create('Ext.menu.Item', {
				text: '新增分组',
				icon: ctx + '/resource/image/icon/add.png',
				listeners: {
					click: this.doAddGroupEvent,
					scope: this
				}
			}));
		} else {
			if (Ext.String.startsWith(id, 'g_')) {
				this.add(Ext.create('Ext.menu.Item', {
					text: '加入图层',
					icon: ctx + '/resource/image/icon/addLayer.png',
					listeners: {
						click: this.doAddLayerEvent,
						scope: this
					}
				}));
				this.add(Ext.create('Ext.menu.Item', {
					text: '新增分组',
					icon: ctx + '/resource/image/icon/add.png',
					listeners: {
						click: this.doAddGroupEvent,
						scope: this
					}
				}));
				this.add(Ext.create('Ext.menu.Item', {
					text: '编辑分组',
					icon: ctx + '/resource/image/icon/edit.png',
					listeners: {
						click: this.doEditGroupEvent,
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
			if (Ext.String.startsWith(id, 'l_')) {
				this.add(Ext.create('Ext.menu.Item', {
					text: '删除',
					icon: ctx + '/resource/image/icon/delete.png',
					listeners: {
						click: this.doDeleteEvent,
						scope: this
					}
				}));
			}
		}
	},
	doAddLayerEvent: function(item, e, eOpts) {
		var node = this.layerTreePanel.getSelectedRecord();
		Ext.create('Base.gis.thematic.LayerSelectWindow', {
			determine: function(records) {
				for (var i = 0; i < records.length; i++) {
					node.insertChild(node.childNodes.length, {
						id: 'l_' + records[i].get('id') +  '_' + myServer.uuid(),
						text: records[i].get('name') + '(' + records[i].get('layerClassNameText') + ')',
						leaf: true
					});
				}
			}
		}).show();
	},
	doAddGroupEvent: function(item, e, eOpts) {
		Ext.create('Base.gis.thematic.LayerGroupFormWindow', {
			layerTreePanel: this.layerTreePanel
		}).show();
	},
	doEditGroupEvent: function(item, e, eOpts) {
		var node = this.layerTreePanel.getSelectedRecord();
		Ext.create('Base.gis.thematic.LayerGroupFormWindow', {
			layerTreePanel: this.layerTreePanel,
			name: node.get('text'),
			sort: node.get('sort')
		}).show();
	},
	doDeleteEvent: function(item, e, eOpts) {
		this.layerTreePanel.getSelectedRecord().remove();
	}
});
