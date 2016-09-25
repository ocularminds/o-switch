package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.core.CoreBankingProvider;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

public enum ProcessorType {
    BALANCE {

        @Override
        public ISOMsg process(final CoreBankingProvider provider, Service service, ISOMsg req)
                throws ISOException {
            return new BalanceProcessor(provider, service, req).process();
        }

    },
    BILL {

        @Override
        public ISOMsg process(final CoreBankingProvider provider, Service service, ISOMsg req)
                throws ISOException {
            return req;
        }

    },
    TRANSFER {

        @Override
        public ISOMsg process(final CoreBankingProvider provider, Service service, ISOMsg req)
                throws ISOException {
            return new TransferProcessor(provider, service, req).process();
        }

    };

    public abstract ISOMsg process(CoreBankingProvider provider, Service service, ISOMsg req)
            throws ISOException;

}
