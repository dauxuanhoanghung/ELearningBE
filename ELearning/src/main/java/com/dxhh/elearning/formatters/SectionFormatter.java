package com.dxhh.elearning.formatters;

import com.dxhh.elearning.pojos.Section;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class SectionFormatter implements Formatter<Section> {

    @Override
    public String print(Section section, Locale locale) {
        return String.valueOf(section.getId());
    }

    @Override
    public Section parse(String text, Locale locale) throws ParseException {
        return new Section(Integer.valueOf(text));
    }

}
