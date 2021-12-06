package org.exposeproject.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/download/*")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TAMPON = 10240;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path == null || "/".equals(path)) {
			response.sendError(404, "Destination introuvable");
		} else {
			path = URLDecoder.decode(path, "UTF-8");
			File file = new File("/root/git/InjectionProjection/src/main/webapp/WEB-INF/pictures", path);
			if (!file.exists()) {
				response.sendError(404, "File not found");
			} else {
				String type = "image/jpg";
				response.reset();
				response.setBufferSize(TAMPON);
				response.setContentType(type);
				response.setHeader("Content-Length", String.valueOf(file.length()));
				//response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
				try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
						BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
					byte b[] = new byte[TAMPON];
					int longueur = 0;
					while ((longueur = bis.read(b)) > 0) {
						bos.write(b);
					}
				} catch (Exception e) {
					e.printStackTrace();

				}

			}
		}
	}

}
