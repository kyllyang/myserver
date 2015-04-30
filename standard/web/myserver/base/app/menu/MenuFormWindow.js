Ext.define('Base.app.menu.MenuFormWindow', {
	extend: 'Ext.window.Window',

	menuTreePanel: null,

	title: '菜单信息',
	width: 800,
	height: 450,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Base.app.menu.MenuCreateForm', {
			menuTreePanel: this.menuTreePanel
		}));
	}
});
