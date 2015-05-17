Ext.define('Base.sys.org.OrgContainer', {
	extend: 'Ext.container.Container',

	layout: 'border',

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Base.sys.org.OrgTreePanel', {
			region: 'west',
			split: true,
			collapsible: true,
			width: (myServer.width - myServer.getMenuTreePanel().getWidth()) / 3
		}));
		this.add(Ext.create('Base.sys.org.OrgTabPanel', {
			region: 'center'
		}));
	}
});
