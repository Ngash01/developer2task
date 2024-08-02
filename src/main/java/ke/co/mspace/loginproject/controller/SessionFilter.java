//package ke.co.mspace.loginproject.controller;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.logging.Logger;
//
///**
// *
// * @author gkowalsk\
// * Taken from example ( https://netbeans.org/kb/docs/javaee/ecommerce/manage-sessions.html?print=yes#seeAlso ) 
// */
//@WebFilter(urlPatterns = "/faces/*" ,filterName = "TimeOfDayFilter" )
//public class SessionFilter implements Filter {
//    private static final Logger LOG = Logger.getLogger(SessionFilter.class.getName());
//
//    /**
//     *
//     * @param request
//     * @param response
//     * @param chain
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//
//        HttpSession session = req.getSession(false);
//
//        // if session doesn't exist, forward user to session error page
//        if (session == null) { 
//            try {
//                LOG.info("Sesion timed out, redirecting to loging.xhtml page");
//                req.getRequestDispatcher("/timeout.xhtml").forward(request, response);
//            } catch (ServletException | IOException ex) {
//                ex.printStackTrace();
//            }
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
// 
//    @Override
//    public void destroy() {}
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//}