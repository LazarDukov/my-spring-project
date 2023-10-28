package com.example.travelseeker.config;


import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public Gson createGson() {

        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        JsonDeserializer<LocalDate> toLocalTime =
                (json, t, c) -> LocalDate.parse(json.getAsString(), dateFormat);

        JsonSerializer<String> fromLocalTime =
                (date, t, c) -> new JsonPrimitive(date);

        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, toLocalTime)
                .registerTypeAdapter(LocalDate.class, fromLocalTime)
                .create();
    }
}
