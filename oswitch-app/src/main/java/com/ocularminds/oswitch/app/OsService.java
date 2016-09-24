package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.app.jdbc.JdbcSource;
import com.ocularminds.oswitch.app.model.Cardlex;
import com.ocularminds.oswitch.app.model.Cardless;
import com.ocularminds.oswitch.app.model.Preference;
import com.ocularminds.oswitch.app.model.Preferences;
import com.ocularminds.oswitch.app.model.Transaction;
import com.ocularminds.oswitch.app.model.Transactions;

public class OsService implements Service {

    JdbcSource source;

    public OsService(final JdbcSource src) {
        this.source = src;
    }

    public void create(final Transaction transaction) {
        new Transactions(this.source).add(transaction);
    }


    public Transaction find(final Transaction tran) {
        return new Transactions(this.source).find(tran.getId());
    }

    public void create(Cardlex cardlex) {
        new Cardless(this.source).add(cardlex);
    }


    public Cardlex find(String account, double amount) {
        return new Cardless(this.source).find(account, amount);
    }

    public void create(final Preference pref) {
        new Preferences(this.source).add(pref);
    }

    public Preference find(String account) {
        return new Preferences(this.source).find(account);
    }

}
