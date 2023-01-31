/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


public abstract class AItems {
    
    private String name;
    double amount;
    private double price;
    
    
    /***
     * Three parameter constructor
     * @param name name of item
     * @param amount amount of item
     * @param price price of item
     */
    public AItems(String name, double amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
    
    /***
     * Two parameter constructor
     * @param amount amount of item
     * @param price price of item
     */
    public AItems(double amount, double price) {
       
        this.amount = amount;
        this.price = price;
    }
    
    /***
     * one parameter constructor
     * @param price price of item
     */
    public AItems( double price) {
       
      
        this.price = price;
    }
    
    /***
     * empty constructor
     * 
     */
    public AItems(){
        
    }
    
    
    
    
    /***
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }
   /***
    * getter for amount
    * @return amount
    */
    public double getAmount() {
        return amount;
    }
    /***
    * getter for price
    * @return amount
    */
    public double getPrice() {
        return amount;
    }
    
 
    
}
