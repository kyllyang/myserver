Ext.define('Base.gis.thematic.ThematicContainer', {
	extend: 'Ext.container.Container',

	layout: 'fit',

	initComponent: function() {
		Ext.apply(this, {
			items: [Ext.create('Base.gis.thematic.ThematicGridPanel')]
		});
		this.callParent();
	}
});
