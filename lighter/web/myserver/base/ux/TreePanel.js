Ext.define("Base.ux.TreePanel", {
	extend: 'Ext.tree.Panel',

	initComponent: function() {
		this.callParent();

		this.on('checkchange', function(node, checked, eOpts) {
			node.eachChild(function(child) {
				this.traverseChild(child, checked);
			}, this);
			this.traverseParent(node);
		}, this);
	},
	traverseChild: function(node, checked) {
		node.set('checked', checked);
		if (node.isNode) {
			node.eachChild(function(child) {
				this.traverseChild(child, checked);
			}, this);
		}
	},
	traverseParent: function(node) {
		if (!Ext.isEmpty(node.parentNode)) {
			node.parentNode.set('checked', this.nodeChecked(node.parentNode));
			this.traverseParent(node.parentNode);
		}
	},
	nodeChecked: function(node) {
		var checked = true;
		Ext.Array.each(node.childNodes, function(value) {
			if (!value.data.checked) {
				checked = false;
				return false;
			}
		}, this);
		return checked;
	},
});
