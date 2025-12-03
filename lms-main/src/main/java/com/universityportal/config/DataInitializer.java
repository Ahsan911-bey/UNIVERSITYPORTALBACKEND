package com.universityportal.config;

import com.universityportal.entity.*;
import com.universityportal.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedData(StudentRepository studentRepository,
                                      TeacherRepository teacherRepository,
                                      AdminRepository adminRepository,
                                      CourseRepository courseRepository,
                                      MarksRepository marksRepository,
                                      AttendanceRepository attendanceRepository,
                                      AssignmentRepository assignmentRepository,
                                      StudentCourseEnrollmentRepository enrollmentRepository,
                                      BatchRepository batchRepository,
                                      AnnouncementRepository announcementRepository,
                                      LearningResourceRepository learningResourceRepository,
                                      JdbcTemplate jdbcTemplate) {
        return args -> {
            // Clear existing data so we always start with the same mock dataset
            marksRepository.deleteAll();
            attendanceRepository.deleteAll();
            assignmentRepository.deleteAll();
            announcementRepository.deleteAll();
            learningResourceRepository.deleteAll();
            enrollmentRepository.deleteAll();
            courseRepository.deleteAll();
            studentRepository.deleteAll();
            teacherRepository.deleteAll();
            adminRepository.deleteAll();
            batchRepository.deleteAll();

            // Try to reset ID counters for H2 so IDs start from 1 again (for easy testing)
            try {
                jdbcTemplate.execute("ALTER TABLE students ALTER COLUMN id RESTART WITH 1");
                jdbcTemplate.execute("ALTER TABLE teachers ALTER COLUMN id RESTART WITH 1");
                jdbcTemplate.execute("ALTER TABLE courses ALTER COLUMN id RESTART WITH 1");
                jdbcTemplate.execute("ALTER TABLE batches ALTER COLUMN id RESTART WITH 1");
            } catch (Exception ex) {
                System.out.println("Could not reset H2 ID sequences: " + ex.getMessage());
            }

            Random random = new Random(42); // Fixed seed for reproducibility

            // ========== CREATE ADMIN ==========
            Admin admin = new Admin();
            admin.setName("Dr. Muhammad Admin");
            admin.setRegNo("ADMIN-001");
            admin.setEmailAddress("admin@university.edu");
            admin.setContactNumber("0300-0000001");
            admin.setGuardianNumber("0300-0000002");
            admin.setFatherName("Admin Father");
            admin.setProgram("Administration");
            admin.setSession("2020-2024");
            admin.setSemester("N/A");
            admin.setCampus("Main");
            admin.setClassName("Admin Office");
            admin.setNationality("Pakistani");
            admin.setDob(LocalDate.of(1975, 1, 1));
            admin.setProfilePic("https://example.com/admin.jpg");
            admin.setPassword("admin123");
            admin.setDesignation("Director");
            admin.setDepartment("IT Administration");
            Admin savedAdmin = adminRepository.save(admin);
            System.out.println("Seeded admin with id = " + savedAdmin.getId() + ", name = " + savedAdmin.getName());

            // ========== CREATE 10 TEACHERS ==========
            String[] teacherNames = {
                "Dr. Ali Ahmed", "Prof. Sara Khan", "Dr. Ahmed Hassan", "Ms. Fatima Ali",
                "Dr. Usman Malik", "Prof. Ayesha Sheikh", "Dr. Bilal Raza", "Ms. Hina Shah",
                "Dr. Zain Abbas", "Prof. Mariam Khan"
            };
            String[] qualifications = {
                "PhD Computer Science", "MSCS", "PhD Software Engineering", "MSCS",
                "PhD Data Science", "MSCS", "PhD AI", "MSCS",
                "PhD Networking", "MSCS"
            };
            String[] specializations = {
                "Artificial Intelligence", "Web Development", "Database Systems", "Mobile Development",
                "Machine Learning", "Cybersecurity", "Cloud Computing", "Software Engineering",
                "Network Security", "Data Structures"
            };
            String[] programs = {"Computer Science", "Software Engineering", "Information Technology"};
            String[] sessions = {"2020-2024", "2021-2025", "2022-2026"};
            String[] campuses = {"Main", "North", "South"};

            List<Teacher> teachers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Teacher teacher = new Teacher();
                teacher.setName(teacherNames[i]);
                teacher.setRegNo("T-" + String.format("%03d", i + 1));
                teacher.setEmailAddress(teacherNames[i].toLowerCase().replace(" ", ".").replace(".", "") + "@university.edu");
                teacher.setContactNumber("0300-" + String.format("%07d", 1000000 + i));
                teacher.setGuardianNumber("0300-" + String.format("%07d", 2000000 + i));
                teacher.setFatherName("Father of " + teacherNames[i]);
                teacher.setProgram(programs[i % programs.length]);
                teacher.setSession(sessions[i % sessions.length]);
                teacher.setSemester(String.valueOf((i % 8) + 1));
                teacher.setCampus(campuses[i % campuses.length]);
                teacher.setClassName("CS-" + (i % 8 + 1) + "A");
                teacher.setNationality("Pakistani");
                teacher.setDob(LocalDate.of(1970 + (i * 2), (i % 12) + 1, (i % 28) + 1));
                teacher.setProfilePic("https://example.com/teacher" + (i + 1) + ".jpg");
                teacher.setPassword("password");
                teacher.setQualification(qualifications[i]);
                teacher.setSpecialization(specializations[i]);
                teachers.add(teacher);
            }
            List<Teacher> savedTeachers = teacherRepository.saveAll(teachers);
            for (Teacher t : savedTeachers) {
                System.out.println("Seeded teacher with id = " + t.getId() + ", name = " + t.getName());
            }

            // ========== CREATE BATCHES ==========
            String[] batchNames = {"Batch-A", "Batch-B", "Batch-C", "Batch-D", "Batch-E"};
            List<Batch> batches = new ArrayList<>();
            for (String batchName : batchNames) {
                Batch batch = new Batch();
                batch.setName(batchName);
                batches.add(batchRepository.save(batch));
            }
            System.out.println("Seeded " + batches.size() + " batches");

            // ========== CREATE 50 STUDENTS ==========
            String[] firstNames = {
                "Ahsan", "Sara", "Ali", "Fatima", "Usman", "Ayesha", "Bilal", "Hina", "Zain", "Mariam",
                "Hamza", "Aisha", "Omar", "Zara", "Hassan", "Layla", "Ibrahim", "Sana", "Yusuf", "Noor",
                "Khalid", "Amina", "Tariq", "Hafsa", "Rashid", "Maryam", "Saad", "Zainab", "Faisal", "Ayesha",
                "Nadeem", "Saima", "Waseem", "Rabia", "Junaid", "Sadia", "Adnan", "Nida", "Shahid", "Samia",
                "Rizwan", "Tahira", "Kamran", "Sobia", "Farhan", "Nazia", "Imran", "Shazia", "Noman", "Saba"
            };
            String[] lastNames = {
                "Khan", "Ali", "Ahmed", "Hassan", "Malik", "Sheikh", "Raza", "Shah", "Abbas", "Butt",
                "Iqbal", "Rashid", "Nawaz", "Chaudhry", "Mirza", "Baig", "Qureshi", "Hashmi", "Siddiqui", "Zaidi"
            };

            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                Student student = new Student();
                String firstName = firstNames[i % firstNames.length];
                String lastName = lastNames[i % lastNames.length];
                student.setName(firstName + " " + lastName);
                student.setRegNo("CS-" + String.format("%03d", i + 1));
                student.setEmailAddress((firstName + "." + lastName).toLowerCase() + "@student.com");
                student.setContactNumber("0300-" + String.format("%07d", 3000000 + i));
                student.setGuardianNumber("0300-" + String.format("%07d", 4000000 + i));
                student.setFatherName("Father of " + firstName + " " + lastName);
                student.setProgram(programs[i % programs.length]);
                student.setSession(sessions[i % sessions.length]);
                student.setSemester(String.valueOf((i % 8) + 1));
                student.setCampus(campuses[i % campuses.length]);
                student.setClassName("CS-" + ((i % 8) + 1) + "A");
                student.setNationality("Pakistani");
                student.setDob(LocalDate.of(2000 + (i % 5), (i % 12) + 1, (i % 28) + 1));
                student.setProfilePic("https://example.com/student" + (i + 1) + ".jpg");
                student.setPassword("password");
                student.setCgpa(2.5 + (random.nextDouble() * 2.5)); // CGPA between 2.5 and 5.0
                student.setWifiAccount("wifi-" + (i + 1));
                student.setOffice365Email((firstName + "." + lastName).toLowerCase() + "@office365.university.edu");
                student.setOffice365Pass("office365pass" + (i + 1));
                String batchName = batchNames[i % batchNames.length];
                student.setBatch(batchName);
                student.setBatchEntity(batches.get(i % batches.size()));
                student.setRollNo(batchName.replace("Batch-", "") + "-" + String.format("%02d", (i % 10) + 1));
                students.add(student);
            }
            List<Student> savedStudents = studentRepository.saveAll(students);
            System.out.println("Seeded " + savedStudents.size() + " students");

            // ========== CREATE COURSES ==========
            String[] courseCodes = {"CS-101", "CS-102", "CS-201", "CS-202", "CS-301", "CS-302", "CS-401", "CS-402"};
            String[] courseNames = {
                "Introduction to Programming", "Data Structures", "Object-Oriented Programming",
                "Database Systems", "Web Development", "Software Engineering", "Machine Learning", "Cloud Computing"
            };
            Integer[] credits = {3, 3, 3, 3, 3, 3, 3, 3};

            List<Course> courses = new ArrayList<>();
            for (int i = 0; i < courseCodes.length; i++) {
                Course course = new Course();
                course.setCourseNo(courseCodes[i]);
                course.setCourseName(courseNames[i]);
                course.setCredits(credits[i]);
                course.setTeacher(savedTeachers.get(i % savedTeachers.size()));
                courses.add(course);
            }
            List<Course> savedCourses = courseRepository.saveAll(courses);
            System.out.println("Seeded " + savedCourses.size() + " courses");

            // ========== ENROLL STUDENTS INTO COURSES ==========
            for (Course course : savedCourses) {
                // Each course gets students from different batches
                int studentsPerCourse = 20;
                for (int i = 0; i < studentsPerCourse; i++) {
                    Student student = savedStudents.get((course.getId().intValue() * studentsPerCourse + i) % savedStudents.size());
                    StudentCourseEnrollment enrollment = new StudentCourseEnrollment();
                    enrollment.setStudent(student);
                    enrollment.setCourse(course);
                    enrollmentRepository.save(enrollment);
                }
            }
            System.out.println("Created enrollments for all courses");

            // ========== CREATE MARKS FOR SOME ENROLLMENTS ==========
            List<StudentCourseEnrollment> allEnrollments = enrollmentRepository.findAll();
            for (int i = 0; i < Math.min(100, allEnrollments.size()); i++) {
                StudentCourseEnrollment enrollment = allEnrollments.get(i);
                Marks marks = new Marks();
                marks.setQuizMarks(5.0 + random.nextDouble() * 5.0);
                marks.setAssignmentMarks(5.0 + random.nextDouble() * 5.0);
                marks.setMidsMarks(15.0 + random.nextDouble() * 10.0);
                marks.setFinalMarks(30.0 + random.nextDouble() * 20.0);
                marks.setStudentCourseEnrollment(enrollment);
                enrollment.setMarks(marks);
                marksRepository.save(marks);
            }
            System.out.println("Created marks for enrollments");

            // ========== CREATE ATTENDANCE FOR SOME ENROLLMENTS ==========
            for (int i = 0; i < Math.min(100, allEnrollments.size()); i++) {
                StudentCourseEnrollment enrollment = allEnrollments.get(i);
                Attendance attendance = new Attendance();
                int totalClasses = 30 + random.nextInt(10);
                int presents = (int) (totalClasses * (0.7 + random.nextDouble() * 0.25)); // 70-95% attendance
                int absents = totalClasses - presents;
                attendance.setTotalClasses(totalClasses);
                attendance.setPresents(presents);
                attendance.setAbsents(absents);
                attendance.setPreviousAttendance("Regular attendance maintained.");
                attendance.setStudentCourseEnrollment(enrollment);
                enrollment.setAttendance(attendance);
                attendanceRepository.save(attendance);
            }
            System.out.println("Created attendance records");

            // ========== CREATE ASSIGNMENTS ==========
            for (Course course : savedCourses) {
                for (int i = 1; i <= 3; i++) {
                    Assignment assignment = new Assignment();
                    assignment.setTitle("Assignment " + i + " - " + course.getCourseName());
                    assignment.setDescription("Complete the exercises related to " + course.getCourseName() + " topics.");
                    assignment.setDueDate(LocalDate.now().plusDays(7 + i * 7));
                    assignment.setTeacher(course.getTeacher());
                    assignment.setCourse(course);
                    assignmentRepository.save(assignment);
                }
            }
            System.out.println("Created assignments for all courses");

            // ========== CREATE ANNOUNCEMENTS ==========
            String[] announcementMessages = {
                "Midterm exam scheduled for next week. Please prepare accordingly.",
                "Assignment submission deadline extended by 2 days.",
                "Class will be held online tomorrow due to weather conditions.",
                "Quiz will be conducted in the next class. Study chapters 1-5.",
                "Project presentations will start from next Monday."
            };
            for (Course course : savedCourses) {
                for (int i = 0; i < 2; i++) {
                    Announcement announcement = new Announcement();
                    announcement.setCourse(course);
                    announcement.setMessage(announcementMessages[(course.getId().intValue() + i) % announcementMessages.length]);
                    announcement.setTimestamp(LocalDateTime.now().minusDays(i * 7));
                    announcementRepository.save(announcement);
                }
            }
            System.out.println("Created announcements for all courses");

            // ========== CREATE LEARNING RESOURCES ==========
            String[] resourceTitles = {
                "Lecture Slides - Week 1", "Textbook Chapter 1", "Video Tutorial - Basics",
                "Practice Problems Set", "Reference Material", "Lab Manual"
            };
            for (Course course : savedCourses) {
                for (int i = 0; i < 3; i++) {
                    LearningResource resource = new LearningResource();
                    resource.setCourse(course);
                    resource.setTitle(resourceTitles[(course.getId().intValue() + i) % resourceTitles.length]);
                    resource.setFileUrl("https://example.com/resources/" + course.getCourseNo() + "/resource" + (i + 1) + ".pdf");
                    learningResourceRepository.save(resource);
                }
            }
            System.out.println("Created learning resources for all courses");

            System.out.println("\n=== DATA SEEDING COMPLETE ===");
            System.out.println("Admin: 1");
            System.out.println("Teachers: " + savedTeachers.size());
            System.out.println("Students: " + savedStudents.size());
            System.out.println("Batches: " + batches.size());
            System.out.println("Courses: " + savedCourses.size());
            System.out.println("Enrollments: " + enrollmentRepository.count());
            System.out.println("Marks: " + marksRepository.count());
            System.out.println("Attendance: " + attendanceRepository.count());
            System.out.println("Assignments: " + assignmentRepository.count());
            System.out.println("Announcements: " + announcementRepository.count());
            System.out.println("Learning Resources: " + learningResourceRepository.count());
        };
    }
}

