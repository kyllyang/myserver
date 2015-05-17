Ext.define('Base.app.menu.MenuModuleThematicGridPanel', {
	extend: 'Base.ux.GridPanel',

	menuId: null,
	title: '关联应用与专题',
	itemId: 'menuModuleThematicGridPanel',

	initComponent: function() {
		var applicationComboBox = Ext.create('Base.app.menu.ApplicationComboBox');
		var thematicComboBox = Ext.create('Base.app.menu.ThematicComboBox');

		Ext.apply(this, {
			url: ctx + '/app/mmt/list.ctrl',
			sortProperty: 'sort',
			sortDirection: 'asc',
			enableRowNumber: false,
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
				text: '应用',
				dataIndex: 'moduleId',
				editor: applicationComboBox,
				renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
					return Ext.isEmpty(value) ? '' : applicationComboBox.findRecord('id', value).get('name');
				},
				flex: 1
			}, {
				text: '专题',
				dataIndex: 'thematicId',
				editor: thematicComboBox,
				renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
					return Ext.isEmpty(value) ? '' : thematicComboBox.findRecord('id', value).get('name');
				},
				flex: 1
			}],
			listeners: {
				afterRender: function(form, eOpts) {
					this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
						enter: function() {
							this.queryData();
						},
						scope: this
					});
				},
				scope: this
			},
			plugins: [Ext.create('Ext.grid.plugin.CellEditing', {
				pluginId: 'cellEditingPlugin',
				clicksToEdit: 1
			})]
		});
		this.callParent();

		this.store.on('beforeload', function(store, operation, eOpts) {
			this.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.store.proxy.extraParams, {
				'qc.menuId': this.menuId
			});
		}, this);
		this.on('editor')
	},
	doAddEvent: function() {
		var record = Ext.ModelManager.create({
			moduleId: null,
			thematicId: null
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
