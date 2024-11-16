/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author suhaib
 */
public class LoginModel implements Model{

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt) {
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        Dao md = Dao.getInstance();
        
        try(Statement st = md.getConnection()){
            String query = "SELECT * FROM login_table WHERE(username = '"+uname+"')";
            String Upass;
            int role_id;
            
            ResultSet rs = md.getData(st, query);
            if(!rs.next()){
                request.setAttribute("error", "User does not Exists!");
                request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
            }
            Upass = rs.getString(2);
            role_id = rs.getInt(3);
            rs.close();
            if(!pass.equals(Upass)){
                request.setAttribute("error", "Wrong Password!");
                request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
            }
            else{
                HttpSession session = request.getSession(true);
                session.setAttribute("username", uname);
                
                if(role_id == 1){
                    String teacher_name = "SELECT teacher_name FROM teacher_info WHERE (username = '"+uname+"')";
                    ResultSet teacher_name_rs = md.getData(st, teacher_name);
                    teacher_name_rs.next();
                    session.setAttribute("teacher_name", teacher_name_rs.getString(1));
                    teacher_name_rs.close();
                    request.getRequestDispatcher("/WEB-INF/pages/userlogin.jsp").forward(request, response);
                }
                else{
                    new ShowPreference().doBusiness(request, response, ctxt);
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
