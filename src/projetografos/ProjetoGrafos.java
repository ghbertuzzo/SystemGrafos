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
public class ProjetoGrafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo grafo = new Grafo(null, null);
        grafo = grafo.iniciaGrafo();
        grafo.addVertice(0, grafo);
        grafo.addVertice(1, grafo);
        grafo.addVertice(2, grafo);
        grafo.addVertice(3, grafo);
        grafo.addVertice(4, grafo);
        grafo.addVertice(5, grafo);
        grafo.removeVertice(5, grafo);
        grafo.addAresta(grafo.getListaVertices().get(0), grafo.getListaVertices().get(1), grafo);
        grafo.addAresta(grafo.getListaVertices().get(1), grafo.getListaVertices().get(2), grafo);
        grafo.addAresta(grafo.getListaVertices().get(0), grafo.getListaVertices().get(3), grafo);
        grafo.addAresta(grafo.getListaVertices().get(0), grafo.getListaVertices().get(4), grafo);
        grafo.addAresta(grafo.getListaVertices().get(1), grafo.getListaVertices().get(4), grafo);
        grafo.addArestaPonderada(grafo.getListaVertices().get(3), grafo.getListaVertices().get(4), 5.5,grafo);
        ArrayList<ArrayList<Vertice>> resp = grafo.geraListaAdj(grafo);
        grafo.printLista(resp, grafo);
        int[][] m = grafo.geraMatrizAdj(grafo);
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(m[i][j]);
            }
            System.out.println("");
        }
        System.out.print("teste");
    }
    
}
