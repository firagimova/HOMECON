 
package Classes;


public class Bill extends AItems{

     String type;
    
     
/**
 *
 * Two parameter constructor
 * @param price price of bill
 * @param type  type of bill
 */
    public Bill(String type, double price) {
        super(price);
        this.type=type;
       
    }
    
 /**
 *
 * one parameter constructor
 * @param type type of bill
 */
    public Bill(String type){
        this.type=type;
    
    }
 
   
}
