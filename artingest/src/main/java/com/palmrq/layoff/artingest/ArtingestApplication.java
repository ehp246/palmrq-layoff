package com.palmrq.layoff.artingest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import me.ehp246.aufkafka.api.annotation.EnableByKafka;
import me.ehp246.aufkafka.api.annotation.EnableForKafka;
import me.ehp246.aufkafka.api.annotation.EnableForKafka.Inbound;
import me.ehp246.aufkafka.api.annotation.EnableForKafka.Inbound.From;
import me.ehp246.aufrest.api.annotation.EnableByRest;

@EnableForKafka(value = { @Inbound(value = @From(value = "${app.kafka.article-inbox}")) })
@EnableByKafka()
@EnableByRest
@ConfigurationPropertiesScan
@SpringBootApplication
public class ArtingestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtingestApplication.class, args);
    }

}
