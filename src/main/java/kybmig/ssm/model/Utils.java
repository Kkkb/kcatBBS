package kybmig.ssm.model;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Utils {
    static final String databaseName = "java";

    public static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }


    // jar 用
    public static InputStream fileStream(String path) throws FileNotFoundException {
        String resource = String.format("%s.class", Utils.class.getSimpleName());
        Utils.log("resource %s", resource);
        Utils.log("resource path %s", Utils.class.getResource(""));
        var res = Utils.class.getResource(resource);
        if (res != null && res.toString().startsWith("jar:")) {
            // 打包后, templates 放在 jar 包的根目录下, 要加 / 才能取到
            // 不加 / 就是从 类的当前包目录下取
            path = String.format("/%s", path);
            InputStream is = Utils.class.getResourceAsStream(path);
            if (is == null) {
                throw new FileNotFoundException(String.format("在 jar 里面找不到 %s", path));
            } else {
                return is;
            }
        } else {
            path = String.format("build/resources/main/%s", path);
            return new FileInputStream(path);
        }
    }

    public static void save(String path, String data) {
        try (FileOutputStream os = new FileOutputStream(path)) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            String s = String.format("Save file <%s> error <%s>", path, e);
            throw new RuntimeException(s);
        }
    }

    public static String load(String path) {
        String content;
        try (FileInputStream is = new FileInputStream(path)) {
            content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            String s = String.format("Load file <%s> error <%s>", path, e);
            throw new RuntimeException(s);
        }
        return content;
    }

    public static String html(String htmlName) {
        String dir = "templates";
        String path = dir + "/" + htmlName;
        byte[] body;
          try(InputStream is = fileStream(path)){
            body = is.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            body = new byte[1];
        }

        String r = new String(body);
        return r;
    }



    public static MysqlDataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123");
        dataSource.setServerName("127.0.0.1");
        return dataSource;
    }

    public static MysqlDataSource getDataSourceWithDB() {
        MysqlDataSource ds = getDataSource();
        ds.setDatabaseName(databaseName);
        return ds;
    }
}
