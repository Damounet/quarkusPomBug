package com.toto.tdng.datachecker.common.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
    public static <T> Set<T> convertListToSet(List<T> list) {
        Set<T> set = new HashSet<>();
        for (T t : list)
            set.add(t);
        return set;
    }
}
