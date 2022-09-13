package com.example.news;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/news")
public class NewsWebController {
	final NewsDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 프로퍼티파일로 부터 저장 경로 참조
	@Value("${news.imgdir}")
	String fdir;
	
	@Autowired
	public NewsWebController(NewsDAO dao) {
		this.dao =  dao;
	}
	
	@PostMapping("/add")
	public String addNews(@ModelAttribute News news, Model m, @RequestParam("file") MultipartFile file) {
		try {
			// 저장 파일 객체 생성
			File dest = new File(fdir+"/"+file.getOriginalFilename()); 
			
			// 파일 저장
			file.transferTo(dest);
			
			// News 객체에 파일 이름 저장
			news.setImg("/img/"+dest.getName());
			dao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("뉴스 추가 과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다!!");
		}
		return "redirect:/news/list";
	}

	@GetMapping("/delete/{aid}")
	public String deleteNews(@PathVariable int aid, Model m) {
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("뉴스 삭제 과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다!!");
		}
		return "redirect:/news/list";
	}

	@GetMapping("/list")
	public String listNews(Model m) {
		List<News> list;
		try {
			list = dao.getAll();
			m.addAttribute("newslist", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("뉴스 목록 생성 과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "news/newsList";
	}

	@GetMapping("/{aid}")
	public String getNews(@PathVariable int aid, Model m) {
		try {
			News n = dao.getNews(aid);
			m.addAttribute("news", n);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("뉴스를 가져오는 과정에서 문제 발생!!");
			m.addAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다!!");
		}
		return "news/newsView";
	}
}