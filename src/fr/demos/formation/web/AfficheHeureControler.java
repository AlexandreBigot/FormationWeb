package fr.demos.formation.web;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AfficheDateControler
 */
// permet à tomcat de savoir que c'est une servlet et donne l'url pour l'atteindre
@WebServlet("/affiche_heure")
public class AfficheHeureControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficheHeureControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// simpliste, le controler n'appelle pas de code métier, il appelle le time.now()
		// il pourrait appeler une méthode ajouter un article au panier par exemple
		LocalTime time = LocalTime.now();
		
		// passage de données entre controler et view
		request.setAttribute("heureActuelle", time);
		
		// maintenant on veut que la jsp affiche l'heure
		// on utilise un request dispatcher pour passer la main à la jsp
		RequestDispatcher rd =
				// voilà vers quoi je veux aller
				request.getRequestDispatcher("/HeureVue.jsp");
				// maintenant vas-y
				rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
