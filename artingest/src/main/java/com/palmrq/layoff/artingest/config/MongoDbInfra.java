package com.palmrq.layoff.artingest.config;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoDbInfra {
    @Bean
    MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(new YearMonthReadConverter(), new YearMonthWriteConverter(),
                new LocalDateToStringConverter(), new StringToLocalDateConverter()));
    }

    static class YearMonthReadConverter implements Converter<Document, YearMonth> {
        @Override
        public YearMonth convert(Document source) {
            return YearMonth.of(source.getInteger("year"), source.getInteger("month"));
        }
    }

    static class YearMonthWriteConverter implements Converter<YearMonth, Document> {
        @Override
        public Document convert(YearMonth source) {
            return new Document().append("year", source.getYear()).append("month", source.getMonthValue());
        }
    }

    static class LocalDateToStringConverter implements Converter<LocalDate, String> {
        @Override
        public String convert(LocalDate source) {
            return source.format(DateTimeFormatter.ISO_DATE);
        }
    }

    static class StringToLocalDateConverter implements Converter<String, LocalDate> {
        @Override
        public LocalDate convert(String source) {
            return LocalDate.parse(source, DateTimeFormatter.ISO_DATE);
        }
    }
}
