Ext.define('Base.app.menu.MenuModuleThematicGridPanel', {
	extend: 'Base.ux.GridPanel',

	menuId: null,
	itemId: 'menuModuleThematicGridPanel',
	height: 200,

	initComponent: function() {
		var applicationComboBox = Ext.create('Base.app.menu.ApplicationComboBox');

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
				field: {
					maxLength: 100,
					allowBlank: false
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
		var validIds = [];
		for (var i = 0; i < ids.length; i++) {
			if (ids[i]) {
				validIds.push(ids[i]);
			}
		}

		Ext.Ajax.request({
			url: ctx + '/app/mmt/delete.ctrl',
			params: {
				ids: validIds
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
	getData: function() {
		var records = this.getStore().getModifiedRecords();
		var datas = [];
		for (var i = 0; i < records.length; i++) {
			datas.push({
				id: records[i].get('id'),
				moduleId: records[i].get('moduleId'),
				thematicId: records[i].get('thematicId')
			});
		}
		return datas;
	},
	queryData: function() {
		this.getStore().load();
	}
});
