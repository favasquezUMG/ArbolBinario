package controllers;

import application.ArbolBinario;
import application.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

public class ArbolBinarioGraficoController {
    ArbolBinario arbol = new ArbolBinario();
    StringBuilder listado = new StringBuilder();
    Stage stage;
    GraphicsContext gc;

    //Dibujar Nodo
    private void dibujarNodo(double x, double y, int valor) {
        double radius = 30;
        gc.setFill(Color.PALEGREEN);
        gc.fillOval(x - radius / 2, y - radius / 2, radius, radius);
        gc.setStroke(Color.DARKOLIVEGREEN);
        gc.strokeOval(x - radius / 2, y - radius / 2, radius, radius);
        gc.setFill(Color.FORESTGREEN);
        gc.fillText(String.valueOf(valor),x-4,y+5);
    }
    private void limpiarCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    //Leer Archivo
    public  void leerArchivo(Path ruta){
        try{
            List<String> lineas = Files.readAllLines(ruta);
            for(String linea : lineas){
                txtFIngresarDato.setText(linea);
            }
        } catch (IOException e){
            lblMensaje.setText("Error al leer el archivo :(");
        }
    }

    public void dibujarArbol(GraphicsContext gc, Nodo nodo, double x, double y, double offset){
        //initialize();
        gc = canvas.getGraphicsContext2D();
        if(nodo != null){
            dibujarNodo(x,y, nodo.getDato());

            if(nodo.getIzquierda()!=null){
                gc.setStroke(Color.SADDLEBROWN);
                gc.strokeLine(x, y, x - offset, y + 100);
                dibujarArbol(gc, nodo.getIzquierda(), x - offset, y + 100, offset / 2);
            }
            if(nodo.getDerecha()!=null){
                gc.setStroke(Color.SADDLEBROWN);
                gc.strokeLine(x, y, x + offset, y + 100);
                dibujarArbol(gc, nodo.getDerecha(), x + offset, y + 100, offset / 2);
            }
        }

    }


    @FXML
    private Canvas canvas;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtFIngresarDato;

    @FXML
    private TextField txtRecorrido;

    @FXML
    void Enter(KeyEvent event) {

    }
    @FXML
    void initialize() {
        gc = canvas.getGraphicsContext2D();
        limpiarCanvas();
    }

    @FXML
    void crearNodo(MouseEvent event) {
        //if (event.getButton() == MouseButton.SECONDARY) {}
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void ingresarDato(ActionEvent event) {
        txtRecorrido.setText("");
        String linea = txtFIngresarDato.getText();
        StringTokenizer listaDatos = new StringTokenizer(txtFIngresarDato.getText(), ",");

        while (listaDatos.hasMoreTokens()){
            String tokenActual = listaDatos.nextToken();
            listado.append(tokenActual).append(", ");
            int dato = Integer.parseInt(tokenActual);
            arbol.insertar(dato);
            dibujarArbol(gc,arbol.getRaizArbol(),canvas.getWidth()-255.5,canvas.getScaleY()+20,200);
        }
        lblMensaje.setText(listado+"");
        txtFIngresarDato.setText("");
        //dibujarArbol(gc,arbol.getRaizArbol(),canvas.getWidth()-255.5,canvas.getScaleY()+20,200);
    }

    @FXML
    void inordenRecorrido(ActionEvent event) {
        txtRecorrido.setText(arbol.inOrden());
    }

    @FXML
    void posordenRecorrido(ActionEvent event) {
        txtRecorrido.setText(arbol.postOrden());
    }

    @FXML
    void preordenRecorrido(ActionEvent event) {
        txtRecorrido.setText(arbol.preOrden());
    }

    @FXML
    void resetearArbol(ActionEvent event) {
        arbol.reiniciarArbol();
        listado = new StringBuilder();
        txtRecorrido.setText("Reiniciado");
        lblMensaje.setText("...");
        limpiarCanvas();
    }

    @FXML
    void seleccionarArchivo(ActionEvent event) {
        FileChooser archivo = new FileChooser();
        archivo.setTitle("Seleccione un archivo .txt");

        File archivoSeleccionado = archivo.showOpenDialog(stage);
        leerArchivo(archivoSeleccionado.toPath());
    }

}
