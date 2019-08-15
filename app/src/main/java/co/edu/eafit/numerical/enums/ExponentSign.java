package co.edu.eafit.numerical.enums;

public enum ExponentSign {
    POSITIVE_EXPONENT("1"),
    NEGATIVE_EXPONENT("0");

    private final String bitSign;

    ExponentSign(String bitSign){
        this.bitSign = bitSign;
    }

    public String getBitSign(){
        return bitSign;
    }

}
