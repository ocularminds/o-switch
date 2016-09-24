package com.ocularminds.oswitch.app.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public final class JdbcSource {

    private String jndi;
    private DataSource source;
    private static Logger logger = Logger.getLogger(JdbcSource.class.getName());

    public JdbcSource(final DataSource src) {
        this.source = src;
    }

    public JdbcSource(String jnd) {
        try {
            this.source =
                    (DataSource) (new InitialContext()).lookup(String.format("java:/%s",
                            new Object[] {jnd}));
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public DataSource get() throws Exception {
        return this.source;
    }

    public Connection connection() throws Exception {
        return this.get().getConnection();
    }

    public final void close(Connection con, PreparedStatement ps) {
        this.close(con, null, ps, null, null);
    }

    public final void close(Connection con, PreparedStatement ps, ResultSet rs) {
        this.close(con, null, ps, null, rs);
    }

    public final void close(Connection con, CallableStatement cs) {
        this.close(con, null, null, cs, null);
    }

    public final void close(Connection con, CallableStatement cs, ResultSet rs) {
        this.close(con, null, null, cs, rs);
    }

    public final void close(Connection con, Statement s) {
        this.close(con, s, null, null, null);
    }

    public final void close(Connection con, Statement s, ResultSet rs) {
        this.close(con, s, null, null, rs);
    }

    public final void close(Connection con) {
        this.close(con, null, null, null, null);
    }

    public final void close(Connection con, Statement s, PreparedStatement ps,
            CallableStatement cs, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cs != null) cs.close();
            if (con != null) con.close();
            rs = null;
            s = null;
            con = null;
            ps = null;
            cs = null;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error closing Connection ->", ex);
        }
    }

    public static final DataSource create() {
        DataSource src;
        String driver = null;
        String user = null;
        String pass = null;
        String url = null;
        System.out.println("Loading database properties");
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(new File("edu.portal.db.properties")));
            driver = (String) props.get("edu.portal.db.driver");
            user = (String) props.get("edu.portal.db.user");
            pass = (String) props.get("edu.portal.db.pass");
            url = (String) props.get("edu.portal.db.jdbc");
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(driver);
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(pass);
            src = new HikariDataSource(config);
            logger.log(Level.INFO, "Connection Pool created ");
        } catch (Exception ex) {
            String warn = "edu.portal.properties not configured for production.\n";
            logger.log(Level.INFO, "edu.portal.properties not found.");
            logger.log(Level.INFO, "Using defaul H2 In-Memory database...");
            logger.log(Level.INFO, warn);
            driver = "org.h2.Driver";
            url = "jdbc:h2:./edu.portal";
            user = "sa";
            pass = "";
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(driver);
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(pass);
            src = new HikariDataSource(config);
            logger.log(Level.INFO, "Connection Pool created ");
        }
        return src;
    }
}
