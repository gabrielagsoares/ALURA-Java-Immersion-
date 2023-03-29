import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    public List<Map<String, String>> parse(String json){

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("It could not find items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributeItems = new HashMap<>();

            Matcher matcherAtributesJson = REGEX_ATRIBUTES_JSON.matcher(item);
            while (matcherAtributesJson.find()) {
                String atribute = matcherAtributesJson.group(1);
                String value = matcherAtributesJson.group(2);
                atributeItems.put(atribute, value);
            }

            data.add(atributeItems);
        }

        return data;

        
        
    }
    
}
