Ext.define('Base.ux.FunctionPicker', {
	extend: 'Ext.form.field.Picker',

	single: true,
	onDetermine: null,
	editable: false,

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
					url: ctx + '/app/module/tree.ctrl',
					extraParams: {
						checked: false,
						'function': true
					}
				},
				reader: {
					type: 'json'
				},
				root: {
					id: null,
					expanded: true
				}
			}),
			border: false,
			useArrows: true,
			rootVisible: false,
			listeners: {
				checkchange: function(node, checked) {
					var treePanel = this.getPicker();


					if (this.single) {
						if (checked) {
							var nodes = treePanel.getChecked();
							for (var i = 0; i < nodes.length; i++) {
								if (node.get('id') != nodes[i].get('id')) {
									nodes[i].set('checked', false);
								}
							}
						}

						treePanel.hide();
					}

					this.onDetermine(treePanel.getChecked());
				},
				scope: this
			}
		});
	}
});
