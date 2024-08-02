
package ke.co.mspace.loginproject.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import ke.co.mspace.loginproject.model.Employee;
import ke.co.mspace.loginproject.util.EmployeeUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Named(value="add_user_controller")
@SessionScoped
public class AddUserController implements Serializable {
    
    
    private int id;
    private String password;
    private String username;
    private String email;
     @Inject
    private EmployeeUtil employeeUtil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeUtil getEmployeeUtil() {
        return employeeUtil;
    }

    public void setEmployeeUtil(EmployeeUtil employeeUtil) {
        this.employeeUtil = employeeUtil;
    }
    
    
    //    create a new user to the db
    public String saveData() {
        
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        
        try(Session session = employeeUtil.getSession()) {
//          calls the getsession method and retrieves the session from hibernate
            

//           
            Transaction tx = session.beginTransaction();
            session.save(employee);
            tx.commit();

            System.out.println("employee successfully created");
            return "employee successfully created";
        } catch (Exception e) {
            System.out.println("An Exception has occured while saving the data" + e);
            return null;
        }
        
        

    }
    
}
