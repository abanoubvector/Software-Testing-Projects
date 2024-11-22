package helper.transformers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONHelper {
    public static String[] parseJSON(String filePath, int index) throws FileNotFoundException {
        JsonArray data = JsonParser.parseReader(new FileReader(filePath)).getAsJsonArray();
        JsonObject firstElement = data.get(index).getAsJsonObject();
        return new String[]{firstElement.get("username").getAsString(),
                firstElement.get("password").getAsString(),
                firstElement.get("expectedResult").getAsString(),
                firstElement.get("testCase").getAsString()
        };
    }
}

//public class JSONHelper {
//    public static String[] parseJSON(String filePath, int index) throws FileNotFoundException {
//        JsonArray data = JsonParser.parseReader(new FileReader(filePath)).getAsJsonArray();
//        JsonObject firstElement = data.get(index).getAsJsonObject();
//        String[] testData = {firstElement.get("username").getAsString(),
//                firstElement.get("password").getAsString(),
//                firstElement.get("expectedResult").getAsString(),
//                firstElement.get("testCase").getAsString()
//        };
//        return testData;
//    }
//}