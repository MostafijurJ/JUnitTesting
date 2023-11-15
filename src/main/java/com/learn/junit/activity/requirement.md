# Business Requirement

Rate user's activity by the following rules:
1. weekly time [min] spent during cardio
2. the number of workout session ( 1 workout = 45 minutes )
3. throw exception when any input is negative

Calculate the  weekly total divide by 7 to find the daily average
if<20 returns "bad"
if >= 20 AND <40 returns "average"
if >40 returns "good"