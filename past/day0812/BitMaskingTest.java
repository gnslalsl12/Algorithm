package day0812;

public class BitMaskingTest {

    static int tv = 1;    
    static int radio = 2; 
    static int mic = 3;

    public static void main(String[] args) {
        byArray();
        byBitMasking();
    }

    static void byArray() {
        boolean[] status = new boolean[9];
        int product = tv; // 1
        // TV 상태 확인
        System.out.printf("제품이 켜져있나? %b%n", status[product]);
        // TV를 켜보자.
        status[tv] = true;
        System.out.printf("제품이 켜졌나? %b%n", status[product]);
        // TV를 꺼보자.
        status[tv] = false;
        System.out.printf("제품이 켜졌나? %b%n", status[product]);
        // TV의 상태를 반전시켜보자.
        status[tv] = !status[tv];
        System.out.printf("제품이 토글되었나? %b%n", status[product]);
        
        // 이제 radio를 관리해볼까?
        product = radio;
    }

    private static void byBitMasking() {
        // TODO: byArray의 내용을 bitMasking으로 변경해보자.
    	
    	int Switches = Integer.MAX_VALUE;
    	System.out.printf("전체 스위치 : %5s\n",Integer.toBinaryString(Switches));
    	
    	System.out.println("기본 상태");
    	System.out.printf("tv의 상태 : %b\n", (Switches&(1<<1))>0 );
    	System.out.printf("mic의 상태 : %b\n", (Switches&(1<<3))>0 );
    	System.out.println();
    	//tv 끄기
    	Switches &= ~(1<<1);
    	System.out.println("tv만 끄기");
    	System.out.printf("tv의 상태 : %b\n", (Switches&(1<<1))>0 );
    	System.out.printf("mic의 상태 : %b\n", (Switches&(1<<3))>0 );
    	System.out.println();
    	//tv 켜기
    	Switches |= (1<<1);
    	System.out.println("tv만 켜기");
    	System.out.printf("tv의 상태 : %b\n", (Switches&(1<<1))>0 );
    	System.out.printf("mic의 상태 : %b\n", (Switches&(1<<3))>0 );
    	System.out.println();
    	//tv 토글
    	Switches ^= (1<<1);
    	System.out.println("tv만 토글");
    	System.out.printf("tv의 상태 : %b\n", (Switches&(1<<1))>0 );
    	System.out.printf("mic의 상태 : %b\n", (Switches&(1<<3))>0 );
    	System.out.println();
    	//전체 토글
    	Switches ^= (Integer.MAX_VALUE);
    	System.out.println("전체 다  토글");
    	System.out.printf("tv의 상태 : %b\n", (Switches&(1<<1))>0 );
    	System.out.printf("mic의 상태 : %b\n", (Switches&(1<<3))>0 );
    	
    	
        // END:
    }
    
    static String toBinaryString(int num) {
        return String.format("%32s", Integer.toBinaryString(num)).replace(" ", "0") + String.format("(%10d)", num);
    }
}
