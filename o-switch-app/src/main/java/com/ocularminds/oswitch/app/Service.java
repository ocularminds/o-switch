package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.app.model.Cardlex;
import com.ocularminds.oswitch.app.model.Cardless;
import com.ocularminds.oswitch.app.model.Preference;
import com.ocularminds.oswitch.app.model.Preferences;
import com.ocularminds.oswitch.app.model.Transaction;
import com.ocularminds.oswitch.app.model.Transactions;

public interface Service {

    void create(Transaction transaction);

    Transaction find(Transaction transaction);

    void create(Cardlex cardlex);

    Cardlex find(String account, double amount);

    void create(Preference pref);

    Preference find(String account);

}
