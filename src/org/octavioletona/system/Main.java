/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.system;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.octavioletona.controller.EmpleadoController;
import org.octavioletona.view.EmpleadoView;
import org.octavioletona.view.VistaPrincipal;
import org.octavioletona.view.VistaPrincipalUI;

public class Main extends Application {

   @Override
    public void start(Stage escenaPrincipal) {
        escenaPrincipal.setTitle("Sistema de Gestión de Empleados");

        EmpleadoView vista = new EmpleadoView();
        new EmpleadoController(vista);

        TabPane raiz = vista.getTabPane();
        Scene escena = new Scene(raiz, 450, 600);
        
        String cssPath = getClass().getResource("/Style/EmpleadoStyle.css").toExternalForm();
      
        escena.getStylesheets().add(cssPath);

        escenaPrincipal.setScene(escena);
        escenaPrincipal.setResizable(false);
        escenaPrincipal.show();
    }
  
        
            public static void main(String[] args) {

        launch(args);
    }
}
    
    

