package co.edu.eafit.numerical.enums;

public enum ErrorMessages {
    BITS_OVERFLOW("The provided amount of bits exceed the machine capacity"),
    MACHINE_NOT_SET_UP("The machine has not been set up yet, please go to the first option on the menu before trying anything here");

    private final String message;

    ErrorMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
