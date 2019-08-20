package co.edu.eafit.numerical.enums;

public enum ProjectProperties {

    MACHINE_CAPACITY("32");

    private String property;

    ProjectProperties (String property){
        this.property = property;
    }

    public String getProperty(){
        return property;
    }

}
