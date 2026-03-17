package com.example.demo.service;

import com.example.demo.util.CosineSimilarity;
import com.example.demo.util.PDFExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.HashMap;

@Service
public class ResumeService {

    public double analyzeResume(MultipartFile file, String jobDescription) {

        // Extract text from uploaded resume
        String resumeText = PDFExtractor.extractText(file);

        // Convert resume and job description into word frequency maps
        Map<String, Integer> resumeVector = getFreq(resumeText);
        Map<String, Integer> jdVector = getFreq(jobDescription);

        // Calculate cosine similarity
        double similarity = CosineSimilarity.similarity(resumeVector, jdVector);

        // Convert to percentage
        return similarity * 100;
    }

    private Map<String, Integer> getFreq(String text) {

        Map<String, Integer> map = new HashMap<>();

        if(text == null || text.isEmpty()){
            return map;
        }

        String[] words = text.toLowerCase().split("\\W+");

        for(String w : words){
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        return map;
    }
}