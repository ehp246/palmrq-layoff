package com.palmrq.layoff.artingest.config;

import java.time.YearMonth;
import java.util.Arrays;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoDbConfig {
    @Bean
    MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(new YearMonthReadConverter(), new YearMonthWriteConverter()));
    }

    class YearMonthReadConverter implements Converter<Document, YearMonth> {
        @Override
        public YearMonth convert(Document source) {
            return YearMonth.of(source.getInteger("year"), source.getInteger("month"));
        }
    }

    class YearMonthWriteConverter implements Converter<YearMonth, Document> {
        @Override
        public Document convert(YearMonth source) {
            return new Document().append("year", source.getYear()).append("month", source.getMonthValue());
        }
    }
}
