/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetografos;

import java.util.ArrayList;
import java.util.Scanner;

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
        int resp = 99,resp2 = 0,resp3 = 0;
        double resp4 = 0;
        while(resp!=0){
            System.out.println("\n1-Imprimir Grafo");
            System.out.println("2-Adicionar Vértice");
            System.out.println("3-Adicionar Aresta");
            System.out.println("4-Adicionar Aresta Ponderada");
            System.out.println("5-Gerar Lista de Adjacência");
            System.out.println("6-Gerar Matriz de Adjacência");
            System.out.println("0-Sair\n");
            Scanner sc = new Scanner(System.in);
            resp = sc.nextInt();
            if(resp==1){
                grafo.printLista(grafo.geraListaAdj(grafo), grafo);
            }else if(resp==2){
                System.out.println("Informe ID do vértice:");
                Scanner sc2 = new Scanner(System.in);
                resp2 = sc2.nextInt();
                grafo.addVertice(resp2, grafo);
            }else if(resp==3){
                System.out.println("Informe ID do vértice de origem:");
                Scanner sc2 = new Scanner(System.in);
                resp2 = sc2.nextInt();
                System.out.println("Informe ID do vértice de destino:");
                Scanner sc3 = new Scanner(System.in);
                resp3 = sc3.nextInt();
                grafo.addAresta(grafo.getVertice(resp2, grafo) , grafo.getVertice(resp3, grafo), grafo);
            }else if(resp==4){
                System.out.println("Informe ID do vértice de origem:");
                Scanner sc2 = new Scanner(System.in);
                resp2 = sc2.nextInt();
                System.out.println("Informe ID do vértice de destino:");
                Scanner sc3 = new Scanner(System.in);
                resp3 = sc3.nextInt();
                System.out.println("Informe o peso da aresta:");
                Scanner sc4 = new Scanner(System.in);
                resp4 = sc4.nextDouble();
                grafo.addArestaPonderada(grafo.getVertice(resp2, grafo), grafo.getVertice(resp3, grafo), resp4,grafo);
            }else if(resp==5){
                ArrayList<ArrayList<Vertice>> listaadj = grafo.geraListaAdj(grafo);
                grafo.printLista(listaadj, grafo);
            }else if(resp==6){
                int[][] matriz = grafo.geraMatrizAdj(grafo);
                grafo.printMatriz(matriz, grafo);
            }else{
                System.out.println("System close");
                System.exit(0);
            }
        }
        
        

    }
    
}
