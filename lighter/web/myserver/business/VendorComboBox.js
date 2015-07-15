Ext.define("Business.VendorComboBox", {
	extend: 'Ext.form.field.ComboBox',

	invokeCode: null,
	initAutoLoad: true,

	initComponent: function() {
		Ext.define("Vendor", {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id', mapping: 'id'},
				{name: 'name', mapping: 'name'}
			]
		});

		var store = Ext.create('Ext.data.Store', {
			model: 'Vendor',
			proxy: {
				type: 'ajax',
				url: ctx + '/business/vendor/dataset.ctrl',
				extraParams: {
					limit: 65535
				},
				reader: {
					type: 'json',
					root: 'dataList',
					totalProperty: 'paginated.totalRecord'
				}
			},
			autoLoad: this.initAutoLoad
		});

		Ext.apply(this, {
			store: store,
			editable: false,
			emptyText: '请选择...',
			displayField: 'name',
			valueField: 'id',
			queryMode: 'remote'
		});
		this.callParent();
	}
});
