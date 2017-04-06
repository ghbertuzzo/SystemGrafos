/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetografos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a1602020
 */
public class Grafo {
    
    public int[][] geraMatrizAdj(Grafo g){
        int matriz[][] = new int[g.getListaVertices().size()][g.getListaVertices().size()];
        for(int i=0;i<g.getListaVertices().size();i++){
            for(int j=0;j<g.getListaVertices().size();j++){
                matriz[i][j]=0;
            }
        }
        for(Aresta a1: g.getListaArestas()){
            matriz[getIndex(a1.getOrigem(),g)][getIndex(a1.getDestino(),g)]=1;
        }
        
        return matriz;
    }
    public int getIndex(Vertice v, Grafo g){
        for(Aresta a1: g.getListaArestas()){
            if((a1.getDestino()==v)||(a1.getOrigem()==v)){
                int cont=0;
                for(int i=0;i<g.getListaVertices().size();i++){
                    if(v==g.getListaVertices().get(i))
                        return cont;
                    else
                        cont++;
                }
            }
        }
        return -1;
    }
    
    public ArrayList<ArrayList<Vertice>> geraListaAdj(Grafo g){
        ArrayList <ArrayList<Vertice>> list = new ArrayList<>();
        for(Vertice v1: g.getListaVertices()){
            ArrayList<Vertice> listas = new ArrayList<>();
            for(Aresta a1: g.getListaArestas()){
                if(a1.getOrigem().getId()==v1.getId())
                    listas.add(a1.getDestino());
            }
            list.add(listas);
        }
        return list;
    }
    public void printLista(ArrayList<ArrayList<Vertice>> lista, Grafo g){
        for(int i=0;i<lista.size();i++){
        //for(ArrayList<Vertice> a1: lista){
            System.out.print("Vertice "+g.getListaVertices().get(i).getId()+" " );
            for(int j=0;j<lista.get(i).size();j++){
                System.out.print("-> "+lista.get(i).get(j).getId()+" ");
            }        
            System.out.println("");
        }
    }
    
    
    public Grafo iniciaGrafo(){
        ArrayList<Vertice> listVertices = new ArrayList<Vertice>();
        ArrayList<Aresta> listArestas = new ArrayList<Aresta>();
        Grafo grafo = new Grafo(listVertices, listArestas);
        return grafo;
    }
    
    public Grafo addVertice (Integer id, Grafo g){
        if(contemNoGrafo(id, g)){
            System.out.println("Erro ao adicionar vertice");
            return null;
        }
        Vertice v1 = new Vertice(id);
        g.getListaVertices().add(v1);
        return g;
    }
    
    public Vertice getVertice (Integer id, Grafo g){
        for(Vertice v1: g.getListaVertices()){
            if(v1.getId()==id)
                return v1;
        }
        return null;
    }
    
    public Grafo removeVertice (Integer id, Grafo g){       
        // VERIFICAR ARESTAS VINCULADAS COM O VERTICE, ANTES DE RETIRAR DO GRAFO
        for(int i=0; i<g.getListaVertices().size(); i++){
            if((id == g.getListaVertices().get(i).getId()) && (!temArestaVinculada(g.getListaVertices().get(i).getId(), g))){
                g.getListaVertices().remove(g.getListaVertices().get(i));
                return g;
            }
        }
        System.out.println("Erro ao remover vertice");
        return null;
    }
    
    public boolean contemNoGrafo (Integer id, Grafo g){
        for(int i=0; i<g.getListaVertices().size(); i++){
            if(id == g.getListaVertices().get(i).getId())
                return true;
        }
        return false;
    }

    private boolean temArestaVinculada(Integer id, Grafo g) {
        for(Aresta a: g.getListaArestas()){
            if( (id == a.getOrigem().getId()) || (id ==a.getDestino().getId()) ){
                return true;
            }
        }
        return false;
    }
    
    public Grafo addAresta(Vertice vorigem, Vertice vdestino, Grafo g){
        boolean temOrigem = false;
        boolean temDestino = false;
        //VERIFICA SE VERTICE DE ORIGEM PASSADO POR PARAMETRO EXISTE NO GRAFO
        for(Vertice v: g.getListaVertices()){
            if(vorigem.getId() == v.getId())
                temOrigem = true;
        }
        //VERIFICA SE VERTICE DE DESTINO PASSADO POR PARAMETRO EXISTE NO GRAFO
        for(Vertice v: g.getListaVertices()){
            if(vdestino.getId() == v.getId())
                temDestino = true;
        }
        //SE EXISTIR TANTO VORIGEM COMO VDESTINO, PERMITE ADICIONAR ARESTA
        if(temOrigem && temDestino){
            Aresta a1 = new Aresta(vorigem, vdestino);
            g.getListaArestas().add(a1);
            return g;
        }else{
            System.out.println("Erro ao adicionar aresta");
            return null;
        }
    }
    
    public Grafo addArestaPonderada(Vertice vorigem, Vertice vdestino, Double peso,Grafo g){
        boolean temOrigem = false;
        boolean temDestino = false;
        //VERIFICA SE VERTICE DE ORIGEM PASSADO POR PARAMETRO EXISTE NO GRAFO
        for(Vertice v: g.getListaVertices()){
            if(vorigem.getId() == v.getId())
                temOrigem = true;
        }
        //VERIFICA SE VERTICE DE DESTINO PASSADO POR PARAMETRO EXISTE NO GRAFO
        for(Vertice v: g.getListaVertices()){
            if(vdestino.getId() == v.getId())
                temDestino = true;
        }
        //SE EXISTIR TANTO VORIGEM COMO VDESTINO, PERMITE ADICIONAR ARESTA
        if(temOrigem && temDestino){
            Aresta a1 = new Aresta(vorigem, vdestino);
            a1.setPeso(peso);
            g.getListaArestas().add(a1);
            return g;
        }else{
            System.out.println("Erro ao adicionar aresta");
            return null;
        }
    }

    public Grafo(ArrayList<Vertice> listaVertices, ArrayList<Aresta> listaArestas) {
        this.listaVertices = listaVertices;
        this.listaArestas = listaArestas;
    }

    public ArrayList<Vertice> getListaVertices() {
        return listaVertices;
    }

    public void setListaVertices(ArrayList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public ArrayList<Aresta> getListaArestas() {
        return listaArestas;
    }

    public void setListaArestas(ArrayList<Aresta> listaArestas) {
        this.listaArestas = listaArestas;
    }
    ArrayList<Vertice> listaVertices;
    ArrayList<Aresta> listaArestas;

    void printMatriz(int[][] matriz, Grafo grafo) {
        for(int i=0;i<grafo.getListaVertices().size();i++){
            for(int j=0;j<grafo.getListaVertices().size();j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
    }
}
