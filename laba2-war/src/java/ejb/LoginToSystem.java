/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lomatik
 */
@WebServlet(name = "LoginToSystem", urlPatterns = {"/LoginToSystem"})
public class LoginToSystem extends HttpServlet {

    private String login_admin = "admin";
    private String password_admin = "123";
    private String login_worker = "worker";
    private String password_worker = "123";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");         
        if (this.login_admin.equals(login) && this.password_admin.equals(password)) {           
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }            
            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(5*60);
            Cookie message = new Cookie("message", "Welcome, Administrator!");
            response.addCookie(message);
            response.sendRedirect("index.jsp");
        } 
       else if (this.login_worker.equals(login) && this.password_worker.equals(password)) {           
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }            
            HttpSession newSession = request.getSession(true);
            newSession.setMaxInactiveInterval(5*60);
            Cookie message = new Cookie("message", "Welcome, Worker!");
            response.addCookie(message);
            response.sendRedirect("index.jsp");
        }    
        
        else {          
            request.setCharacterEncoding("UTF-8");   
            request.getRequestDispatcher("/index.html").include(request, response);
            response.setContentType("text/html;charset=UTF-8");
            ServletOutputStream out = response.getOutputStream();
            out.write("<center><h1><font color=orange>Невірно введені дані!!! Повторіть спробу</font></h1></center>".getBytes("UTF-8"));         
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
