package co.edu.eafit.numerical;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MachineNumbers extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_numbers);
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
}
