package com.drosophila.birdwatching.utils;

import com.drosophila.birdwatching.enums.Age;
import com.drosophila.birdwatching.enums.Sex;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

public class FigureConverter {

    @ReadingConverter
    public static class SexReadingConverter implements Converter<String, Sex> {
        @Override
        public Sex convert(final String s) {
            return Sex.fromString(s);
        }
    }

    @ReadingConverter
    public static class AgeReadingConverter implements Converter<String, Age> {
        @Override
        public Age convert(final String s) {
            return Age.fromString(s);
        }
    }

}
