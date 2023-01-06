package day0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11723 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());
		ArrayList<String> lines = new ArrayList<>();
		ArrayList<String> ts = new ArrayList<>();
		for(int i = 20; i>0; i--) {
			ts.add(Integer.toString(i));
		}
		for(int i = 0; i < n ; i++) {
			String tmp = read.readLine();
			if(!tmp.contains(" ")) {
				lines = new ArrayList<>();
				if(tmp.equals("all")){
					lines.addAll(ts);
				}
			}else {
				StringTokenizer tokens = new StringTokenizer(tmp);
				String str = tokens.nextToken();
				String num = tokens.nextToken();
				
				switch(str) {
				case("add"):{
					if(lines.contains(num)) break;
					lines.add(num);
					break;
				}
				case("remove"):{
					if(!lines.contains(num)) break;
					else lines.remove(num);
					break;
				}
				case("check"):{
					//if(lines.contains(num)) System.out.println(1);
                    //else System.out.println(0);
                    if(lines.contains(num)) sb.append(1).append("\n");
					else sb.append(0).append("\n");
					break;
				}
				case("toggle"):{
					if(lines.contains(num)) lines.remove(num);
					else lines.add(num);
					break;
				}
			}
			

			}
		}
		System.out.print(sb);
		

	}

}