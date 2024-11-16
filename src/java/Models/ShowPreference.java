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
public class ShowPreference implements Model {

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctx) {

        try {

            Dao md = Dao.getInstance();
            Statement st = md.getConnection();

            String teachPrefQuery = "SELECT * from pref_table";
            ResultSet pref_rs = md.getData(st, teachPrefQuery);

            Statement st1 = md.getConnection();

            List<TeacherPreference> tpList = new ArrayList<>();

            while (pref_rs.next()) {

                String username = pref_rs.getString(1);

                String teacherExpQuery = "SELECT DISTINCT a.teacher_name, b.ov_exp FROM teacher_info a INNER JOIN teacher_exp b ON a.username = b.username WHERE (a.username = '" + username + "')";
                ResultSet teacherDetail_rs = md.getData(st1, teacherExpQuery);

                teacherDetail_rs.next();
                String teacherName = teacherDetail_rs.getString("teacher_name");
                int exp = teacherDetail_rs.getInt("ov_exp");
                teacherDetail_rs.close();

                String pref1 = pref_rs.getString(2);
                String pref2 = pref_rs.getString(3);
                String pref3 = pref_rs.getString(4);
                
                TeacherPreference tp = new TeacherPreference();

                tp.setUsername(username);
                tp.setTeacherName(teacherName);
                tp.setExp(exp);
                tp.setPref1(pref1);
                tp.setPref2(pref2);
                tp.setPref3(pref3);

                tpList.add(tp);

            }
            if(st1 != null) {
                st1.close();
            }
            if(pref_rs != null) {
                pref_rs.close();
            }
            if(st != null) {
                st.close();
            }
            
            request.setAttribute("teacherList", tpList);

            request.getRequestDispatcher("/WEB-INF/pages/adminlogin.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
