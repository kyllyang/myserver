Ext.onReady(function() {
	myServer.setWidth(window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth);
	myServer.setHeight(window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight);

	myServer.businessWindowMap = new Ext.util.HashMap();

	myServer.setViewport(Ext.create('Ext.container.Viewport', {
		renderTo: Ext.getBody(),
		layout: 'fit',
		items: [
			Ext.create('Base.gis.MapContainer')
		]
	}));

	myServer.setInfomationNotification(Ext.create('Base.InfomationNotification').show());
});
