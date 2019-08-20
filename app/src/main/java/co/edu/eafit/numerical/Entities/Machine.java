package co.edu.eafit.numerical.Entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

import co.edu.eafit.numerical.utils.NumberBaseConverter;

public class Machine {

    public static int machineBits;
    public static Machine machine;
    private int[] exponentBits;
    private int[] mantissaBits;
    private int[] exponentSign;
    private int[] mantissaSign;

    public Machine(int mantissaBitsAmt, int exponentBitsAmt) {
        this.mantissaBits = new int[mantissaBitsAmt];
        this.exponentBits = new int[exponentBitsAmt];
        this.exponentSign = new int[1];
        this.mantissaSign = new int[1];
        machine = this;
    }

    public void addNumber(double number) {
        //Set sign bit
        if (number > 0) {
            mantissaSign[0] = 1;
        } else {
            mantissaSign[0] = 0;
        }
        /* Array convertedBinaryNumResult, positions explanation:
            position 0: integer part
            position 1: decimal part if there is, otherwise null
            position 2: position where decimal part starts repeating itself
            position 3: position from decimal part where the repeating decimal ends
            note: if position 2 and position 3 equal zero, it is because there is not decimal part
        * */
        String[] convertedBinaryNumResult = NumberBaseConverter.base10toBinary(number);
        /* We will use BigDecimal conversion to avoid precision loss*/
        double binaryNumber = BigDecimal.valueOf(Double.parseDouble(convertedBinaryNumResult[0]))
                .add(BigDecimal.valueOf(Double.parseDouble(
                        convertedBinaryNumResult[1]) / convertedBinaryNumResult[0].length()))
                .doubleValue();
        /* Array numberFloatingPointResult positions explanation:
         *   position 0: converted number into floating point notation mantissa
         *   position 1: exponent sign bit
         *   position 2: exponent number */
        String[] numberFloatingPointResult = NumberBaseConverter.toFloatingPointNotation(binaryNumber);
        String numberFloatingPointStr = numberFloatingPointResult[0];
        String exponentFloatingPointStr = numberFloatingPointResult[2];
        /* We assume the machine has as implicit bit 0.1, so we will not store the initial bit from the mantissa */
        numberFloatingPointStr = numberFloatingPointStr.substring(3);
        int mantissaSpaceLeft = mantissaBits.length;
        /* check if the number is bigger than the mantissa's supported capacity, if so, the number is rounded by
         * eliminating its excess */
        if (mantissaSpaceLeft < numberFloatingPointStr.length()) {
            numberFloatingPointStr = numberFloatingPointStr.substring(0, mantissaSpaceLeft);
            for (int i = 0; i < mantissaSpaceLeft; i++) {
                mantissaBits[i] = Integer.parseInt(String.valueOf(numberFloatingPointStr.charAt(i)));
            }
            /* check if the number length is equal to mantissa's supported capacity, if so, all is match */
        } else if (mantissaSpaceLeft == numberFloatingPointStr.length()) {
            for (int i = 0; i < mantissaSpaceLeft; i++) {
                mantissaBits[i] = Integer.parseInt(String.valueOf(numberFloatingPointStr.charAt(i)));
            }
        } else {
            /* check if the binary number does not have a repeating decimal part so we need to fill out
             * the rest of the mantissa with zeros */
            mantissaSpaceLeft = mantissaSpaceLeft - numberFloatingPointStr.length();
            if (convertedBinaryNumResult[1] == null
                    || (convertedBinaryNumResult[2].equals("0") && convertedBinaryNumResult[3].equals("0"))) {
                for (int i = 0; i < numberFloatingPointStr.length(); i++) {
                    mantissaBits[i] = Integer.parseInt(String.valueOf(numberFloatingPointStr.charAt(i)));
                }
                for (int i = mantissaBits.length - 1; i >= mantissaSpaceLeft; i--) {
                    mantissaBits[i] = 0;
                }
            } else {
                /* calculates and fill out the mantissa left bits with the repeating decimal part of the number */
                int timesRepeating = mantissaSpaceLeft / (Integer.parseInt(convertedBinaryNumResult[2]) - Integer.parseInt(convertedBinaryNumResult[3]) + 1);
                int timesRepeatingLeft = mantissaSpaceLeft % (Integer.parseInt(convertedBinaryNumResult[2]) - Integer.parseInt(convertedBinaryNumResult[3]) + 1);
                int positionMantissa = mantissaBits.length - mantissaSpaceLeft;
                for (int i = 1; i <= timesRepeating; i++) {
                    for (int j = Integer.parseInt(convertedBinaryNumResult[2]); j <= Integer.parseInt(convertedBinaryNumResult[3]); j++) {
                        mantissaBits[positionMantissa] = Integer.parseInt(String.valueOf(convertedBinaryNumResult[1].charAt(j)));
                        positionMantissa++;
                    }
                }
                for (int k = Integer.parseInt(convertedBinaryNumResult[2]); k <= Integer.parseInt(convertedBinaryNumResult[2]) + timesRepeatingLeft; k++) {
                    mantissaBits[positionMantissa] = Integer.parseInt(String.valueOf(convertedBinaryNumResult[1].charAt(k)));
                    positionMantissa++;
                }
            }
        }

        /* check if the exponent is bigger than the machine's supported capacity, if so, the exponent is rounded by
         * eliminating its excess */
        int exponentSpaceLeft = exponentBits.length;
        if (exponentSpaceLeft < exponentFloatingPointStr.length()) {
            exponentFloatingPointStr = exponentFloatingPointStr.substring(0, exponentSpaceLeft);
            for (int i = 0; i < exponentSpaceLeft; i++) {
                exponentBits[i] = Integer.parseInt(String.valueOf(exponentFloatingPointStr.charAt(i)));
            }
            /* check if the exponent is smaller than the machine's supported capacity, if so, the machine's left bits
             * are filled out with zeros */
        } else if (exponentSpaceLeft > exponentFloatingPointStr.length()) {
            for (int i = 0; i < exponentSpaceLeft; i++) {
                exponentBits[i] = Integer.parseInt(String.valueOf(exponentFloatingPointStr.charAt(i)));
            }
            exponentSpaceLeft = exponentSpaceLeft - exponentFloatingPointStr.length();
            for (int i = exponentBits.length - 1; i >= exponentSpaceLeft; i--) {
                exponentBits[i] = 0;
            }
        } else {
            /* The last option is that both are equal, so the exponent number cant fit in the machine's
             * exponent storage */
            for (int i = 0; i < exponentSpaceLeft; i++) {
                exponentBits[i] = Integer.parseInt(String.valueOf(exponentFloatingPointStr.charAt(i)));
            }
        }
        //Set exponent sign
        exponentSign[0] = Integer.parseInt(numberFloatingPointResult[1]);
    }

    public int[] getMantissaSign() {
        return mantissaSign;
    }

    public void setMantissaSign(int[] mantissaSign) {
        this.mantissaSign = mantissaSign;
    }

    public int[] getMantissaBits() {
        return mantissaBits;
    }

    public void setMantissaBits(int[] mantissaBits) {
        this.mantissaBits = mantissaBits;
    }

    public int[] getExponentBits() {
        return exponentBits;
    }

    public void setExponentBits(int[] exponentBits) {
        this.exponentBits = exponentBits;
    }

    public int[] getExponentSign() {
        return exponentSign;
    }

    public void setExponentSign(int[] exponentSign) {
        this.exponentSign = exponentSign;
    }

    public static Machine getMachine() {
        return machine;
    }

    public static void setMachine(Machine machine) {
        Machine.machine = machine;
    }

    public void setMachineBits(int machineBits) {
        this.machineBits = machineBits;
    }

    public String getNumberOnMachine(){
        return getMantissaSign()
                + "0.1" //Machine's implicit bit
                + Arrays.asList(getMantissaBits()).stream().map(String::valueOf).collect(Collectors.joining())
                + getExponentSign()
                + Arrays.asList(getExponentBits()).stream().map(String::valueOf).collect(Collectors.joining());
    }

    public double getBiggestNumber(){
        /* all mantissa's bits equal to 1 remembering to add the implicit bit at the end */
        int acumMantissa = 1;
        for (int i = 1; i < mantissaBits.length; i++){
            acumMantissa = acumMantissa + (int) Math.pow(10, i);
        }
        double mantissaWithImplicitBit = BigDecimal.valueOf(acumMantissa)
                .divide(BigDecimal.valueOf((int) Math.pow(10, mantissaBits.length + 1)))
                .add(BigDecimal.valueOf(0.1))
                .doubleValue();
        /* all exponent's bits equal to 1*/
        int acumExponent = 1;
        for (int i = 1; i < exponentBits.length; i++){
            acumExponent = acumExponent + (int) Math.pow(10, i);
        }
        double mantissaBase10 = NumberBaseConverter.base2toBase10(mantissaWithImplicitBit);
        double exponentBase10 = NumberBaseConverter.base2toBase10(acumExponent);
        return mantissaBase10 * Math.pow(2, exponentBase10);
    }

    public double getSmallestPositiveNum(){
        /* all exponent's bits equal to 1*/
        int acumExponent = 1;
        for (int i = 1; i < exponentBits.length - 1; i++){
            acumExponent = acumExponent + (int) Math.pow(10, i);
        }
        /* implicit bit at the max negative exponent */
        double exponentBase10 = NumberBaseConverter.base2toBase10(acumExponent);
        return Math.pow(0.1, exponentBase10 * -1);
    }

    public double getEpsilon(){
        return Math.pow(2, mantissaBits.length * -1);
    }

    public double getDecimalNumber(int[] machineNumber){
        /* machineNumber array positions definition:
        * position 0: mantissa sign
        * position 1: mantissa number
        * position 2: exponent sign
        * position 3: exponent number*/
        double mantissaNumber = NumberBaseConverter.base2toBase10(machineNumber[1]);
        double exponentNumber = NumberBaseConverter.base2toBase10(machineNumber[3]);
        if (machineNumber[0] == 0) {
            mantissaNumber = mantissaNumber * - 1;
        }
        if (machineNumber[2] == 0){
            exponentNumber = exponentNumber * -1;
        }
        return mantissaNumber * Math.pow(2, exponentNumber);
    }


}
