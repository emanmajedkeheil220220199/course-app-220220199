package com.example.courseapplication220220199.Entity;


import androidx.room.TypeConverter;

import androidx.room.TypeConverter;
import java.net.URI;

public class UriConverter {

    @TypeConverter
    public static String fromUri(URI uri) {
        return (uri == null) ? null : uri.toString();
    }

    @TypeConverter
    public static URI toUri(String uriString) {
        return (uriString == null || uriString.isEmpty()) ?
                null : URI.create(uriString);
    }
}
