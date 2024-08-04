
package ke.co.mspace.loginproject.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named(value="login_controller")
@SessionScoped
public class LoginController implements Serializable{
    private String email;
    private String password;
    
    @Inject
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
    
    
    public String login(){
       
        String page = "login.xhtml";
        
        
        List<Map<String, Object>> allEmployees = employeeController.fetchEmployees();
        
        for(int i=0; i<allEmployees.size(); i++){
            Map<String, Object> firstEmployee = allEmployees.get(i);
             String db_email =  (String)firstEmployee.get("email");
             String db_password = (String)firstEmployee.get("password");

            if(email.equals(db_email)&& password.equals(db_password)){
                
//                 HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//                        .getExternalContext().getSession(true);
//                session.setAttribute("user", email);
                
                 return  "user_profile.xhtml?redirect=true";
            }else{
                System.out.println("Wrong credentials!");
                FacesMessage msg = new FacesMessage();
                msg.setDetail("Incorrect username or password");
                msg.setSeverity(FacesMessage.SEVERITY_WARN);
                msg.setSummary("Invalid Logins!");

                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
               
          }
        return page;
    
    }
    
    
    
    
}
