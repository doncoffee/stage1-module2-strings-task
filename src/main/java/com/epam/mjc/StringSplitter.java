package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> delimiterList = new ArrayList<>(delimiters);
        List<String> list = new ArrayList<>();
        String regex = "[";
        for (int i = 0; i < delimiterList.size(); i++) {
            if (i != delimiterList.size() - 1) {
                regex += delimiterList.get(i);
            } else  {
                regex += delimiterList.get(i) + "]";
            }
        }
        String[] arr = source.split(regex);
        for (String str : arr) {
            if (str != null && !str.isEmpty()) {
                list.add(str);
            }
        }
        return list;
    }
}

