package IOT.IotLog.service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class LogParserService {

    public List<JsonObject> parseLogs(MultipartFile logFile) throws Exception {
        List<JsonObject> parsedLogs = new ArrayList<>();

        String content = new String(logFile.getBytes());
        String[] lines = content.split("\n");

        for (String line : lines) {
            try {
                JsonObject jsonLog = JsonParser.parseString(line).getAsJsonObject();

                // Decode Base64 images if present
                if (jsonLog.has("image")) {
                    String base64Image = jsonLog.get("image").getAsString();
                    byte[] decodedImage = Base64.getDecoder().decode(base64Image);

                    // Save or process the image
                    System.out.println("Decoded Image Size: " + decodedImage.length);
                }

                parsedLogs.add(jsonLog);
            } catch (Exception e) {
                System.err.println("Error parsing line: " + line);
            }
        }

        return parsedLogs;
    }
}


