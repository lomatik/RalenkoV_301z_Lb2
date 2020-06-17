/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabeans.workwithderby;

import com.library.Genres;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lomatik
 */
@WebServlet(name = "Show_to_delete_genre", urlPatterns = {"/Show_to_delete_genre"})
public class Show_to_delete_genre extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("laba2-warPU");
        EntityManager em = factory.createEntityManager();
        
        Query query = null;
        
        String And = " AND ";
        
        String sql;
        
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
        
        if ("".equals(name) && "".equals(type) 
                && "".equals(year)){
        query = em.createNamedQuery("Genres.findAll");
        }
        
        else if (!"".equals(name) || !"".equals(type) 
                || !"".equals(year) ){
            sql = "SELECT g FROM Genres g WHERE ";
            if (!"".equals(name)) {
                sql += "g.namegenre = :namegenre";
                if(!"".equals(type) || !"".equals(year)){
                    sql += And;
                }
            }
        
            if (!"".equals(type)) {
                sql += "g.typegenre = :typegenre";
                if(!"".equals(type)){
                    sql += And;
                }
            }
        
            if (!"".equals(year)) {
                sql += "g.yeargenre = :yeargenre";
            }
            query= em.createQuery(sql);
            if (!"".equals(name)) query.setParameter("namegenre", name);
            if (!"".equals(type)) query.setParameter("typegenre", type);
            if (!"".equals(year)) query.setParameter("yeargenre", Integer.parseInt(year));
        }
        
        List<Genres> genres = query.getResultList();
        
        String[] nameArray = new String[1000];
        String[] typeArray = new String[1000];
        int[] yearArray = new int[1000];
        int countofArray = 0;
        
        for (Genres item: genres){
            int id = item.getId();
            String namegenre = item.getNamegenre();
            String typegenre = item.getTypegenre();
            int yeargenre = item.getYeargenre();
            
            nameArray[countofArray] = namegenre;
            typeArray[countofArray] = typegenre;
            yearArray[countofArray] = yeargenre;
            
            countofArray++;
            
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("namegenre: " + namegenre);
            System.out.println("typegenre: " + typegenre);
            System.out.println("yeargenre: " + yeargenre);
            
            System.out.println(item.toString());
        }
        
        em.close();
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("/selecttodelete_genre.jsp").forward(request,response);
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
        Logger.getLogger(Show_to_delete.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(Show_to_delete.class.getName()).log(Level.SEVERE, null, ex);
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
