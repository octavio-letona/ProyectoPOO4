/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.model;

/**
 *
 * @author informatica
 */
public abstract class Empleado {
    // atributos
    
    private String id;
    private String nombre;
    
    
    //constructores
    
    public Empleado(){
    
    }
     
   public Empleado(String id, String nombre ){
       this.id = id;
       this.nombre = nombre;
       
   } 
    
    // metodos
   public void setId(String id){
       this.id = id;
   }
   
   public String getId(){
       return this.id;
   }
   
   public void setNombre(String nombre){
       this.nombre = nombre;
   }
   
   public String getNombre(){
       return this.nombre;
   }
   
   public abstract double calcularTotal();
   
}
