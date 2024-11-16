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
public class GetTeacherCount implements Model {

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctx) {
        
        try {
            
            Dao md = Dao.getInstance();
            Statement st = md.getConnection();
            
            String subjectQuery = "SELECT * FROM subject_table";
            ResultSet subject_rs = md.getData(st, subjectQuery);
            
            List<Subject> subjects = new ArrayList<>();
            
            while(subject_rs.next()){
                
                String sub_code = subject_rs.getString(1);
                String sub_name = subject_rs.getString(2);
                
                Subject subject = new Subject(sub_code, sub_name);
                
                subjects.add(subject);
            }
            if(subject_rs != null) {
                subject_rs.close();
            }

            if(st != null) {
                st.close();
            }
            
            request.setAttribute("subjects", subjects);
            request.getRequestDispatcher("/WEB-INF/pages/getTeacherCount.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
}
