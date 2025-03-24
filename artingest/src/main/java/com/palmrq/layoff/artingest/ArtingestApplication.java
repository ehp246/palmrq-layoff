package com.palmrq.layoff.artingest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.palmrq.layoff.artingest.article.kafka.inbox.OnActicleSubmitted;
import com.palmrq.layoff.artingest.article.kafka.outbox.OnArticleExtracted;
import com.palmrq.layoff.artingest.config.KafkaInfra;

import me.ehp246.aufkafka.api.annotation.EnableByKafka;
import me.ehp246.aufkafka.api.annotation.EnableForKafka;
import me.ehp246.aufkafka.api.annotation.EnableForKafka.Inbound;
import me.ehp246.aufkafka.api.annotation.EnableForKafka.Inbound.From;
import me.ehp246.aufrest.api.annotation.EnableByRest;

@EnableForKafka(value = {
        @Inbound(value = @From(value = "${app.kafka.topic.article.inbox}"), scan = OnActicleSubmitted.class, invocationListener = KafkaInfra.BEAN_INVOCATION_LISTENER),
        @Inbound(value = @From(value = "${app.kafka.topic.article.outbox}"), scan = OnArticleExtracted.class) })
@EnableByKafka()
@EnableByRest
@ConfigurationPropertiesScan
@SpringBootApplication
public class ArtingestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtingestApplication.class, args);
    }

}
