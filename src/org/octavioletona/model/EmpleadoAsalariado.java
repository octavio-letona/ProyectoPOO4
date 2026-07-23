
package org.octavioletona.model;

public class EmpleadoAsalariado extends Empleado{

    private double salarioMensual;
    
    public EmpleadoAsalariado(){
        
    }    
    
       public EmpleadoAsalariado(String id, String nombre, double salarioMensual){
          super(id, nombre);
         this.salarioMensual = salarioMensual;  
    }    
    

    public void setSalarioMensual(double salarioMensual){
            //acceder al atributo de clase y asignar el pareametro del metodo
            //validaciones
            this.salarioMensual = salarioMensual;
    }
    
    public double getSalarioMensual(){
        return this.salarioMensual;
    }
    
    @Override
    public  double calcularTotal() {
        return this.calcularTotal();
        
    }
    

}
