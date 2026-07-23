/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import org.octavioletona.model.*; // Importamos el paquete modelo

import java.util.ArrayList;

public class VistaPrincipalUI {

    // El TabPane que será expuesto e invocado por el MainApp
    private TabPane tabPane = new TabPane();

    // Estructura de Datos Polimórfica
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    // Componentes del Formulario
    private TextField txtId = new TextField();
    private TextField txtNombre = new TextField();
    private ComboBox<String> cbTipo = new ComboBox<>();
    private VBox panelDinamico = new VBox(12); 

    // Campos específicos para las clases hijas
    private TextField txtSalario = new TextField();
    private TextField txtHoras = new TextField();
    private TextField txtTarifa = new TextField();
    private TextField txtVentas = new TextField();
    private TextField txtPorcentaje = new TextField();

    // Componentes del Resumen
    private ListView<String> listViewResumen = new ListView<>();

    // Constantes de Diseño (Inspiradas en tu imagen de referencia)
    private final String COLOR_FONDO_LAVANDA = "-fx-background-color: #EAEAFF;";
    private final String ESTILO_TARJETA = "-fx-background-color: #FFFFFF; "
                                        + "-fx-background-radius: 20px; "
                                        + "-fx-padding: 24px; "
                                        + "-fx-effect: dropshadow(three-pass-box, rgba(100, 92, 255, 0.06), 15, 0, 0, 8);";
    
    private final String ESTILO_INPUT = "-fx-background-color: #F4F3FF; "
                                      + "-fx-background-radius: 12px; "
                                      + "-fx-padding: 12px; "
                                      + "-fx-text-fill: #1A134B; "
                                      + "-fx-prompt-text-fill: #8C89A0; "
                                      + "-fx-font-size: 13px; "
                                      + "-fx-font-family: 'Segoe UI', Arial;";

    private final String ESTILO_LABEL = "-fx-text-fill: #1A134B; "
                                      + "-fx-font-weight: bold; "
                                      + "-fx-font-size: 13px; "
                                      + "-fx-font-family: 'Segoe UI', Arial;";

    private final String ESTILO_BOTON_PRIMARIO = "-fx-background-color: linear-gradient(to right, #6D5BFA, #8372FF); "
                                               + "-fx-text-fill: white; "
                                               + "-fx-background-radius: 12px; "
                                               + "-fx-padding: 14px; "
                                               + "-fx-font-weight: bold; "
                                               + "-fx-font-size: 13px; "
                                               + "-fx-cursor: hand;";

    public VistaPrincipalUI() {
        inicializarUI();
    }

    private void inicializarUI() {
        // Estilo general para las pestañas
        tabPane.setStyle(COLOR_FONDO_LAVANDA);

        // -------------------------------------------------------------
        // TAB 1: REGISTRO (Diseño de Tarjeta Flotante)
        // -------------------------------------------------------------
        Tab tabRegistro = new Tab("Registro");
        tabRegistro.setClosable(false);
        tabRegistro.setStyle("-fx-background-color: white; -fx-background-radius: 10px 10px 0px 0px; -fx-font-weight: bold; -fx-text-fill: #6D5BFA;");

        // Contenedor que da el margen lavanda exterior
        StackPane wrapperRegistro = new StackPane();
        wrapperRegistro.setStyle(COLOR_FONDO_LAVANDA);
        wrapperRegistro.setPadding(new Insets(20));

        // Tarjeta Blanca Interior (Igual a la de "Full Name" en tu imagen)
        VBox cardRegistro = new VBox(12);
        cardRegistro.setStyle(ESTILO_TARJETA);

        // Configuración y estilizado de elementos comunes
        aplicarEstiloLabel(new Label("ID del Empleado:"), new Label("Nombre del Empleado:"), new Label("Tipo de Contrato:"));
        aplicarEstiloInput(txtId, txtNombre, cbTipo);

        txtId.setPromptText("Ej. EMP-001");
        txtNombre.setPromptText("Ej. Alex Rodríguez");
        
        cbTipo.getItems().addAll("Asalariado", "Por Horas", "Comisionado");
        cbTipo.setPromptText("Seleccione una opción");
        cbTipo.setMaxWidth(Double.MAX_VALUE); // Para que ocupe todo el ancho
        cbTipo.setOnAction(e -> actualizarFormularioDinamico());

        // Botón Guardar con estilo gradiente premium
        Button btnGuardar = new Button("Guardar Empleado");
        btnGuardar.setStyle(ESTILO_BOTON_PRIMARIO);
        btnGuardar.setMaxWidth(Double.MAX_VALUE);
        btnGuardar.setOnAction(e -> guardarEmpleado());

        // Ensamblar Tarjeta de Registro
        cardRegistro.getChildren().addAll(
                new Label("ID del Empleado:"), txtId,
                new Label("Nombre del Empleado:"), txtNombre,
                new Label("Tipo de Contrato:"), cbTipo,
                panelDinamico, 
                btnGuardar
        );

        wrapperRegistro.getChildren().add(cardRegistro);
        tabRegistro.setContent(wrapperRegistro);

        // -------------------------------------------------------------
        // TAB 2: RESUMEN (Diseño de Tarjeta de Listado)
        // -------------------------------------------------------------
        Tab tabResumen = new Tab("Resumen");
        tabResumen.setClosable(false);
        tabResumen.setStyle("-fx-background-color: #F4F3FF; -fx-background-radius: 10px 10px 0px 0px; -fx-text-fill: #8C89A0;");

        StackPane wrapperResumen = new StackPane();
        wrapperResumen.setStyle(COLOR_FONDO_LAVANDA);
        wrapperResumen.setPadding(new Insets(20));

        VBox cardResumen = new VBox(15);
        cardResumen.setStyle(ESTILO_TARJETA);

        // Estilizar el ListView para que luzca moderno y redondeado
        listViewResumen.setStyle("-fx-background-color: #FFFFFF; "
                               + "-fx-background-radius: 12px; "
                               + "-fx-border-color: #E2DFFF; "
                               + "-fx-border-radius: 12px; "
                               + "-fx-border-width: 1.5px; "
                               + "-fx-padding: 5px;");

        Button btnCalcular = new Button("Calcular Total de Nómina");
        btnCalcular.setStyle(ESTILO_BOTON_PRIMARIO);
        btnCalcular.setMaxWidth(Double.MAX_VALUE);
        btnCalcular.setOnAction(e -> procesarYMostrarTotales());

        cardResumen.getChildren().addAll(
                new Label("Listado de Nómina:"),
                listViewResumen,
                btnCalcular
        );

        wrapperResumen.getChildren().add(cardResumen);
        tabResumen.setContent(wrapperResumen);

        // Agregar las dos pestañas decoradas al TabPane
        tabPane.getTabs().addAll(tabRegistro, tabResumen);
    }

    /**
     * Devuelve el TabPane completamente construido y decorado. 
     * Este es el método que invocará MainApp.
     */
    public TabPane obtenerTabPane() {
        return this.tabPane;
    }

    private void actualizarFormularioDinamico() {
        panelDinamico.getChildren().clear();
        String seleccion = cbTipo.getValue();
        if (seleccion == null) return;

        // Estilizamos los campos específicos antes de mostrarlos
        aplicarEstiloInput(txtSalario, txtHoras, txtTarifa, txtVentas, txtPorcentaje);

        switch (seleccion) {
            case "Asalariado":
                txtSalario.setPromptText("Salario mensual (ej. 3200)");
                Label lblSalario = new Label("Salario Mensual:");
                aplicarEstiloLabel(lblSalario);
                panelDinamico.getChildren().addAll(lblSalario, txtSalario);
                break;

            case "Por Horas":
                txtHoras.setPromptText("Cantidad de horas trabajadas");
                txtTarifa.setPromptText("Tarifa cobrada por hora");
                Label lblHoras = new Label("Horas Trabajadas:");
                Label lblTarifa = new Label("Tarifa por Hora:");
                aplicarEstiloLabel(lblHoras, lblTarifa);
                panelDinamico.getChildren().addAll(lblHoras, txtHoras, lblTarifa, txtTarifa);
                break;

            case "Comisionado":
                txtVentas.setPromptText("Monto total de ventas realizadas");
                txtPorcentaje.setPromptText("Porcentaje de comisión (0-100)");
                Label lblVentas = new Label("Ventas Totales:");
                Label lblPorcentaje = new Label("Porcentaje de Comisión:");
                aplicarEstiloLabel(lblVentas, lblPorcentaje);
                panelDinamico.getChildren().addAll(lblVentas, txtVentas, lblPorcentaje, txtPorcentaje);
                break;
        }
    }

    private void guardarEmpleado() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String tipo = cbTipo.getValue();

        if (id.isEmpty() || nombre.isEmpty() || tipo == null) {
            mostrarAlerta("Campos Vacíos", "Por favor, complete los datos básicos del empleado.");
            return;
        }

        try {
            Empleado nuevoEmpleado = null;

            switch (tipo) {
                case "Asalariado":
                    double salario = Double.parseDouble(txtSalario.getText());
                    nuevoEmpleado = new EmpleadoAsalariado(id, nombre, salario);
                    break;

                case "Por Horas":
                    int horas = Integer.parseInt(txtHoras.getText());
                    double tarifa = Double.parseDouble(txtTarifa.getText());
                    nuevoEmpleado = new EmpleadoPorHoras(id, nombre, horas, tarifa);
                    break;

                case "Comisionado":
                    double ventas = Double.parseDouble(txtVentas.getText());
                    double porcentaje = Double.parseDouble(txtPorcentaje.getText());
                    nuevoEmpleado = new EmpleadoComisionado(id, nombre, ventas, porcentaje);
                    break;
            }

            if (nuevoEmpleado != null) {
                listaEmpleados.add(nuevoEmpleado);
                mostrarAlerta("Éxito", "El empleado " + nombre + " ha sido registrado.");
                limpiarCampos();
            }

        } catch (NumberFormatException ex) {
            mostrarAlerta("Error de formato", "Asegúrese de escribir números válidos en los campos del contrato.");
        }
    }

    private void procesarYMostrarTotales() {
        listViewResumen.getItems().clear();

        if (listaEmpleados.isEmpty()) {
            listViewResumen.getItems().add("No hay empleados en la base de datos.");
            return;
        }

        for (Empleado emp : listaEmpleados) {
            String fila = String.format("💳 ID: %s | %s (%s)\n👉 Total Devengado: $%.2f\n",
                    emp.getId(),
                    emp.getNombre(),
                    emp.getClass().getSimpleName(),
                    emp.calcularTotal()
            );
            listViewResumen.getItems().add(fila);
        }
    }

    // Auxiliares de Estilo para evitar duplicar líneas CSS
    private void aplicarEstiloInput(Control... controles) {
        for (Control c : controles) {
            c.setStyle(ESTILO_INPUT);
        }
    }

    private void aplicarEstiloLabel(Label... etiquetas) {
        for (Label l : etiquetas) {
            l.setStyle(ESTILO_LABEL);
        }
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        cbTipo.setValue(null);
        panelDinamico.getChildren().clear();
        txtSalario.clear();
        txtHoras.clear();
        txtTarifa.clear();
        txtVentas.clear();
        txtPorcentaje.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        
        // Estilizar el cuadro de alerta para que no desentone con la app
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white; -fx-font-family: 'Segoe UI';");
        dialogPane.getButtonTypes().forEach(buttonType -> {
            Button btn = (Button) dialogPane.lookupButton(buttonType);
            btn.setStyle("-fx-background-color: #6D5BFA; -fx-text-fill: white; -fx-background-radius: 6px;");
        });
        
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}