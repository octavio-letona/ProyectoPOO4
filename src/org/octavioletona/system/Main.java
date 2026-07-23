/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        //nodo raiz
        Pane raiz = new Pane();
        //escena
        Scene escena = new Scene(raiz, 450, 600);
        //cargamos escen en escenario y mostramos escenario
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
        
        
        
    }
    
}
