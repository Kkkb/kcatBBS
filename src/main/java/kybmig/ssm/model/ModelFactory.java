package kybmig.ssm.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelFactory {
    static String dbPath = "db";

    public static <T> ArrayList<T> load(String className, int fieldCount, Deserializer<T> deserializer) {
        String fileName = String.format("%s/%s.txt", dbPath,className);
        String data = Utils.load(fileName);
        if (data.strip().length() == 0) {
            return new ArrayList<>();
        } else {
            String[] modelsDataRaw = data.split("\n");
            List<String> modelsData = Arrays.asList(modelsDataRaw);

            ArrayList<T> models = new ArrayList<>();
            for (int i = 0; i < modelsData.size(); i = i + fieldCount) {
                List<String> modelData = modelsData.subList(i, i + fieldCount);
                Utils.log("序列化前的数据 <%s>", modelsData);
                T model = deserializer.deserialize(modelData);
                Utils.log("序列化后的数据 <%s>", model);
                models.add(model);
            }
            return models;
        }
    }

    public static <T> void save(String className, ArrayList<T> list, Serializer<T> serializer) {
        StringBuilder content = new StringBuilder();
        for (T m: list) {
            ArrayList<String> lines = serializer.serialize(m);
             for (String line: lines) {
                 content.append(line);
                 content.append("\n");
             }
        }
        String fileName = String.format("%s/%s.txt", dbPath,className);
        Utils.save(fileName, content.toString());
    }
}
