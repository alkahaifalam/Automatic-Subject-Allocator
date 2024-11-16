/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author suhaib
 */
public class LogoutModel implements Model{

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctx) {
        
        HttpSession session = request.getSession(false);
        
        session.invalidate();
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
