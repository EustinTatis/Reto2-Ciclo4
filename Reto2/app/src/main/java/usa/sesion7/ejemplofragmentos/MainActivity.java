package usa.sesion7.ejemplofragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragment_inicio, fragment1, fragment2, fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_inicio = new Fragment_Inicio();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_fragments, fragment_inicio).commit();
    }

    public void onClick(View view) {

        transaction=getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.boton1: transaction.replace(R.id.contenedor_fragments, fragment1);
            transaction.addToBackStack(null);
            break;

            case R.id.boton2: transaction.replace(R.id.contenedor_fragments, fragment2);
            transaction.addToBackStack(null);
            break;

            case R.id.boton3: transaction.replace(R.id.contenedor_fragments, fragment3);
            transaction.addToBackStack(null);
            break;

            case R.id.boton_inicio: transaction.replace(R.id.contenedor_fragments, fragment_inicio);
            transaction.addToBackStack(null);
            break;
        }
        transaction.commit();
    }
}