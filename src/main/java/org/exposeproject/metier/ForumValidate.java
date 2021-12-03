package org.exposeproject.metier;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class ForumValidate implements Serializable, FormAttributs {

	private HttpServletRequest req;
	private HashMap<String, String> errors;
	private HttpServletResponse resp;

	public ForumValidate(HttpServletRequest request) {
		this.req = request;
		errors = new HashMap<String, String>();
	}

	public void validateForm(HashMap<String, String> attributs) {

		String username = attributs.get(ATTRIBUT_USERNAME);
		String password = attributs.get(ATTRIBUT_PASSWORD);
		String nomporteur = attributs.get(ATTRIBUT_NOM_PORTEUR);
		String bin = attributs.get(ATTRIBUT_CARD_NUMBER);
		String date = attributs.get(ATTRIBUT_EXPIRATION_DATE);
		String cvv = attributs.get(ATTRIBUT_SECURITY_CODE);
		String filename = attributs.get(ATTRIBUT_PHOTO);

		verfierFichier(filename);

	}

	public void verfierFichier(String filename) {
		if (filename.indexOf(".") != -1) {
			String extension = filename.substring(filename.lastIndexOf("."));
			if (!(extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png"))) {
				this.errors.put(ERROR_PHOTO, "Le fichier doit etre une photo valide jpg jpeg png");
			}
		} else {
			this.errors.put(ERROR_PHOTO, "Le fichier doit etre une photo valide jpg jpeg png");
		}

	}

	public boolean isValid() {
		return errors.size() == 0;
	}

	public HttpServletRequest getReq() {
		return req;
	}

	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	public HttpServletResponse getResp() {
		return resp;
	}

	public void setResp(HttpServletResponse resp) {
		this.resp = resp;
	}

}
