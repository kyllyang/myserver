Ext.define('Sys.dict.DictContainer', {
	extend: 'Ext.container.Container',

	layout: 'border',

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Sys.dict.DictTreePanel', {
			region: 'west',
			split: true,
			collapsible: true,
			width: myServer.getWidth() / 6
		}));
		this.add(Ext.create('Sys.dict.DictItemGridPanel', {
			region: 'center'
		}));
	}
});
