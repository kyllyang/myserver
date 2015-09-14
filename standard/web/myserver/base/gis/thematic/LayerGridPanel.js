Ext.define('Base.gis.thematic.LayerGridPanel', {
	extend: 'Base.ux.GridPanel',

	thematicId: null,
	itemId: 'layerGridPanel',
	border: 0,
	height: 200,

	initComponent: function() {
		Ext.apply(this, {
			url: ctx + '/app/mmt/list.ctrl',
			sortProperty: 'sort',
			sortDirection: 'asc',
			enablePaging: false,
			enableEditBtn: false,
			enableViewBtn: false,
			enableColumnEditBtn: false,
			enableColumnViewBtn: false,
			enableColumnDelBtn: false,
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '名称',
				dataIndex: 'name',
				flex: 1
			}, {
				text: '类型',
				dataIndex: 'layerClassNameText',
				flex: 1
			}, {
				text: '排序',
				dataIndex: 'sort',
				flex: 1
			}],
			plugins: [Ext.create('Ext.grid.plugin.CellEditing', {
				pluginId: 'cellEditingPlugin',
				clicksToEdit: 1
			})]
		});
		this.callParent();

		this.store.on('beforeload', function(store, operation, eOpts) {
			this.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.store.proxy.extraParams, {
				'qc.thematicId': this.thematicId
			});
		}, this);
	},
	doAddEvent: function() {
		var record = Ext.ModelManager.create({
			id: null,
			name: null,
			layerClassNameText: null,
			sort: null
		}, Ext.getClassName(this.getStore().model));
		this.getStore().insert(0, record);
		this.getPlugin('cellEditingPlugin').startEditByPosition({row: 0, column: 0});
	},
	doDeleteEvent: function(ids) {
		this.getStore().remove(this.getSelectionModel().getSelection());
	},
	getData: function() {
		var store = this.getStore();
		var datas = [];
		for (var i = 0; i < store.getCount(); i++) {
			var record = store.getAt(i);
			datas.push({
				moduleId: record.get('moduleId'),
				thematicId: record.get('thematicId')
			});
		}
		return datas;
	},
	queryData: function() {
		this.getStore().load();
	}
});
