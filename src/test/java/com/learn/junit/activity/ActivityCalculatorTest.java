package com.learn.junit.activity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ActivityCalculatorTest {

    @BeforeEach
    void start() {
        System.out.println("Activity Calculator started");
    }

    @AfterEach
    void end() {
        System.out.println("Activity Calculator stopped");
    }


    @Test
    void should_ReturnBad_When_AvgBelo20() {

        // given
        int weeklyCardioMin = 40;
        int weeklyWorkoutSessions = 1;

        // when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        // then
        assertEquals("bad", actual);

    }

    @Test
    void should_ReturnAverage_When_AvgBetween20To40() {

        // given
        int weeklyCardioMin = 40;
        int weeklyWorkoutSessions = 1;

        // when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        // then
        assertEquals("average", actual);
    }

    @Test
    void should_ReturnGood_When_AvgAbove40() {

        // given
        int weeklyCardioMin = 40;
        int weeklyWorkoutSessions = 1;

        // when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        // then
        assertEquals("good", actual);
    }

    @Test
    void should_ThrowException_When_InputBelow0() {

        // given
        int weeklyCardioMin = -40;
        int weeklyWorkoutSessions = 1;

        // when
        Executable executable = () -> ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        // then
        assertThrows(RuntimeException.class, executable);
    }

}