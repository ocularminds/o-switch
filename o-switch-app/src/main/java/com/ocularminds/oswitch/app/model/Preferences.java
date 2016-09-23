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

public class Preferences {

    private JdbcSource source;
    private Logger logger;

    public Preferences() {
        this(new JdbcSource("edu_portal"));
    }

    public Preferences(JdbcSource src) {
        logger = Logger.getLogger(Preferences.class.getName());
        source = src;
    }

    public Preference get(String id) {
        Preference preference = new Preference();
        try {
            preference =
                    new JdbcSession(source.get())
                            .sql(new StringBuilder("select xid, acct_no, amount, lang, token ")
                                    .append("from os_pref where subid = ?").toString()).set(id)
                            .select(new Outcome<Preference>() {

                                public Preference handle(ResultSet rs, Statement s)
                                        throws SQLException {
                                    if (rs.next()) {
                                        return read(rs);
                                    } else {
                                        return null;
                                    }
                                }
                            });
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error selecting preference ", ex);
        }
        return preference;
    }

    public Preference find(String name) {
        Preference preference = null;
        try {
            preference =
                    new JdbcSession(source.get())
                            .sql(new StringBuilder("select xid, acct_no, amount, lang, token ")
                                    .append("from os_pref where subid = ?").toString()).set(name)
                            .select(new Outcome<Preference>() {

                                public Preference handle(ResultSet rs, Statement s)
                                        throws SQLException {
                                    if (rs.next()) {
                                        return read(rs);
                                    } else {
                                        return null;
                                    }
                                }
                            });
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error selecting preference ", ex);
        }
        return preference;
    }

    public void add(Preference preference) {
        try {
            new JdbcSession(source.get())
                    .sql(new StringBuilder("INSERT INTO os_pref(")
                            .append("xid, acct_no, amount, lang, token")
                            .append(") VALUES(?,?,?,?,?)").toString()).set(new Identifier().next())
                    .set(preference.getAccount()).set(preference.getAmount())
                    .set(preference.getLanguage()).set(preference.getToken()).insert(Outcome.VOID);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error saving preference!", ex);
        }
    }

    public void update(Preference preference) {
        try {
            new JdbcSession(source.get())
                    .sql("UPDATE os_pref set name = ?, " + "deptid = ?, facid = ?, term_id = ?, "
                            + "classid = ?, details = ?, units= ? " + "where subid = ?")
                    .set(preference.getAccount()).set(preference.getAmount())
                    .set(preference.getLanguage()).set(preference.getToken())
                    .set(preference.getId()).update(Outcome.VOID);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error Occured!", ex);
        }
    }

    public final List iterate() {
        List<Preference> records = new ArrayList<>();
        try {
            records =
                    new JdbcSession(source.get()).sql(
                            "select subid, code, name, " + "deptid, facid, term_id, classid,"
                                    + "details, units from os_pref").select(
                            new Outcome<List<Preference>>() {
                                @Override
                                public List<Preference> handle(ResultSet rs, Statement st)
                                        throws SQLException {
                                    List<Preference> records = new ArrayList<>();
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

    public Preference read(ResultSet rs) {
        Preference preference = new Preference();
        try {
            String id = rs.getString("pref_id");
            String account = rs.getString("acct_no");
            double amount = rs.getDouble("amount");
            String language = rs.getString("lang");
            preference.setId(id);
            preference.setAccount(account);
            preference.setAmount(amount);
            preference.setLanguage(language);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return preference;
    }
}
