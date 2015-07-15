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
				items: []
			}, {
				title: '按客户汇总',
				layout: 'fit',
				border: false,
				items: []
			}, {
				title: '按项目汇总',
				layout: 'fit',
				border: false,
				items: []
			}, {
				title: '区域',
				layout: 'fit',
				border: false,
				items: [Ext.create('Business.AreaGridPanel')]
			}]
		});
		this.callParent();
	}
});
