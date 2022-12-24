package org.exposeproject.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.exposeproject.dao.config.DAOFactory;
import org.exposeproject.dao.config.DatabaseStarterListner;
import org.exposeproject.dao.interfaces.MessageInterface;
import org.exposeproject.dao.interfaces.UserInterface;
import org.exposeproject.exception.NotFoundException;
import org.exposeproject.exception.SQLErrorException;
import org.exposeproject.metier.FormAttributs;
import org.exposeproject.metier.ForumValidate;
import org.exposeproject.models.User;

@WebServlet("/signup")
@MultipartConfig(location="/root/git/InjectionProjection/src/main/webapp/pictures", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class SignUP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TAILLE_TAMPON = 1024;
		private UserInterface userImplement;

	@Override
	public void init() throws ServletException {
		userImplement = UserInterface.getInstance();
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//R�cuperer les variables
		
		String username = getParamFromMultipartRequest(request,FormAttributs.ATTRIBUT_USERNAME);
		String password = getParamFromMultipartRequest(request, FormAttributs.ATTRIBUT_PASSWORD);
		String nom_porteur = getParamFromMultipartRequest(request, FormAttributs.ATTRIBUT_NOM_PORTEUR);
		String bin = getParamFromMultipartRequest(request, FormAttributs.ATTRIBUT_CARD_NUMBER);
		String date = getParamFromMultipartRequest(request, FormAttributs.ATTRIBUT_EXPIRATION_DATE);
		String cvv = getParamFromMultipartRequest(request, FormAttributs.ATTRIBUT_SECURITY_CODE);
		String filename = getNomFichier(request.getPart(FormAttributs.ATTRIBUT_PHOTO));
		
		//Upload Shell
		
		ecrireFichier(request.getPart(FormAttributs.ATTRIBUT_PHOTO), filename, "/root/git/InjectionProjection/src/main/webapp/pictures/");
		
		User user = new User(username, password, "/root/git/InjectionProjection/src/main/webapp/pictures/"+filename, bin, Integer.parseInt(cvv), date);
		try {
			userImplement.addUser(user);
		} catch (SQLErrorException | NotFoundException e) {
			e.printStackTrace();
		}
		response.sendRedirect("login");
	}
	
	private void ecrireFichier(Part part, String nomFichier, String chemin) {
		try (BufferedInputStream entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
				BufferedOutputStream sortie = new BufferedOutputStream(
						new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON)) {
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getParamFromMultipartRequest(HttpServletRequest request, String paramName)
			throws IOException, ServletException {
		Part part = request.getPart(paramName);
		Scanner scanner = new Scanner(part.getInputStream());
		String myString = scanner.nextLine();
		scanner.close();
		return myString;
	}

	private static String getNomFichier(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
