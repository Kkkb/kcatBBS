package kybmig.ssm;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    private static Logger logger = LoggerFactory.getLogger(Utility.class);

    static public void log(String format, Object...args) {
        logger.info(String.format(format, args));
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

    public static String formattedTime(Long unixTime) {
        // 先创建 unix 时间
        Date date = new Date(unixTime * 1000);

        // 将 unix 时间 转成相应格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);

        return dateString;
    }

    public static void ensure(boolean condition, String message) {
        if (!condition) {
            log("%s", message);
        } else {
            log("测试成功");
        }
    }

}
