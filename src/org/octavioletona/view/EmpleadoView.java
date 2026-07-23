/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.view;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class EmpleadoView {

    private TabPane tabPane = new TabPane();

    // Componentes del Formulario (Ahora privados con Getters)
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

    // Botones y Lista
    private Button btnGuardar = new Button("Guardar Empleado");
    private Button btnCalcular = new Button("Calcular Total de Nómina");
    private ListView<String> listViewResumen = new ListView<>();

    // Constantes de Diseño Lavanda (CSS)
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

    public EmpleadoView() {
        inicializarUI();
    }

    private void inicializarUI() {
        tabPane.setStyle(COLOR_FONDO_LAVANDA);

        // -------------------------------------------------------------
        // TAB 1: REGISTRO (Diseño de Tarjeta)
        // -------------------------------------------------------------
        Tab tabRegistro = new Tab("Registro");
        tabRegistro.setClosable(false);
        tabRegistro.setStyle("-fx-background-color: white; -fx-background-radius: 10px 10px 0px 0px; -fx-font-weight: bold; -fx-text-fill: #6D5BFA;");

        StackPane wrapperRegistro = new StackPane();
        wrapperRegistro.setStyle(COLOR_FONDO_LAVANDA);
        wrapperRegistro.setPadding(new Insets(20));

        VBox cardRegistro = new VBox(12);
        cardRegistro.setStyle(ESTILO_TARJETA);

        aplicarEstiloLabel(new Label("ID del Empleado:"), new Label("Nombre del Empleado:"), new Label("Tipo de Contrato:"));
        aplicarEstiloInput(txtId, txtNombre, cbTipo);

        txtId.setPromptText("Ej. EMP-001");
        txtNombre.setPromptText("Ej. Alex Rodríguez");
        
        cbTipo.getItems().addAll("Asalariado", "Por Horas", "Comisionado");
        cbTipo.setPromptText("Seleccione una opción");
        cbTipo.setMaxWidth(Double.MAX_VALUE);

        btnGuardar.setStyle(ESTILO_BOTON_PRIMARIO);
        btnGuardar.setMaxWidth(Double.MAX_VALUE);

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
        // TAB 2: RESUMEN
        // -------------------------------------------------------------
        Tab tabResumen = new Tab("Resumen");
        tabResumen.setClosable(false);
        tabResumen.setStyle("-fx-background-color: #F4F3FF; -fx-background-radius: 10px 10px 0px 0px; -fx-text-fill: #8C89A0;");

        StackPane wrapperResumen = new StackPane();
        wrapperResumen.setStyle(COLOR_FONDO_LAVANDA);
        wrapperResumen.setPadding(new Insets(20));

        VBox cardResumen = new VBox(15);
        cardResumen.setStyle(ESTILO_TARJETA);

        listViewResumen.setStyle("-fx-background-color: #FFFFFF; "
                               + "-fx-background-radius: 12px; "
                               + "-fx-border-color: #E2DFFF; "
                               + "-fx-border-radius: 12px; "
                               + "-fx-border-width: 1.5px; "
                               + "-fx-padding: 5px;");

        btnCalcular.setStyle(ESTILO_BOTON_PRIMARIO);
        btnCalcular.setMaxWidth(Double.MAX_VALUE);

        cardResumen.getChildren().addAll(
                new Label("Listado de Nómina:"),
                listViewResumen,
                btnCalcular
        );

        wrapperResumen.getChildren().add(cardResumen);
        tabResumen.setContent(wrapperResumen);

        tabPane.getTabs().addAll(tabRegistro, tabResumen);
    }

    // --- Métodos de Asistencia para Modificar el Formulario Dinámicamente ---
    
    public void mostrarCamposAsalariado() {
        panelDinamico.getChildren().clear();
        aplicarEstiloInput(txtSalario);
        Label lblSalario = new Label("Salario Mensual:");
        aplicarEstiloLabel(lblSalario);
        txtSalario.setPromptText("Salario mensual (ej. 3200)");
        panelDinamico.getChildren().addAll(lblSalario, txtSalario);
    }

    public void mostrarCamposPorHoras() {
        panelDinamico.getChildren().clear();
        aplicarEstiloInput(txtHoras, txtTarifa);
        Label lblHoras = new Label("Horas Trabajadas:");
        Label lblTarifa = new Label("Tarifa por Hora:");
        aplicarEstiloLabel(lblHoras, lblTarifa);
        txtHoras.setPromptText("Cantidad de horas");
        txtTarifa.setPromptText("Tarifa cobrada");
        panelDinamico.getChildren().addAll(lblHoras, txtHoras, lblTarifa, txtTarifa);
    }

    public void mostrarCamposComisionado() {
        panelDinamico.getChildren().clear();
        aplicarEstiloInput(txtVentas, txtPorcentaje);
        Label lblVentas = new Label("Ventas Totales:");
        Label lblPorcentaje = new Label("Porcentaje de Comisión:");
        aplicarEstiloLabel(lblVentas, lblPorcentaje);
        txtVentas.setPromptText("Monto total de ventas");
        txtPorcentaje.setPromptText("Porcentaje (0-100)");
        panelDinamico.getChildren().addAll(lblVentas, txtVentas, lblPorcentaje, txtPorcentaje);
    }

    public void limpiarPanelDinamico() {
        panelDinamico.getChildren().clear();
    }

    public void limpiarCampos() {
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

    public void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white; -fx-font-family: 'Segoe UI';");
        dialogPane.getButtonTypes().forEach(buttonType -> {
            Button btn = (Button) dialogPane.lookupButton(buttonType);
            btn.setStyle("-fx-background-color: #6D5BFA; -fx-text-fill: white; -fx-background-radius: 6px;");
        });
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // --- Helpers de Estilos ---
    private void aplicarEstiloInput(Control... controles) {
        for (Control c : controles) c.setStyle(ESTILO_INPUT);
    }

    private void aplicarEstiloLabel(Label... etiquetas) {
        for (Label l : etiquetas) l.setStyle(ESTILO_LABEL);
    }

    // --- GETTERS (Para uso exclusivo del Controlador) ---
    public TabPane getTabPane() { return tabPane; }
    public TextField getTxtId() { return txtId; }
    public TextField getTxtNombre() { return txtNombre; }
    public ComboBox<String> getCbTipo() { return cbTipo; }
    public TextField getTxtSalario() { return txtSalario; }
    public TextField getTxtHoras() { return txtHoras; }
    public TextField getTxtTarifa() { return txtTarifa; }
    public TextField getTxtVentas() { return txtVentas; }
    public TextField getTxtPorcentaje() { return txtPorcentaje; }
    public Button getBtnGuardar() { return btnGuardar; }
    public Button getBtnCalcular() { return btnCalcular; }
    public ListView<String> getListViewResumen() { return listViewResumen; }
}