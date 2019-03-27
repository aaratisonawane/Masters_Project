package com.se.pranita.termproject.utils;

import com.se.pranita.termproject.model.Course;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Pranita on 11/5/16.
 */
public class ProgramRequirement {

    private static String[] departments = {"Chemical Engineering",
            "Computer Science",
            "Computer Engineering",
            "Electrical Engineering",
            "Environmental Sciences",
            "Mechanical Engineering"};
    private static String[][] courses_names = {{"Engineering Thermodynamics", "Chemical Process Analysis",
            "Applied Fluid Mechanics", "Separation Processes"},
            {"Compiler Construction", "Computer Graphics", "Design and Analysis of Algorithms", "Machine Learning"},
            {"Advance Computer Architecture", "GPU Architecture", "Parallel Programming", "Computer Vision"},
            {"Advanced Electromagnetics", "Computer-Aided Electronic Circuit Simulation",
                    "Stochastic Processes", "Linear System Theory"},
            {"Human and Ecological Risk Assessment", "Vadose Zone Processes",
                    "Research Seminar in Environmental Sciences", "Environmental Metrics"},
            {"Methods of Engineering Analysis", "Sustainable Product Design",
                    "Optimal Control and Estimation", "Linear System Theory"}};
//    String[] programs = {"BS", "MS", "Ph.D"};

    public static String getCreditsRequired(String program) {
        if(program.equalsIgnoreCase("bs"))
            return "72";
        else if(program.equalsIgnoreCase("ms"))
            return "36";
        else
            return "48";
    }

    public static String getStatus(String program, String department, ArrayList<Course> courses) {
        ArrayList<Course> inComplete = coreRequirements(department, courses).stream().filter(course ->
                course.getStatus() == Course.CourseStatus.COMPLETED).collect(Collectors.toCollection(ArrayList::new));
        if(String.valueOf(courses.size() * 3).equalsIgnoreCase(getCreditsRequired(program)) && inComplete.size() == getTotalCore(program))
            return "Completed";
        else
            return "Is Candidate";
    }

    public static ArrayList<Course> coreRequirements(String department, ArrayList<Course> courses) {

        ArrayList<Course> core = new ArrayList<>();

        int index = 0;
        for(String dept: departments) {
            if(dept.equalsIgnoreCase(department))
                break;
            index += 1;
        }

        for(int i = 0 ; i < courses_names[index].length ; i++) {
            int finalIndex = index;
            int finalI = i;
            ArrayList<Course> req = courses.stream().filter(course -> course.getName()
                    .equalsIgnoreCase(courses_names[finalIndex][finalI]))
                    .collect(Collectors.toCollection(ArrayList::new));
            if (req.size() > 0){
                core.add(req.get(0));
            }else {
                Course coreCourse = new Course();
                coreCourse.setName(courses_names[index][i]);
                coreCourse.setStatus(Course.CourseStatus.UNENROLLED);
                core.add(coreCourse);
            }
        }

        return core;
    }

    public static int getTotalCore(String program) {
        if(program.equalsIgnoreCase("bs"))
            return 4;
        else if(program.equalsIgnoreCase("ms"))
            return 2;
        else
            return 3;
    }
}
