package kybmig.ssm;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class MyJDBC {
    public static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    public static MysqlDataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("12345");
        dataSource.setServerName("127.0.0.1");
        dataSource.setDatabaseName("ssm");

        // 用来设置时区和数据库连接的编码
        try {
            dataSource.setCharacterEncoding("UTF-8");
            dataSource.setServerTimezone("UTC");

            Utility.log("url: %s", dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    public static void addBySQL(String content) {
        MysqlDataSource ds = getDataSource();
        String sql = String.format("INSERT INTO `ssm`.`Todo` (content) VALUES ('%s');", content);

        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void selectBySQL() {
        MysqlDataSource ds = getDataSource();
        String sql = String.format("SELECT * FROM `ssm`.`Todo`");

        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            /*rs.next();
            log("id: %s", rs.getInt("id"));
            log("content: %s", rs.getString("content"));

            rs.next();
            log("id: %s", rs.getInt("id"));
            log("content: %s", rs.getString("content"));*/

            while (rs.next()) {
                log("id: %s", rs.getInt("id"));
                log("content: %s", rs.getString("content"));

            }
            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void selectByIdSQL(String id) {
        MysqlDataSource ds = getDataSource();
        String sql = String.format("SELECT * FROM `ssm`.`Todo` WHERE id = %s", id);

        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                log("id: %s", rs.getInt("id"));
                log("content: %s", rs.getString("content"));

            }
            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void selectByIdSQLSafe(String id) {
        MysqlDataSource ds = getDataSource();
        String sql = "SELECT * FROM `ssm`.`Todo` WHERE id = ?";

        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                log("id: %s", rs.getInt("id"));
                log("content: %s", rs.getString("content"));

            }
            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        addBySQL("todo3");
//        selectBySQL();
//        selectByIdSQL("1 or true");
        selectByIdSQLSafe("1 or true");
    }


}
