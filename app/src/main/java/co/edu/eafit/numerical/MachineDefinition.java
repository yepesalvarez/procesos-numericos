package co.edu.eafit.numerical;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MachineDefinition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_definition);
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
