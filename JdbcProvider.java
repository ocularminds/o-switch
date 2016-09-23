package com.ocularminds.oswitch.app.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum JdbcProvider {

    ORACLE {

        @Override
        public String date() {
            return "sysdate";
        }

        @Override
        public String concat(String column1, String column2, String separator) {
            return (new StringBuilder()).append("to_char(").append(column1)
                    .append(")||").append(separator).append("||to_char(")
                    .append(column2).append(")").toString();
        }

        @Override
        public String toChar(String column) {
            return (new StringBuilder()).append("to_char(").append(column)
                    .append(")").toString();
        }

        @Override
        public String substring(String d1, int from, int digits) {
            return (new StringBuilder()).append("substr(").append(d1)
                    .append(",").append(from).append(",").append(digits)
                    .append(1).append(")").toString();
        }

        @Override
        public String sysdateTrunc(int from, int digits) {
            return (new StringBuilder()).append("substr(sysdate,").append(from)
                    .append(",").append(digits).append(1).append(")")
                    .toString();
        }

        @Override
        public String dateVal(String d1, int type) {
            String dateMin =
                    (new StringBuilder()).append("to_date('").append(d1)
                            .append(" 00:00', 'MM-DD-YYYY HH24:MI')")
                            .toString();
            String dateMax =
                    (new StringBuilder()).append("to_date('").append(d1)
                            .append(" 23:59', 'MM-DD-YYYY HH24:MI')")
                            .toString();
            return type == 0 ? dateMin : dateMax;
        }

        @Override
        public String dateVal(String d1) {
            return (new StringBuilder()).append("TO_DATE('")
                    .append((new SimpleDateFormat("dd-MM-yyyy")).format(d1))
                    .append("', 'dd-Mon-yyyy HH:MI:SS AM')").toString();
        }

        @Override
        public String dateVal(Date d1) {
            return (new StringBuilder()).append("TO_DATE('")
                    .append((new SimpleDateFormat("dd-MM-yyyy")).format(d1))
                    .append("', 'dd-Mon-yyyy HH:MI:SS AM')").toString();
        }

        @Override
        public String dateDiff(Date d1, Date d2) {
            return (new StringBuilder()).append("(TO_DATE('")
                    .append((new SimpleDateFormat("dd-MM-yyyy")).format(d1))
                    .append("', 'dd-Mon-yyyy HH:MI:SS AM') - TO_DATE('")
                    .append((new SimpleDateFormat("dd-MM-yyyy")).format(d2))
                    .append("', 'dd-Mon-yyyy HH:MI:SS AM'))").toString();
        }

    },
    MSSQL {

        @Override
        public String date() {
            return "getDate()";
        }

        @Override
        public String concat(String column1, String column2, String separator) {
            return (new StringBuilder()).append("convert(varchar,")
                    .append(column1).append(")+ '").append(separator)
                    .append("' +convert(varchar,").append(column2).append(")")
                    .toString();
        }

        @Override
        public String toChar(String column) {
            return (new StringBuilder()).append("convert(varchar,")
                    .append(column).append(")").toString();
        }

        @Override
        public String substring(String d1, int from, int digits) {
            return (new StringBuilder()).append("substring(").append(d1)
                    .append(",").append(from).append(",").append(digits)
                    .append(1).append(")").toString();
        }

        @Override
        public String sysdateTrunc(int from, int digits) {
            return (new StringBuilder()).append("substring(getDate(),")
                    .append(from).append(",").append(digits).append(")")
                    .toString();
        }

        @Override
        public String dateVal(String d1, int type) {
            String ss[] = d1.split("-");
            String dm =
                    (new StringBuilder()).append(ss[1]).append("-")
                            .append(ss[0]).append("-").append(ss[2]).toString();
            String mssql0 =
                    (new StringBuilder()).append("convert(datetime, '")
                            .append(dm).append(" 00:00', 105)").toString();
            String mssql1 =
                    (new StringBuilder()).append("convert(datetime, '")
                            .append(dm).append(" 23:59', 105)").toString();
            return type == 0 ? mssql0 : mssql1;
        }

        @Override
        public String dateVal(String d1) {
            return (new StringBuilder()).append("CONVERT(datetime, '")
                    .append(d1).append("', 105)").toString();
        }

        @Override
        public String dateVal(Date d1) {
            SimpleDateFormat sdo = new SimpleDateFormat("dd-MM-yyyy");
            return (new StringBuilder()).append("CONVERT(datetime, '")
                    .append(sdo.format(d1)).append("', 105)").toString();
        }

        @Override
        public String dateDiff(Date d1, Date d2) {
            return "";
        }

    },
    MYSQL {

        @Override
        public String date() {
            return "now()";
        }

        @Override
        public String concat(String column1, String column2, String separator) {
            return (new StringBuilder()).append("concat(").append(column1)
                    .append(",'").append(separator).append("',")
                    .append(column2).append(")").toString();
        }

        @Override
        public String toChar(String column) {
            return column;
        }

        @Override
        public String substring(String d1, int from, int digits) {
            return "";
        }

        @Override
        public String sysdateTrunc(int from, int digits) {
            return "";
        }

        @Override
        public String dateVal(String d1, int type) {
            String ss[] = d1.split("-");
            String dm =
                    (new StringBuilder()).append(ss[2]).append("-")
                            .append(ss[0]).append("-").append(ss[1]).toString();
            String date0 =
                    (new StringBuilder()).append("'").append(dm)
                            .append(" 00:00'").toString();
            String date1 =
                    (new StringBuilder()).append("'").append(dm)
                            .append(" 23:59'").toString();
            return type == 0 ? date0 : date1;
        }

        @Override
        public String dateVal(String d1) {
            return (new StringBuilder()).append("TO_DATE('").append(d1)
                    .append("', 'dd-Mon-yyyy HH:MI:SS AM')").toString();
        }

        @Override
        public String dateVal(Date d1) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return (new StringBuilder()).append("date '")
                    .append(sdf.format(d1)).append("' ").toString();
        }

        @Override
        public String dateDiff(Date d1, Date d2) {
            return "";
        }

    },
    POSTGRES {

        @Override
        public String date() {
            return "current_date";
        }

        @Override
        public String concat(String column1, String column2, String separator) {
            return (new StringBuilder()).append("to_char(").append(column1)
                    .append(")||").append(separator).append("||to_char(")
                    .append(column2).append(")").toString();
        }

        @Override
        public String toChar(String column) {
            return (new StringBuilder()).append("to_char(").append(column)
                    .append(")").toString();
        }

        @Override
        public String substring(String d1, int from, int digits) {
            return (new StringBuilder()).append("substring(to_char(")
                    .append(d1).append(", 'DD-MM-YYYY') from ").append(from)
                    .append(" for ").append(digits).append(")").toString();
        }

        @Override
        public String sysdateTrunc(int from, int digits) {
            return (new StringBuilder())
                    .append("substring(to_char(current_date, 'DD-MM-YYYY') from ")
                    .append(from).append(" for ").append(digits).append(")")
                    .toString();
        }

        @Override
        public String dateVal(String d1, int type) {
            String ss[] = d1.split("-");
            String dm =
                    (new StringBuilder()).append(ss[1]).append("-")
                            .append(ss[0]).append("-").append(ss[2]).toString();
            String postgres0 =
                    (new StringBuilder()).append("date '").append(d1)
                            .append("' ").toString();
            String postgres1 =
                    (new StringBuilder()).append("date '").append(d1)
                            .append("' ").toString();
            return type == 0 ? postgres0 : postgres1;
        }

        @Override
        public String dateVal(String d1) {
            return (new StringBuilder()).append("date '").append(d1)
                    .append("' ").toString();
        }

        @Override
        public String dateVal(Date d1) {
            return "";
        }

        @Override
        public String dateDiff(Date d1, Date d2) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return (new StringBuilder()).append("(date '")
                    .append(sdf.format(d1)).append("' - date '")
                    .append(sdf.format(d2)).append("')").toString();
        }

    };

    private JdbcProvider(String s, String provida) {
        provider = provida;
    }

    public static JdbcProvider parse(String cod) {
        JdbcProvider provider = MSSQL;
        JdbcProvider ajdbcprovider[] = values();
        int i = ajdbcprovider.length;
        int j = 0;
        do {
            if (j >= i) break;
            JdbcProvider e = ajdbcprovider[j];
            if (cod.trim() == e.provider) {
                provider = e;
                break;
            }
            j++;
        } while (true);
        return provider;
    }

    public abstract String date();

    public abstract String concat(String s, String s1, String s2);

    public abstract String toChar(String s);

    public abstract String substring(String s, int i, int j);

    public abstract String sysdateTrunc(int i, int j);

    public abstract String dateVal(String s, int i);

    public abstract String dateVal(String s);

    public abstract String dateVal(Date date1);

    public abstract String dateDiff(Date date1, Date date2);

    private String provider;
    public static final int MIN = 0;
    public static final int MAX = 1;
}
