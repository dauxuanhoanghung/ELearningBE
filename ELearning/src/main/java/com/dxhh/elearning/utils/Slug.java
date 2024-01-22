package com.dxhh.elearning.utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class Slug {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String generate(String input) {
        String noWhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String vietnameselized = vietnameselizeString(noWhitespace);
        String normalized = normalizeString(vietnameselized);
        return toSlugString(normalized);
    }

    private static String normalizeString(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\p{ASCII}]", "");
    }

    private static String vietnameselizeString(String input) {
        input = input.replaceAll("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a")
                .replaceAll("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e")
                .replaceAll("ì|í|ị|ỉ|ĩ", "i")
                .replaceAll("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o")
                .replaceAll("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u")
                .replaceAll("ỳ|ý|ỵ|ỷ|ỹ", "y")
                .replaceAll("đ", "d")
                .replaceAll("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ", "A")
                .replaceAll("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ", "E")
                .replaceAll("Ì|Í|Ị|Ỉ|Ĩ", "I")
                .replaceAll("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ", "O")
                .replaceAll("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ", "U")
                .replaceAll("Ỳ|Ý|Ỵ|Ỷ|Ỹ", "Y")
                .replaceAll("Đ", "D");
        input = input.replaceAll("/", "-");
        input = input.replaceAll("[()]", "-");
        input = input.replaceAll("--", "-");
        return input.toLowerCase();
    }

    private static String toSlugString(String input) {
        String noSpecialCharacters = NON_LATIN.matcher(input).replaceAll("");
        return noSpecialCharacters.toLowerCase(Locale.ENGLISH);
    }
}
