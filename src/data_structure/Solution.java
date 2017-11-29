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
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = super_reduced_string(s);
        System.out.println(result);
    }
}

