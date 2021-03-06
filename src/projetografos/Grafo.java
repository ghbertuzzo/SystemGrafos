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
public class Grafo {
    
    Integer tempo;
    ArrayList<Vertice> ordenTop;
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
        int j=0;
        for(Vertice v2 : g.getListaVertices()){
            if(v==v2)
                return j;
            else
                j++;
        }
        return 0;
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
    
    public void OrdenacaoTopologica(){
        initDFS(this, this.getListaVertices().get(0));
        ArrayList<Vertice> aux = new ArrayList<>();
        int i=ordenTop.size();
        while(i!=0){
            aux.add(ordenTop.get(i-1));
            i--;
        }
        for(Vertice v : aux){
            System.out.println("Vertice v"+v.getId()+"\nTempo Finalização: "+v.getTfinal()+"\nTempo descoberto: "+v.getTdescoberto());
        }
    }
    public void DFSVisit(Grafo grafo, Vertice vertice){        
        tempo = tempo+1;
        vertice.setTdescoberto(tempo);
        vertice.setColor(1);
        if((geraListaAdj(grafo).get(getIndex(vertice, grafo)))!=null){
            if((geraListaAdj(grafo).get(getIndex(vertice, grafo))).size()>0){
                for(Vertice vadj2: (geraListaAdj(grafo).get(getIndex(vertice, grafo)))){
                    if(vadj2.getColor()==0){
                        vadj2.setPredecessor(vertice);                        
                        DFSVisit(grafo, vadj2);
                    }

                }
            }
        }
        vertice.setColor(2);
        tempo=tempo+1;
        vertice.setTfinal(tempo);
        ordenTop.add(vertice);
    }
        
    
    public void initDFS(Grafo grafo, Vertice vInic){      
        for(Vertice v: grafo.getListaVertices()){
            v.color=0;
            v.dist=0;
            v.predecessor=null;
        }
        ordenTop = new ArrayList<>();
        tempo = 0;
        if(vInic.getColor()==0)
            DFSVisit(grafo, vInic);
        for(Vertice v: grafo.getListaVertices()){
            if(v.getColor()==0)
                DFSVisit(grafo, v);
        }
        //DFSProcess(grafo);
        return;
    }
    public void DFSProcess(Grafo grafo){
        for(Vertice v : grafo.getListaVertices()){
            System.out.println("Vertice "+v.getId()+" Descoberto tempo: "+v.getTdescoberto()+" Finalizado tempo: "+v.getTfinal());
        }
    }
    
    public Arvore initBFS(Grafo grafo, Vertice vInic){
        for(Vertice v: grafo.getListaVertices()){
            v.color=0;
            v.dist=0;
            v.predecessor=null;
        }
        ArrayList<Vertice> impar = new ArrayList<>();
        ArrayList<Vertice> par = new ArrayList<>();
        vInic.color=1;
        vInic.dist=0;
        vInic.predecessor=null;        
        System.out.println("Iniciando busca no Vertice: "+vInic.getId()+" \nCor: "+vInic.getColor()+" \nDistância: "+vInic.getDist());
        ArrayList<Vertice> filaBFS = new ArrayList<>();
        ArrayList<Vertice> listaBusca = new ArrayList<>();
        filaBFS.add(vInic);  
        ArrayList<Arvore> filh = new ArrayList<>();
        Arvore a1 = new Arvore(vInic,filh); 
        Arvore aux = a1;
        while(filaBFS.size()!=0){
            Vertice v1 = filaBFS.get(0);
            filaBFS.remove(0);
            //aux = primeiro filho nao visitado
            if(aux.getFilhos().size()>0){
                aux = aux.getFilhos().get(getFilhoNaoVisitado(aux));
            }
            for(Vertice vAdj: (geraListaAdj(grafo).get(getIndex(v1, grafo)))){
                if(vAdj.getColor()==0){                    
                    //aux = aux.getFilhos().get(0);
                    vAdj.setColor(1);//SETA COR CINZA DESCOBRE VERTICE
                    listaBusca.add(vAdj);
                    vAdj.setDist(v1.getDist()+1);
                    
                    if(vAdj.getDist()%2==1)//IMPAR
                        impar.add(vAdj);
                    if(vAdj.getDist()%2==0)//PAR
                        par.add(vAdj);
                    
                    vAdj.setPredecessor(v1);                    
                    //System.out.println("Vertice: "+vAdj.getId()+" Cor: "+vAdj.getColor()+" Distância: "+vAdj.getDist());
                    filaBFS.add(vAdj);
                    ArrayList<Arvore> filho = new ArrayList<>();
                    aux.getFilhos().add(new Arvore(vAdj,filho));                    
                }
            }
            aux.setVisitado(true);
            v1.setColor(2);//SET COR PRETA
            //System.out.println("Vertice: "+v1.getId()+" Cor: "+v1.getColor()+" Distância: "+v1.getDist());
        }
        //comparar conjuntos pares e impares pra ver se grafo é bipartido
        int flag = 0;
        for(int i=0;i<par.size();i++){
            for(Aresta a : grafo.getListaArestas()){
                if(par.get(i).getId()==a.getOrigem().getId()){//ENCONTRA ARESTA QUE TEM ORIGEM NUM VERTICE PAR
                    for(Vertice parn: par){
                        if(a.getDestino().getId()==parn.getId())//ENCONTRA ARESTA QUE TEM ORIGEM e DESTINO NUM VERTICE PAR
                            flag=1;
                    }
                }
            }
        }
        for(int i=0;i<impar.size();i++){
            for(Aresta a : grafo.getListaArestas()){
                if(impar.get(i).getId()==a.getOrigem().getId()){//ENCONTRA ARESTA QUE TEM ORIGEM NUM VERTICE IMPAR
                    for(Vertice imparn: impar){
                        if(a.getDestino().getId()==imparn.getId())//ENCONTRA ARESTA QUE TEM ORIGEM e DESTINO NUM VERTICE IMPAR
                            flag=1;
                    }
                }
            }
        }
        if(flag==0)
            System.out.println("grafo bipartido");
        else
            System.out.println("grafo naobipartido");
        
        
        //reset visitados
        resetVisit(a1);        
        return a1;
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
    
    public void removeAresta(Vertice vorigem, Vertice vdestino, Grafo g){
        if(g.getListaArestas().size()<=0)
            System.out.println("O grafo não possui esta aresta");
        for(Aresta a: g.getListaArestas()){
            if((vorigem == a.getOrigem()) && (vdestino == a.getDestino())){
                g.getListaArestas().remove(a);
                return;
            }
        }
        System.out.println("O grafo não possui esta aresta");
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
        System.out.print("  ");
        for(Vertice v1: grafo.getListaVertices()){
            System.out.print("v"+v1.getId().toString());
        }
        System.out.println("");
        for(int i=0;i<grafo.getListaVertices().size();i++){
            System.out.print("v"+grafo.getListaVertices().get(i).getId().toString()+" ");
            for(int j=0;j<grafo.getListaVertices().size();j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
    }

    private int getFilhoNaoVisitado(Arvore aux) {
        if(aux.isVisitado())
            for(int i=0;i<aux.getFilhos().size();i++){
                Arvore a1 = aux.getFilhos().get(i);
                if(!a1.isVisitado())
                    return i;
            }
        return -1;
    }
    void printArvoreBusca(Arvore a) {
        int tam = a.getSize();
        System.out.println(" Vertice "+a.getRaiz().getId()+" Nível "+a.getRaiz().getDist());
        for(int i=0;i<tam;i++){            
            printArvoreBusca(a.getFilhos().get(i));
        }
    }

    private void resetVisit(Arvore a1) {        
        a1.setVisitado(false);
        int tam = a1.getSize();
        Arvore aux = a1;
        for(int i=0;i<tam;i++){
            resetVisit(a1.getFilhos().get(i));
        }
        return;
    }
    
}
