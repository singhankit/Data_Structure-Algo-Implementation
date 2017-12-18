package data_structure;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
interface Poet {
	default void write() 
{ System.out.println("Poet's default method"); } }
interface Writer extends Poet{
	default void write2() {
		System.out.println("Writer's default method"); } }

	public class Solution implements  Writer{ 
		public static void main(String args[]){
			Solution john = new Solution();
			john.write(); // which write method to call, from Poet // or, from Writer } }
	}

	}


