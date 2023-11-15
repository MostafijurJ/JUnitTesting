package com.learn.junit.activity;

public class ActivityCalculator {
    private static final int WORKOUT_DURATION_MIN = 45;

    public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkoutSessions) {
        int totalCardioMin = weeklyCardioMin + weeklyWorkoutSessions * WORKOUT_DURATION_MIN;
        int avgDailyCardioMin = totalCardioMin / 7;

        if (avgDailyCardioMin < 20) {
            return "bad";
        } else if (avgDailyCardioMin <= 40) {
            return "average";
        } else return "good";

    }
}
