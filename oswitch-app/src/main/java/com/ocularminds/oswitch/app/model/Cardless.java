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

public class Cardless {

    private JdbcSource source;
    final Logger logger = Logger.getLogger(Cardless.class.getName());

    public Cardless() {
        this(new JdbcSource("edu_portal"));
    }

    public Cardless(JdbcSource src) {
        this.source = src;
    }

    public Cardlex get(String id) {
        Cardlex cardlex = new Cardlex();
        try {
            cardlex =
                    new JdbcSession(source.get())
                            .sql(new StringBuilder("select xid, acct_no, code, amount, xdate ")
                                    .append("from os_cardlex where xid = ?").toString()).set(id)
                            .select(new Outcome<Cardlex>() {

                                public Cardlex handle(ResultSet rs, Statement s)
                                        throws SQLException {
                                    if (rs.next()) {
                                        return read(rs);
                                    } else {
                                        return null;
                                    }
                                }
                            });
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error selecting cardlex ", ex);
        }
        return cardlex;
    }

    public Cardlex find(String account, double amount) {
        Cardlex cardlex = null;
        try {
            cardlex =
                    new JdbcSession(source.get())
                            .sql(new StringBuilder("select xid, acct_no, code, amount, xdate ")
                                    .append("from os_cardlex_tran where acct_no = ? ")
                                    .append("and amount = ?").toString()).set(account).set(amount)
                            .select(new Outcome<Cardlex>() {

                                public Cardlex handle(ResultSet rs, Statement s)
                                        throws SQLException {
                                    if (rs.next()) {
                                        return read(rs);
                                    } else {
                                        return null;
                                    }
                                }
                            });
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error selecting cardlex ", ex);
        }
        return cardlex;
    }

    public String add(Cardlex cardlex) {
        String id = null;
        try {
            id = new Identifier().next();
            new JdbcSession(source.get())
                    .sql(new StringBuilder("INSERT INTO os_cardlex(")
                            .append("xid, acct_no, code, amount, xdate")
                            .append(") VALUES(?,?,?,?,?)").toString()).set(new Identifier().next())
                    .set(id).set(cardlex.getAccount()).set(cardlex.getToken())
                    .set(cardlex.getAmount())
                    .set(new java.sql.Date(new java.util.Date().getTime())).insert(Outcome.VOID);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error saving cardlex!", ex);
        }
        return id;
    }

    public final List<Cardlex> iterate() {
        List<Cardlex> records = new ArrayList<>();
        try {
            records =
                    new JdbcSession(source.get()).sql("xid, acct_no, code, amount, xdate").select(
                            new Outcome<List<Cardlex>>() {
                                @Override
                                public List<Cardlex> handle(ResultSet rs, Statement st)
                                        throws SQLException {
                                    List<Cardlex> records = new ArrayList<>();
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

    public Cardlex read(ResultSet rs) {
        Cardlex cardlex = new Cardlex();
        try {
            String id = rs.getString("xid");
            String code = rs.getString("code");
            String account = rs.getString("acct_no");
            double amount = rs.getDouble("amount");
            Date date = rs.getDate("xdate");
            cardlex.setId(id);
            cardlex.setToken(code);
            cardlex.setDate(date);
            cardlex.setAccount(account);
            cardlex.setAmount(amount);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return cardlex;
    }
}
