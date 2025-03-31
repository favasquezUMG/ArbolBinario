package application;

public class Nodo {
    private int dato;
    int alturaNodo;
    Nodo izq,der;

    public Nodo(int dato){
        this.dato = dato;
        this.alturaNodo = 1;
        izq = null;
        der = null;
    }

    public int getDato(){
        return this.dato;
    }

    public void setDato(int dato){
        this.dato = dato;
    }


    public Nodo getIzquierda(){
        return izq;
    }

    public void setIzquierda(Nodo izq){
        this.izq = izq;
    }

    public Nodo getDerecha(){
        return der;
    }

    public void setDerecha(Nodo der){
        this.der = der;
    }
}
