package Classes;

import Frames.LoginFrame;
import Frames.NeedsFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class Expenses implements IExpenses {

    Statement statement;
    PreparedStatement state;
    ResultSet resultSet;
    Connection connection = null;
    dbHelper dbHelper1 = new dbHelper();
    double sum;
    double userSum;
    double sumAll;
    double specialSum;
    int counter;
    Shop shop;
    Bill bill;
    
/***
 * takes the user table and counts the house members in table
 * @return return member numbers of house as a counter
 * 
 */
    public int NumberOfHousemate() {
        try {
            counter = 0;
            connection = dbHelper1.getConnection();
            String sql = "select * from User ";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("UserName");
                counter++;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return counter;
    }
    
/***
 * calculate all expense and return the debt of one logged in user(member housemate) 
 * @return resultSum 
 * 
 */
    @Override
    public double calculateExpense() {
        try {
            sum = 0;
            
            sum = calculateSumExpense();

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            userSum = 0;
            connection = dbHelper1.getConnection();
            String sql = "select * from expenses where User = ?";
            state = connection.prepareStatement(sql);
            state.setString(1, LoginFrame.UserName);
            resultSet = state.executeQuery();

            while (resultSet.next()) {
                int price = resultSet.getInt("Price");
                userSum = userSum + price;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Classes.Expenses.calculateExpense()");
        }
        System.out.println(userSum);
        int num = NumberOfHousemate();
        double resultSum = ((sum) / num)-userSum;

        return resultSum;

    }
    
/***
 * takes the user information and expense information and add the expense to database
 * @param userName
 * @param type
 * @param expenseName
 * @param amount
 * @param date input date
 * @param price 
 *
 */
    @Override
    public void addExpenseToDB(String userName, String type, String expenseName, int amount, String date, int price) {
        String sql = "INSERT INTO expenses(User,ExpenseType,ExpenseName,Amount,Date,Price) VALUES(?,?,?,?,?,?)";
        try {

            connection = dbHelper1.getConnection();
            state = connection.prepareStatement(sql);
            state.setString(1, userName);
            state.setString(2, type);
            state.setString(3, expenseName);
            state.setInt(4, amount);
            state.setString(5, date);
            state.setInt(6, price);

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(NeedsFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                state.executeUpdate();
                state.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(NeedsFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
/***
 * update function for database
 * @param model
 * 
 */
    @Override
    public void tableUpdate(DefaultTableModel model) {

        try {
            connection = dbHelper1.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from expenses");

            while (resultSet.next()) {
                String UserName = resultSet.getString("User");
                String type = resultSet.getString("ExpenseType");
                int amount = resultSet.getInt("Amount");
                String date = resultSet.getString("Date");
                int price = resultSet.getInt("Price");;
                model.addRow(new Object[]{UserName, type, amount, date, price});

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
/***
 * takes information expense table and calculate all sum expense(total in main menu)
 * @return return sumAll
 * 
 */
    @Override
    public double calculateSumExpense() {

        try {
            sumAll = 0;
            connection = dbHelper1.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from expenses");

            while (resultSet.next()) {

                double price = resultSet.getInt("Price");
                sumAll = sumAll + price;

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sumAll;
    }
    
/***
 * takes information expense table for type and calculate special expense
 * @param expName
 * @return return specialSum(total sum per type)
 */
    @Override
    public  double calculateSpecialExpenseSum(String expName) {
        try {
            specialSum = 0;
            connection = dbHelper1.getConnection();
            String sql1 = "select * from expenses where ExpenseType = ?";
            state = connection.prepareStatement(sql1);
            state.setString(1, expName);
            resultSet = state.executeQuery();

            while (resultSet.next()) {
                System.out.println("1");
                int price = resultSet.getInt("Price");
                specialSum = specialSum + price;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return specialSum;

    }

}
