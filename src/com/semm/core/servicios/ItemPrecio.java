/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.semm.core.servicios;

/**
 *
 * @author ruchango
 */
public class ItemPrecio {
    
      private Item item;
      private int cant_min,cant_max;
      private float precio;
      
      public Item getItem(){
          return this.item;
      }
      
      public void setItem(Item item){
          this.item = item;
      }
      
      public int getCant_Min(){
          return this.cant_min;
      }
      
      public void setCant_Min(int cant_min){
          this.cant_min = cant_min;
      }
      
      public int getCant_Max(){
          return this.cant_max;
      }
      
      public void setCant_Max(int cant_max){
          this.cant_max = cant_max;
      }

      public float getPrecio(){
          return this.precio;
      }
      
      public void setPrecio(float precio){
          this.precio = precio;
      }
}
