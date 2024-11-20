package IOT.IotLog.controller;
import com.google.gson.JsonObject;

import IOT.IotLog.service.LogParserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogParserService logParserService;

    @PostMapping("/upload")
    public List<JsonObject> uploadLogFile(@RequestParam("file") MultipartFile file) {
        try {
            return logParserService.parseLogs(file);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse log file: " + e.getMessage());
        }
    }
}



