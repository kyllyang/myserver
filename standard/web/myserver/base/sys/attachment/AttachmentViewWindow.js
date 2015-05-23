Ext.define('Base.sys.attachment.AttachmentViewWindow', {
	extend: 'Ext.window.Window',

	entityId: null,

	initComponent: function() {
		Ext.apply(this, {
			title: '附件预览',
			width: 800,
			height: 600,
			border: false,
			modal: true,
			resizable: false,
			layout: 'fit',
			html: "<img src=\'" + ctx + "/sys/attachment/view.ctrl?qc.id=" + this.entityId + "\'/>"
		});
		this.callParent();
	}
});
