package ke.co.mspace.loginproject.util;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@Named
@SessionScoped
public class EmployeeUtil implements Serializable{
    
    public Session getSession(){
        try{
         Configuration con = new Configuration().configure("hibernate.cfg.xml");
        
        SessionFactory sf = con.buildSessionFactory();
        
        Session session = sf.openSession();
        return session;
        }
        catch(HibernateException exception){
            System.out.println("A hibernate error has occured!" + exception);
            return null;
        }
    }
}
