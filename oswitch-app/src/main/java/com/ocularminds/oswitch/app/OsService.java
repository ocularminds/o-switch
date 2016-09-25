package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.app.jdbc.JdbcSource;
import com.ocularminds.oswitch.app.model.Cardlex;
import com.ocularminds.oswitch.app.model.Cardless;
import com.ocularminds.oswitch.app.model.Preference;
import com.ocularminds.oswitch.app.model.Preferences;
import com.ocularminds.oswitch.app.model.Transaction;
import com.ocularminds.oswitch.app.model.Transactions;

public class OsService implements Service {

    Transactions transactions;
    Cardless cardless;
    Preferences preferences;

    public OsService(final JdbcSource src) {
        this(new Transactions(src), new Cardless(src), new Preferences(src));
    }

    public OsService(final Transactions trans, final Cardless cads, final Preferences prefs) {
        this.transactions = trans;
        this.cardless = cads;
        this.preferences = prefs;
    }

    public void create(final Transaction transaction) {
        this.transactions.add(transaction);
    }


    public Transaction find(final Transaction tran) {
        return this.transactions.find(tran.getId());
    }

    public void create(Cardlex cardlex) {
        this.cardless.add(cardlex);
    }


    public Cardlex find(String account, double amount) {
        return this.cardless.find(account, amount);
    }

    public void create(final Preference pref) {
        this.preferences.add(pref);
    }

    public Preference find(String account) {
        return this.preferences.find(account);
    }

}
