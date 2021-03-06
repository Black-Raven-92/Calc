package Presenter;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

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

    private static final String KEY_STATE = "KEY_STATE";


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

    public void onSaveState(Bundle bundle) {
        bundle.putParcelable(KEY_STATE, new State(argOne, argTwo, previousOperation, operationSelected, isDotPressed, divider, view));


    }

    public void onRestoreState(Bundle bundle) {
        State state = bundle.getParcelable(KEY_STATE);
        argOne = state.argOne;
        argTwo = state.argTwo;
        isDotPressed = state.isDotPressed;
        divider = state.divider;
        operationSelected = state.operationSelected;
        previousOperation = state.previousOperation;
        view=state.view;
        view.ShowResult(String.valueOf(argOne));

    }

    public static class State implements Parcelable {
        private Double argOne;
        private Double argTwo;
        private Operation previousOperation;
        private Operation operationSelected;
        private boolean isDotPressed;
        private int divider;
        private CalcView view;

        public State(Double argOne, Double argTwo, Operation previousOperation, Operation operationSelected, boolean isDotPressed, int divider, CalcView view) {
            this.argOne = argOne;
            this.argTwo = argTwo;
            this.previousOperation = previousOperation;
            this.operationSelected = operationSelected;
            this.isDotPressed = isDotPressed;
            this.divider = divider;
            this.view = view;
        }

        public Double getArgOne() {
            return argOne;
        }

        public Double getArgTwo() {
            return argTwo;
        }

        public Operation getPreviousOperation() {
            return previousOperation;
        }

        public Operation getOperationSelected() {
            return operationSelected;
        }

        public boolean isDotPressed() {
            return isDotPressed;
        }

        public int getDivider() {
            return divider;
        }

        protected State(Parcel in, CalcView view) {
            this.view = view;
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

        public static final Creator<State> CREATOR = new Creator<State>() {
            public CalcView view;

            @Override
            public State createFromParcel(Parcel in) {
                return new State(in, view);
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
