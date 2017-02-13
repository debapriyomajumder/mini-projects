package com.debapriyo.projects.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Debapriyo Majumder (x086021)
 * @version 1.0
 * @since 2/13/2017
 */
public class MapTest {

    private Map<String, String> unitToBeTested;

    @Before
    public void setUp() throws Exception {
        unitToBeTested = new Map<>();
        unitToBeTested.put("NL", "The Netherlands");
        unitToBeTested.put("UK", "United Kingdom");
        unitToBeTested.put("IN", "India");
        unitToBeTested.put("US", "United States");
        unitToBeTested.put("DK", "Denmark");
        unitToBeTested.put("IN", "Republic of India");
    }

    @Test
    public void test() {
        assertEquals("The map should have this mapping", "The Netherlands", unitToBeTested.get("NL"));
        assertEquals("The map should have this mapping", "Republic of India", unitToBeTested.get("IN"));
        assertNull("The map should not have this mapping", unitToBeTested.get("OB"));
    }


}