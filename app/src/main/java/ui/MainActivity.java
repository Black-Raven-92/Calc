package ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import Presenter.Presenter;
import logic.CalcImpl;
import logic.Operation;
import ua.black_raven.alculator.R;

public class MainActivity extends AppCompatActivity implements CalcView {
    private TextView txtResult;
    private Presenter presenter;
    private final String RESULT="RES";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveState(outState);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState==null){
            presenter = new Presenter(this, new CalcImpl());
        }else {
            presenter.restoreState(savedInstanceState);

        }

        txtResult = findViewById(R.id.textView);
        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.btn_0, 0);
        digits.put(R.id.btn_1, 1);
        digits.put(R.id.bnt_2, 2);
        digits.put(R.id.btn_3, 3);
        digits.put(R.id.btn_4, 4);
        digits.put(R.id.btn_5, 5);
        digits.put(R.id.bnt_6, 6);
        digits.put(R.id.btn_7, 7);
        digits.put(R.id.btn_8, 8);
        digits.put(R.id.btn_9, 9);
        View.OnClickListener digitsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };
        findViewById(R.id.btn_0).setOnClickListener(digitsClickListener);
        findViewById(R.id.btn_1).setOnClickListener(digitsClickListener);
        findViewById(R.id.bnt_2).setOnClickListener(digitsClickListener);
        findViewById(R.id.btn_3).setOnClickListener(digitsClickListener);
        findViewById(R.id.btn_4).setOnClickListener(digitsClickListener);
        findViewById(R.id.btn_5).setOnClickListener(digitsClickListener);
        findViewById(R.id.bnt_6).setOnClickListener(digitsClickListener);
        findViewById(R.id.btn_7).setOnClickListener(digitsClickListener);
        findViewById(R.id.btn_8).setOnClickListener(digitsClickListener);
        findViewById(R.id.btn_9).setOnClickListener(digitsClickListener);

        Map<Integer, Operation> operation = new HashMap<>();
        operation.put(R.id.btn_add, Operation.ADD);
        operation.put(R.id.btn_min, Operation.SUB);
        operation.put(R.id.bnt_mul, Operation.MUL);
        operation.put(R.id.btn_dev, Operation.DIV);

        View.OnClickListener operationsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperationPressed(operation.get(view.getId()));

            }
        };

        findViewById(R.id.btn_add).setOnClickListener(operationsClickListener);
        findViewById(R.id.btn_min).setOnClickListener(operationsClickListener);
        findViewById(R.id.bnt_mul).setOnClickListener(operationsClickListener);
        findViewById(R.id.btn_dev).setOnClickListener(operationsClickListener);

        findViewById(R.id.equals).setOnClickListener(v -> presenter.onSumKeyPressed());
        findViewById(R.id.button_dot).setOnClickListener(v -> presenter.onDotPressed());
        findViewById(R.id.btn_ac).setOnClickListener(v -> presenter.onCleanPressed());
    }

    @Override
    public void ShowResult(String result) {
        txtResult.setText(result);

    }
}