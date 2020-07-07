package kybmig.ssm.model;

import java.util.List;

public interface Deserializer <T> {
    T deserialize(List<String> modelData);
}
