package ch12;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch10.News;
import ch10.NewsDAO;

@Path("/news")
public class NewsApiService {
	NewsDAO dao;
	
	public NewsApiService() {
		dao = new NewsDAO();
	}
	
	// 뉴스 등록
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addNews(News news) {
		try {
			dao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
			return "News API: 뉴스 등록 실패!!";
		}
		return "News API: 뉴스 등록됨!!";
	}
	
	// 뉴스 삭제
	@DELETE
	@Path("{aid}")
	public String delNews(@PathParam("aid") int aid) {
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			return "News API: 뉴스 삭제 실패!! - "+ aid;
		}
		return "News API: 뉴스 삭제됨!! - "+ aid;
	}
	
	// 뉴스 목록
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getNewsList() {
		List<News> newsList = null;
		
		try {
			newsList = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newsList;
	}
	
	// 뉴스 상세 정보
	@GET
	@Path("{aid}")
	@Produces(MediaType.APPLICATION_JSON)
	public News getNews(@PathParam("aid") int aid) {
		News news = null;
		
		try {
			news = dao.getNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return news;
	}
}