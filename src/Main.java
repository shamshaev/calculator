import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
    public static String calc(String input) throws Exception {
        String [] numbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String [] operators = {"+", "-", "*", "/"};
        int a, b;
        String [] splitInputs = input.split(" ");
        if (splitInputs.length!=3||!isNumberAndOperatorOk(numbers, operators, splitInputs)) {
            throw new Exception();
        }
        if (isNumberRoman(numbers, splitInputs[0])) {
            a = Arrays.asList(numbers).indexOf(splitInputs[0])+1;
            b = Arrays.asList(numbers).indexOf(splitInputs[2])+1;
        } else {
            a = Integer.parseInt(splitInputs[0]);
            b = Integer.parseInt(splitInputs[2]);
        }
        String operator = splitInputs[1];
        int result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
        if (isNumberRoman(numbers, splitInputs[0])&&result<1) {
            throw new Exception();
        } else if (isNumberRoman(numbers, splitInputs[0])) {
            return convertNumberToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }
    public static boolean isNumberAndOperatorOk(String [] numbers, String [] operators, String[] splitInputs) {
        int aIndex = Arrays.asList(numbers).indexOf(splitInputs[0]);
        int bIndex = Arrays.asList(numbers).indexOf(splitInputs[2]);
        boolean numbersCorrect = aIndex>=0&&bIndex>=0&&(aIndex<10&&bIndex<10||aIndex>=10&&bIndex>=10);
        boolean operatorCorrect = Arrays.asList(operators).contains(splitInputs[1]);
        return numbersCorrect&&operatorCorrect;
    }
    public static boolean isNumberRoman(String [] numbers, String string) {
        return Arrays.asList(numbers).indexOf(string)<10;
    }
    public static String convertNumberToRoman(int result){
        if (result==100) {
            return "C";
        }
        if (result>=50) {
            if (result>=90) {
                return "XC"+convertNumberToRoman(result-90);
            } else {
                return "L"+convertNumberToRoman(result-50);
            }
        }
        if (result>=10) {
            if (result>=40) {
                return "XL"+convertNumberToRoman(result-40);
            } else {
                return "X"+convertNumberToRoman(result-10);
            }
        }
        if (result>=5) {
            if (result==9) {
                return "IX";
            } else {
                return "V"+convertNumberToRoman(result-5);
            }
        }
        if (result>0) {
            if (result==4) {
                return "IV";
            } else {
                return "I"+convertNumberToRoman(result-1);
            }
        }
        return "";
    }
}