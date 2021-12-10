package usa.sesion7.ejemplofragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity_burger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_burger);

        Fragment fragment1 = new Fragment_burger1();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_burger, fragment1).commit();
    }
}