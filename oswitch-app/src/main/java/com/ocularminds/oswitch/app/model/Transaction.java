package com.ocularminds.oswitch.app.model;

import com.ocularminds.oswitch.app.jdbc.JdbcSource;
import com.ocularminds.oswitch.core.Entry;

public class Transaction extends Entry {

    private Channel channel;

    // private String

    public enum Channel {
        ATM, WEB, POS, API;
    }

    public Transaction() {
        super();
    }

    public Transaction(final Entry entry) {
        super(entry.getDebit(), entry.getCredit(), entry.getAmount());
        setAccount(entry.getAccount());
        setType(entry.getType());
        setDate(entry.getDate());
        setNarration(entry.getNarration());
    }

    public Channel getChannel() {
        return this.channel;
    }

    public final void setChannel(final Channel chan) {
        this.channel = chan;
    }
}
