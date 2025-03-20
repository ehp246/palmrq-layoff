package com.palmrq.layoff.artingest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import me.ehp246.aufrest.api.annotation.EnableByRest;

//@EnableForKafka(value = { @Inbound(value = @From(value = "app.kafak.inbox-topic.articles")) })
@EnableByRest
@ConfigurationPropertiesScan
@SpringBootApplication
public class ArtIngestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtIngestApplication.class, args);
    }

}
