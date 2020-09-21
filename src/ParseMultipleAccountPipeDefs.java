import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * Created by mayankthirani on 12/26/19.
 */
public class ParseMultipleAccountPipeDefs {

    private static final TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE =
            new TypeReference<Map<String, Object>>() {
            };

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URI url = URI.create
                    ("file:///Users/mayankthirani/Downloads/pipedefsrt_multiple_account.json");
            Map<String, Object> readJSON = objectMapper.readValue(url.toURL(), MAP_TYPE_REFERENCE);
            Map<String, Object> account_defs = (Map<String, Object>) readJSON.get("account_defs");
            System.out.println(account_defs);
            String account_stringValue = objectMapper.writeValueAsString(account_defs);
            System.out.println(account_stringValue);

            account_defs.forEach((eachAcctDefKey, eachAcctDefValue) -> {
                Map<String, Object> readPropMap = (Map<String, Object>) ((Map<String, Object>)
                        eachAcctDefValue).get("property_map");
                Map<String, Object> settingsEachProp = (Map<String, Object>) readPropMap.get
                        ("settings");
                System.out.println(settingsEachProp);
                settingsEachProp.forEach((eachPropKey, eachPropValue) -> {
                    Map<String, Object> eachProp = (Map<String, Object>) eachPropValue;
                    if (eachProp.containsKey("sym_key")) {
                        String sym_key = (String) eachProp.get("sym_key");
                        String value = (String) eachProp.get("value");
                        String key = (String) eachProp.get("key");
                        String sym_iv = (String) eachProp.get("sym_iv");
                    } else if (eachProp.containsKey("key")) {
                        String value = (String) eachProp.get("value");
                        String key = (String) eachProp.get("key");
                    } else {
                        System.out.println(eachProp.get("value"));
                    }
                });
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
