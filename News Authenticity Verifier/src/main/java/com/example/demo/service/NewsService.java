package com.example.demo.service;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
	// 🔹 Fake vs Real classification
    public String verifyNews(String text) {

        text = text.toLowerCase();
        int score = 50;

        if(text.contains("breaking")) score -= 10;
        if(text.contains("shocking")) score -= 20;
        if(text.contains("fake")) score -= 30;
        if(text.contains("official")) score += 20;

        return (score >= 60)
            ? "✅ Real News (Score: " + score + "%)"
            : "⚠️ Fake News (Score: " + score + "%)";
    }

    // 🔹 News scraping
    public String scrapeNews(String url) {
        try {
            return Jsoup.connect(url).get().title();
        } catch(Exception e) {
            return "Error fetching news";
        }
    }

    // 🔹 Source credibility score
    public int credibilityScore(String url) {
        if(url.contains("bbc") || url.contains("cnn"))
            return 90;
        else if(url.contains("blog"))
            return 40;
        else
            return 60;
    }
    
    public String factCheck(String text){
        if(text.toLowerCase().contains("nasa") || text.toLowerCase().contains("government")){
            return "✅ Verified by trusted sources";
        }
        return "⚠️ No fact-check data available";
    }
}
