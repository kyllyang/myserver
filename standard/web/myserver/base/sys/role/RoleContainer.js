Ext.define('Base.sys.role.RoleContainer', {
	extend: 'Ext.container.Container',

	layout: 'border',

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Base.sys.role.RoleGridPanel', {
			region: 'center'
		}));
		this.add(Ext.create('Base.sys.role.ModuleTreePanel', {
			region: 'east',
			split: true,
			collapsible: true,
			width: myServer.getWidth() / 6
		}));
	}
});
