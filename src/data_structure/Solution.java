package data_structure;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static String super_reduced_string(String s){
         String str=s;
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
           if(i+1 < str.length() && str.charAt(i+1)==c){
                str=str.replaceFirst(""+c,"0");
                str=str.replaceFirst(""+c,"0");
            }
        }str=str.replace("0","");
        return str.length()>0?str:"Empty String";
    }
   
    public static void main(String[] args) {
//    	for(int i=0,j=5;i<6 || j<8;i++,j++){
//    		System.out.println(i+"....."+j);
//    		
//    	}
    	 HashMap<Integer,String> map=new HashMap<>();
    	    map.put(1, "value");
    	    map.put(null, "value3");
    	    map.put(1, "value2");
    	    
    	    for(Map.Entry<Integer,String> entry:map.entrySet()){
    	    	System.out.println(entry.getKey()+" ....."+entry.getValue());
    	    }

    }
}

