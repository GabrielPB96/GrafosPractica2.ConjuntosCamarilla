public class Main {
  public static void main(String[] args) {
    Grafo g = new Grafo();
    int[] verts = {0,1,2,3,4,5,6};
    for(Integer v : verts) {
      g.insertarVertice(v);
    }

    g.insertarArista(0, 4);
    g.insertarArista(0, 1);
    g.insertarArista(0, 5);
    g.insertarArista(1, 2);
    g.insertarArista(1, 3);
    g.insertarArista(1, 4);
    g.insertarArista(1, 5);
    g.insertarArista(2, 3);
    g.insertarArista(2, 5);
    g.insertarArista(3, 4);
    g.insertarArista(3, 5);
    g.insertarArista(3, 6);
    g.insertarArista(4, 6);

    System.out.println("Conjuntos Camarilla");
    System.out.println(g.conjuntosCamarilla());
    System.out.println();
    System.out.println("Numero Clique: "+g.numClique());
    System.out.println(g.getCamarilla());
  }
}