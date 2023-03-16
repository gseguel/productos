package com.gseguel.productos.utils;

import com.google.common.collect.Range;

public class Utils {

    public static Range<Integer> ranges(Integer inferior, Integer superior) {
        Range<Integer> ranges = Range.open(inferior, superior);
        return ranges;
    }
}
