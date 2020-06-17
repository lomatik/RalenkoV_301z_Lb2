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
@WebServlet(name = "Show_genres_to_insert", urlPatterns = {"/Show_genres_to_insert"})
public class Show_genres_to_insert extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        String next_jsp;
        
        if (request.getParameter("next_jsp") == null) {
            next_jsp= "";
        }
        
        else next_jsp = request.getParameter("next_jsp");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("laba2-warPU");
        EntityManager em = factory.createEntityManager();
        
        Query query = em.createNamedQuery("Genres.findAll");;
        
        List<Genres> genres = query.getResultList();
        for (Genres item: genres){
            int id = item.getId();
            String namegenre = item.getNamegenre();
            String typegenre = item.getTypegenre();
            int yeargenre = item.getYeargenre();
            
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("namegenre: " + namegenre);
            System.out.println("typegenre: " + typegenre);
            System.out.println("yeargenre: " + yeargenre);
            
            System.out.println(item.toString());
        }
        em.close();
        request.setAttribute("genres", genres);
        if ("".equals(next_jsp)) request.getRequestDispatcher("/insert.jsp").forward(request,response);
        else request.getRequestDispatcher("/"+next_jsp+".jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Show_to_Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Show_to_Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}