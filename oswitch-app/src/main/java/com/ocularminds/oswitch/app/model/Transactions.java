package com.ocularminds.oswitch.app.model;

import com.jcabi.jdbc.*;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ocularminds.oswitch.common.Identifier;
import com.ocularminds.oswitch.app.jdbc.JdbcSource;

public class Transactions {

    private JdbcSource source;
    private Logger logger;

    public Transactions() {
        this(new JdbcSource("edu_portal"));
    }

    public Transactions(JdbcSource src) {
        this.logger = Logger.getLogger(Transactions.class.getName());
        this.source = src;
    }

    public Transaction get(String id) {
        Transaction transaction = new Transaction();
        try {
            transaction =
                    new JdbcSession(source.get())
                            .sql(new StringBuilder("select tran_id, ref,typ,channel, ")
                                    .append("debit, credit, amount, narration,tran_dt, ")
                                    .append("beneficiary")
                                    .append("from os_trans where tran_id = ?").toString()).set(id)
                            .select(new Outcome<Transaction>() {
                                public Transaction handle(ResultSet rs, Statement s)
                                        throws SQLException {
                                    if (rs.next()) {
                                        return read(rs);
                                    } else {
                                        return null;
                                    }
                                }
                            });
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error selecting transaction ", ex);
        }
        return transaction;
    }

    public Transaction find(String name) {
        Transaction transaction = null;
        try {
            transaction =
                    new JdbcSession(source.get())
                            .sql(new StringBuilder("select tran_id, ref,typ,channel, ")
                                    .append("debit, credit, amount, narration,tran_dt, ")
                                    .append("beneficiary")
                                    .append("from os_trans where tran_id = ?").toString())
                            .set(name).select(new Outcome<Transaction>() {

                                public Transaction handle(ResultSet rs, Statement s)
                                        throws SQLException {
                                    if (rs.next()) {
                                        return read(rs);
                                    } else {
                                        return null;
                                    }
                                }
                            });
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error selecting transaction ", ex);
        }
        return transaction;
    }

    public void add(Transaction transaction) {
        try {
            new JdbcSession(source.get())
                    .sql(new StringBuilder("INSERT INTO os_tran(")
                            .append("tran_id, ref,typ,channel, ")
                            .append("debit, credit, amount, narration,tran_dt, ")
                            .append("beneficiary) VALUES(").append("?,?,?,?,?,?,?,?,?)").toString())
                    .set(new Identifier().next()).set(transaction.getReference())
                    .set(transaction.getType().toString()).set(transaction.getChannel().toString())
                    .set(transaction.getDebit()).set(transaction.getCredit())
                    .set(transaction.getAmount()).set(transaction.getNarration())
                    .set(new java.sql.Date(new java.util.Date().getTime()))
                    .set(transaction.getBeneficiary()).insert(Outcome.VOID);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error saving transaction!", ex);
        }
    }

    public final List<Transaction> iterate() {
        List<Transaction> records = new ArrayList<>();
        try {
            records =
                    new JdbcSession(source.get()).sql(
                            new StringBuilder("select tran_id, ref,typ,channel, ")
                                    .append("debit, credit, amount, narration,tran_dt, ")
                                    .append("beneficiary").append("from os_trans").toString())
                            .select(new Outcome<List<Transaction>>() {
                                @Override
                                public List<Transaction> handle(final ResultSet rs,
                                        final Statement st) throws SQLException {
                                    final List<Transaction> records = new ArrayList<>();
                                    while (rs.next()) {
                                        records.add(read(rs));
                                    }
                                    return records;
                                }
                            });
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public Transaction read(final ResultSet rs) {
        Transaction transaction = new Transaction();
        try {
            String id = rs.getString("tran_id");
            String ref = rs.getString("ref");
            String type = rs.getString("typ");
            String channel = rs.getString("channel");
            String debit = rs.getString("debit");
            String credit = rs.getString("credit");
            double amount = rs.getDouble("amount");
            String narration = rs.getString("narration");
            Date date = rs.getDate("tran_dt");
            String beneficiary = rs.getString("beneficiary");
            transaction.setId(id);
            transaction.setReference(ref);
            // transaction.setType(Transaction.Type.valueOf(type));
            transaction.setChannel(Transaction.Channel.valueOf(channel));
            transaction.setDebit(debit);
            transaction.setCredit(credit);
            transaction.setAmount(amount);
            transaction.setNarration(narration);
            transaction.setDate(date);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return transaction;
    }
}
