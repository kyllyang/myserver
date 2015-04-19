Ext.define('Base.BusinessWindow', {
	extend: 'Ext.window.Window',

	width: myServer.getWidth() * 0.6,
	height: myServer.getHeight() * 0.6,
	border: false,
	layout: 'fit',
	minimizable: true,

	title: null,
	menuId: null,
	funcCode: null,

	listeners: {
		minimize: function(win, eOpts) {
			win.hide();
			myServer.getInfomationNotification().setWindowCheckItemChecked(this.menuId, false);
		},
		show: function(win, eOpts) {
			myServer.getInfomationNotification().setWindowCheckItemChecked(this.menuId, true);
		},
		close: function(panel, eOpts) {
			myServer.getBusinessWindowMap().removeAtKey(this.menuId);
			myServer.getInfomationNotification().refreshWindowButton();
		},
		scope: this
	},

	initComponent: function() {
		this.callParent();

		myServer.getBusinessWindowMap().add(this.menuId, this);
		myServer.getInfomationNotification().refreshWindowButton();

		this.add(Ext.create(this.funcCode.className, this.funcCode.config));
	}
});
