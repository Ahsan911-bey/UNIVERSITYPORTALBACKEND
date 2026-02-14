package com.universityportal.config;

import com.universityportal.entity.*;
import com.universityportal.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner seedData(StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            AdminRepository adminRepository,
            UserRepository userRepository,
            CourseRepository courseRepository,
            MarksRepository marksRepository,
            AttendanceRepository attendanceRepository,
            AssignmentRepository assignmentRepository,
            StudentCourseEnrollmentRepository enrollmentRepository,
            StudentSubmissionRepository studentSubmissionRepository,
            BatchRepository batchRepository,
            AnnouncementRepository announcementRepository,
            LearningResourceRepository learningResourceRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {

            // SAFETY CHECK: Only seed if the database is effectively empty (no users)
            if (userRepository.count() > 0) {
                System.out.println("Database already contains data. Seeding skipped.");
                return;
            }

            System.out.println("Starting Database Seeding...");
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
            admin.setSession("FA23");
            admin.setSemester("N/A");
            admin.setCampus("ISL");
            admin.setClassName("Admin Office");
            admin.setNationality("Pakistani");
            admin.setDob(LocalDate.of(1975, 1, 1));
            admin.setProfilePic("https://i.pinimg.com/736x/92/a5/9d/92a59d1de006b99bce2a064f1234867c.jpg");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setDesignation("Director");
            admin.setDepartment("IT Administration");
            Admin savedAdmin = adminRepository.save(admin);
            System.out.println("Seeded admin.");

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
            String[] programs = { "Computer Science", "Software Engineering", "Information Technology" };
            String[] teacherSessions = { "SP25", "FA24", "SU24" };
            String[] teacherCampuses = { "SWL", "ISL", "LHR" };

            List<Teacher> teachers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Teacher teacher = new Teacher();
                teacher.setName(teacherNames[i]);
                teacher.setRegNo("T-" + String.format("%03d", i + 1));
                teacher.setEmailAddress(
                        teacherNames[i].toLowerCase().replace(" ", ".").replace(".", "") + "@university.edu");
                teacher.setContactNumber("0300-" + String.format("%07d", 1000000 + i));
                teacher.setGuardianNumber("0300-" + String.format("%07d", 2000000 + i));
                teacher.setFatherName("Father of " + teacherNames[i]);
                teacher.setProgram(programs[i % programs.length]);
                teacher.setSession(teacherSessions[i % teacherSessions.length]);
                teacher.setSemester(String.valueOf((i % 8) + 1));
                teacher.setCampus(teacherCampuses[i % teacherCampuses.length]);
                teacher.setClassName("CS-" + (i % 8 + 1) + "A");
                teacher.setNationality("Pakistani");
                teacher.setDob(LocalDate.of(1970 + (i * 2), (i % 12) + 1, (i % 28) + 1));
                teacher.setProfilePic("https://i.pinimg.com/736x/02/ce/ee/02ceee8ad5f56722d5871a9bfe37b4d7.jpg");
                teacher.setPassword(passwordEncoder.encode("password"));
                teacher.setQualification(qualifications[i]);
                teacher.setSpecialization(specializations[i]);
                teachers.add(teacher);
            }
            List<Teacher> savedTeachers = teacherRepository.saveAll(teachers);
            System.out.println("Seeded " + savedTeachers.size() + " teachers");

            // ========== CREATE BATCHES ==========
            String[] batchNames = { "Batch-A", "Batch-B", "Batch-C", "Batch-D", "Batch-E" };
            List<Batch> batches = new ArrayList<>();
            for (String batchName : batchNames) {
                Batch batch = new Batch();
                batch.setName(batchName);
                batches.add(batchRepository.save(batch));
            }

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

            String[] sessionsPerBatch = { "SP25", "FA24", "SU24", "FA23", "SP23" };
            String[] classNamesPerBatch = { "CS-1A", "CS-2A", "CS-3A", "CS-4A", "CS-5A" };
            String[] campusesPerBatch = { "SWL", "ISL", "LHR", "SWL", "ISL" };
            String[] programsPerBatch = {
                    "Computer Science",
                    "Software Engineering",
                    "Information Technology",
                    "Computer Science",
                    "Software Engineering"
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

                int batchIndex = Math.min(i / 10, batches.size() - 1);
                student.setProgram(programsPerBatch[batchIndex]);
                student.setSession(sessionsPerBatch[batchIndex]);
                student.setCampus(campusesPerBatch[batchIndex]);
                student.setClassName(classNamesPerBatch[batchIndex]);

                student.setSemester(String.valueOf((i % 8) + 1));
                student.setNationality("Pakistani");
                student.setDob(LocalDate.of(2000 + (i % 5), (i % 12) + 1, (i % 28) + 1));
                student.setProfilePic(
                        "https://pyxis.nymag.com/v1/imgs/a85/912/a5ef47190c966169cf6e9c6da815b0f0ad-07-john-wick-2-2.rsquare.w400.jpg");
                student.setPassword(passwordEncoder.encode("password"));
                student.setCgpa(2.5 + (random.nextDouble() * 2.5));
                student.setWifiAccount("wifi-" + (i + 1));
                student.setOffice365Email((firstName + "." + lastName).toLowerCase() + "@office365.university.edu");
                student.setOffice365Pass("office365pass" + (i + 1));

                Batch assignedBatch = batches.get(batchIndex);
                student.setBatchEntity(assignedBatch);
                student.setRollNo(
                        assignedBatch.getName().replace("Batch-", "") + "-" + String.format("%02d", (i % 10) + 1));
                students.add(student);
            }
            List<Student> savedStudents = studentRepository.saveAll(students);
            System.out.println("Seeded " + savedStudents.size() + " students");

            // ========== CREATE COURSES ==========
            String[] allCourseNames = {
                    "Introduction to Programming", "Data Structures", "Object-Oriented Programming", "Database Systems",
                    "Web Development", "Software Engineering", "Machine Learning", "Cloud Computing",
                    "Operating Systems", "Computer Networks", "Discrete Mathematics", "Compiler Construction",
                    "Human Computer Interaction", "Information Security", "Mobile Application Development",
                    "Big Data Analytics",
                    "Artificial Intelligence", "Distributed Systems", "Digital Logic Design", "Computer Architecture",
                    "Numerical Computing", "Parallel Computing", "Information Retrieval", "Computer Graphics"
            };
            List<Course> courses = new ArrayList<>();
            for (int i = 0; i < allCourseNames.length; i++) {
                Course course = new Course();
                course.setCourseNo("CS-" + (101 + i));
                course.setCourseName(allCourseNames[i]);
                course.setCredits(3);
                course.setTeacher(savedTeachers.get(i % savedTeachers.size()));
                courses.add(course);
            }
            List<Course> savedCourses = courseRepository.saveAll(courses);
            System.out.println("Seeded " + savedCourses.size() + " courses");

            // ========== ENROLL STUDENTS ==========
            int coursesPerStudent = 6;
            int totalCourses = savedCourses.size();
            for (Student student : savedStudents) {
                int startIndex = (int) ((student.getId() - 1) % totalCourses);
                int step = Math.max(1, totalCourses / coursesPerStudent);
                for (int k = 0; k < coursesPerStudent; k++) {
                    Course course = savedCourses.get((startIndex + k * step) % totalCourses);
                    StudentCourseEnrollment enrollment = new StudentCourseEnrollment();
                    enrollment.setStudent(student);
                    enrollment.setCourse(course);
                    enrollmentRepository.save(enrollment);
                }
            }

            // ========== CREATE MARKS ==========
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

            // ========== CREATE ATTENDANCE ==========
            for (StudentCourseEnrollment enrollment : allEnrollments) {
                Attendance attendance = new Attendance();
                int totalClasses = 30 + random.nextInt(10);
                int presents = (int) (totalClasses * (0.7 + random.nextDouble() * 0.25));
                int absents = totalClasses - presents;
                attendance.setTotalClasses(totalClasses);
                attendance.setPresents(presents);
                attendance.setAbsents(absents);
                attendance.setPreviousAttendance("Regular attendance maintained.");
                attendance.setStudentCourseEnrollment(enrollment);
                attendanceRepository.save(attendance);
                enrollment.setAttendance(attendance);
                enrollmentRepository.save(enrollment);
            }

            // ========== CREATE ASSIGNMENTS ==========
            // Use relative path or a default string for now, to avoid C: constraints
            // crashing on other OS
            // In a real app, this should be a configurable property.
            String teacherFilePath = "src/main/resources/filestorage/assignment.pdf";

            for (Course course : savedCourses) {
                for (int i = 1; i <= 3; i++) {
                    Assignment assignment = new Assignment();
                    assignment.setTitle("Assignment " + i + " - " + course.getCourseName());
                    assignment
                            .setDescription("Complete the exercises related to " + course.getCourseName() + " topics.");
                    assignment.setDueDate(LocalDate.now().plusDays(7 + i * 7));
                    assignment.setTeacher(course.getTeacher());
                    assignment.setCourse(course);
                    assignment.setFileUrl(teacherFilePath);
                    assignmentRepository.save(assignment);
                }
            }

            // ========== CREATE STUDENT SUBMISSIONS ==========
            for (Student student : savedStudents) {
                List<StudentCourseEnrollment> studentEnrollments = enrollmentRepository
                        .findByStudentId(student.getId());
                for (StudentCourseEnrollment enrollment : studentEnrollments) {
                    List<Assignment> courseAssignments = assignmentRepository
                            .findByCourseId(enrollment.getCourse().getId());
                    for (int i = 0; i < courseAssignments.size(); i++) {
                        Assignment assignment = courseAssignments.get(i);
                        if (random.nextDouble() < 0.3) {
                            StudentSubmission submission = new StudentSubmission();
                            submission.setStudent(student);
                            submission.setAssignment(assignment);
                            submission.setFileUrl("src/main/resources/filestorage/assignment.pdf");
                            if (random.nextDouble() < 0.7) {
                                submission.setSubmittedAt(LocalDateTime.now().minusDays(random.nextInt(5)));
                            } else {
                                submission.setSubmittedAt(LocalDateTime.now().plusDays(random.nextInt(3) + 1));
                            }
                            studentSubmissionRepository.save(submission);
                        }
                    }
                }
            }

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
                    announcement.setMessage(
                            announcementMessages[(course.getId().intValue() + i) % announcementMessages.length]);
                    announcement.setTimestamp(LocalDateTime.now().minusDays(i * 7));
                    announcementRepository.save(announcement);
                }
            }

            // ========== CREATE LEARNING RESOURCES ==========
            String[] resourceTitles = {
                    "Introductory Video",
                    "Supplementary Video"
            };
            String[] resourceUrls = {
                    "https://www.youtube.com/watch?v=nKE8pqvhrs8",
                    "https://www.youtube.com/watch?v=gJrjgg1KVL4"
            };
            for (Course course : savedCourses) {
                for (int i = 0; i < resourceUrls.length; i++) {
                    LearningResource resource = new LearningResource();
                    resource.setCourse(course);
                    resource.setTitle(course.getCourseNo() + " - " + resourceTitles[i]);
                    resource.setFileUrl(resourceUrls[i]);
                    learningResourceRepository.save(resource);
                }
            }

            System.out.println("=== DATA SEEDING COMPLETE ===");
        };
    }
}
