package co.edu.eafit.numerical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.eafit.numerical.utils.NumberBaseConverter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        Button convert_button = findViewById(R.id.btn_convert);
        EditText decimal_number = (EditText) findViewById(R.id.txt_decimal_number);
        TextView binary_number = (TextView) findViewById(R.id.txt_binary_num);
        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] binaryNumber = NumberBaseConverter.base10toBinary(Double.parseDouble(decimal_number.getText().toString()));
                binary_number.setText(binaryNumber[0] + "." + binaryNumber[1] + " .. " + binaryNumber[2] + " .. " + binaryNumber[3]);

//                binary_number.setText(String.valueOf(NumberBaseConverter.base2toBase10(Double.parseDouble(decimal_number.getText().toString()))));
//                binary_number.setText(NumberBaseConverter.toFloatingPointNotation(Double.parseDouble(decimal_number.getText().toString())));
            }
        });
*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_setup_machine:
                Intent intentSetupMachine = new Intent(MainActivity.this, MachineDefinition.class);
                startActivity(intentSetupMachine);
                break;
            case R.id.itm_machine_numbers:
                Intent intentNumbers = new Intent(MainActivity.this, MachineNumbers.class);
                startActivity(intentNumbers);
                break;
            case R.id.itm_math_operations:
                Intent intentMachineOps = new Intent(MainActivity.this, MachineOperations.class);
                startActivity(intentMachineOps);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
