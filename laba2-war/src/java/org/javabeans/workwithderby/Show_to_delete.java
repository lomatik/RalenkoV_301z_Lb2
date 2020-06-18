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
@WebServlet(name = "Show_to_delete", urlPatterns = {"/Show_to_delete"})
public class Show_to_delete extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        request.getSession(true);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("laba2-warPU");
        EntityManager em = factory.createEntityManager();
        
        Query query = null;
        
        String And = " AND ";
        
        String sql;
        
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
        
        if ("".equals(surname_of_author) && "".equals(name_of_author) 
                && "".equals(name_of_book) && "".equals(year_of_book) 
                && "".equals(city_of_print) && "".equals(id_genre_book)){
            query = em.createNamedQuery("Books.findAll");
        }
        
        else if (!"".equals(surname_of_author) || !"".equals(name_of_author) 
                || !"".equals(name_of_book) || !"".equals(year_of_book) 
                || !"".equals(city_of_print) || !"".equals(id_genre_book)){
            sql = "SELECT b FROM Books b WHERE ";
            if (!"".equals(surname_of_author)) {
                sql += "b.surname_of_author = :surname_of_author";
                if(!"".equals(name_of_author) || !"".equals(name_of_book) 
                    || !"".equals(year_of_book) || !"".equals(city_of_print)
                        || !"".equals(id_genre_book)){
                    sql += And;
                }
            }
        
            if (!"".equals(name_of_author)) {
                sql += "b.name_of_author = :name_of_author";
                if(!"".equals(name_of_book) || !"".equals(year_of_book) 
                        || !"".equals(city_of_print) || !"".equals(id_genre_book)){
                    sql += And;
                }
            }
        
            if (!"".equals(name_of_book)) {
                sql += "b.name_of_book = :name_of_book";
                if(!"".equals(year_of_book) || !"".equals(city_of_print)
                        || !"".equals(id_genre_book)){
                    sql += And;
                }
            }
        
            if (!"".equals(year_of_book)) {
                sql += "b.year_of_book = :year_of_book";
                if(!"".equals(city_of_print) || !"".equals(id_genre_book)){
                    sql += And;
                }
            }
        
            if (!"".equals(year_of_book)) {
                sql += "b.year_of_book = :year_of_book";
                if(!"".equals(id_genre_book)){
                    sql += And;
                }
            }
            
            if(!"".equals(id_genre_book)){
                sql += "b.idgenre = :idgenre";
            }
            
            query = em.createQuery(sql);
            
            if (!"".equals(surname_of_author)) query.setParameter("surname_of_author", surname_of_author);
            if (!"".equals(name_of_author)) query.setParameter("name_of_author", name_of_author);
            if (!"".equals(name_of_book)) query.setParameter("name_of_book", name_of_book);
            if (!"".equals(year_of_book)) query.setParameter("year_of_book", Integer.parseInt(year_of_book));
            if (!"".equals(city_of_print)) query.setParameter("city_of_print", city_of_print);
            if (!"".equals(id_genre_book)) {
                Genres genre = em.find(Genres.class, Integer.parseInt(id_genre_book));
                query.setParameter("idgenre", genre);
            }
        }
        
        String[] namegenreArray = new String[10000];
        List<Genres> genres = em.createNamedQuery("Genres.findAll").getResultList();
        
        for (Genres item: genres) {
            int id_genre = item.getId();
            String name_genre = item.getNamegenre();
            String typegenre = item.getTypegenre();
            int yeargenre = item.getYeargenre();
            
            System.out.println("\n================\n");
            System.out.println("id: " + id_genre);
            System.out.println("namegenre: " + name_genre);
            System.out.println("typegenre: " + typegenre);
            System.out.println("yeargenre: " + yeargenre);
            
            System.out.println(item.toString());
            namegenreArray[id_genre] = name_genre;
        }
        
        List<Books> books = query.getResultList();

        for (Books item: books) {
            int id = item.getId();
            String name = item.getName_of_author();
            String surnameauthor = item.getSurname_of_author();
            String namebook = item.getName_of_book();
            int yearbook = item.getYear_of_book();
            String cityofprint = item.getCity_of_print();
            int id_genre = item.getIdgenre();
            
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("name: " + name);
            System.out.println("surnameauthor: " + surnameauthor);
            System.out.println("namebook: " + namebook);
            System.out.println("yearbook: " + yearbook);
            System.out.println("cityofprint: " + cityofprint);
            System.out.println("idgenre: " + id_genre);
        }

        em.close();
        request.setAttribute("Books", books);
        request.getRequestDispatcher("/selecttodelete.jsp").forward(request,response);
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
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Show_to_delete.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
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
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Show_to_delete.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
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
