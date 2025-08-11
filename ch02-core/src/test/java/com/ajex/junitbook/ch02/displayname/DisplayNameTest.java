package com.ajex.junitbook.ch02.displayname;

import com.manning.junitbook.ch02.displayname.SUT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class showin the @DisplayName annotation.")
public class DisplayNameTest {
    private SUT sut = new SUT();

    @Test
    @DisplayName("Our system under test says hello.")
    void testHello() {
        assertEquals("Hello", sut.hello());
    }

    @Test
    @DisplayName("\uD83D\uDE31")
    void testTalking() {
        assertEquals("How are you?", sut.talk());
    }

    @Test
    void testBye() {
        assertEquals("Bye", sut.bye());
    }
}
