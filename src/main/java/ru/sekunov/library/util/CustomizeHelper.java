package ru.sekunov.library.util;

import org.springframework.stereotype.Service;

@Service
public class CustomizeHelper {
    public static String parseAuthorName(String name) {
        String[] names = name.split(" ");
        if (names.length == 3) {
            return names[0].charAt(0) + "." + names[1].charAt(0) + ". " + names[2];
        } else {
            return names[0].charAt(0) + ". " + names[1];
        }
    }
}
