package day0812;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BitMaskingExercise {
    public static void main(String[] args) {
        setup();
        System.out.println("일반");
         sumFirstVisitByArray();
         System.out.println("비트");
        sumFirstVisitByBit();

//       nums = Arrays.copyOf(nums, 4);
//        cnt = 0;
//         permutationByArray(0, new int [3], new boolean[nums.length]);
//        System.out.println("/////////////////////");
//         cnt = 0;
//         permutationByBit(0, new int[3], 0);
//
//         makePowerSet(0, new boolean[nums.length]);
//         System.out.println("/////////////////////");
//        makePowerSet();
    }

    private static int[] nums;

    // 0~31까지의 정수 100개를 무작위로 만들어본다.
    private static void setup() {
        nums = new int[10];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(32);
        }
        System.out.println("배열: " + Arrays.toString(nums));
        System.out.println();
    }

    private static void sumFirstVisitByArray() {
        // TODO:nums에서 처음 나온 배열 요소들만 더해서 그 값을 출력하시오.
    	int sum = 0;
    	boolean [] Sel = new boolean [32];
    	for(int i = 0 ; i < nums.length ; i++) {
    		if(Sel[nums[i]]) {
    			continue;
    		}
    		Sel[nums[i]] = true;
    		sum += nums[i];
    		
    	}
    	System.out.println(sum);
    	
    	
        // END:
    }

    private static void sumFirstVisitByBit() {
        // TODO: 위 메서드를 bit 연산자로 처리하시오.
    	
    	int sum = 0;
    	int Switches = 0;
    	for(int i = 0 ; i < nums.length ; i++) {
    		if((Switches & (1<<nums[i])) != 0) {
    			continue;
    		}else {
    			Switches |= 1<<nums[i];
    			sum += nums[i];
 
    		}
    	}
    	System.out.println(sum);
        // END:
    }

    static int cnt = 0;

    // TODO: 배열 기반으로 순열을 작성해보고 visited를 왜 원상복구시키고 있는지 고민해보자.
    
//    permutationByArray(0, new int [3], new boolean[nums.length]);
    
    private static void permutationByArray(int cntnw , int [] S, boolean[] From) {
    	if(cntnw == S.length) {
    		System.out.println(Arrays.toString(S));
    		return;
    	}
    	
    	for(int i = 0; i<nums.length; i++) {
    		if(!From[i]) {
    			From[i] = true;
    			S[cntnw] = nums[i];
    			permutationByArray(cntnw+1, S, From);
    			From[i] = false;    			
    		}
       	}
    }
    
    // END:
 // permutationByBit(0, new int[3], 0);
    // TODO: bit 연산을 이용해서 순열을 작성해보자.
    private static void permutationByBit(int cntnw, int[] S, int Sel) {
    	if(cntnw == S.length) {
    		System.out.println(Arrays.toString(S));
    		return;
    	}
    	
    	for(int i = 0; i < nums.length; i++) {
    		if((Sel&(1<<nums[i])) == 0) {
    			Sel |= (1<<nums[i]);
    			S[cntnw] = nums[i];
    			permutationByBit(cntnw+1, S, Sel);
    			Sel &= ~(1<<nums[i]);
    		}
    	}
    }
    
    
    
    // END:
    // makePowerSet(0, new boolean[nums.length]);

    // TODO: 중복순열을 이용해서 nums의 부분집합을 만들어보자.
    private static void makePowerSet(int cntnw, boolean[] Sel) {
    	if(cntnw == Sel.length) {
    		toPrintPS(nums,Sel);
    		
    		return;
    	}
    	
    	Sel[cntnw] = true;
    	makePowerSet(cntnw+1,Sel);
    	Sel[cntnw] = false;
    	makePowerSet(cntnw+1,Sel);
    	
    	
    }
    
    private static void toPrintPS(int[] N, boolean[] S) {
    	ArrayList<Integer> Selected = new ArrayList<>();
    	ArrayList<Integer> UnSelected = new ArrayList<>();
    	for(int i = 0 ; i< S.length; i++) {
    		if(S[i]) {
    			Selected.add(N[i]);
    		}else if(!S[i]) {
    			UnSelected.add(N[i]);
    		}
    	}
    	System.out.printf("선택됨 : %s, 선택되지 않음 : %s\n" ,Selected, UnSelected);
    	return;
    	
    	
    }
    
    // END:

    // TODO: bitmasking을 이용해서 부분집합을 구해보자.
    // END:
    private static void makePowerSet() {
    	
    	for(int i = 0; i < (1<<nums.length) ; i++) {
    		List<Integer> Selected = new ArrayList<>();
    		List<Integer> UnSelected = new ArrayList<>();
    		System.out.println(i + " : " + Integer.toBinaryString(i)); //인덱스 자체가 요소들에 대한 포함ㅇ ㅕ부를 비트로 갖고있음
    		for(int j = 0; j < nums.length; j++) {
    			if((i&1<<j) > 0) {
    				Selected.add(nums[j]);
    			}else {
    				UnSelected.add(nums[j]);
    			}
    			
    		}
    		System.out.printf("선택됨 : %s, 선택되지 않음 : %s\n", Selected, UnSelected);
    		
    	}
    	
    	
    }
    
    //
  
    
    
    
    
}
