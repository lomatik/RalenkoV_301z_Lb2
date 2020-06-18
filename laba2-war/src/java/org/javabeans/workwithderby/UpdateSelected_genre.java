/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabeans.workwithderby;

import com.library.Genres;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author lomatik
 */
@WebServlet(name = "UpdateSelected_genre", urlPatterns = {"/UpdateSelected_genre"})
public class UpdateSelected_genre extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("laba2-warPU");
        EntityManager em = factory.createEntityManager();
        
        String And = " ,";
        
        String name;
        String type;
        String year;   
        
        if (request.getParameter("name") == null) {
            name= "";
        }
        else name = request.getParameter("name");
        
        if (request.getParameter("type") == null) {
            type= "";
        }
        else type = request.getParameter("type");
        
        if (request.getParameter("year") == null){
            year= "";
        }
        else year = request.getParameter("year");
      
        String idchecked = request.getParameter("idchecked");
        
        Genres genre = em.find(Genres.class, Integer.parseInt(idchecked));
        
        String sql;
        sql = "UPDATE GENRES SET ";
        
        if (!"".equals(name)) {
            sql += "NAMEGENRE = '" + name + "'";
            genre.setNamegenre(name);
            if (!"".equals(type) || !"".equals(year)) sql += And;
        }
        
        if (!"".equals(type)) {
            sql += "TYPEGENRE = '" + type + "'";
            genre.setTypegenre(type);
            if (!"".equals(year)) sql += And;
        }
        
        if (!"".equals(year)) {
            genre.setYeargenre(Integer.parseInt(year));
            sql += "YEARGENRE = " + year;
        }
        
        em.getTransaction().begin();
        em.merge(genre);
        em.getTransaction().commit();
        
        sql+=" WHERE ID = " +idchecked;
        
        System.out.println(sql);
        
        em.close();
        
        request.getRequestDispatcher("/updatesuccess.jsp").forward(request,response);
        
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateSelected.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateSelected.class.getName()).log(Level.SEVERE, null, ex);
        }
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
