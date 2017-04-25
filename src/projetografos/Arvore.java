/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetografos;

import java.util.ArrayList;

/**
 *
 * @author Bertuzzo
 */
public class Arvore {
        
    Vertice raiz;
    ArrayList<Arvore> filhos;
    Integer sizeFilhos;

    public Integer getSize(){
        return filhos.size();
    }
    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    boolean visitado;
    
    public Arvore(Vertice raiz,ArrayList<Arvore> filhos) {
        this.raiz = raiz;
        this.filhos = filhos;
    }

    public Vertice getRaiz() {
        return raiz;
    }

    public void setRaiz(Vertice raiz) {
        this.raiz = raiz;
    }

    public ArrayList<Arvore> getFilhos() {
        return filhos;
    }

    public void setFilhos(ArrayList<Arvore> filhos) {
        this.filhos = filhos;
    }
    
    public void addFilho(Arvore a){
        this.filhos.add(a);
    }
}