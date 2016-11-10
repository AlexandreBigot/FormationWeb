package fr.demos.formation.web;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaisieInscriptionControler
 */
@WebServlet("/SaisieInscriptionControler")
public class SaisieInscriptionControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaisieInscriptionControler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// je crée ma session
		HttpSession session = request.getSession();
// on cast l'atribut de session en local time et on le mets en valeur de time
// si on a pas encore fait de doGet l'attribut "heurePremiereConnexion" de la session est null
// si on a déja fait un doGet on recopie l'heure de première session		
		LocalTime time = (LocalTime)session.getAttribute("heurePremiereConnexion");
		if (time == null){
			time = LocalTime.now();
			session.setAttribute("heurePremiereConnexion", time);
		}
		
// je créée ma source de pays
		ArrayList<String> listePays = new ArrayList<String>();
		listePays.add("France");
		listePays.add("Trumpland");
		listePays.add("Suisse");
		listePays.add("Canada");
// je stocke ma liste de pays en attribut de ma request pour pouvoir utiliser EL
		request.setAttribute("paysDeMaListe", listePays);
		
		RequestDispatcher rd = request.getRequestDispatcher("/SaisieInscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// je stocke l'heure de la dernière requête		
		HttpSession session = request.getSession();
		LocalTime lastRequestTime = LocalTime.now();
		session.setAttribute("heureDerniereRequete", lastRequestTime);
		
// je crée des messages d'erreur dans une arraylist si un champ n'est pas bien rempli lors du submit		
		ArrayList<String> messagesErreurFormulaire = new ArrayList<String>();
		String action = request.getParameter("action");
		if (action != null && action.equals("créer")) {
			String valueNom = request.getParameter("nom");
			String valueAgeString = request.getParameter("age");
			String valueCourriel = request.getParameter("courriel");
			String valueMessage = request.getParameter("message");

			// transforme valueAgeString en age int
			int valueAge = 0;
			try {
				valueAge = Integer.parseInt(valueAgeString);
			} catch (NumberFormatException ex) {
				messagesErreurFormulaire.add("Un age doit être un nombre pas un String !!!");
			} // catch

			// mon age est-il positif ?
			if (valueAge < 0) {
				messagesErreurFormulaire.add("Un age ne peut pas être négatif");
			}

			// teste si mes champs sont bien remplis
			// je vais stocker un ou plusieurs messages d'erreurs
			// idéalement dans hashmap mais on va d'abord faire arraylist
			if (valueNom.equals("")) {
				messagesErreurFormulaire.add("Il faut donner son nom !");
			}
			if (valueAgeString.equals("")) {
				messagesErreurFormulaire.add("Il faut donner son age !");
			}
			if (valueCourriel.equals("")) {
				messagesErreurFormulaire.add("Il faut donner son email !");
			}
			if (valueMessage.equals("")) {
				messagesErreurFormulaire.add("Il faut laisser un message !");
			}

			if (messagesErreurFormulaire.isEmpty()) {
				// enregistrer les données reçues du formulaire
				// on va mettre un message de validation dans la jsp en
				// affichant les valeurs
				String formulaireEnregistre = "Vos données ont bien été enregistrées";
				request.setAttribute("messageFormulaireEnregistre", formulaireEnregistre);
			} else {
				String test = "test";
				request.setAttribute("messageErreur",messagesErreurFormulaire);
			//	request.setAttribute("messageErreur", test);

			} // if else message d'erreur
		} // if else créer
		

		if (action != null && action.equals("déconnecter")) {
			session.invalidate();
			// + renvoyer vers une nouvelle page sinon on reste dans la page sans faire de doGet
			//  et une nouvelle session est crée sans être passé par le doGet et sans mettre l'heure de première connexion
		} // if déconnecter

		// je recharge la page
		RequestDispatcher rd = request.getRequestDispatcher("/SaisieInscription.jsp");
		rd.forward(request, response);

	} // doPost

} // class
