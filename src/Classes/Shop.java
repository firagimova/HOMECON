/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


public class Shop extends AItems{
    String type;
     
    /***
     * four parameter constructor
     * @param type
     * @param name
     * @param amount
     * @param price 
     */
    public Shop(String type,String name, double amount, double price) {
        super(name, amount, price);
        this.type = type;
    }
    /***
     * one parameter constructor
     * @param type 
     */
    public Shop(String type){
         
        this.type = type;
    }
  
}
