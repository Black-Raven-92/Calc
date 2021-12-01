package logic;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import ua.black_raven.alculator.R;

public enum Theme {

    ONE (R.style.Theme_Сalculator_One, R.string.theme_one,"One"),
    TWO (R.style.Theme_Сalculator_Two, R.string.theme_two,"Two"),
    THREE (R.style.Theme_Сalculator, R.string.theme_three,"Three");
    @StyleRes
    private final int theme;
    @StringRes
    private final int name;
    private String key;

    Theme(int theme, int name, String key){
        this.theme=theme;
        this.name=name;
        this.key=key;
    }


    public int getTheme() {
        return theme;
    }

    public int getName() {
        return name;
    }
    public String getKey(){
        return key;
    }
}
