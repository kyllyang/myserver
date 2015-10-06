Ext.define('Base.gis.thematic.ThematicGridPanel', {
	extend: 'Base.ux.GridPanel',

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'thematicGridPanel',
			url: ctx + '/gis/thematic/dataset.ctrl',
			sortProperty: 'sort',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				dataIndex: 'mapId',
				hidden: true
			}, {
				text: '名称',
				dataIndex: 'name'
			}],
			tbar: [{
				xtype: 'tbseparator'
			}, {
				text: '地图',
				icon: ctx + '/resource/image/icon/map.png',
				handler: this.doMap,
				scope: this
			}, {
				text: '视图',
				icon: ctx + '/resource/image/icon/gisview.png',
				handler: this.doView,
				scope: this
			}, {
				text: '图层',
				icon: ctx + '/resource/image/icon/layer.png',
				handler: this.doLayer,
				scope: this
			}, {
				text: '控件',
				icon: ctx + '/resource/image/icon/control.png',
				handler: this.doControl,
				scope: this
			}, {
				text: '交互',
				icon: ctx + '/resource/image/icon/interaction.png',
				handler: this.doInteraction,
				scope: this
			}, {
				text: '工具栏',
				icon: ctx + '/resource/image/icon/toolbar.png',
				handler: this.doToolbar,
				scope: this
			}],
			dockedItems: [Ext.create('Ext.form.Panel', {
				itemId: 'queryForm',
				dock: 'top',
				autoHeight: true,
				bodyPadding: 5,
				layout: 'hbox',
				defaults: {
					labelAlign: 'right',
					labelWidth: 80,
					labelSeparator: '：',
					width: 250
				},
				items: [{
					xtype: 'textfield',
					fieldLabel: '名称',
					name: 'name'
				}, {
					xtype: 'button',
					text: '查询',
					width: 80,
					margin: '0 0 0 10',
					handler: this.queryData,
					scope: this
				}, {
					xtype: 'button',
					text: '清空',
					width: 80,
					margin: '0 0 0 10',
					handler: this.clear,
					scope: this
				}]
			})],
			listeners: {
				afterRender: function(form, eOpts){
					this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
						enter: this.queryData,
						scope: this
					});
				},
				scope: this
			}
		});
		this.callParent();

		this.on('itemdblclick', function(grid, record, item, index, e, eOpts){
			this.viewEventHandler();
		});

		this.store.on('beforeload', function(store, operation, eOpts) {
			var form = this.getComponent('queryForm').getForm();

			this.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.store.proxy.extraParams, {
				'qc.name': form.findField('name').getValue()
			});
		}, this);

		this.queryData();
	},
	doAddEvent: function() {
		Ext.create('Base.gis.thematic.ThematicFormWindow', {opener: this}).show();
	},
	doEditEvent: function(id) {
		Ext.create('Base.gis.thematic.ThematicFormWindow', {opener: this, entityId: id}).show();
	},
	doViewEvent: function(id) {
		Ext.create('Base.gis.thematic.ThematicFormWindow', {opener: this, entityId: id, readOnlyForm: true}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/gis/thematic/delete.ctrl',
			params: {
				ids: ids
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
	doMap: function() {
		var records = this.getSelectedRecords();
		if (records.length == 1) {
			Ext.create('Base.gis.thematic.MapFormWindow', {
				opener: this,
				mapId: records[0].get('mapId')
			}).show();
		} else {
			Ext.Msg.alert("系统提示", "请选择一条数据！");
		}
	},
	doView: function() {
		var records = this.getSelectedRecords();
		if (records.length == 1) {
			Ext.create('Base.gis.thematic.ViewFormWindow', {
				opener: this,
				mapId: records[0].get('mapId')
			}).show();
		} else {
			Ext.Msg.alert("系统提示", "请选择一条数据！");
		}
	},
	doLayer: function() {
		var records = this.getSelectedRecords();
		if (records.length == 1) {
			Ext.create('Base.gis.thematic.LayerFormWindow', {
				opener: this,
				mapId: records[0].get('mapId')
			}).show();
		} else {
			Ext.Msg.alert("系统提示", "请选择一条数据！");
		}
	},
	doControl: function() {
		var records = this.getSelectedRecords();
		if (records.length == 1) {
			Ext.create('Base.gis.thematic.ControlFormWindow', {
				opener: this,
				mapId: records[0].get('mapId')
			}).show();
		} else {
			Ext.Msg.alert("系统提示", "请选择一条数据！");
		}
	},
	doInteraction: function() {
		var records = this.getSelectedRecords();
		if (records.length == 1) {
			Ext.create('Base.gis.thematic.InteractionFormWindow', {
				opener: this,
				mapId: records[0].get('mapId')
			}).show();
		} else {
			Ext.Msg.alert("系统提示", "请选择一条数据！");
		}
	},
	doToolbar: function() {
		var records = this.getSelectedRecords();
		if (records.length == 1) {
			Ext.create('Base.gis.thematic.ToolbarFormWindow', {
				opener: this,
				mapId: records[0].get('mapId')
			}).show();
		} else {
			Ext.Msg.alert("系统提示", "请选择一条数据！");
		}
	},
	queryData: function() {
		this.getStore().load();
	},
	clear: function() {
		var form = this.getComponent('queryForm').getForm();
		form.findField('name').setValue('');
		this.queryData();
	}
});
