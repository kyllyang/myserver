Ext.define("Base.app.menu.ApplicationComboBox", {
	extend: 'Ext.form.field.ComboBox',

	initComponent: function() {
		Ext.define("DataModel", {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id'},
				{name: 'name'}
			]
		});

		var store = Ext.create('Ext.data.Store', {
			model: 'DataModel',
			proxy: {
				type: 'ajax',
				url: ctx + '/app/module/application.ctrl',
				extraParams: {
					'qc.invokeCode': this.invokeCode
				},
				reader: {
					type: 'json'
				}
			},
			autoLoad: true
		});

		Ext.apply(this, {
			store: store,
			queryMode: 'remote',
			displayField: 'name',
			valueField: 'id',
			editable: false
		});

		this.callParent();
	}
});
