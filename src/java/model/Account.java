/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Doan Quan
 */
public class Account {
<<<<<<< HEAD:src/java/model/Account.java
    private int id;
=======
    private String id;
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Model/Account.java
    String username;
    String password;
    String email;
    String role ;
<<<<<<< HEAD:src/java/model/Account.java
   
=======
    private String name;
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Model/Account.java
    
    
    public Account() {
    }

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

<<<<<<< HEAD:src/java/model/Account.java
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

   
=======
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Model/Account.java
      
    
    
    
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    
    

   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + username + ", email=" + email + ", password=" + password + '}';
    }
    
    
    
}
