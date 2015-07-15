Ext.define('Business.CustomerPicker', {
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
			url: ctx + '/business/customer/dataset.ctrl',
			sortProperty: 'id',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '拜访日期',
				dataIndex: 'visitDate',
				renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
					return Ext.Date.format(Ext.Date.parse(value, 'Y-m-d H:i:s.u'), 'Y-m-d');
				}
			}, {
				text: '单位名称',
				dataIndex: 'companyName'
			}, {
				text: '联系人',
				dataIndex: 'linkMan'
			}, {
				text: '职务',
				dataIndex: 'job'
			}, {
				text: '联系电话',
				dataIndex: 'phone'
			}, {
				text: '办公电话',
				dataIndex: 'officePhone'
			}, {
				text: '邮箱',
				dataIndex: 'email'
			}, {
				text: '等级',
				dataIndex: 'level'
			}, {
				text: '拜访成果',
				dataIndex: 'visitResult'
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
