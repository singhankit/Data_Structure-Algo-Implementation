package data_structure;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class demo1 {

    static int alternatingCharacters(String s){
        int count=0;
        char[] str=s.toCharArray();
        for (int i = 0; i < str.length-1; i++) {
			char c=str[i];
			if(c=='A'){
				if(str[i+1]!='B'){
					count++;
				}
			}
			if(c=='B'){
				if(str[i+1]!='A'){
					count++;
				}
			}
		}
  return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = alternatingCharacters(s);
            System.out.println(result);
        }
    }
}