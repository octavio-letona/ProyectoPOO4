
package org.octavioletona.model;

public class EmpleadoPorHoras extends Empleado {

    private int horasTrabajadas; // Atributos únicos
    private double tarifaHora;

    public EmpleadoPorHoras(String id, String nombre, int horasTrabajadas, double tarifaHora) {
        super(id, nombre);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaHora = tarifaHora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(double tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    // 4. Polimorfismo
    @Override
    public double calcularTotal() {
        return this.horasTrabajadas * this.tarifaHora;
    }
}