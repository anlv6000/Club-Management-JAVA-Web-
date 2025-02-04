/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author Doan Quan
 */
public class SignUp_Validation {

    public SignUp_Validation() {
        
    }
   public String validationUsername(String user, String pass, String RePass){
       if(user ==null || user.trim().isEmpty() || !user.matches("[a-zA-Z0-9_]+")){
           return "Invalid username. Only letters, digits, and underscores are allowed." ; 
       
   
   }
        

      if (pass==null || pass.length() < 8 || !pass.matches(".*[A-Z].*") || !pass.matches(".*[a-z].*") || !pass.matches(".*\\d.*") || !pass.matches(".*[!@#$%^&*].*")) {
           return "Password must be at least 8 characters long and contain uppercase, lowercase, digits, and special characters.";
            
      }      
       
      
       if (!pass.equals(RePass)){
           return "Passwords do not match.";
          
       }
       return null;
        
       
    
    
}
   
}
