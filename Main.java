public class Main {
  public static void main(String[] args) {
    Grafo g = new Grafo();
    int[] verts = {0,1,2,3,4,5};
    for(Integer v : verts) {
      g.insertarVertice(v);
    }

    g.insertarArista(0,1);
    g.insertarArista(0,2);
    g.insertarArista(0,3);
    g.insertarArista(3,5);
    g.insertarArista(1,5);
    g.insertarArista(1,2);
    g.insertarArista(2,3);
    g.insertarArista(2,4);
    g.insertarArista(2,5);
    g.insertarArista(3,4);
    g.insertarArista(5,4);

    System.out.println("Conjuntos Camarilla");
    System.out.println(g.conjuntosCamarilla());
    System.out.println();
    System.out.println("Numero Clique: "+g.numClique());
    System.out.println(g.getCamarilla());
  }
}