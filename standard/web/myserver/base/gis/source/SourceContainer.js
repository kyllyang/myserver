Ext.define('Base.gis.source.SourceContainer', {
	extend: 'Ext.container.Container',

	layout: 'fit',

	initComponent: function() {
		Ext.apply(this, {
			items: [Ext.create('Base.gis.source.SourceGridPanel')]
		});
		this.callParent();
	}
});
