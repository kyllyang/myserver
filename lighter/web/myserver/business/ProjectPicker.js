Ext.define('Business.ProjectPicker', {
	extend: 'Ext.form.field.Picker',

	single: true,
	onDetermine: null,
	editable: false,

	initComponent: function() {
		this.callParent();
	},
	createPicker: function() {
		var gridPanel = Ext.create('Base.ux.GridPanel', {
			floating: true,
			enableAddBtn: false,
			enableEditBtn: false,
			enableViewBtn: false,
			enableDelBtn: false,
			enableColumnEditBtn: false,
			enableColumnViewBtn: false,
			enableColumnDelBtn: false,
			selectMode: this.single ? 'SINGLE' : 'MULTI',
			url: ctx + '/business/project/dataset.ctrl',
			sortProperty: 'id',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '客户名称',
				dataIndex: 'customerCompanyName'
			}, {
				text: '项目名称',
				dataIndex: 'name'
			}, {
				text: '项目联系人',
				dataIndex: 'linkMan'
			}, {
				text: '联系电话',
				dataIndex: 'phone'
			}, {
				text: '资金情况',
				dataIndex: 'fund'
			}],
			tbar: [{
				text: '确定',
				icon: ctx + '/resource/image/icon/confirm.png',
				handler: function() {
					gridPanel.hide();
					this.onDetermine(gridPanel.getSelectedRecords());
				},
				scope: this
			}, {
				text: '清空',
				icon: ctx + '/resource/image/icon/clear.png',
				handler: function() {
					gridPanel.hide();
					gridPanel.getSelectionModel().deselectAll();
					this.onDetermine(gridPanel.getSelectedRecords());
				},
				scope: this
			}],
			listeners: {
				render: function(gridPanel, eOpts) {
					gridPanel.getStore().load();
				},
				itemdblclick: function(gridView, record, item, index, e, eOpts) {
					gridPanel.hide();
					this.onDetermine(gridPanel.getSelectedRecords());
				},
				scope: this
			}
		});
		return gridPanel;
	}
});
