/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.octavioletona.view.VistaPrincipal;
import org.octavioletona.view.VistaPrincipalUI;

public class Main extends Application {

   @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Gestión de Empleados");

        // Instanciamos el componente visual que creamos por separado
                //VistaPrincipal vista = new VistaPrincipal();
        VistaPrincipalUI vistaUI = new VistaPrincipalUI();
        // Le asignamos la vista a la escena (Scene)
        TabPane vista = vistaUI.obtenerTabPane();

        Scene scene = new Scene(vista, 400, 500);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
            public static void main(String[] args) {

        launch(args);
    }
    }
    

