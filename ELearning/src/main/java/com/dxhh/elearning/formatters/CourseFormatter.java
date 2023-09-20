package com.dxhh.elearning.formatters;

import com.dxhh.elearning.pojos.Course;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CourseFormatter implements Formatter<Course> {

    @Override
    public Course parse(String text, Locale locale) throws ParseException {
        return new Course(Integer.valueOf(text));
    }

    @Override
    public String print(Course course, Locale locale) {
        return String.valueOf(course.getId());

    }
}
