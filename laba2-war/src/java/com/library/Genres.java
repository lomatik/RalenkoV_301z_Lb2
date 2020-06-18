/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;

/**
 *
 * @author lomatik
 */
@Entity
@Table(name = "GENRES", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Genres.findAll", query="SELECT g FROM Genres g"), 
    @NamedQuery(name = "Genres.findById", query = "SELECT g FROM Genres g WHERE g.id = :id"), 
    @NamedQuery(name = "Genres.findByNameGenre", query = "SELECT g FROM Genres g WHERE g.namegenre = :namegenre"), 
    @NamedQuery(name = "Genres.findByTypeGenre", query = "SELECT g FROM Genres g WHERE g.typegenre = :typegenre")})
public class Genres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name="NAMEGENRE")
    String namegenre;
    @Column(name="TYPEGENRE")
    String typegenre;
    //@Valid
    //@NotNull(message="Введіть рік жанру!!!!!")
    //@Pattern(regexp = "[0-9]*", message = "В році жанру знаходяться символи, які відмінні від чисел!!!!!")
    //@Size(min = 3, message="Довжина року повинна бути більше 3 символів!!!!!")
    @Column(name="YEARGENRE")
    int yeargenre;

    public Genres() {
    }

    public Genres(String namegenre, String typegenre, int yeargenre) {
        this.namegenre = namegenre;
        this.typegenre = typegenre;
        this.yeargenre = yeargenre;
    }
    public String getNamegenre() {
        return namegenre;
    }

    public void setNamegenre(String namegenre) {
        this.namegenre = namegenre;
    }

    public String getTypegenre() {
        return typegenre;
    }

    public void setTypegenre(String typegenre) {
        this.typegenre = typegenre;
    }

    public int getYeargenre() {
        return yeargenre;
    }

    public void setYeargenre(int yeargenre) {
        this.yeargenre = yeargenre;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.namegenre);
        hash = 67 * hash + Objects.hashCode(this.typegenre);
        hash = 67 * hash + this.yeargenre;
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
        final Genres other = (Genres) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.yeargenre != other.yeargenre) {
            return false;
        }
        if (!Objects.equals(this.namegenre, other.namegenre)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", namegenre=" + namegenre + ", typegenre=" + typegenre + ", yeargenre=" + yeargenre + '}';
    }

    
}
