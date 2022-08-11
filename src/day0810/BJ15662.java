package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15662 {
	static ArrayList<ArrayList<Integer>> Wheels;
	static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(read.readLine());
		Wheels = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < T ; i++) {
			char [] templine = read.readLine().toCharArray();
			ArrayList<Integer> tempwheelsline = new ArrayList<>();
			for(int j = 0; j < 8; j++) {
				tempwheelsline.add((int)(templine[j]-'0'));
			}
			Wheels.add(tempwheelsline);
		}
		int K = Integer.parseInt(read.readLine());
		for(int i = 0; i < K ; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			int Nth = Integer.parseInt(tokens.nextToken());
			int dir = Integer.parseInt(tokens.nextToken());
			
			RotateWheels(Nth-1,dir);
		}
		int sum = 0;
		for(ArrayList<Integer> SdirOut : Wheels) {
			sum += SdirOut.get(0);
		}
		System.out.println(sum);
	}
	
	private static void RotateWheels(int n, int d) {
	
		if(n >0) { //왼쪽에 바퀴가 있을 때
			if(Wheels.get(n-1).get(2) != Wheels.get(n).get(6)) {
				RotateWheelsOnlyLeft(n-1, d*(-1));
			}
		}
		if(n < T-1){ //오른쪽에 바퀴가 있을 때
			if(Wheels.get(n).get(2) != Wheels.get(n+1).get(6)) {
				RotateWheelsOnlyRight(n+1, d*(-1));
			}
		}
		
		switch(d) {
		case(-1):{
			int ex = Wheels.get(n).get(0);
			for(int i = 0; i < 7; i++) {
				Wheels.get(n).set(i, Wheels.get(n).get(i+1));
			}
			Wheels.get(n).set(7, ex);
			break;
		}
		case(1):{
			int ex = Wheels.get(n).get(7);
			for(int i = 7; i >0; i--) {
				Wheels.get(n).set(i, Wheels.get(n).get(i-1));
			}
			Wheels.get(n).set(0, ex);
			break;
		}
		}
	}
	
	private static void RotateWheelsOnlyLeft(int n, int d) {
		if(n >0){ //왼쪽에 바퀴가 있을 때
			if(Wheels.get(n-1).get(2) != Wheels.get(n).get(6)) {
				RotateWheelsOnlyLeft(n-1, d*(-1));
			}
		}
		switch(d) {
		case(-1):{
			int ex = Wheels.get(n).get(0);
			for(int i = 0; i < 7; i++) {
				Wheels.get(n).set(i, Wheels.get(n).get(i+1));
			}
			Wheels.get(n).set(7, ex);
			break;
		}
		case(1):{
			int ex = Wheels.get(n).get(7);
			for(int i = 7; i >0; i--) {
				Wheels.get(n).set(i, Wheels.get(n).get(i-1));
			}
			Wheels.get(n).set(0, ex);
			break;
		}
		}
	}
		
	private static void RotateWheelsOnlyRight(int n, int d) {
		if(n < T-1){ //오른쪽에 바퀴가 있을 때
			if(Wheels.get(n).get(2) != Wheels.get(n+1).get(6)) {
				RotateWheelsOnlyRight(n+1, d*(-1));
			}
		}
		switch(d) {
		case(-1):{
			int ex = Wheels.get(n).get(0);
			for(int i = 0; i < 7; i++) {
				Wheels.get(n).set(i, Wheels.get(n).get(i+1));
			}
			Wheels.get(n).set(7, ex);
			break;
		}
		case(1):{
			int ex = Wheels.get(n).get(7);
			for(int i = 7; i >0; i--) {
				Wheels.get(n).set(i, Wheels.get(n).get(i-1));
			}
			Wheels.get(n).set(0, ex);
			break;
		}
		}
	}
}