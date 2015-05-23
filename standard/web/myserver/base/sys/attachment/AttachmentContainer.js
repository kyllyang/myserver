Ext.define('Base.sys.attachment.AttachmentContainer', {
	extend: 'Ext.container.Container',

	initComponent: function() {
		Ext.apply(this, {
			layout: 'fit',
			items: [Ext.create('Base.sys.attachment.AttachmentGridPanel')]
		});
		this.callParent();
	}
});
