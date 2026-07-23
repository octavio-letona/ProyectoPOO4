/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.controller;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import org.octavioletona.model.Empleado;
import org.octavioletona.model.EmpleadoAsalariado;
import org.octavioletona.model.EmpleadoComisionado;
import org.octavioletona.model.EmpleadoPorHoras;
import org.octavioletona.view.EmpleadoView;

public class EmpleadoController {

    private EmpleadoView vista;
    private ArrayList<Empleado> listaEmpleados;

    public EmpleadoController(EmpleadoView vista) {
        this.vista = vista;
        this.listaEmpleados = new ArrayList<>();
        
        // Conectamos los listeners a los componentes de la vista
        inicializarEventos();
    }

    private void inicializarEventos() {
        // Evento 1: Detectar cambios en la selección del tipo de empleado
        vista.getCbTipo().setOnAction(e -> manejarCambioTipo());

        // Evento 2: Guardar el empleado ingresado
        vista.getBtnGuardar().setOnAction(e -> guardarEmpleado());

        // Evento 3: Procesar y calcular la nómina total
        vista.getBtnCalcular().setOnAction(e -> calcularNomina());
    }

    /**
     * Reacciona a la selección del ComboBox en la Vista.
     */
    private void manejarCambioTipo() {
        String seleccion = vista.getCbTipo().getValue();
        if (seleccion == null) {
            vista.limpiarPanelDinamico();
            return;
        }

        // El controlador le ordena a la vista cómo reconfigurar sus campos físicos
        switch (seleccion) {
            case "Asalariado":
                vista.mostrarCamposAsalariado();
                break;
            case "Por Horas":
                vista.mostrarCamposPorHoras();
                break;
            case "Comisionado":
                vista.mostrarCamposComisionado();
                break;
        }
    }

    /**
     * Extrae los datos de la vista, los valida y genera un objeto polimórfico en el modelo.
     */
    private void guardarEmpleado() {
        String id = vista.getTxtId().getText().trim();
        String nombre = vista.getTxtNombre().getText().trim();
        String tipo = vista.getCbTipo().getValue();

        if (id.isEmpty() || nombre.isEmpty() || tipo == null) {
            vista.mostrarAlerta("Campos Vacíos", "Por favor, complete los datos básicos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            Empleado nuevoEmpleado = null;

            switch (tipo) {
                case "Asalariado":
                    double salario = Double.parseDouble(vista.getTxtSalario().getText());
                    nuevoEmpleado = new EmpleadoAsalariado(id, nombre, salario);
                    break;

                case "Por Horas":
                    int horas = Integer.parseInt(vista.getTxtHoras().getText());
                    double tarifa = Double.parseDouble(vista.getTxtTarifa().getText());
                    nuevoEmpleado = new EmpleadoPorHoras(id, nombre, horas, tarifa);
                    break;

                case "Comisionado":
                    double ventas = Double.parseDouble(vista.getTxtVentas().getText());
                    double porcentaje = Double.parseDouble(vista.getTxtPorcentaje().getText());
                    nuevoEmpleado = new EmpleadoComisionado(id, nombre, ventas, porcentaje);
                    break;
            }

            if (nuevoEmpleado != null) {
                listaEmpleados.add(nuevoEmpleado);
                vista.mostrarAlerta("Éxito", "El empleado '" + nombre + "' fue registrado.", Alert.AlertType.INFORMATION);
                vista.limpiarCampos();
            }

        } catch (NumberFormatException ex) {
            vista.mostrarAlerta("Error de Formato", "Asegúrese de ingresar números válidos en los campos del contrato.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Recorre la lista de modelos de manera polimórfica y empuja los resultados a la Vista.
     */
    private void calcularNomina() {
        vista.getListViewResumen().getItems().clear();

        if (listaEmpleados.isEmpty()) {
            vista.getListViewResumen().getItems().add("No hay empleados registrados en el sistema.");
            return;
        }

        // Iteración polimórfica pura
        for (Empleado emp : listaEmpleados) {
            String fila = String.format("💳 ID: %s | %s (%s)\n👉 Pago Total: $%.2f\n",
                    emp.getId(),
                    emp.getNombre(),
                    emp.getClass().getSimpleName(),
                    emp.calcularTotal() // El polimorfismo decide qué fórmula usar en tiempo de ejecución
            );
            vista.getListViewResumen().getItems().add(fila);
        }
    }
}