Ext.define('Base.sys.dict.DictContainer', {
	extend: 'Ext.container.Container',

	layout: 'border',

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Base.sys.dict.DictTreePanel', {
			region: 'west',
			split: true,
			collapsible: true,
			width: myServer.getWidth() / 6
		}));
		this.add(Ext.create('Base.sys.dict.DictItemGridPanel', {
			region: 'center'
		}));
	}
});
