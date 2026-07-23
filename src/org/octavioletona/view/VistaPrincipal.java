/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import java.util.ArrayList;
import org.octavioletona.model.Empleado;
import org.octavioletona.model.EmpleadoAsalariado;
import org.octavioletona.model.EmpleadoComisionado;
import org.octavioletona.model.EmpleadoPorHoras;

public class VistaPrincipal extends TabPane {

    // Estructura de Datos: Lista polimórfica
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    // Componentes del formulario de Registro
    private TextField txtId = new TextField();
    private TextField txtNombre = new TextField();
    private ComboBox<String> cbTipo = new ComboBox<>();
    private VBox panelDinamico = new VBox(10); 

    // Campos de texto específicos para los hijos
    private TextField txtSalario = new TextField();
    private TextField txtHoras = new TextField();
    private TextField txtTarifa = new TextField();
    private TextField txtVentas = new TextField();
    private TextField txtPorcentaje = new TextField();

    // Componentes de la pestaña Resumen
    private ListView<String> listViewResumen = new ListView<>();

    // Constructor: Aquí construimos toda la interfaz gráfica
    public VistaPrincipal() {
        // -------------------------------------------------------------
        // TAB 1: REGISTRO (Formulario Dinámico)
        // -------------------------------------------------------------
        Tab tabRegistro = new Tab("Registro");
        tabRegistro.setClosable(false);

        VBox rootRegistro = new VBox(10);
        rootRegistro.setPadding(new Insets(15));

        txtId.setPromptText("Ingrese ID");
        txtNombre.setPromptText("Ingrese Nombre");

        cbTipo.getItems().addAll("Asalariado", "Por Horas", "Comisionado");
        cbTipo.setPromptText("Seleccione Tipo de Empleado");
        cbTipo.setOnAction(e -> actualizarFormularioDinamico());

        Button btnGuardar = new Button("Guardar Empleado");
        btnGuardar.setOnAction(e -> guardarEmpleado());

        rootRegistro.getChildren().addAll(
                new Label("ID del Empleado:"), txtId,
                new Label("Nombre del Empleado:"), txtNombre,
                new Label("Tipo de Contrato:"), cbTipo,
                panelDinamico, 
                btnGuardar
        );
        tabRegistro.setContent(rootRegistro);

        // -------------------------------------------------------------
        // TAB 2: RESUMEN (Cálculos y Visualización)
        // -------------------------------------------------------------
        Tab tabResumen = new Tab("Resumen");
        tabResumen.setClosable(false);

        VBox rootResumen = new VBox(10);
        rootResumen.setPadding(new Insets(15));

        Button btnCalcular = new Button("Calcular Total");
        btnCalcular.setOnAction(e -> procesarYMostrarTotales());

        rootResumen.getChildren().addAll(
                new Label("Listado de Nómina:"),
                listViewResumen,
                btnCalcular
        );
        tabResumen.setContent(rootResumen);

        // Agregamos las pestañas a este TabPane (this)
        this.getTabs().addAll(tabRegistro, tabResumen);
    }

    private void actualizarFormularioDinamico() {
        panelDinamico.getChildren().clear();
        String seleccion = cbTipo.getValue();
        if (seleccion == null) return;

        switch (seleccion) {
            case "Asalariado":
                txtSalario.setPromptText("Salario Mensual (ej. 2500)");
                panelDinamico.getChildren().addAll(new Label("Salario Mensual:"), txtSalario);
                break;

            case "Por Horas":
                txtHoras.setPromptText("Horas Trabajadas");
                txtTarifa.setPromptText("Tarifa por Hora");
                panelDinamico.getChildren().addAll(
                        new Label("Horas Trabajadas:"), txtHoras,
                        new Label("Tarifa por Hora:"), txtTarifa
                );
                break;

            case "Comisionado":
                txtVentas.setPromptText("Ventas Totales Realizadas");
                txtPorcentaje.setPromptText("Porcentaje de Comisión (0-100)");
                panelDinamico.getChildren().addAll(
                        new Label("Ventas Totales:"), txtVentas,
                        new Label("Porcentaje de Comisión:"), txtPorcentaje
                );
                break;
        }
    }

    private void guardarEmpleado() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String tipo = cbTipo.getValue();

        if (id.isEmpty() || nombre.isEmpty() || tipo == null) {
            mostrarAlerta("Error", "Por favor, complete los campos básicos.");
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
                mostrarAlerta("Éxito", "Empleado guardado correctamente.");
                limpiarCampos();
            }

        } catch (NumberFormatException ex) {
            mostrarAlerta("Error de datos", "Asegúrese de ingresar valores numéricos válidos.");
        }
    }

    private void procesarYMostrarTotales() {
        listViewResumen.getItems().clear();

        if (listaEmpleados.isEmpty()) {
            listViewResumen.getItems().add("No hay empleados registrados en el sistema.");
            return;
        }

        for (Empleado emp : listaEmpleados) {
            String fila = String.format("ID: %s | %s | Tipo: %s | Total a Pagar: $%.2f",
                    emp.getId(),
                    emp.getNombre(),
                    emp.getClass().getSimpleName(), 
                    emp.calcularTotal() // Polimorfismo
            );
            listViewResumen.getItems().add(fila);
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
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}