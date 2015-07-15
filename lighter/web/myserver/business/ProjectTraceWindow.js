Ext.define('Business.ProjectTraceWindow', {
	extend: 'Ext.window.Window',

	projectId: null,
	projectName: null,

	title: '跟踪',
	width: 1000,
	height: 600,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Business.ProjectTraceGridPanel', {
			projectId: this.projectId,
			projectName: this.projectName
		}));
	}
});
