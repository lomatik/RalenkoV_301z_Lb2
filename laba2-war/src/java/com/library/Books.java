/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 *
 * @author lomatik
 */
@Entity
@Table(name = "BOOKS", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query="SELECT b FROM Books b"), 
    @NamedQuery(name = "Books.findByID", query = "SELECT b FROM Books b WHERE b.id = :id"), 
    @NamedQuery(name = "Books.findByNamebook", query = "SELECT b FROM Books b WHERE b.name_of_book = :name_of_book")})
public class Books implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    public int id;
    @Column(name="SURNAMEAUTHOR")
    public String surname_of_author;
    @Column(name="NAMEAUTHOR")
    public String name_of_author;
    @Valid
    @NotNull(message="Введіть назву книги!!!")
    @Pattern(regexp = "[a-zA-Z]*", message = "Ви ввели некоректні символи у назві книги!!!")
    @Size(min = 3, message="Довжина назви книги повинна бути більшою за 3 символи!!!")
    @Column(name="NAMEBOOK")
    public String name_of_book;
    @Column(name="YEARBOOK")
    public int year_of_book;
    @Column(name="CITYOFPRINT")
    public String city_of_print;
    @JoinColumn(name = "IDGENRE", referencedColumnName = "ID")
    @OneToOne
    public Genres idgenre;

    public Books() {
    }

    public Books(String surname_of_author, String name_of_author, String name_of_book, int year_of_book, String city_of_print) {
        this.surname_of_author = surname_of_author;
        this.name_of_author = name_of_author;
        this.name_of_book = name_of_book;
        this.year_of_book = year_of_book;
        this.city_of_print = city_of_print;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname_of_author() {
        return surname_of_author;
    }

    public void setSurname_of_author(String surname_of_author) {
        this.surname_of_author = surname_of_author;
    }

    public String getName_of_author() {
        return name_of_author;
    }

    public void setName_of_author(String name_of_author) {
        this.name_of_author = name_of_author;
    }

    public String getName_of_book() {
        return name_of_book;
    }

    public void setName_of_book(String name_of_book) {
        this.name_of_book = name_of_book;
    }

    public int getYear_of_book() {
        return year_of_book;
    }

    public void setYear_of_book(int year_of_book) {
        this.year_of_book = year_of_book;
    }

    public String getCity_of_print() {
        return city_of_print;
    }

    public void setCity_of_print(String city_of_print) {
        this.city_of_print = city_of_print;
    }

    public int getIdgenre() {
        return idgenre.getId();
    }
    
    public String getGenre(){
        return idgenre.getNamegenre();
    }

    public void setIdgenre(Genres idgenre) {
        this.idgenre = idgenre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.surname_of_author);
        hash = 79 * hash + Objects.hashCode(this.name_of_author);
        hash = 79 * hash + Objects.hashCode(this.name_of_book);
        hash = 79 * hash + this.year_of_book;
        hash = 79 * hash + Objects.hashCode(this.city_of_print);
        hash = 79 * hash + Objects.hashCode(this.idgenre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Books other = (Books) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.year_of_book != other.year_of_book) {
            return false;
        }
        if (!Objects.equals(this.surname_of_author, other.surname_of_author)) {
            return false;
        }
        if (!Objects.equals(this.name_of_author, other.name_of_author)) {
            return false;
        }
        if (!Objects.equals(this.name_of_book, other.name_of_book)) {
            return false;
        }
        if (!Objects.equals(this.city_of_print, other.city_of_print)) {
            return false;
        }
        if (!Objects.equals(this.idgenre, other.idgenre)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", surname_of_author=" + surname_of_author + ", name_of_author=" + name_of_author + ", name_of_book=" + name_of_book + ", year_of_book=" + year_of_book + ", city_of_print=" + city_of_print + '}';
    }
    
}
