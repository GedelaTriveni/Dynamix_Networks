package com.example.demo.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class ReportService {

    public static void generateReport(double score) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Resume_Report.pdf"));
            document.open();

            // Formatting score to two decimal places
            DecimalFormat df = new DecimalFormat("0.00");
            String formattedScore = df.format(score);

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph title = new Paragraph("AI Resume Analyzer Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);

            Paragraph line = new Paragraph("-------------------------------------------");

            Paragraph result = new Paragraph("Resume Match Score : " + formattedScore + "%", textFont);

            document.add(title);
            document.add(line);
            document.add(result);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}