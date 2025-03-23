package application;

public class ArbolBinario {
    private Nodo raiz;
    private int alt;
    private int num_nodos;

    public ArbolBinario(){
        this.raiz = null;
        alt = 0;
        num_nodos = 0;
    }

    //Solo se usa si se quiere hacer sin recursividad
    public boolean existeNodo(int dato){
        Nodo aux = raiz;
        while (aux!=null) {

            if(dato == aux.getDato()){
                System.out.println("Ya existe");
                return true;
            }else{
                if(dato>aux.getDato()){
                    aux = aux.getDerecha();
                }else{
                    aux = aux.getIzquierda();
                }
                System.out.println("Se asignó en un hijo");
            }
        }
        return false;

    }

    public void insertar(int dato){
        raiz = insertarRecursivo(raiz, dato);
    }

    public Nodo insertarRecursivo(Nodo raizInsertar, int dato){
        if(raizInsertar == null){
            return new Nodo(dato);
        }

        if(dato<raizInsertar.getDato()){
            raizInsertar.izq = insertarRecursivo(raizInsertar.izq, dato);
        }else{
            raizInsertar.der = insertarRecursivo(raizInsertar.der, dato);
        }
        return raizInsertar;
    }

    //Recorridos
    public String preOrden(){
        //StringBuilder es un tipo string que se puede editar no como un String normal que no se puede editar
        //(se usa para no crear varios string cada vez que se edite la respuesta, algo asi le entendi)
        StringBuilder resultado = new StringBuilder();
        preOrdenRecursivo(raiz, resultado);
        return resultado.toString();
    }
    public void preOrdenRecursivo(Nodo raiz, StringBuilder r){
        if(raiz == null){
            return;
        }
        //append es para editar el String y añadirle algo al texto
        //existe insert pero es para agregar en cierta posicion del texto
        r.append(raiz.getDato()).append(", ");
        System.out.println(""+raiz.getDato());
        preOrdenRecursivo(raiz.izq, r);
        preOrdenRecursivo(raiz.der, r);
    }

    public String inOrden(){
        StringBuilder resultado = new StringBuilder();
        inOrdenRecursivo(raiz, resultado);
        return resultado.toString();
    }
    public void inOrdenRecursivo(Nodo raiz, StringBuilder r){
        if(raiz == null){
            return;
        }
        inOrdenRecursivo(raiz.izq, r);
        r.append(raiz.getDato()).append(", ");
        System.out.println(raiz.getDato()+"");
        inOrdenRecursivo(raiz.der, r);
    }

    public String postOrden(){
        StringBuilder resultado = new StringBuilder();
        postOrdenRecursivo(raiz, resultado);
        return resultado.toString();
    }
    public void postOrdenRecursivo(Nodo raiz, StringBuilder r){
        if(raiz == null){
            return;
        }
        postOrdenRecursivo(raiz.izq, r);
        postOrdenRecursivo(raiz.der, r);
        r.append(raiz.getDato()).append(", ");
        System.out.println(raiz.getDato()+"");
    }
    //Fin de los recorridos

    public void buscar(int valor){
        boolean buscar = buscarRecursivo(raiz, valor);
        if(buscar == true){
            System.out.println("Si existe");
        }else{
            System.out.println("No existe");
        }
    }

    private boolean buscarRecursivo(Nodo raiz, int valor){
        boolean buscar;
        if(raiz == null){
            return false;
        }
        if(raiz.getDato() == valor){
            return true;
        }
        if(raiz.getDato()<valor){
            buscar = buscarRecursivo(raiz.izq, valor);
        }if(raiz.getDato()>valor){
            buscar = buscarRecursivo(raiz.der, valor);
        }
        return false;
    }

    private Nodo eliminar(){
        return null;
    }

    public void reiniciarArbol(){
        this.raiz = null;
        this.alt = 0;
        this.num_nodos = 0;
    }

    public String getDatoArbol(){
        return ""+raiz.getDato();
    }
}
