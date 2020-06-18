/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabeans.workwithderby;

import com.library.Books;
import com.library.Genres;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author lomatik
 */
@WebServlet(name = "create_new_servlet", urlPatterns = {"/create_new_servlet"})
public class create_new_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String surname_of_author = request.getParameter("surname_of_author");
        String name_of_author = request.getParameter("name_of_author");
        String name_of_book = request.getParameter("name_of_book");
        int year_of_book = Integer.parseInt(request.getParameter("year_of_book"));
        String city_of_print = request.getParameter("city_of_print");
        String id_genre = request.getParameter("id_genre");
        Genres genre = null;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("laba2-warPU");
        EntityManager em = factory.createEntityManager();
        
               
        List<Genres> genres = em.createNamedQuery("Genres.findAll").getResultList();
        
        for (Genres item: genres) {
            int id = item.getId();
            String name_genre = item.getNamegenre();
            String typegenre = item.getTypegenre();
            int yeargenre = item.getYeargenre();
            
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("namegenre: " + name_genre);
            System.out.println("typegenre: " + typegenre);
            System.out.println("yeargenre: " + yeargenre);
            
            if (item.getId() == Integer.parseInt(id_genre)) genre = item;
        }
        
        Books books = new Books();
        books.setName_of_book(name_of_book);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ValidatorFactory factoryV = Validation.buildDefaultValidatorFactory();
        Validator validator = factoryV.getValidator();
        Set<ConstraintViolation<Books>> constraints = validator.validate(books);
        out.println("<html><body><center>");
        for (ConstraintViolation<Books> constraint : constraints) {
        out.println("<h2>"+ constraint.getMessage()+"</h2>");         
        }
        if(constraints.size()==0){
        
        books.setIdgenre(genre);
        books.setName_of_author(name_of_author);
        books.setSurname_of_author(surname_of_author);
        books.setYear_of_book(year_of_book);
        books.setCity_of_print(city_of_print);
        
        em.getTransaction().begin();
        em.persist(books);
        em.getTransaction().commit();
        
        em.close();
        
        request.getRequestDispatcher("/successfullyinserted.jsp").forward(request,response);
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
