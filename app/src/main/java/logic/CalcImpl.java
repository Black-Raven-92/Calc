package logic;

public class CalcImpl implements Calc {
    @Override
    public double performOperation(double argOne, double argTwo, Operation operation) {
        switch (operation){
            case ADD:
                return argOne+argTwo;
            case SUB:
                return argOne-argTwo;
            case DIV:
                return argOne/argTwo;
            case MUL:
                return argOne*argTwo;
        }
        return 0.0;
    }
}
