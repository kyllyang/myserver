Ext.define('Base.app.menu.MenuFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,

	title: '菜单信息',
	width: 600,
	height: 240,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	menuTreePanel: null,

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Base.app.menu.MenuForm', {
			menuTreePanel: this.menuTreePanel
		}));
	}
});
