package ke.co.mspace.loginproject.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ke.co.mspace.loginproject.model.Employee;
import ke.co.mspace.loginproject.util.EmployeeUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


@ViewScoped
@Named
public class EmployeeController implements Serializable {
    

//   autowire the session management utility class 
    @Inject
    private EmployeeUtil employeeUtil;
    
    private Employee employee;
    
    private List<Map<String, Object>> allnewEmployees = new ArrayList<>();
    
    private Employee selectedEmployee2;
    
    public List<Map<String, Object>> getAllnewEmployees() {
        return allnewEmployees;
    }

    public void setAllnewEmployees(List<Map<String, Object>> allnewEmployees) {
        this.allnewEmployees = allnewEmployees;
    }
    
    public EmployeeUtil getEmployeeUtil() {
        return employeeUtil;
    }

    public Employee getSelectedEmployee2() {
        return selectedEmployee2;
    }

    public void setSelectedEmployee2(Employee selectedEmployee2) {
        this.selectedEmployee2 = selectedEmployee2;
    }

    

    
//    update an employee
    public void updateEmployee(){
        try(Session session = employeeUtil.getSession()){       
            
            Transaction tx = session.beginTransaction();
            
            Query query = session.createQuery("UPDATE Employee SET email=:email, username=:username, password=:password WHERE id=:id");
            
            query.setParameter("email", selectedEmployee2.getEmail());
            query.setParameter("username", selectedEmployee2.getUsername());
            query.setParameter("password", selectedEmployee2.getPassword());
            query.setParameter("id", selectedEmployee2.getId());
            
            int status = query.executeUpdate();
            
            System.out.println("update status: " + status);
            
            tx.commit();

            
        }catch(Exception e){
            System.out.println("An error occured while updating employee: " + e);
        }
    }
    
//    Delete an employee
    
     public String deleteEmployee(Employee employee){
        try(Session session = employeeUtil.getSession()){
            

            Transaction tx = session.beginTransaction();
            session.delete(employee);

            tx.commit();
            
            System.out.println("employee deleted successfully!");

        }catch(Exception e){
            System.out.println("An error occured while deleting " + e);
        }
        return "employee deleted successfully!";
    }
     
//     fetch all employees and put them in a map
     public List<Map<String, Object>> fetchEmployees() {
        List<Employee> allEmployees = new ArrayList<>();


        try (Session session = employeeUtil.getSession()){
            
            allEmployees = session.createQuery("from Employee ", Employee.class).list();
            
                        
            for(int i=0; i<allEmployees.size(); i++){
                Map<String, Object> employeeMap = new HashMap<>();

                employeeMap.put("id",allEmployees.get(i).getId());
                employeeMap.put("username",allEmployees.get(i).getUsername());
                employeeMap.put("email",allEmployees.get(i).getEmail());
                employeeMap.put("password",allEmployees.get(i).getPassword());

                allnewEmployees.add(employeeMap);
            }
            
            System.out.println("All new employees here! " + allnewEmployees.get(0));
            
            Map<String, Object> firstEmployee = allnewEmployees.get(0);
            
            String password = (String)firstEmployee.get("password");
            System.out.println("password of first employee " + password);

            
            
        } catch (Exception e) {
            System.out.println("An error has occured while fetching the data " + e);

        }
        return allnewEmployees;
        
    }
     
//     fetch all employees and return a list
     public List<Employee> fetchEmployees2() {
         
         try(Session session = employeeUtil.getSession()) {
            
            List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
            
            return employees;
         }catch(Exception e){
             System.out.println("An error has occured while fetching the data " + e);
             return null;
         }
     }
     
     
     public String logout(){
         System.out.println("Logout method called!");
         return "login.xhtml?redirect=true";
     }
     
}






