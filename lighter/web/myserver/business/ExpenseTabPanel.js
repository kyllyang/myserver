Ext.define('Business.ExpenseTabPanel', {
	extend: 'Ext.tab.Panel',

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				title: '明细',
				layout: 'fit',
				border: false,
				items: [Ext.create('Business.ExpenseGridPanel')]
			}, {
				title: '按区域汇总',
				layout: 'fit',
				border: false,
				items: [Ext.create('Business.ExpenseStatAreaGridPanel')]
			}, {
				title: '按客户汇总',
				layout: 'fit',
				border: false,
				items: [Ext.create('Business.ExpenseStatCustomerGridPanel')]
			}, {
				title: '按项目汇总',
				layout: 'fit',
				border: false,
				items: [Ext.create('Business.ExpenseStatProjectGridPanel')]
			}, {
				title: '区域',
				layout: 'fit',
				border: false,
				items: [Ext.create('Business.AreaGridPanel')]
			}],
			listeners: {
				tabchange: function(tabPanel, newCard, oldCard, eOpts) {
					newCard.down('gridpanel').queryData();
				},
				scope: this
			}
		});
		this.callParent();
	}
});
