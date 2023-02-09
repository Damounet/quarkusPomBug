package com.toto.tdng.datachecker.common.model;

import lombok.Data;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Language {

    private static final String REGEX = "([a-zA-Z]+)-([a-zA-Z]+)";

    private final String languageIsoCode;
    private final String countryIsoCode;
  

    public static Language createFromString(String language) {
        final Pattern p = Pattern.compile(REGEX);
        final Matcher m = p.matcher(language);

        if (m.matches()) {
            String languageIsoCode = m.group(1);
            String countryIsoCode = m.group(2);

            return new Language(
                languageIsoCode,
                countryIsoCode
            );
        }

        throw new ClassCastException("Language does not match REGEX, must be not valid " + language);
    }


    @Override
    public String toString() {
        return MessageFormat.format(
                "{0}-{1}",
                getLanguageIsoCode(),
                getCountryIsoCode()
        );
    }
}
