package day0812;

public class BitOperatorTest {
    public static void main(String[] args) {
        System.out.println(toBinaryString(30));

//        shiftOperator();

        bitLogical();
        
    }

    static String toBinaryString(int num) {
        return String.format("%32s", Integer.toBinaryString(num)).replace(" ", "0") + String.format("(%10d)", num);
    }

    private static void shiftOperator() {
    	
        // 비트 이동 연산자 - 해당 비트를 원하는 위치로 이동시키기(숫자로는 *2, /2)
        int i = 0b1111111111;
        System.out.println("===================");
        System.out.println(i);
        System.out.printf("%s%n", toBinaryString(i));

        // TODO: i에 대해 <<, >> 연산을 진행해보고 bit의 이동 및 결과를 확인하세요.   
        System.out.printf("%s\n", toBinaryString(i<<2));
        System.out.printf("%s\n", toBinaryString(i>>2));
        System.out.println("===================");
        
        // END:
    }

    private static void bitLogical() {
    	System.out.println("-------------------------------------------");
        int i = 100;
        int j = 200;
        System.out.println(toBinaryString(i));
        System.out.println(toBinaryString(j));
        System.out.println(toBinaryString(i|j));
        System.out.println(toBinaryString(~(i^j)));
        System.out.println("-------------------------------------------");

        // TODO: i와 j에 대해서 &, |, ^, ~ 연산의 결과를 확인하시오.
        // END:
    }
}
