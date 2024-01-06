package org.example;

import java.util.*;

public class Profit {

    record Job(int startTime, int endTime, int profit) {
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        jobs.sort(Comparator.comparing(Job::endTime));
        TreeMap<Integer, Integer> bestValues = new TreeMap<>();
        for (Job job : jobs) {
            bestValues.put(
                    job.endTime,
                    Math.max(
                            Optional.ofNullable(bestValues.floorEntry(job.startTime))
                                    .map(Map.Entry::getValue)
                                    .orElse(0) + job.profit,
                            Optional.ofNullable(bestValues.floorEntry(job.endTime)).map(Map.Entry::getValue).orElse(0)
                    )
            );
        }
        //System.out.println(bestValues);
        return bestValues.values().stream().mapToInt(i -> i).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(new Profit().jobScheduling(
                new int[]{1,2,3,4,6},
                new int[]{3,5,10,6,9},
                new int[]{20,20,100,70,60}
        ));
    }
}
