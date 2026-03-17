package com.example.demo.util;

import java.util.*;

public class CosineSimilarity {
	public static double similarity(Map<String,Integer> v1,Map<String,Integer> v2){

        Set<String> words=new HashSet<>();

        words.addAll(v1.keySet());
        words.addAll(v2.keySet());

        double dot=0;
        double norm1=0;
        double norm2=0;

        for(String w:words){

            int x=v1.getOrDefault(w,0);
            int y=v2.getOrDefault(w,0);

            dot+=x*y;
            norm1+=x*x;
            norm2+=y*y;
        }

        if(norm1==0||norm2==0) return 0;

        return dot/(Math.sqrt(norm1)*Math.sqrt(norm2));
    }
}
