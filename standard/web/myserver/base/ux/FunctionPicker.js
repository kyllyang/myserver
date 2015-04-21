Ext.define('Base.ux.FunctionPicker', {
	extend: 'Ext.form.field.Picker',

	initComponent: function() {
		this.callParent();
	},
	createPicker: function() {
		Ext.define('TreeModel', {
			extend: 'Ext.data.Model',
			fields: ['id', 'text']
		});

		return Ext.create('Ext.tree.Panel', {
			floating: true,
			store: Ext.create('Ext.data.TreeStore', {
				model: 'DateModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/app/module/tree.ctrl'
				},
				reader: {
					type: 'json'
				},
				root: {
					id: null,
					text: '应用模块'
				}
			}),
			border: false,
			useArrows: true
		});
	}
});
