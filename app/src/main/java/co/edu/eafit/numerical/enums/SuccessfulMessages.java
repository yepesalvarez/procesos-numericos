package co.edu.eafit.numerical.enums;

public enum SuccessfulMessages {

    MACHINE_CREATED("Machine set up succesfully! select a menu's option to start testing it!!");

    private final String message;

    SuccessfulMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
