package org.txxf.lang.java;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * Unit test for String test.
 */
public class StringTest 
{
    @Test
    public void testStringSplit()
    {
        String str1 = "WEB-INF/classes";
        String[] items = str1.split("/");
        System.out.println("items length: " + items.length);
        System.out.println("items: " + Arrays.toString(items));
        assertEquals(2, items.length);
        assertEquals("WEB-INF", items[0]);
        assertEquals("classes", items[1]);


        String str2 = "/WEB-INF/classes";
        assertEquals(3, str2.split("/").length);
    }
}
