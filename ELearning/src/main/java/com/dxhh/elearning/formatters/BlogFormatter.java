package com.dxhh.elearning.formatters;

import com.dxhh.elearning.pojos.Blog;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class BlogFormatter implements Formatter<Blog> {

    @Override
    public String print(Blog blog, Locale locale) {
        return String.valueOf(blog.getId());
    }

    @Override
    public Blog parse(String text, Locale locale) throws ParseException {
        return new Blog(Integer.valueOf(text));
    }

}