package com.example.demo.util;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


public class PDFExtractor {
	 public static String extractText(MultipartFile file){

	        try{

	            InputStream input=file.getInputStream();

	            PDDocument doc=PDDocument.load(input);

	            PDFTextStripper stripper=new PDFTextStripper();

	            String text=stripper.getText(doc);

	            doc.close();

	            return text;

	        }catch(Exception e){
	            return "";
	        }
	    }
}
