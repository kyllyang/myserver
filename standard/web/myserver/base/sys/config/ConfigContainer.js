Ext.define('Base.sys.config.ConfigContainer', {
	extend: 'Ext.container.Container',

	initComponent: function() {
		Ext.apply(this, {
			layout: 'fit',
			items: [Ext.create('Base.sys.config.ConfigGridPanel')]
		});
		this.callParent();
	}
});
