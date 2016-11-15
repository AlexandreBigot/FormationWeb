package fr.demos.formation.web;

import java.time.LocalTime;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class DemoListener
 *
 */
@WebListener
public class DemoListener implements ServletContextListener, HttpSessionListener {

//	private int CompteurSessions;
	private Compteur monCompteur = new Compteur();
	
    public DemoListener() {
        // TODO Auto-generated constructor stub
    }

    // ecoute le premier request.getSession() (cr�ation de session)
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	monCompteur.incrementeValeur();
    	arg0.getSession().getServletContext().setAttribute("CompteurSession", monCompteur);
    	
    	// incr�mente le compteur
    //	CompteurSessions++;
    	// mets � jour la valeur dans l'objet Integer et non dans la variable de calcul de type int
    //	arg0.getSession().getServletContext().setAttribute("CompteurSession", CompteurSessions);

    	// version dans la servlet directement
//		LocalTime time = (LocalTime)session.getAttribute("heurePremiereConnexion");
//		if (time == null){
//			time = LocalTime.now();
//			session.setAttribute("heurePremiereConnexion", time);
    	
    	LocalTime time = LocalTime.now();
    	arg0.getSession().setAttribute("heurePremiereConnexion", time);
    	
    }

    // timeout (p�riode max sans activit�) ou session.invalidate
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	monCompteur.decrementeValeur();
    	arg0.getSession().getServletContext().setAttribute("CompteurSession", monCompteur);
    	// d�cr�mente le compteur
   // 	CompteurSessions--;
    	// mets � jour la valeur dans l'objet Integer et non dans la variable de calcul de type int
  //  	arg0.getSession().getServletContext().setAttribute("CompteurSession", CompteurSessions);
    }

    // �coute l'arr�t de l'appli (donc l'arr�t de Tomcat)
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    // �coute le d�marrage de l'appli (donc le d�marrage de Tomcat)
    public void contextInitialized(ServletContextEvent arg0)  { 
    	
    	// On stock le compteur dans un attribut mais attention il stocke un objet
    	// donc il cast en automatiquement en Interger !
    	// Donc � chaque fois qu'on fait un calcul dessus il faut le restocker sinon il ne va pas mettre � jour l'objet
    	// mais cr�er une nouvelle variable de type int et non integer
    	//arg0.getServletContext().setAttribute("CompteurSession", CompteurSessions);
    
    }
	
}
