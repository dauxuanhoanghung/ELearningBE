package com.dxhh.elearning.formatters;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Lecture;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class LectureFormatter implements Formatter<Lecture> {

    @Override
    public String print(Lecture lecture, Locale locale) {
        return String.valueOf(lecture.getId());
    }

    @Override
    public Lecture parse(String text, Locale locale) throws ParseException {
        return new Lecture(Integer.valueOf(text));
    }

}