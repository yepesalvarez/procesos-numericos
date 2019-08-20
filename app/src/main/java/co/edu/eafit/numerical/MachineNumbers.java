package co.edu.eafit.numerical;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import co.edu.eafit.numerical.Entities.Machine;
import co.edu.eafit.numerical.enums.ErrorMessages;

public class MachineNumbers extends AppCompatActivity {

    TextView lbl_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_numbers);

        lbl_result = findViewById(R.id.lbl_result_numbers);

        Button btn_biggest_number = findViewById(R.id.btn_biggest_number);
        btn_biggest_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(machineExists()){
                    lbl_result.setText(String.valueOf(Machine.getMachine().getBiggestNumber()));
                }
            }
        });

        Button btn_epsilon = findViewById(R.id.btn_epsilon);
        btn_epsilon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(machineExists()){
                    lbl_result.setText(String.valueOf(Machine.getMachine().getEpsilon()));
                }
            }
        });

        Button btn_smallest_positive = findViewById(R.id.btn_smallest_positive);
        btn_smallest_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(machineExists()){
                    lbl_result.setText(String.valueOf(Machine.getMachine().getSmallestPositiveNum()));
                }
            }
        });

        Button btn_clean = findViewById(R.id.btn_clean_numbers);
        lbl_result.setText("");



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
                Intent intentHome = new Intent(MachineNumbers.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.itm_setup_machine:
                Intent intentSetupMachine = new Intent(MachineNumbers.this, MachineDefinition.class);
                startActivity(intentSetupMachine);
                break;
            case R.id.itm_math_operations:
                Intent intentMachineOps = new Intent(MachineNumbers.this, MachineOperations.class);
                startActivity(intentMachineOps);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private boolean machineExists(){
        if(Machine.machine == null){
            lbl_result.setTextColor(Color.RED);
            lbl_result.setText(ErrorMessages.MACHINE_NOT_SET_UP.getMessage());
            return false;
        }
        lbl_result.setTextColor(Color.GREEN);
        return true;
    }

}
