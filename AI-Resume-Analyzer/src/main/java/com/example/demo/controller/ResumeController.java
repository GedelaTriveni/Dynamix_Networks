package com.example.demo.controller;

import com.example.demo.service.ResumeService;
import com.example.demo.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@Controller
public class ResumeController {

    @Autowired
    private ResumeService service;

    double latestScore;

    @PostMapping("/analyze")
    public String analyzeResume(@RequestParam("resume") MultipartFile file,
                                @RequestParam("jobDescription") String jd,
                                Model model) {

        latestScore = service.analyzeResume(file, jd);

        // Generate PDF report
        ReportService.generateReport(latestScore);

        model.addAttribute("score", latestScore);

        return "result";
    }

    @GetMapping("/downloadReport")
    public ResponseEntity<InputStreamResource> downloadReport() throws Exception {

        File file = new File("Resume_Report.pdf");

        InputStreamResource resource =
                new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=Resume_Report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

}