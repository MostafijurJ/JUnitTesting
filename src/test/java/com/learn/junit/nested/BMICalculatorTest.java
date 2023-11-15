package com.learn.junit.nested;

import com.learn.junit.BMICalculator;
import com.learn.junit.Coder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private final String environment = "prod";
    @BeforeAll
    static void start(){
        System.out.println("~~~~~~~~~~~ BMICalculator test started ~~~~~~~~~~~");
    }

    @AfterAll
    static void end(){
        System.out.println("~~~~~~~~~~~~~~~ BMICalculator test ended~~~~~~~~~~~~~~~~~~~~~~~~");
    }


    @Nested
    class IsDietRecommendedTests{
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
    }



    @Nested
    class CalculateBMICalculator{

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


    @Nested
    @DisplayName("GetBMIScoresTestsPerformanceAndEnvironmentWise")
    class GetBMIScoresTestsPerformanceAndEnvironmentWise{
        // Performance test
        @Test
        void should_ReturnCoderWith_WorstBMI_In_ONE_MS_When_CoderList10000Elements(){

            //given
            List<Coder> coderList = new ArrayList<>();

            for (int i = 0; i < 10000; i++) {
                double randomHeight = 1.60 + Math.random() * 0.40;
                double randomWeight = 50 + Math.random() * 50;

                Coder coder = new Coder(randomHeight, randomWeight);
                coderList.add(coder);
            }

            //when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coderList);

            //then
            assertTimeout(Duration.ofMillis(70), executable);
        }

        // ignore environment wise test cases
        @Test
        void should_Ignore_Environment_WiseTest(){

            assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
            //given
            List<Coder> coderList = new ArrayList<>();

            for (int i = 0; i < 10000; i++) {
                double randomHeight = 1.60 + Math.random() * 0.40;
                double randomWeight = 50 + Math.random() * 50;

                Coder coder = new Coder(randomHeight, randomWeight);
                coderList.add(coder);
            }
            //when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coderList);

            //then
            assertTimeout(Duration.ofMillis(70), executable);
        }
    }




}