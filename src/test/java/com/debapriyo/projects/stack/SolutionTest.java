package com.debapriyo.projects.stack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Debapriyo Majumder
 * @version 1.0
 * @since 2/13/2017
 */
public class SolutionTest {

    private Solution unitToBeTested;

    @Before
    public void setUp() throws Exception {
        unitToBeTested = new Solution();
    }

    @Test
    public void test() {
        assertEquals("Result did not match",7,unitToBeTested.solution("13 DUP 4 POP 5 DUP + DUP + -"));
    }


}