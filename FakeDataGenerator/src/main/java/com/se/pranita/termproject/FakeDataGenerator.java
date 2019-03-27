package com.se.pranita.termproject;

import com.se.pranita.termproject.model.*;
import com.se.pranita.termproject.model.announcement.Announcement;
import com.se.pranita.termproject.model.announcement.AnnouncementFactory;
import com.se.pranita.termproject.model.announcement.Event;
import com.se.pranita.termproject.model.dao.*;
import com.se.pranita.termproject.model.user.*;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Person;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pranita on 7/5/16.
 */
public class FakeDataGenerator {

    public static void main(String[] main) {

        final boolean saveToDB = true;

        ArrayList<User> students = createFakeUser(User.UserType.STUDENT, saveToDB);
        ArrayList<User> faculty = createFakeUser(User.UserType.FACULTY, saveToDB);
        ArrayList<User> staff = createFakeUser(User.UserType.STAFF, saveToDB);
        ArrayList<Resource> resources = createFakeResources(saveToDB);
        createFakeReservations(students, faculty, staff, resources, saveToDB);
        ArrayList<Course> courses = createFakeCourses(faculty, saveToDB);
        createFakeCourseUser(students, courses, saveToDB);
        createFakeAlumni(saveToDB);
        createFakeAnnouncements(faculty, staff, saveToDB);
        createFakeDiscussions(students, faculty, staff, saveToDB);
        createFakeExams(students, faculty, staff, saveToDB);
        createFakeResults(faculty, saveToDB);

    }

    public static void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void createFakeResults(ArrayList<User> faculty, boolean saveToDB) {
        System.out.println("\n\n***Results***");
        Random rand = new Random();
        String[] courses_names = {"Engineering Thermodynamics", "Chemical Process Analysis",
                "Applied Fluid Mechanics", "Separation Processes",
                "Compiler Construction", "Computer Graphics", "Design and Analysis of Algorithms", "Machine Learning",
                "Advance Computer Architecture", "GPU Architecture", "Parallel Programming", "Computer Vision",
                "Advanced Electromagnetics", "Computer-Aided Electronic Circuit Simulation",
                "Stochastic Processes", "Linear System Theory",
                "Human and Ecological Risk Assessment", "Vadose Zone Processes",
                "Research Seminar in Environmental Sciences", "Environmental Metrics",
                "Methods of Engineering Analysis", "Sustainable Product Design",
                "Optimal Control and Estimation", "Linear System Theory"};
        Fairy fairy = Fairy.create();
        for(int i = 0 ; i < 12 ; i++) {
            try {
                sleep();
                Result result = new Result();
                result.setNetID(faculty.get(rand.nextInt(faculty.size())).getNetID());
                result.setExamName(courses_names[rand.nextInt(courses_names.length)]);
                result.setResultDetails(fairy.textProducer().loremIpsum());
//                try {
//                    if (saveToDB)
//                        new ResultDAO().save(result.getNetID(), result.getResultDetails(), result.getExamName());
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                System.out.println(result);
            }catch (Exception ignored){}
        }
    }

    private static void createFakeExams(ArrayList<User> students, ArrayList<User> faculty, ArrayList<User> staff,
                                        boolean saveToDB) {
        System.out.println("\n\n***Exams***");
        String[] courses_names = {"Engineering Thermodynamics", "Chemical Process Analysis",
                "Applied Fluid Mechanics", "Separation Processes",
                "Compiler Construction", "Computer Graphics", "Design and Analysis of Algorithms", "Machine Learning",
                "Advance Computer Architecture", "GPU Architecture", "Parallel Programming", "Computer Vision",
                "Advanced Electromagnetics", "Computer-Aided Electronic Circuit Simulation",
                        "Stochastic Processes", "Linear System Theory",
                "Human and Ecological Risk Assessment", "Vadose Zone Processes",
                        "Research Seminar in Environmental Sciences", "Environmental Metrics",
                "Methods of Engineering Analysis", "Sustainable Product Design",
                        "Optimal Control and Estimation", "Linear System Theory"};
        ArrayList<User> users = new ArrayList<>();
        users.addAll(staff);
        users.addAll(faculty);
        ArrayList<Exam> exams = new ArrayList<>();
        Fairy fairy = Fairy.create();
        Random rand = new Random();
        for(int i = 0 ; i < 10 ; i++) {
            sleep();
            try {
                Exam exam = new Exam();
                exam.setExamID(i);
                exam.setNetID(users.get(rand.nextInt(users.size())).getNetID());
                exam.setName(courses_names[rand.nextInt(courses_names.length)]);
                exam.setDateOfExam(new SimpleDateFormat("yyyy-MM-dd").format(fairy.dateProducer().randomDateBetweenYears(2015, 2017).toDate()));
                exam.setAdditionalDetails(fairy.textProducer().loremIpsum());
                try {
                    if (saveToDB)
                        new ExamDAO().save(exam.getExamID(), exam.getNetID(), exam.getName(),
                                Date.valueOf(exam.getDateOfExam()), exam.getAdditionalDetails());
                    exams.add(exam);
                } catch (SQLException ignored) {
                }
                System.out.println(exam);
            }catch (Exception ignored){
                ignored.printStackTrace();
            }
        }

        for(int i = 0 ; i < students.size() ; i++) {
            for(int j = 0 ; j < 2 ; j++) {
                sleep();
                try {
                    if(saveToDB)
                        new ExamDAO().enroll(exams.get(rand.nextInt(exams.size())).getExamID(), students.get(i).getNetID(), true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createFakeDiscussions(ArrayList<User> students, ArrayList<User> faculty,
                                              ArrayList<User> staff, boolean saveToDB) {
        System.out.println("\n\n***Discussions***");
        ArrayList<User> users = new ArrayList<>();
        users.addAll(faculty);
        users.addAll(staff);
        ArrayList<Discussion> discussions = new ArrayList<>();
        Random rand = new Random();
        Fairy fairy = Fairy.create();
        for(int i = 0 ; i < 12 ; i++) {
            sleep();
            Discussion discussion = new Discussion();
            String netID = users.get(rand.nextInt(users.size())).getNetID();
            discussion.setNetID(netID);
            discussion.setTitle(fairy.textProducer().sentence(rand.nextInt(3) + 2));
            discussion.setDetails(fairy.textProducer().paragraph());
            discussion.setType(Discussion.DiscussionType.TOPIC);
            try {
                if(saveToDB)
                new DiscussionDAO().save(discussion.getNetID(), discussion.getTitle(), discussion.getDetails(),
                        discussion.getType().getValue(), null);
                discussions.add(discussion);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(discussion);
        }

        if(saveToDB) {
            ArrayList<Discussion> reply = new ArrayList<>();
            try {
                discussions = new DiscussionDAO().get();
                System.out.println("\n\n***Replies***");
                for (int i = 0; i < discussions.size(); i++) {
                    for (int j = 0; j < rand.nextInt(4); j++) {
                        sleep();
                        try {
                            Discussion discussion = new Discussion();
                            String netID = students.get(rand.nextInt(students.size())).getNetID();
                            discussion.setNetID(netID);
                            discussion.setDiscussion_id(discussions.get(i).getId());
                            discussion.setTitle(fairy.textProducer().sentence(rand.nextInt(3) + 2));
                            discussion.setDetails(fairy.textProducer().paragraph());
                            discussion.setType(Discussion.DiscussionType.REPLY);
                            try {
                                new DiscussionDAO().save(discussion.getNetID(), discussion.getTitle(), discussion.getDetails(),
                                            discussion.getType().getValue(), String.valueOf(discussion.getDiscussion_id()));
                                reply.add(discussion);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            System.out.println(discussion);
                        } catch (Exception ignored) {
                        }
                    }
                }
            }catch (Exception ignored) {}
            System.out.println(reply);
        }

    }

    private static void createFakeAnnouncements(ArrayList<User> faculty, ArrayList<User> staff, boolean saveToDB) {
        System.out.println("\n\n****Announcements****");
        ArrayList<User> users = new ArrayList<>();
        users.addAll(faculty);
        users.addAll(staff);
        Random rand = new Random();
        Fairy fairy = Fairy.create();
        for(int i = 0 ; i < 12 ; i++) {
            sleep();
            try {
                Announcement announcement = new AnnouncementFactory().getAnnouncement(rand.nextInt(4));
                User user = users.get(rand.nextInt(users.size()));
                String netID = user.getNetID();
                announcement.setNetID(netID);
                Company company = fairy.company();
                announcement.setLink(company.url());
                announcement.setTitle(company.name());
                announcement.setDetails(fairy.textProducer().paragraph());
                if (announcement.getType() == Announcement.AnnouncementType.EVENT) {
                    ((Event) announcement).setEventDatetime(new Timestamp(fairy.dateProducer().randomDateBetweenNowAndFuturePeriod(Period.months(10)).getMillis()));
                    ((Event) announcement).setEventVenue(fairy.textProducer().randomString(3) + " " + (rand.nextInt(99) + 100));
                    try {
                        if (saveToDB)
                            new AnnouncementDAO().save(announcement.getNetID(), announcement.getTitle(), announcement.getDetails(),
                                    announcement.getLink(), announcement.getType(),
                                    new SimpleDateFormat("MM/dd/yyyy h:mm a").format(((Event) announcement).getEventDatetime()),
                                    ((Event) announcement).getEventVenue());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        if (saveToDB)
                            new AnnouncementDAO().save(announcement.getNetID(), announcement.getTitle(), announcement.getDetails(),
                                    announcement.getLink(), announcement.getType(), null, null);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(announcement);
            }catch (Exception e) {}
        }
    }

    private static void createFakeAlumni(boolean saveToDB) {
        Fairy fairy = Fairy.create();
        for (int i = 0; i < 10; i++) {
            sleep();
            Alumni alumni = new Alumni();
            Company company = fairy.company();
            alumni.setName(company.name());
            alumni.setImage(null);
            alumni.setHomepage(company.url());
            alumni.setDescription(fairy.textProducer().paragraph());
            System.out.println(alumni);
            try {
                if (saveToDB)
                    new AlumniDAO().save(alumni.getName(), alumni.getHomepage(), alumni.getDescription(), alumni.getImage());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createFakeCourseUser(ArrayList<User> students,
                                             ArrayList<Course> courses, boolean saveToDB) {
        Random rand = new Random();
        for (User user :
                students) {
            for (int i = 0; i < rand.nextInt(4); i++) {
                sleep();
                Course course = courses.get(rand.nextInt(courses.size()));
                try {
                    if (saveToDB)
                        new CoursesDAO().enroll(true, user.getNetID(), course.getNumber(), course.getTerm(), course.getYear());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static ArrayList<Course> createFakeCourses(ArrayList<User> faculty, boolean saveToDB) {
        ArrayList<Course> courses = new ArrayList<>();
        String[] course_initials = {"CHE", "CS", "CE", "EE", "ES", "ME"};
        String[] departments = {"Chemical Engineering",
                "Computer Science",
                "Computer Engineering",
                "Electrical Engineering",
                "Environmental Sciences",
                "Mechanical Engineering"};
        String[][] courses_names = {{"Engineering Thermodynamics", "Chemical Process Analysis",
                "Applied Fluid Mechanics", "Separation Processes"},
                {"Compiler Construction", "Computer Graphics", "Design and Analysis of Algorithms", "Machine Learning"},
                {"Advance Computer Architecture", "GPU Architecture", "Parallel Programming", "Computer Vision"},
                {"Advanced Electromagnetics", "Computer-Aided Electronic Circuit Simulation",
                        "Stochastic Processes", "Linear System Theory"},
                {"Human and Ecological Risk Assessment", "Vadose Zone Processes",
                        "Research Seminar in Environmental Sciences", "Environmental Metrics"},
                {"Methods of Engineering Analysis", "Sustainable Product Design",
                        "Optimal Control and Estimation", "Linear System Theory"}};
        String[] terms = {"Fall", "Spring"};
        String[] hours = {"9 am to 10 am", "10 am to 11 am", "11 am to 12 pm", "12 pm to 1 pm", "1 pm to 2 pm",
                "2 pm to 3 pm",};
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};
        Fairy fairy = Fairy.create();
        Random rand = new Random();
        for (int i = 0; i < departments.length; i++) {
            for (int j = 0; j < courses_names[i].length; j++) {
                sleep();
                Course course = new Course();
                course.setNumber(course_initials[i] + " " + (rand.nextInt(200) + 99));
                course.setName(courses_names[i][j]);
                course.setDepartment(departments[i]);
                course.setCourse_syllabus(fairy.textProducer().paragraph());
                course.setInstructor(faculty.get(rand.nextInt(faculty.size())).getNetID());
                course.setIns_office_hour(days[rand.nextInt(days.length)] + ", " + hours[rand.nextInt(hours.length)]);
                course.setIns_office(fairy.textProducer().randomString(3) + " " + (rand.nextInt(99) + 100));
                course.setTa_name(fairy.person().fullName());
                course.setTa_office_hour(days[rand.nextInt(days.length)] + ", " + hours[rand.nextInt(hours.length)]);
                course.setTa_office(fairy.textProducer().randomString(3) + " " + (rand.nextInt(99) + 100));
                course.setTa_email(fairy.person().email());
                course.setTerm(terms[rand.nextInt(terms.length)]);
                course.setYear(rand.nextInt(4) + 2013);
                try {
                    if (saveToDB)
                        new CoursesDAO().save(course.getNumber(), course.getName(), course.getDepartment(), course.getTerm(),
                                course.getYear(), course.getCourse_syllabus(), course.getIns_office(),
                                course.getInstructor(), course.getIns_office_hour(), course.getTa_name(),
                                course.getTa_email(), course.getTa_office(), course.getTa_office_hour());
                    courses.add(course);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(course);
            }
        }

        return courses;
    }

    private static ArrayList<Reservation> createFakeReservations(ArrayList<User> students, ArrayList<User> faculty,
                                                                 ArrayList<User> staff, ArrayList<Resource> resources,
                                                                 boolean saveToDB) {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        ArrayList<User> users = new ArrayList<User>();
        users.addAll(students);
        users.addAll(faculty);
        users.addAll(staff);
        Random rand = new Random();
        Fairy fairy = Fairy.create();
        String[] range = {"9-10", "10-11", "11-12", "12-1", "1-2", "2-3", "3-4", "4-5", "5-6", "6-7"};
        for (User user :
                users) {
            sleep();
            reservations.add(reserveResource(resources.get(rand.nextInt(resources.size())).getName(), user.getNetID(),
                    range[rand.nextInt(range.length)],
                    fairy.dateProducer().randomDateBetweenNowAndFuturePeriod(Period.days(20)), saveToDB));
            reservations.add(reserveResource(resources.get(rand.nextInt(resources.size())).getName(), user.getNetID(),
                    range[rand.nextInt(range.length)],
                    fairy.dateProducer().randomDateBetweenNowAndFuturePeriod(Period.days(20)), saveToDB));
        }
        return reservations;
    }

    private static Reservation reserveResource(String resource, String netID, String slot, DateTime dateTime, boolean saveToDB) {
        Reservation reservation = new Reservation();
        reservation.setNetID(netID);
        reservation.setResourceName(resource);
        reservation.setSlot_time_range(slot);
        reservation.setSlot_date(Date.valueOf(dateTime.toString("yyyy-MM-dd")));
        try {
            if (saveToDB)
                new ReservationDAO().save(reservation.getResourceName(), reservation.getNetID(),
                        reservation.getSlot_date(), reservation.getSlot_time_range());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(reservation);
        return reservation;
    }

    private static ArrayList<Resource> createFakeResources(boolean saveToDB) {
        System.out.println("\n\n***Resources***");
        Fairy fairy = Fairy.create();
        Random rand = new Random();
        String[] types = {"Projector", "Conference Room", "Scanner", "Printer", "Fax Machine", "Laptop"};
        ArrayList<Resource> resources = new ArrayList<Resource>();
        for (int i = 0; i < 50; i++) {
            sleep();
            Resource resource = new Resource();
            resource.setInfo(fairy.textProducer().paragraph());

            resource.setType(types[rand.nextInt(types.length)]);
            resource.setName(fairy.company().name());
            try {
                if (saveToDB)
                    new ResourcesDAO().save(resource.getName(), resource.getType(), resource.getInfo());
                resources.add(resource);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(resource);
        }

        return resources;
    }

    private static ArrayList<User> createFakeUser(User.UserType type, boolean saveToDB) {
        ArrayList<User> users = new ArrayList<User>();
        Fairy fairy = Fairy.create();
        Random rand = new Random();
        String[] terms = {"Fall", "Spring"};
        String[] programs = {"BS", "MS", "Ph.D"};
        String[] departments = {"Chemical Engineering",
                "Computer Science",
                "Computer Engineering",
                "Electrical Engineering",
                "Environmental Sciences",
                "Mechanical Engineering"};
        for (int i = 0; i < 50; i++) {
            sleep();
            User user = new UserFactory().getUser(type.getValue());
            Person person = fairy.person();
            user.setFirstName(person.firstName());
            user.setLastName(person.lastName());
            user.setNetID(user.getFirstName().charAt(0) + "" + user.getLastName().charAt(0) + "" + (rand.nextInt(900000) + 100000));
            user.setPassword(fairy.textProducer().randomString(10));
            if (type == User.UserType.STUDENT) {

                ((Student) user).setStartTerm(terms[rand.nextInt(terms.length)]);
                ((Student) user).setStartYear(rand.nextInt(6) + 2010);

                ((Student) user).setProgram(programs[rand.nextInt(programs.length)]);

                ((Student) user).setDepartment(departments[rand.nextInt(departments.length)]);

                try {
                    if (saveToDB)
                        new UserDAO().save(user.getNetID(), user.getPassword(), user.getFirstName(), user.getLastName(), type,
                                ((Student) user).getDepartment(), ((Student) user).getProgram(), ((Student) user).getStartTerm(),
                                String.valueOf(((Student) user).getStartYear()));
                    users.add(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    if (saveToDB)
                        new UserDAO().save(user.getNetID(), user.getPassword(), user.getFirstName(), user.getLastName(), type,
                                null, null, null, null);
                    users.add(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
        System.out.println(users);
        return users;
    }

}
