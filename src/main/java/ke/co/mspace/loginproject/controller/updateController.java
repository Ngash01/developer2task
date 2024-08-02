
package ke.co.mspace.loginproject.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import ke.co.mspace.loginproject.model.Employee;

@Named
@SessionScoped
public class updateController implements Serializable{
    private String email;
    private String password;
    private String username;
    private Employee selectedEmployee;
    private EmployeeController employeeController;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }
    
    
    
     public String updateEmployee() {
        if (selectedEmployee != null) {
            // Update selectedEmployee with new values
            selectedEmployee.setUsername(username);
            selectedEmployee.setEmail(email);
            selectedEmployee.setPassword(password);
            
            employeeController.updateEmployee(selectedEmployee);
            
        }
        return "employee updated successfully!";
    }
    
    
}
