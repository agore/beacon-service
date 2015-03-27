package org.bitxbit.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BeaconEventDao {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/beacon", "aditya", "");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long setBeaconEvent(BeaconEvent beaconEvent) {
        Connection con = getConnection();
        if (con == null) return 0;

        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("insert into beacon.beaconevent (`b_id_1`, `b_id_2`, `b_id_3`, `visit`, `conversion`) values (?, ?, ?, ?, ?)");
            ps.setString(1, beaconEvent.getBeaconId1());
            ps.setInt(2, beaconEvent.getBeaconId2());
            ps.setInt(3, beaconEvent.getBeaconId3());
            ps.setBoolean(4, beaconEvent.getVisit());
            ps.setBoolean(5, beaconEvent.getConversion());
            int count = ps.executeUpdate();
//            if (count != 0) {
//                stmt = con.createStatement();
//                rs = stmt.executeQuery("select LAST_INSERT_ID()");
//                return rs.getLong(0);
//            }
            return 1; //TODO
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close();}catch(Exception e) {}
            try { if (con != null) con.close();}catch(Exception e) {}
        }

        return 0;
    }

    public int getNumVisits() {
        Connection con = getConnection();
        if (con == null) return 0;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select count(*) from beaconevent where visit=1");
            rs.next();
            int c = rs.getInt(0);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close();}catch(Exception e) {}
            try { if (stmt != null) stmt.close();}catch(Exception e) {}
            try { if (con != null) con.close();}catch(Exception e) {}
        }

        return 0;
    }
}
