Ext.define('Business.CustomerTraceWindow', {
    extend: 'Ext.window.Window',

    customerId: null,
    customerCompanyName: null,

    title: '跟踪',
    width: 1000,
    height: 600,
    border: false,
    modal: true,
    resizable: false,
    layout: 'fit',

    initComponent: function() {
        this.callParent();

        this.add(Ext.create('Business.CustomerTraceGridPanel', {
            customerId: this.customerId,
            customerCompanyName: this.customerCompanyName
        }));
    }
});
