/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ceavalos
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Date fechaEmision = sumarRestarHorasFecha(new Date(),2);
         
         System.out.println("fecha salida : " + fechaEmision);
    }
    //fecha salida : Tue Apr 25 12:38:16 CST 2023
    //fecha salida : Tue Apr 25 14:37:15 CST 2023
    //fecha salida : Tue Apr 25 20:37:51 CST 2023
    
     public static Date sumarRestarHorasFecha(Date fecha, int horas){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fecha); // Configuramos la fecha que se recibe
      calendar.add(Calendar.HOUR, horas);  // numero de horas a añadir, o restar en caso de horas<0
      return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
 }
}
