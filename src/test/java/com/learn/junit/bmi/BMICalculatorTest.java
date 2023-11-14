package com.learn.junit.bmi;

import com.learn.junit.BMICalculator;
import com.learn.junit.Coder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @BeforeAll
    static void start(){
        System.out.println("~~~~~~~~~~~ BMICalculator test started ~~~~~~~~~~~");
    }

    @AfterAll
    static void end(){
        System.out.println("~~~~~~~~~~~~~~~ BMICalculator test ended~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Test
    void should_ReturnTrue_When_DietRecommended(){

        //given
        double height = 170;
        double weight = 75;

        //when
        boolean dietRecommended = BMICalculator.isDietRecommended(height, weight);

        //then
        assertFalse(dietRecommended);

    }


    @ParameterizedTest
    @CsvSource(value = {"89.02, 1.72","95.0, 1.75","110.0, 1.78"})
    void should_ReturnTrue_When_DietRecommended_Param(Double heightValue, Double weightValue){

        //given
        double height = heightValue;
        double weight = weightValue;

        //when
        boolean dietRecommended = BMICalculator.isDietRecommended(height, weight);

        //then
        assertTrue(dietRecommended);

    }


    @Test
    void should_ReturnFalse_When_DietNotRecommended(){

        //given
        double height = 100;
        double weight = 500;

        //when
        boolean dietNotRecommended = BMICalculator.isDietRecommended(height, weight);

        //then
        assertFalse(dietNotRecommended);
    }


    @Test
    void should_ReturnCoderWith_WorstBMI_When_CoderListNotEmpty(){

        //given
        Coder coder1 = new Coder(1.80, 60);
        Coder coder2 = new Coder(1.82, 98);
        Coder coder3 = new Coder(1.82, 64);

        //when
        Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(List.of(coder1, coder2, coder3));

        //then
        assertAll(
                () -> assertEquals(1.82, coderWithWorstBMI.getHeight()),
                () -> assertEquals(98, coderWithWorstBMI.getWeight())
        );
    }

    @Test
    void should_ReturnNull_WorstBMI_When_CoderListEmpty(){

        //given
        Coder coder1 = new Coder(1.80, 60);
        Coder coder2 = new Coder(1.82, 98);
        Coder coder3 = new Coder(1.82, 64);

        //when
        Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(new ArrayList<>());

        //then
       assertNull(coderWithWorstBMI);
    }

    @Test
    void should_ReturnCorrectBMIScore_When_CoderListNotEmpty(){

        //given
        Coder coder1 = new Coder(1.80, 60);
        Coder coder2 = new Coder(1.82, 98);
        Coder coder3 = new Coder(1.82, 64.7);

        double[]  expected = {18.52, 29.59, 19.53};

        //when
        double[] bmiScores = BMICalculator.getBMIScores(List.of(coder1, coder2, coder3));

        //then
        assertArrayEquals(expected, bmiScores);
    }

}