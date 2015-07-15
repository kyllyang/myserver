Ext.define('Business.ExpenseTabPanel', {
	extend: 'Ext.tab.Panel',

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				title: '明细',
				layout: 'fit',
				items: [Ext.create('Business.ExpenseGridPanel')]
			}, {
				title: '按区域汇总',
				layout: 'fit',
				items: []
			}, {
				title: '按客户汇总',
				layout: 'fit',
				items: []
			}, {
				title: '按项目汇总',
				layout: 'fit',
				items: []
			}, {
				title: '区域',
				layout: 'fit',
				items: [Ext.create('Business.AreaGridPanel')]
			}]
		});
		this.callParent();
	}
});
