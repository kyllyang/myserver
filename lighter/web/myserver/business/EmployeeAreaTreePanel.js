Ext.define('Business.EmployeeAreaTreePanel', {
	extend: 'Ext.tree.Panel',

	initComponent: function() {
		Ext.define('TreeModel', {
			extend: 'Ext.data.Model',
			fields: ['id', 'text']
		});

		Ext.apply(this, {
			itemId: 'employeeAreaTreePanel',
			autoScroll: true,
			useArrows: true,
			store: Ext.create('Ext.data.TreeStore', {
				model: 'TreeModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/business/employee/tree.ctrl'
				},
				reader: {
					type: 'json'
				},
				root: {
					id: null,
					text: '吉林省汇旺科技有限公司'
				}
			})
		});
		this.callParent();

		this.getStore().on('load', function(store, node, records, successful, eOpts) {
			var selectNode = store.getRootNode();
			var selectionModel = this.getSelectionModel();
			selectionModel.deselect(selectNode);
			selectionModel.select(selectNode);
			selectNode.expand(true);
		}, this);

		this.queryData();
	},
	queryData: function() {
		this.getStore().load();
	}
});
