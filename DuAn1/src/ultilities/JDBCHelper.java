package ultilities;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class JDBCHelper {

    public static PreparedStatement getStatement(String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            Connection cn = DBConnect.getConnection();
            if (sql.trim().startsWith("{")) {
                ps = cn.prepareCall(sql);
            } else {
                ps = cn.prepareStatement(sql);
            }   
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static ResultSet excuteQuery(String sql, Object... args) {
        ResultSet rs = null;        
        try {
            Connection cn = DBConnect.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
                    
        }
        return rs;
    }

    public static Integer excuteUpdate(String sql, Object... args) {
        Integer row = null;
        try {
            Connection cn = DBConnect.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
                    
        }
        return row;
    }

    public static Object excuteValue(String sql, Object... args) {
        try {
            ResultSet rs = excuteQuery(sql, args);
            if (rs.next()) {
                return rs.getObject(1);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
