package ua.black_raven.alculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_add, btn_min, btn_dev, btn_mul, btn_clear, btn_dot, btn_settings;
    private final int[] buttonsID = new int[]{R.id.btn_0, R.id.btn_1, R.id.bnt_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.bnt_6, R.id.btn_7, R.id.btn_8,
            R.id.btn_9, R.id.btn_dev, R.id.btn_min, R.id.btn_add, R.id.bnt_mul};
    String text;
    TextView history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_clear=findViewById(R.id.btn_ac);
        history=findViewById(R.id.textView);
        setNumberButtonClick();
        btn_clear.setOnClickListener(v->{
            history.setText("");
        });

    }



    private void setNumberButtonClick() {
        for (int i = 0; i < buttonsID.length; i++) {
            findViewById(buttonsID[i]).setOnClickListener(v -> {
                Button btn = (Button) v;
                history.setText((String)history.getText()+btn.getText());

            });
        }
    }
}