package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


@WebServlet("/ProjetsRealise")

public class ProjetsRealiseServlet extends ProjetServlet{
	
			
			private static final long serialVersionUID = -4457728187106L;

			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				TemplateEngine templateEngine = this.createTemplateEngine(req);
				resp.setCharacterEncoding("UTF-8");

				WebContext context = new WebContext(req, resp, getServletContext());

				templateEngine.process("ProjetsRealise", context, resp.getWriter());

			}	
	

}
