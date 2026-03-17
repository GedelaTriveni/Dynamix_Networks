package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.NewsService;
import com.example.demo.model.NewsRequest;

@RestController
@RequestMapping("/news")
public class NewsController {
	   @Autowired
	    private NewsService service;

	    // 🔹 Classification
	    @PostMapping("/check")
	    public String check(@RequestBody NewsRequest req) {
	        return service.verifyNews(req.getText());
	    }

	    // 🔹 Scraping
	    @GetMapping("/scrape")
	    public String scrape(@RequestParam String url) {
	        return service.scrapeNews(url);
	    }

	    // 🔹 Credibility
	    @GetMapping("/score")
	    public int score(@RequestParam String url) {
	        return service.credibilityScore(url);
	    }
	    
	    @GetMapping("/factcheck")
	    public String factCheck(@RequestParam String text){
	        return service.factCheck(text);
	    }
}
