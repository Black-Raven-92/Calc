package Presenter;

import android.os.Parcel;
import android.os.Parcelable;

import logic.Operation;

public class State implements Parcelable {
    protected Double argOne;
    protected Double argTwo;
    protected Operation previousOperation;
    protected Operation operationSelected;
    protected boolean isDotPressed;
    protected int divider;


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

