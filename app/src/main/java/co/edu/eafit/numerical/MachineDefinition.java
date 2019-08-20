package co.edu.eafit.numerical;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import co.edu.eafit.numerical.Entities.Machine;
import co.edu.eafit.numerical.enums.ErrorMessages;
import co.edu.eafit.numerical.enums.ProjectProperties;
import co.edu.eafit.numerical.enums.SuccessfulMessages;

public class MachineDefinition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_definition);

        Machine.machineBits = Integer.parseInt(ProjectProperties.MACHINE_CAPACITY.getProperty());
        TextView lbl_machine_bits = findViewById(R.id.lbl_machine_bits);
        lbl_machine_bits.setText(lbl_machine_bits.getText().toString().concat(String.valueOf(Machine.machineBits)));

        TextView lbl_errors_machine = findViewById(R.id.lbl_errors_machine);
        EditText txt_mantissa_bits = findViewById(R.id.txt_mantissa_bits);
        EditText txt_exponent_bits = findViewById(R.id.txt_exponent_bits);

        Button btn_save_machine = findViewById(R.id.btn_save_machine);
        btn_save_machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mantissaBits = Integer.parseInt(txt_mantissa_bits.getText().toString());
                int exponentBits = Integer.parseInt(txt_exponent_bits.getText().toString());
                if((exponentBits + mantissaBits) > Machine.machineBits){
                    lbl_errors_machine.setText(ErrorMessages.BITS_OVERFLOW.getMessage());
                } else {
                    Machine.machine = new Machine(mantissaBits, exponentBits);
                    lbl_errors_machine.setTextColor(Color.GREEN);
                    lbl_errors_machine.setText(SuccessfulMessages.MACHINE_CREATED.getMessage());
                }
            }
        });

        Button btn_clean_screen = findViewById(R.id.btn_clean_machine);
        btn_clean_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lbl_errors_machine.setText("");
                txt_exponent_bits.setText("");
                txt_mantissa_bits.setText("");
            }
        });
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
            case R.id.itm_home:
                Intent intentHome = new Intent(MachineDefinition.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.itm_machine_numbers:
                Intent intentNumbers = new Intent(MachineDefinition.this, MachineNumbers.class);
                startActivity(intentNumbers);
                break;
            case R.id.itm_math_operations:
                Intent intentMachineOps = new Intent(MachineDefinition.this, MachineOperations.class);
                startActivity(intentMachineOps);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
