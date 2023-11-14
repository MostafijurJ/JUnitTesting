package com.learn.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    void setUp() {
        System.out.println(" ~~~~~~~~~~~~~~ A unit test was started. ~~~~~~~~~~~~~~ ");
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void tearDown() {
        System.out.println(" ~~~~~~~~~~~~~~ A unit test was finished. ~~~~~~~~~~~~~~ ");
    }

    @RepeatedTest(value = 100000, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_ReturnCorrectDietPlan_When_CorrectCoder() {

        // given
        Coder coder = new Coder(1.82,75.0,26, Gender.MALE);
        DietPlan expectedDietPlanner = new DietPlan(2202,110, 73,275);

        //when
        DietPlan actualDietPlanner = dietPlanner.calculateDiet(coder);

        // then
        assertAll(
                () -> assertEquals(expectedDietPlanner.getCalories(), actualDietPlanner.getCalories()),
                () -> assertEquals(expectedDietPlanner.getProtein(), actualDietPlanner.getProtein()),
                () -> assertEquals(expectedDietPlanner.getFat(), actualDietPlanner.getFat()),
                () -> assertEquals(expectedDietPlanner.getCarbohydrate(), actualDietPlanner.getCarbohydrate())
        );

    }
}