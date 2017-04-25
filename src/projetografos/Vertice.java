/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetografos;

import java.util.ArrayList;

/**
 *
 * @author a1602020
 */
public class Vertice {

    Integer id;   
    Integer color;
    Integer dist;
    Vertice predecessor;
    Integer tdescoberto;

    public Integer getTdescoberto() {
        return tdescoberto;
    }

    public void setTdescoberto(Integer tdescoberto) {
        this.tdescoberto = tdescoberto;
    }

    public Integer getTfinal() {
        return tfinal;
    }

    public void setTfinal(Integer tfinal) {
        this.tfinal = tfinal;
    }
    Integer tfinal;
    
    public Vertice(Integer id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public Vertice getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertice predecessor) {
        this.predecessor = predecessor;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    
}

