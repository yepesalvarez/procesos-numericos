package co.edu.eafit.numerical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.eafit.numerical.Utils.NumberBaseConverter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button convert_button = findViewById(R.id.btn_convert);
        EditText decimal_number = (EditText) findViewById(R.id.txt_decimal_number);
        TextView binary_number = (TextView) findViewById(R.id.txt_binary_num);
        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String[] binaryNumber = NumberBaseConverter.base10toBinary(Double.parseDouble(decimal_number.getText().toString()),false);
//                binary_number.setText(binaryNumber[0] + "." + binaryNumber[1] + " .. " + binaryNumber[2] + " .. " + binaryNumber[3]);

//                binary_number.setText(String.valueOf(NumberBaseConverter.base2toBase10(Double.parseDouble(decimal_number.getText().toString()))));
            }
        });


    }
}
