package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {

    record Project(int profit, int capital) {}

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        var projects = new ArrayList<Project>();
        for (int i = 0; i < profits.length; i++) {
            projects.add(new Project(profits[i], capital[i]));
        }
        var availableProjects = new PriorityQueue<>(Comparator.comparing(Project::profit).reversed());
        projects.sort(Comparator.comparing(Project::capital).reversed());
        for (int i = 0; i < k; i++) {
            while (!projects.isEmpty() && projects.getLast().capital <= w) {
                availableProjects.add(projects.removeLast());
            }
            if (availableProjects.isEmpty()) {
                return w;
            }
            w += availableProjects.poll().profit();
        }
        return w;
    }

}
