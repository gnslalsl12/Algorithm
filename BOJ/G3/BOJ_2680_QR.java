package G3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2680_QR {
	static int Count;
	static String Dict = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			String Line = read.readLine();
			String output = solv(Line);
			write.write(Count + " " + output + "\n");
		}
		write.close();
		read.close();
	}

	private static String solv(String Line) {
		String WholeBinaryLine = doCutDouble(Line);
		Count = 0;
		return doCutModes(WholeBinaryLine);
	}

	private static String doCutModes(String input) {
		int firstindex = 0;
		int ModeBits;
		int BitCount;
		int Length;
		String result = "";
		while (true) {
			if (firstindex >= input.length() || firstindex + 4 >= input.length())
				break;
			ModeBits = fromBinarytoInteger(input.substring(firstindex, firstindex += 4));
			if (ModeBits == 1) {
				BitCount = fromBinarytoInteger(input.substring(firstindex, firstindex += 10));
				Count += BitCount;
				Length = (BitCount / 3) * 10;
				if (BitCount % 3 == 2)
					Length += 7;
				else if (BitCount % 3 == 1)
					Length += 4;
				result += getNumeric(input.substring(firstindex, firstindex += Length), BitCount);
			} else if (ModeBits == 2) {
				BitCount = fromBinarytoInteger(input.substring(firstindex, firstindex += 9));
				Count += BitCount;
				Length = (BitCount / 2) * 11;
				if (BitCount % 2 == 1)
					Length += 6;
				result += getAlphanumeric(input.substring(firstindex, firstindex += Length), BitCount);
			} else if (ModeBits == 4) {
				BitCount = fromBinarytoInteger(input.substring(firstindex, firstindex += 8));
				Count += BitCount;
				Length = BitCount * 8;
				result += getEightBitByte(input.substring(firstindex, firstindex += Length), BitCount);
			} else if (ModeBits == 8) {
				BitCount = fromBinarytoInteger(input.substring(firstindex, firstindex += 8));
				Count += BitCount;
				Length = BitCount * 13;
				result += getKanji(input.substring(firstindex, firstindex += Length), BitCount);
			} else
				break;
		}
		return result;
	}

	private static String getKanji(String KanjiString, int count) {
		String result = "";
		int index = 0;
		String temp = "";
		int tempInteger;
		while (count-- > 0) {
			tempInteger = fromBinarytoInteger(KanjiString.substring(index, index += 13));
			temp = Integer.toHexString(tempInteger).toUpperCase();
			result += "#";
			if (temp.length() == 3)
				result += '0';
			else if (temp.length() == 2)
				result += "00";
			else if (temp.length() == 1)
				result += "000";
			result += temp;
		}
		return result;
	}

	private static String getEightBitByte(String EBBString, int count) {
		String result = "";
		int index = 0;
		String temp = "";
		int tempInteger;
		while (count-- > 0) {
			tempInteger = fromBinarytoInteger(EBBString.substring(index, index += 8));
			if (tempInteger >= 32 && tempInteger <= 127) {
				if (tempInteger == 32) {
					result += " ";
				} else if (tempInteger == 35) {
					result += "\\#";
				} else if (tempInteger == 92) {
					result += "\\\\";
				} else {
					result += (char) (tempInteger);
				}
			} else {
				temp = Integer.toHexString(tempInteger).toUpperCase();
				if (temp.length() == 1)
					temp = '0' + temp;
				result += "\\" + temp;
			}
		}
		return result;
	}

	private static String getAlphanumeric(String AlphanumericString, int count) {
		String result = "";
		int index = 0;
		while (true) {
			if (count >= 2) {
				int temp = fromBinarytoInteger(AlphanumericString.substring(index, index += 11));
				result += Dict.charAt(temp / 45);
				result += Dict.charAt(temp % 45);
				count -= 2;
				if (count == 0)
					break;
			} else {
				int temp = fromBinarytoInteger(AlphanumericString.substring(index, index += 6));
				result += Dict.charAt(temp % 45);
				break;
			}
		}
		return result;
	}

	private static String getNumeric(String NumericString, int count) {
		String result = "";
		int index = 0;
//		System.out.println(NumericString);
//		System.out.println(count);
		String temp;
		while (true) {
			if (count >= 3) {
				temp = Integer.toString(fromBinarytoInteger(NumericString.substring(index, index += 10)));
				if (temp.length() == 2)
					result += '0';
				else if (temp.length() == 1)
					result += "00";
				result += temp;
				count -= 3;
				if (count == 0)
					break;
			} else if (count == 2) {
				temp = Integer.toString(fromBinarytoInteger(NumericString.substring(index, index += 7)));
				if (temp.length() == 1)
					result += '0';
				result += temp;
				break;
			} else {
				temp = Integer.toString(fromBinarytoInteger(NumericString.substring(index, index += 4)));
				result += temp;
				break;
			}

		}
		return result;
	}

	private static String doCutDouble(String Line) {
		String WholeBinaryLine = "";
		for (int index = 0; index < 38; index += 2) {
			WholeBinaryLine += fromHextoBin(Line.charAt(index), Line.charAt(index + 1));
		}
		return WholeBinaryLine;
	}

	private static String fromHextoBin(char temp1, char temp2) {
		int result = 0;
		if (temp1 >= '0' && temp1 <= '9') {
			result += temp1 - '0';
		} else {
			result += temp1 - 'A' + 10;
		}
		result *= 16;
		if (temp2 >= '0' && temp2 <= '9') {
			result += temp2 - '0';
		} else {
			result += temp2 - 'A' + 10;
		}
		String resultString = Integer.toBinaryString(result);
		String output = "";
		for (int i = 0; i < 8 - resultString.length(); i++) {
			output += '0';
		}
		return output + resultString;
	}

	private static int fromBinarytoInteger(String input) {
		int result = 0;
		int pow = 1;
		for (int index = input.length() - 1; index >= 0; index--) {
			if (input.charAt(index) == '1')
				result += pow;
			pow *= 2;
		}
		return result;
	}

}
