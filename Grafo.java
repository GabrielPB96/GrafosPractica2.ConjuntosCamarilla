import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
public class Grafo{
  private HashMap<Integer, ArrayList<Integer>> grafo;
  int cantAristas,numeroClique;
  HashSet<Integer> conjuntoCamarilla;
  
  public Grafo() {
    cantAristas = 0;
    numeroClique = 0;
    conjuntoCamarilla = new HashSet<Integer>();
    grafo = new HashMap<Integer, ArrayList<Integer>>();
  }

  public boolean insertarVertice(int vertice){
    grafo.put(vertice, new ArrayList<Integer>());
    return true;
  }

  public boolean insertarArista(int origen,int destino) {
    try{
      boolean b = !grafo.get(origen).contains(destino) 
                  && !grafo.get(destino).contains(origen);
      if(!grafo.get(origen).contains(destino)){
        grafo.get(origen).add(destino);
      }
      if(!grafo.get(destino).contains(origen)){
        grafo.get(destino).add(origen);
      }
      if(b) cantAristas++;
      return true;
    }catch(Exception e){
      return false;
    }
  }

  public boolean eliminarVertice(int vertice) {
    try{
      grafo.remove(vertice);
      for(ArrayList<Integer> v : grafo.values()) {
        if(v.contains(vertice)){
          v.remove(Integer.valueOf(vertice));
          cantAristas--;
        }
      }
      return true;
    }catch(Exception e){
      return false;
    }
  }

  public int getA(){
    return cantAristas;
  }

  public int getN() {
    return grafo.size();
  }

  public int numClique() {
    return numeroClique;
  }

  public HashSet<Integer> getCamarilla() {
    return conjuntoCamarilla;
  }

  public ArrayList<HashSet<Integer>> conjuntosCamarilla() {
    ArrayList<HashSet<Integer>> res = new ArrayList<HashSet<Integer>>();
    for(Integer v : grafo.keySet()) {
      recorrer(v, res);
    }
    return res;
  }
  
  private void recorrer(int ini,ArrayList<HashSet<Integer>> c) {
    boolean[] vis = new boolean[grafo.size()];
    Grafo subGrafo = new Grafo();
    dfs(ini, vis, subGrafo, c, ini);
  }

  
  private void dfs(int v,boolean[] vis,Grafo subGrafo, ArrayList<HashSet<Integer>> c, int root) {
    vis[v] = true;
    subGrafo.insertarVertice(v);
    for(Integer u : grafo.get(v)) {
      if(!vis[u]) {
        dfs(u, vis, subGrafo, c, root);
      }else{
        if(u == root) {
          crearAristas(subGrafo);          
          if(subGrafo.esCompleto()) {
            HashSet<Integer> n = new HashSet<Integer>();
            for(Integer k : subGrafo.grafo.keySet()) {
              n.add(k);
            }
            if(!c.contains(n) && n.size() > 2) {
              c.add(n);
              if(n.size() > numeroClique) {
                numeroClique = n.size();
                conjuntoCamarilla = n;
              }
            }
          }
        }
      }
    }
    vis[v] = false;
    subGrafo.eliminarVertice(v);
  }

  private void crearAristas(Grafo subGrafo) {
    for(Integer vSub : subGrafo.grafo.keySet()) {
      ArrayList<Integer> ady = grafo.get(vSub);
      for(Integer vAd : ady) {
        if(subGrafo.grafo.containsKey(vAd)) {
          subGrafo.insertarArista(vSub,vAd);
        }
      }
    }
  }

  public boolean esCompleto() {
    int n = grafo.size();
    int c = (n*(n-1)) / 2;
    return c == cantAristas;
  }

  public String toString() {
    String res = "";
    for(Integer i : grafo.keySet()){
      ArrayList<Integer> v = grafo.get(i);
      res += i + " -> ";
      for(Integer u : v) {
        res += u +" ";
      }
      res += "\n";
    }
    return res;
  }
}