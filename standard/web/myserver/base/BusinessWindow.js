Ext.define('Base.BusinessWindow', {
	extend: 'Ext.window.Window',

	title: null,
	menuId: null,
	funcCode: null,

	x: myServer.getWidth() * 0.2,
	width: myServer.getWidth() * 0.7,
	height: myServer.getHeight() * 0.6,
	border: false,
	layout: 'fit',
	minimizable: true,

	initComponent: function() {
		this.callParent();

		var businessWindowMap = myServer.getBusinessWindowMap();
		businessWindowMap.add(this.menuId, this);
		var infomationNotification = myServer.getInfomationNotification();
		infomationNotification.refreshWindowButton();

		this.add(Ext.create(this.funcCode.className, this.funcCode.config));

		this.on('minimize', function(win, eOpts) {
			win.hide();
			infomationNotification.setWindowCheckItemChecked(this.menuId, false);
		}, this);
		this.on('show', function(win, eOpts) {
			infomationNotification.setWindowCheckItemChecked(this.menuId, true);
		}, this);
		this.on('close', function(panel, eOpts) {
			businessWindowMap.removeAtKey(this.menuId);
			infomationNotification.refreshWindowButton();
		}, this);
	}
});
