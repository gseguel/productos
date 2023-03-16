package com.gseguel.productos.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Range;

class UtilsTest {

    
    @Test
    void rangesTrue() {
        Range<Integer> range = Utils.ranges(1, 20);
        assertThat(range.contains(5), is(true));
    }
    
    @Test
    void rangesFalse() {
        Range<Integer> range = Utils.ranges(1, 20);
        assertThat(range.contains(30), is(false));
    }
}