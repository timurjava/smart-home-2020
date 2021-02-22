package ru.sbt.mipt.oop.Reader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeReaderJson implements SmartHomeReader {
    private  Gson gson;
    private String json;
    @Override
    public SmartHome read(String jsonName) {
        try {
            gson = new Gson();
            json = new String(Files.readAllBytes(Paths.get(jsonName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);

    }
}
