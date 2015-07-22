Ext.define('Business.ProductTabPanel', {
	extend: 'Ext.tab.Panel',

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			items: [{
				title: '厂商',
				layout: 'fit',
				border: false,
				items: [Ext.create('Business.VendorGridPanel')]
			}, {
				title: '设备',
				layout: 'fit',
				border: false,
				items: [Ext.create('Business.ProductGridPanel')]
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
