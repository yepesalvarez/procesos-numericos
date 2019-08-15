package co.edu.eafit.numerical.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.eafit.numerical.enums.ExponentSign;

public class NumberBaseConverter {

    /* ================== base 10 number to base 2 ==================================================================*/
    public static String[] base10toBinary(double decimalNumber){
        int integerPart = (int) decimalNumber;
        double decimalPart = (BigDecimal.valueOf(decimalNumber).subtract(BigDecimal.valueOf(integerPart))).doubleValue();
        //Integer part conversion
        List<Integer> binaryIntegerPart = new ArrayList<>();
        String number = "0";
        while(integerPart != 0){
            int remainder = integerPart % 2;
            binaryIntegerPart.add(remainder);
            integerPart = integerPart / 2;
        }
        if (!binaryIntegerPart.isEmpty()){
            Collections.reverse(binaryIntegerPart);
            number = binaryIntegerPart.stream().map(String::valueOf).collect(Collectors.joining());
        }
        //Decimal part conversion
        String decimal = "";
        int repeatingStart = 0;
        int repeatingEnd = 0;
        if(decimalPart != 0){
            List<Integer> binaryDecimalPart = new ArrayList<>();
            List<Double> products = new ArrayList<>();
            boolean repeatingDecimal = false;
            while(!repeatingDecimal) {
                if(products.contains(decimalPart)){
                    repeatingDecimal = true;
                    repeatingStart = products.indexOf(decimalPart);
                    repeatingEnd = products.size() - 1;
                }else{
                    products.add(decimalPart);
                    int integerProduct = (int) (decimalPart * 2);
                    binaryDecimalPart.add(integerProduct);
                    decimalPart = decimalPart * 2;
                    if (integerProduct == 1) {
                        decimalPart = (BigDecimal.valueOf(decimalPart).subtract(BigDecimal.ONE)).doubleValue();
                    }
                }
            }
            decimal = binaryDecimalPart.stream().map(String::valueOf).collect(Collectors.joining());
        }
        String[] result = new String[4];
        //integer part
        result[0] = number;
        //decimal part
        result[1] = decimal;
        //decimal position where the repeating starts
        result[2] = String.valueOf(repeatingStart);
        //decimal position where the repeating ends
        result[3] = String.valueOf(repeatingEnd);
        return result;
    }

    /* ================== base 2 number to base 10 ==================================================================*/
    public static double base2toBase10(double binaryNumber){
        int binaryIntegerPart = (int) binaryNumber;
        double binaryDecimalPart = (BigDecimal.valueOf(binaryNumber).subtract(BigDecimal.valueOf(binaryIntegerPart))).doubleValue();
        String integerPartString = String.valueOf(binaryIntegerPart);
        String decimalPartString = String.valueOf(binaryDecimalPart).substring(2);
        int integerPartSize = integerPartString.length();
        int integerPartBase10 = 0;
        for(int i = 0; i < integerPartString.length(); i++){
            integerPartBase10 = integerPartBase10 + ((int) Math.pow(2, integerPartSize - (i + 1)) * Integer.parseInt(String.valueOf(integerPartString.charAt(i))));
        }
        double decimalPartBase10 = 0;
        if(decimalPartString.length() > 0){
            for(int i = 0; i < decimalPartString.length(); i++){
                decimalPartBase10 = decimalPartBase10 + (Math.pow(2, (i + 1) * - 1) * Integer.parseInt(String.valueOf(decimalPartString.charAt(i))));
            }
        }
        return integerPartBase10 + decimalPartBase10;
    }

    /* ================== number to floating point notation base 10 =================================================*/
    public static String[] toFloatingPointNotation(double number){
        String result[] = new String[3];
        int integerPart = (int) number;
        double decimalPart = (BigDecimal.valueOf(number).subtract(BigDecimal.valueOf(integerPart))).doubleValue();
        int integerPartSize = 0;
        double integerPartFPN = 0;
        if (integerPart != 0) {
            integerPartSize = String.valueOf(integerPart).length();
            integerPartFPN = integerPart / Math.pow(10, integerPartSize);
            if(decimalPart != 0) {
                decimalPart = BigDecimal.valueOf(decimalPart).divide(BigDecimal.valueOf(Math.pow(10, integerPartSize))).doubleValue();
                result [0] = String.valueOf(BigDecimal.valueOf(integerPartFPN).add(BigDecimal.valueOf(decimalPart)));
                result [1] = ExponentSign.POSITIVE_EXPONENT.getBitSign();
                result [2] = String.valueOf(integerPartSize);
            }else{
                result [0] = String.valueOf(integerPartFPN);
                result [1] = ExponentSign.POSITIVE_EXPONENT.getBitSign();
                result [2] = String.valueOf(integerPartSize);
            }
        } else {
            if (decimalPart == 0.0){
                result [0] = String.valueOf(integerPart);
                result [1] = ExponentSign.POSITIVE_EXPONENT.getBitSign();
            }
            int decimalPartSize = 0;
            String decimalPartString = String.valueOf(decimalPart).substring(2);
            int i = 0;
            while (i < decimalPartString.length() && decimalPartString.charAt(i) == '0'){
                i++;
            }
            decimalPart = BigDecimal.valueOf(decimalPart).multiply(BigDecimal.valueOf(Math.pow(10, i))).doubleValue();
            if(i == 0){
                result [0] = String.valueOf(decimalPart);
                result [1] = ExponentSign.POSITIVE_EXPONENT.getBitSign();
            }else
                result [0] = String.valueOf(decimalPart);
                result [1] = ExponentSign.NEGATIVE_EXPONENT.getBitSign();
                result [2] = String.valueOf(i);
        }
        return result;
    }
}
