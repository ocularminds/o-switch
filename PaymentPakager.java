public class PaymentPackager extends ISOBasePackager {

    protected ISOFieldPackager fld[] = {
            /* 0000 */ new IFB_NUMERIC  (  4, "Message Type Indicator", false),
            /* 0001 */ new IFB_BITMAP   ( 16, "Bitmap"),
            /* 0002 */ new IFB_LLLCHAR  (999, "Primary Account number"),
            /* 0003 */ new IFB_NUMERIC  (  6, "Processing Code", true),
            /* 0004 */ new IFB_NUMERIC  ( 12, "Amount, Transaction", true),
            //.....
            /* 0063 */ new IFB_LLLCHAR  (999, "Reserved for national use"),
            /* 0064 */ new IFB_BINARY   ( 20, "Message authentication code field"),
            //.....
            /* 0125 */ new IF_UNUSED    (),
            /* 0126 */ new IF_UNUSED    (),
            /* 0127 */ new IF_UNUSED    (),
            /* 0128 */ new IFB_BINARY   ( 20, "Message authentication code field"),
    };

    public PaymentPackager() {
        super();
        setFieldPackager(fld);
    }
}

paymentServer.addISORequestListener(paymentProcessor);