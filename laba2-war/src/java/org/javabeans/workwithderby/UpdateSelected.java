/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabeans.workwithderby;

import com.library.Books;
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
@WebServlet(name = "UpdateSelected", urlPatterns = {"/UpdateSelected"})
public class UpdateSelected extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("laba2-warPU");
        EntityManager em = factory.createEntityManager();
        
        String And = " , ";
        
        String surname_of_author;
        String name_of_author;
        String name_of_book;   
        String year_of_book;
        String city_of_print;
        String id_genre_book;
        
        if (request.getParameter("surname_of_author") == null) {
            surname_of_author= "";
        }
        else surname_of_author = request.getParameter("surname_of_author");
        
        if (request.getParameter("name_of_author") == null) {
            name_of_author= "";
        }
        else name_of_author = request.getParameter("name_of_author");
        
        if (request.getParameter("name_of_book") == null){
            name_of_book= "";
        }
        else name_of_book = request.getParameter("name_of_book");
        
        if (request.getParameter("year_of_book") == null){
            year_of_book= "";
        }
        else year_of_book = request.getParameter("year_of_book");
        
        if (request.getParameter("city_of_print") == null){
            city_of_print = "";
        }
        else city_of_print = request.getParameter("city_of_print");
        
        if (request.getParameter("id_genre") == null) id_genre_book = "";
        else id_genre_book = request.getParameter("id_genre");
        
        String idchecked = request.getParameter("idchecked");
        
        Books book = em.find(Books.class, Integer.parseInt(idchecked));
        
        String sql;
        sql = "UPDATE BOOKS SET ";
        
        if (!"".equals(surname_of_author)) {
            book.setSurname_of_author(surname_of_author);
                sql += "SURNAMEAUTHOR = '" + surname_of_author + "'";
                if(!"".equals(name_of_author) || !"".equals(name_of_book) 
                    || !"".equals(year_of_book) || !"".equals(city_of_print)
                        || !"".equals(id_genre_book)){
                    sql += And;
                }
            }
        
            if (!"".equals(name_of_author)) {
                book.setName_of_author(name_of_author);
                sql += "NAMEAUTHOR = '" + name_of_author + "'";
                if(!"".equals(name_of_book) || !"".equals(year_of_book) 
                        || !"".equals(city_of_print) || !"".equals(id_genre_book)){
                    sql += And;
                }
            }
        
            if (!"".equals(name_of_book)) {
                book.setName_of_book(name_of_book);
                sql += "NAMEBOOK = '" + name_of_book + "'";
                if(!"".equals(year_of_book) || !"".equals(city_of_print)
                        || !"".equals(id_genre_book)){
                    sql += And;
                }
            }
        
            if (!"".equals(year_of_book)) {
                book.setYear_of_book(Integer.parseInt(year_of_book));
                sql += "YEARBOOK = " + year_of_book;
                if(!"".equals(city_of_print) || !"".equals(id_genre_book)){
                    sql += And;
                }
            }
        
            if (!"".equals(city_of_print)) {
                book.setCity_of_print(city_of_print);
                sql += "CITYOFPRINT = '" + city_of_print + "'";
                if(!"".equals(id_genre_book)){
                    sql += And;
                }
            }
            
            if(!"".equals(id_genre_book)){
                Genres genre = em.find(Genres.class, Integer.parseInt(id_genre_book));
                book.setIdgenre(genre);
                sql += "IDGENRE = " + id_genre_book;
            }
        
        sql+=" WHERE ID = " +idchecked;
        
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
        
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
