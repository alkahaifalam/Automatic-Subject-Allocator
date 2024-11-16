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
public class GetPreference implements Model {

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctx) {
        
        try {
            Dao md = Dao.getInstance();
            Statement st = md.getConnection();
            
            HttpSession session = request.getSession(false);
            String username = (String)session.getAttribute("username");
            
            String checkQuery = "SELECT * FROM pref_table WHERE (username = "+username+")";
            ResultSet rs = md.getData(st, checkQuery);
            if(rs.next()){
                request.setAttribute("error", "Already Submitted!");
                request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
            }
            else{
                String pref1 = request.getParameter("pref1");
                if(pref1.equals("Preference 1")) pref1 = null;
                String pref2 = request.getParameter("pref2");
                if(pref2.equals("Preference 2")) pref2 = null;
                String pref3 = request.getParameter("pref3");
                if(pref3.equals("Preference 3")) pref3 = null;

                String prefQuery = "INSERT INTO pref_table VALUES ('"+username+"','"+pref1+"','"+pref2+"','"+pref3+"')";
                md.storeData(st, prefQuery);

                request.getRequestDispatcher("/WEB-INF/pages/userSuccess.jsp").forward(request, response);
            }
            if(rs != null) {
                rs.close();
            }

            if(st != null) {
                st.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
