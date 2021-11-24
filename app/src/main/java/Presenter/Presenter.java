package Presenter;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.ParameterizedType;

import logic.Calc;
import logic.Operation;
import ui.CalcView;

public class Presenter {
    private static final int DECIMAL_BASE = 10;
    private CalcView view;
    private Calc calculator;
    private Double argOne = 0.0;
    private Double argTwo = null;
    private Operation previousOperation = null;
    private Operation operationSelected;
    private boolean isDotPressed;
    private int divider;


    public Presenter(CalcView view, Calc calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if (argTwo == null) {
            if (isDotPressed) {
                argOne = argOne + digit / (double) divider;
                divider *= DECIMAL_BASE;
            } else {
                argOne = argOne * DECIMAL_BASE + digit;
            }
            displayResult(argOne);
        } else {
            if (isDotPressed) {
                argTwo = argTwo + digit / (double) divider;
                divider *= DECIMAL_BASE;
            } else {
                argTwo = argTwo * DECIMAL_BASE + digit;
            }
            displayResult(argTwo);
        }

    }

    public void onOperationPressed(Operation operation) {
        operationSelected = operation;
        if (previousOperation != null) {
            double result = calculator.performOperation(argOne, argTwo, previousOperation);
            displayResult(result);
            argOne = result;
        }
        previousOperation = operation;
        argTwo = 0.0;
        isDotPressed = false;
    }

    public void onSumKeyPressed() {
        double result = calculator.performOperation(argOne, argTwo, operationSelected);
        displayResult(result);

    }

    public void onDotPressed() {
        if (!isDotPressed) {
            isDotPressed = true;
            divider = DECIMAL_BASE;
        }
    }

    public void onCleanPressed() {
        argOne = 0.0;
        argTwo = null;
        previousOperation = null;
        operationSelected = null;
        isDotPressed = false;
        view.ShowResult("0");
    }


    private void displayResult(double arg) {
        long longValue = (long) arg;
        if (longValue == arg) {
            view.ShowResult(String.valueOf(longValue));
        } else {
            view.ShowResult(String.valueOf(arg));
        }


    }
    public void saveState(Bundle oustate){
        State state = new State(argOne,argTwo,previousOperation,operationSelected,isDotPressed,divider);
        oustate.putParcelable("test",state);


    }

    public void restoreState(Bundle bundle){
        State outState = (State) bundle.getParcelable("test");
        this.argTwo=outState.argTwo;
        this.argOne=outState.argOne;
        this.isDotPressed=outState.isDotPressed;
        this.divider=outState.divider;
        double result = calculator.performOperation(argOne, argTwo, operationSelected);
        displayResult(result);



    }

    private class State implements Parcelable {
        private Double argOne;
        private Double argTwo;
        private Operation previousOperation;
        private Operation operationSelected;
        private boolean isDotPressed;
        private int divider;


        public State(Double argOne, Double argTwo, Operation previousOperation, Operation operationSelected, boolean isDotPressed, int divider) {
            this.argOne = argOne;
            this.argTwo = argTwo;
            this.previousOperation = previousOperation;
            this.operationSelected = operationSelected;
            this.isDotPressed = isDotPressed;
            this.divider = divider;
        }

        protected State(Parcel in) {
            if (in.readByte() == 0) {
                argOne = null;
            } else {
                argOne = in.readDouble();
            }
            if (in.readByte() == 0) {
                argTwo = null;
            } else {
                argTwo = in.readDouble();
            }
            isDotPressed = in.readByte() != 0;
            divider = in.readInt();
        }

        public final Creator<State> CREATOR = new Creator<State>() {
            @Override
            public State createFromParcel(Parcel in) {
                return new State(in);
            }

            @Override
            public State[] newArray(int size) {
                return new State[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (argOne == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeDouble(argOne);
            }
            if (argTwo == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeDouble(argTwo);
            }
            dest.writeByte((byte) (isDotPressed ? 1 : 0));
            dest.writeInt(divider);
        }
    }


}
