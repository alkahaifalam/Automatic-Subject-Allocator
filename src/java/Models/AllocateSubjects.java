/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;

/**
 *
 * @author suhaib
 */
public class AllocateSubjects implements Model {

    Dao md = Dao.getInstance();

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctx) {

        try(Statement st = md.getConnection()){
            
            List<Teacher> teachers = new ArrayList<>();

            String getPref = "SELECT * FROM pref_table";
            ResultSet pref = md.getData(st, getPref);

            while (pref.next()) {

                String username = pref.getString(1);

                Statement st1 = md.getConnection();
                String teacher_info_query = "SELECT DISTINCT a.teacher_name, b.ov_exp FROM teacher_info a INNER JOIN teacher_exp b ON a.username = b.username WHERE (a.username = '" + username + "')";
                ResultSet teacher_info = md.getData(st1, teacher_info_query);
                teacher_info.next();

                String teacher_name = teacher_info.getString(1);
                int exp = teacher_info.getInt(2); 

                Teacher teacher = new Teacher(teacher_name, username, Arrays.asList(pref.getString(2), pref.getString(3), pref.getString(4)), exp);

                teachers.add(teacher);
                
                teacher_info.close();
                st1.close();
            }
            
            pref.close();
            
            String subToBeAllocQuer = "SELECT * FROM subject_table";
            ResultSet subToBeAlloc = md.getData(st, subToBeAllocQuer);
            
            Map<Subject, List<Teacher>> subjectTeacherMap = new HashMap<>();
            List<Teacher> sub_teachers_list = new ArrayList<>();
            Map<Subject, Integer> teacherCountMap = new HashMap<>();
            List<Subject> subjects = new ArrayList<>();
//            int i = 1;
            while (subToBeAlloc.next()) {
                String sub_code = subToBeAlloc.getString(1);
                String sub_name = subToBeAlloc.getString(2);
                Subject subject = new Subject(sub_code, sub_name);
                subjects.add(subject);
//                String index = "teacher_count"+i;
//                teacherCountMap.put(subject, Integer.parseInt(request.getParameter(index)));
//                i++;
                for(Teacher teacher : teachers){
                    if(teacher.preferences.contains(sub_code)){
                        sub_teachers_list.add(teacher);
                    }
                }
                subjectTeacherMap.put(subject, sub_teachers_list);
            }

            List<Allocation> allocations = new ArrayList<>();

            // Allocate subjects to teachers
            for (Subject subject : subjects) {
                Allocation allocation = AStarAlgorithm.allocateSubjectToTeacher(subject, teachers);
                if (allocation != null) {
                    allocations.add(allocation);
                } else {
                    System.out.println("No teacher available for subject: " + subject.name);
                }
            }

            setCount(allocations);
            
            subToBeAlloc.close();

            // Print allocations
            request.setAttribute("allocations", allocations);
            request.getRequestDispatcher("/WEB-INF/pages/showAllocated.jsp").forward(request, response);

//            for (Allocation allocation : allocations) {
//                System.out.println("Subject: " + allocation.subject.name + ", Teacher: " + allocation.teacher.name);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setCount(List<Allocation> allocations) {
        try(Statement st = md.getConnection()){

            for (Allocation allocation : allocations) {
                String teacher_name = allocation.getTeacher().getName();
                String subject_code = allocation.getSubject().getCode();
                String username = allocation.getTeacher().getUsername();
                int ov_exp = allocation.getTeacher().getExperience();

//                System.out.println(teacher_name + " " + subject_code + "  " + username);

                String getCountQuery = "SELECT count FROM teacher_exp WHERE (username = '" + username + "' and sub_code = '" + subject_code + "')";
                ResultSet rs_count = md.getData(st, getCountQuery);

                if (!rs_count.next()) {
                    String getSnoQuer = "SELECT MAX(sno) FROM teacher_exp";
                    ResultSet rs_sno = md.getData(st, getSnoQuer);
                    rs_sno.next();
                    int sno = rs_sno.getInt(1);
                    sno++;
                    String setCountQuery = "INSERT INTO teacher_exp VALUES (" + sno + "','" + username + "','" + subject_code + "', 1, "+ov_exp+", 1)";
                    md.storeData(st, setCountQuery);
                    
                    rs_sno.close();
                } else {
                    int count = rs_count.getInt(1);
                    count++;

                    String setCountQuery = "UPDATE teacher_exp SET count = " + count + " WHERE (username = '" + username + "' and sub_code = '" + subject_code + "')";
                    md.storeData(st, setCountQuery);
                }
                rs_count.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
//    private Map<Subject, List<Teacher>> getSubjectTeacherMap() {
//        Map<Subject, List<Teacher>> subjectTeacherMap = new HashMap<>();
//        Statement st1 = md.getConnection();
//        
//        String subQuer = "SELECT * FROM subject_table";
//        ResultSet sub_rs = md.getData(st1, subQuer);
//        
//        Statement st2 = md.getConnection();
//        
//        while(sub_rs.next()){
//            String tech_quer = "SELECT a.teacher_name FROM teacher_exp WHERE (pef"
//        }
//    }

}

class AStarAlgorithm {

    public static Allocation allocateSubjectToTeacher(Subject subject, List<Teacher> teachers) {
        Teacher selectedTeacher = null;
        double maxScore = Double.NEGATIVE_INFINITY;

        for (Teacher teacher : teachers) {
            if (isEligible(teacher, subject)) {
                if (teacher.preferences.contains(subject.code)) {
                    double score = calculateScore(subject, teacher);
                    if (score > maxScore) {
                        selectedTeacher = teacher;
                        maxScore = score;
                    }
                }
            }
        }

        if (selectedTeacher != null) {
            return new Allocation(subject, selectedTeacher);
        }

        return null; // No suitable teacher found
    }

    private static List<Teacher> selectBestTeachers(List<Teacher> teachers, int requiredCount) {
        teachers.sort(Comparator.comparingDouble(t -> calculateScore(null, (Teacher) t)).reversed());
        return teachers.subList(0, Math.min(requiredCount, teachers.size()));
    }

    private static double calculateScore(Subject subject, Teacher teacher) {
        double preferenceWeight = 1.5;
        double experienceWeight = 0.5;
//        double applicationTimeWeight = 0.1;

        int preferenceIndex = teacher.preferences.indexOf(subject.name);
        int experience = teacher.experience;

        return preferenceWeight * (1.0 / (preferenceIndex + 1)) + experienceWeight * experience;
    }

    private static boolean isEligible(Teacher teacher, Subject subject) {

        try {
            Dao md = Dao.getInstance();
            Statement st = md.getConnection();

            String getCountQuery = "SELECT count FROM teacher_exp WHERE (username = '" + teacher.getUsername() + "' and sub_code = '" + subject.getCode() + "')";
            ResultSet rs_getCount = md.getData(st, getCountQuery);
            if (!rs_getCount.next()) {
                return true;
            }
            int count = rs_getCount.getInt(1);
            if (count == 3) {
                String setCountQuery = "UPDATE teacher_exp SET count = 0 WHERE (username = '" + teacher.getUsername() + "' and sub_code = '" + subject.getCode() + "')";
                md.storeData(st, setCountQuery);
                return false;
            }
            rs_getCount.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
