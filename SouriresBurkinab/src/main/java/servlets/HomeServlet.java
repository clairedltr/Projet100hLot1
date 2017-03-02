package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import services.ArticleService;


@WebServlet("/Accueil")
public class HomeServlet extends ProjetServlet{

	private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			TemplateEngine templateEngine = this.createTemplateEngine(req);
			resp.setCharacterEncoding("UTF-8");
			
			WebContext context = new WebContext(req, resp, getServletContext());
			context.setVariable("article", ArticleService.getInstance().listArticle());
			templateEngine.process("Accueil", context, resp.getWriter());
		}

		
	}
