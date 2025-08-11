package com.ajex.junitbook.ch02.lifecycle;

import com.manning.junitbook.ch02.lifecycle.ResourceForAllTests;
import com.manning.junitbook.ch02.lifecycle.SUT;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class SUTTest {
    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;
    
    @BeforeAll
    static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("Our resource for all tests");
    }
    
    @AfterAll
    static void tearDownClass() {
        resourceForAllTests.close();
    }
    
    @BeforeEach
    void setUp() {
        systemUnderTest = new SUT("Our system under test");
    }

    @AfterEach
    void tearDown() {
        systemUnderTest.close();
    }

    @Test
    @DisplayName("SUT should work correctly")
    void testRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }
    @Test
    @DisplayName("SUT should handle additional work")
    void testCanReceiveAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }

}
