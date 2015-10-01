Ext.define('Base.gis.thematic.LayerSelectWindow', {
	extend: 'Ext.window.Window',

	determine: null,
	grid: null,

	title: '图层信息',
	width: 800,
	height: 400,
	border: false,
	modal: true,
	resizable: false,
	buttonAlign: 'center',
	layout: 'fit',

	initComponent: function() {
		Ext.apply(this, {
			buttons:[{
				xtype: 'button',
				text: '确定',
				handler: this.doDetermineEvent,
				scope: this
			}, {
				xtype: 'button',
				text: '关闭',
				handler: this.closeWindow,
				scope: this
			}]
		});
		this.callParent();

		this.grid = Ext.create('Base.ux.GridPanel', {
			itemId: 'layourGrid',
			url: ctx + '/gis/layer/dataset.ctrl',
			sortProperty: 'sort',
			sortDirection: 'asc',
			idProperty: 'id',
			enableAddBtn: false,
			enableEditBtn: false,
			enableViewBtn: false,
			enableDelBtn: false,
			enableColumnEditBtn: false,
			enableColumnViewBtn: false,
			enableColumnDelBtn: false,
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '名称',
				dataIndex: 'name'
			}, {
				text: '图层类型',
				dataIndex: 'layerClassNameText'
			}, {
				text: '范围',
				dataIndex: 'extent'
			}, {
				text: '最小分辨率',
				dataIndex: 'minResolution'
			}, {
				text: '最大分辨率',
				dataIndex: 'maxResolution'
			}, {
				text: '可见',
				dataIndex: 'visible'
			}, {
				text: '透明度',
				dataIndex: 'opacity'
			}, {
				text: '亮度',
				dataIndex: 'brightness'
			}, {
				text: '对比度',
				dataIndex: 'contrast'
			}, {
				text: '色度',
				dataIndex: 'hue'
			}, {
				text: '饱和度',
				dataIndex: 'saturation'
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
		this.add(this.grid);

		this.grid.on('itemdblclick', function(grid, record, item, index, e, eOpts){
			this.doDetermineEvent();
		}, this);

		this.grid.store.on('beforeload', function(store, operation, eOpts) {
			var form = this.grid.getComponent('queryForm').getForm();

			this.grid.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.grid.store.proxy.extraParams, {
				'qc.name': form.findField('name').getValue()
			});
		}, this);

		this.queryData();
	},
	queryData: function() {
		this.grid.getStore().load();
	},
	clear: function() {
		var form = this.getComponent('queryForm').getForm();
		form.findField('name').setValue('');
		this.queryData();
	},
	doDetermineEvent: function() {
		if (!Ext.isEmpty(this.determine)) {
			this.determine(this.grid.getSelectedRecords());
		}
		this.closeWindow();
	},
	closeWindow: function() {
		this.close();
	}
});
