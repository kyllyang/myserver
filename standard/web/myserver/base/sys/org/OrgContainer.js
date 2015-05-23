Ext.define('Base.sys.org.OrgContainer', {
	extend: 'Ext.container.Container',

	initComponent: function() {
		var departmentTreePanel = Ext.create('Base.sys.org.DepartmentTreePanel', {
			region: 'west',
			split: true,
			collapsible: true,
			width: myServer.getWidth() / 8
		});
		var employeeGridPanel = Ext.create('Base.sys.org.EmployeeGridPanel', {
			departmentTreePanel: departmentTreePanel,
			region: 'center'
		});
		var roleTreePanel = Ext.create('Base.sys.org.RoleTreePanel', {
			region: 'east',
			split: true,
			collapsible: true,
			width: myServer.getWidth() / 8
		});

		Ext.apply(this, {
			layout: 'border',
			items: [departmentTreePanel, employeeGridPanel, roleTreePanel]
		});
		this.callParent();
	}
});
