Ext.define('Base.sys.dict.DictContainer', {
	extend: 'Ext.container.Container',

	initComponent: function() {
		Ext.apply(this, {
			layout: 'border',
			items: [Ext.create('Base.sys.dict.DictTreePanel', {
				region: 'west',
				split: true,
				collapsible: true,
				width: myServer.getWidth() / 6
			}), Ext.create('Base.sys.dict.DictItemGridPanel', {
				region: 'center'
			})]
		});
		this.callParent();
	}
});
