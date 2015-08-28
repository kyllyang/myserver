Ext.define('Base.ux.HelpQtip', {
	extend: 'Ext.AbstractPlugin',

	init: function (cmp) {
		this.setCmp(cmp);

		cmp.on('beforerender', function () {
			var fields = cmp.query('field[qtip]');
			for (var i = 0; i < fields.length; i++) {
				fields[i].on('render', this.register, this);
			}
		}, this);
	},

	register: function (field) {
		var obj = {
			target: field.id
		};

		if (typeof field.qtip === 'string') {
			obj.text = field.qtip;
		} else if (typeof field.qtip === 'object') {
			Ext.applyIf(obj, field.qtip);
		}

		Ext.tip.QuickTipManager.register(obj);
	}
});
