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
				text: '名称',
				dataIndex: 'name'
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
	queryData: function() {
		this.getStore().load();
	},
	clear: function() {
		var form = this.getComponent('queryForm').getForm();
		form.findField('name').setValue('');
		this.queryData();
	}
});
