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

		this.on('itemclick', function(treePanel, record, item, index, e, eOpts) {
			var gridPanel = myServer.getViewport().down('tabpanel').getActiveTab().down('gridpanel');
			var itemId = gridPanel.itemId;
			if ('customerGridPanel' == itemId || 'projectGridPanel' == itemId) {
				gridPanel.queryData();
			} else if ('expenseGridPanel' == itemId) {
				gridPanel = myServer.getViewport().down('tabpanel').getActiveTab().down('tabpanel').getActiveTab().down('gridpanel');
				if ('expenseGridPanel' == gridPanel.itemId || 'expenseStatAreaGridPanel' == gridPanel.itemId || 'expenseStatCustomerGridPanel' == gridPanel.itemId || 'expenseStatProjectGridPanel' == gridPanel.itemId) {
					gridPanel.queryData();
				}
			}
		}, this);

		this.getStore().on('load', function(store, node, records, successful, eOpts) {
			var selectNode = store.getRootNode();
			var selectionModel = this.getSelectionModel();
			selectionModel.deselect(selectNode);
			selectionModel.select(selectNode);
			selectNode.expand(true);

			myServer.getViewport().down('#customerGridPanel').queryData();
		}, this);

		this.queryData();
	},
	queryData: function() {
		this.getStore().load();
	},
	getSelectedRecordEmployeeId: function() {
		var id = null;
		var record = this.getSelectionModel().getLastSelected();
		if (!Ext.isEmpty(record)) {
			var recordId = record.get('id');
			if (!Ext.isEmpty(recordId)) {
				var ids = recordId.split('_');
				if ('employee' == ids[1]) {
					id = ids[2];
				} else if ('area' == ids[1]) {
					id = record.parentNode.get('id').split('_')[2];
				}
			}
		}
		return id;
	},
	getSelectedRecordAreaId: function() {
		var id = null;
		var record = this.getSelectionModel().getLastSelected();
		if (!Ext.isEmpty(record)) {
			var recordId = record.get('id');
			if (!Ext.isEmpty(recordId)) {
				var ids = recordId.split('_');
				if ('area' == ids[1]) {
					id = ids[2];
				}
			}
		}
		return id;
	}
});
