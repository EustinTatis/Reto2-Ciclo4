package usa.sesion7.ejemplofragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity_perro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_perro);

        Fragment fragment2 = new Fragment_perro();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_perros, fragment2).commit();
    }
}