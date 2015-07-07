Ext.define('Business.UserAreaTreePanel', {
	extend: 'Ext.tree.Panel',

	initComponent: function() {
		Ext.define('TreeModel', {
			extend: 'Ext.data.Model',
			fields: ['id', 'text']
		});

		Ext.apply(this, {
			itemId: 'roleTreePanel',
			autoScroll: true,
			useArrows: true,
			store: Ext.create('Ext.data.TreeStore', {
				root: {
					text: '吉林省汇旺科技有限公司',
					expanded: true,
					children: [{
						text: '郑忠伟',
						leaf: false,
						expanded: true,
						children: [{
							text: '长春市',
							leaf: true
						}, {
							text: '松原市',
							leaf: true
						}]
					}, {
						text: '孙庆福',
						leaf: false,
						expanded: true,
						children: [{
							text: '长春市',
							leaf: true
						}, {
							text: '通化市',
							leaf: true
						}]
					}]
				}
			})
		});
		this.callParent();
	}
});
