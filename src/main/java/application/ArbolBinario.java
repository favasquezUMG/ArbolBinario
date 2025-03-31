package application;

public class ArbolBinario {
    private Nodo raiz;
    private int alt;
    private int num_nodos;

    public ArbolBinario(){
        this.raiz = null;
        alt = 1;
        num_nodos = 0;
    }

    public int obtenerAltura(){
        return obtenerAlturaRecursivo(raiz);
    }
    private int obtenerAlturaRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzquierda = obtenerAlturaRecursivo(nodo.izq);
        int alturaDerecha = obtenerAlturaRecursivo(nodo.der);

        return Math.max(alturaIzquierda, alturaDerecha) + 1;
    }

    public Nodo getRaizArbol(){
        return raiz;
    }

    public int getDatoArbol(){
        return raiz.getDato();
    }

    public void insertar(int dato){
        raiz = insertarRecursivo(raiz, dato);
    }
    private Nodo insertarRecursivo(Nodo raizInsertar, int dato){
        if(raizInsertar == null){
            return new Nodo(dato);
        }

        if(dato<raizInsertar.getDato()){
            raizInsertar.izq = insertarRecursivo(raizInsertar.izq, dato);
        }else{
            raizInsertar.der = insertarRecursivo(raizInsertar.der, dato);
        }
        //return raizInsertar;
        return balanceArbol(raizInsertar);
    }

    //Recorridos
    public String preOrden(){
        //StringBuilder es un tipo string que se puede editar no como un String normal que no se puede editar
        //(se usa para no crear varios string cada vez que se edite la respuesta, algo asi le entendi)
        StringBuilder resultado = new StringBuilder();
        preOrdenRecursivo(raiz, resultado);
        return resultado.toString();
    }
    private void preOrdenRecursivo(Nodo raiz, StringBuilder r){
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
    private void inOrdenRecursivo(Nodo raiz, StringBuilder r){
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
    private void postOrdenRecursivo(Nodo raiz, StringBuilder r){
        if(raiz == null){
            return;
        }
        postOrdenRecursivo(raiz.izq, r);
        postOrdenRecursivo(raiz.der, r);
        r.append(raiz.getDato()).append(", ");
        System.out.println(raiz.getDato()+"");
    }
    //Fin de los recorridos

    public int buscar(int valor){
        alt = 1;
        buscarRecursivo(raiz, valor);
        return alt;
    }
    private Nodo buscarRecursivo(Nodo raizBuscar, int valor){
        System.out.println(""+raizBuscar.getDato());
        if(raizBuscar.getDato() == valor){
            return raizBuscar;
        }

        if(valor<raizBuscar.getDato()){
            raizBuscar.izq = buscarRecursivo(raizBuscar.izq, valor);
        }else{
            raizBuscar.der = buscarRecursivo(raizBuscar.der, valor);
        }
        alt++;
        return raizBuscar;
    }

    private Nodo sucesorRecursivo(Nodo raizSucesor){
        if(raizSucesor.izq != null){
            return sucesorRecursivo(raizSucesor.izq);
        }
        return raizSucesor;
    }
    public void eliminar(int valor){
        eliminarRecursivo(raiz,valor);
    }
    private Nodo eliminarRecursivo(Nodo raizEliminar, int valor){
        if(raizEliminar == null){
            return raizEliminar;
            //return balanceArbol(raizEliminar);
        }
        //1er caso
        if(valor<raizEliminar.getDato()){
            raizEliminar.izq = eliminarRecursivo(raizEliminar.izq, valor);
        }else if (valor>raizEliminar.getDato()){
            raizEliminar.der = eliminarRecursivo(raizEliminar.der, valor);
        }else{
            //2do caso
            if(raizEliminar.der == null){
                raizEliminar = raizEliminar.izq;
            } else if (raizEliminar.izq == null) {
                raizEliminar = raizEliminar.der;
            }else{
                //3er caso
                Nodo temporal = sucesorRecursivo(raizEliminar.der);
                raizEliminar.setDato(temporal.getDato());
                raizEliminar.der = eliminarRecursivo(raizEliminar.der, raizEliminar.getDato());
            }
        }
        if(raizEliminar == null){
            return raizEliminar;
            //return balanceArbol(raizEliminar);
        }
        return raizEliminar;
        //return balanceArbol(raizEliminar);
    }

    public void reiniciarArbol(){
        this.raiz = null;
        this.alt = 0;
        this.num_nodos = 0;
    }

    //Parte AVL
    public int altura(Nodo raizAltura){
        if(raizAltura == null){
            return 0;
        }
        return raizAltura.alturaNodo;
    }
    public void restablecerAltura(Nodo raizRestablecerAltura){
        int izq = altura(raiz.izq);
        int der = altura(raiz.der);

        raizRestablecerAltura.alturaNodo = Math.max(izq, der) + 1;
    }
    public int balance(Nodo raizBalance){
        if(raizBalance == null){
            return 0;
        }
        return (altura(raizBalance.der)-altura(raizBalance.izq));
    }
    public Nodo balanceArbol(Nodo raizBalanceArbol){
        restablecerAltura(raizBalanceArbol);
        int bal = balance(raizBalanceArbol);

        if(bal > 1){ //Der
            if(balance(raizBalanceArbol.der)<0){ //Der,Izq
                raizBalanceArbol.der = rotacionDer(raizBalanceArbol.der);
                return rotacionIzq(raizBalanceArbol);
            }
            //Der,Der
            return rotacionIzq(raizBalanceArbol);
        }
        if (bal < -1){ //Izq
            if (balance(raizBalanceArbol.izq)> 0){ //Izq,Der
                raizBalanceArbol.izq = rotacionIzq(raizBalanceArbol.izq);
                return rotacionDer(raizBalanceArbol);
            }
            //Izq,Izq
            return rotacionDer(raizBalanceArbol);
        }
        return raizBalanceArbol;
    }
    public Nodo rotacionIzq(Nodo x){
        Nodo y = x.der;
        Nodo temp = y.izq;

        y.izq = x;
        x.der = temp;

        restablecerAltura(x);
        restablecerAltura(y);

        return y;
    }
    public Nodo rotacionDer(Nodo y){
        Nodo x = y.izq;
        Nodo temp = x.der;

        x.der = y;
        y.izq = temp;

        restablecerAltura(y);
        restablecerAltura(x);

        return x;
    }
}
