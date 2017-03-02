package services;

import java.io.IOException;
import java.util.List;

import daos.ArticleDao;
import pojos.Article;

public class ArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	
	private static class ArticleServiceHolder {
		private static ArticleService instance = new ArticleService();
	}
	
	public static ArticleService getInstance() {
		return ArticleServiceHolder.instance;
	}

	private ArticleService() {
	}
	public List<Article> listArticles() {	
		return articleDao.listArticles();	
	}
	public List<Article> listProjetRéalisé(){
		return articleDao.listProjetRéalisé();
	}
	public List<Article> listArticle(){
		return articleDao.listArticle();
	}
	public List<Article> listAsso(){
		return articleDao.listAsso();
	}
	public Article getArticle(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("Article id must be provided.");
		}
		return articleDao.getArticle(id);
	}
	public void addArticle(Article newArticle) throws IOException {
		if(newArticle == null){
			throw new IllegalArgumentException("An article must be provided.");
		}
		if(newArticle.getType() == null || "".equals(newArticle.getType())) {
			throw new IllegalArgumentException("An article must have a type.");
		}
		if(newArticle.getTitre() == null || "".equals(newArticle.getTitre())) {
			throw new IllegalArgumentException("An article must have a titre.");
		}
		if(newArticle.getContenu() == null || "".equals(newArticle.getContenu()))  {
			throw new IllegalArgumentException("A resto must have a contenu.");
		}
		if(newArticle.getDatePubli() == null || "".equals(newArticle.getDatePubli()))  {
			throw new IllegalArgumentException("A resto must have a date.");
		}

		articleDao.addArticle(newArticle);
		
	}
	
	public void deleteArticle(Integer articleId) {
		if(articleId == null) {
			throw new IllegalArgumentException("Article id must be provided.");
		}
		articleDao.deleteArticle(articleId);
	}

}