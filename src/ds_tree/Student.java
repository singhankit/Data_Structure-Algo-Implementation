package ds_tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class TestClass {
    public static void main(String args[] ) throws Exception {

        Scanner s = new Scanner(System.in);
        int num=s.nextInt();
        for(int i=0;i<num;i++){
            String name = s.next(); 
            List<Integer> list=new ArrayList<>();
            String vwls="aeiou";
            if(! name.contains("_")){
                System.out.println(1);
            }
            else{
                //int [] arr=new int[];
                String str="";
                int len=name.length();
                int count=0;
                for(int k=0;k<name.length();k++){
                    String cc=""+name.charAt(k);
                    if(name.charAt(k)=='_'){
                        list.add(count);
                    }
                    if(vwls.contains(cc) && !str.contains(cc)){
                        str+=cc;
                        count++;
                    }
                    
                }
                int sum=1;
                for(int j=0;j<list.size();j++){
                       sum*=list.get(j); 
                }
    System.out.println(sum);
            }
        }
    }
}

