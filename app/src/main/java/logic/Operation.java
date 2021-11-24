package logic;

public enum Operation {
    ADD ("+"),
    SUB ("-"),
    MUL ("*"),
    DIV ("/"),
    SUM("=");
    private String name;

    Operation(String name) {
        this.name = name;
    }

    public String toString(){
        return name;

    }
}
