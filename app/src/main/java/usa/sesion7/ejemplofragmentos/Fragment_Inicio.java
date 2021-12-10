package usa.sesion7.ejemplofragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class Fragment_Inicio extends Fragment {

    public Fragment_Inicio() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__inicio, container, false);

        Button boton_promo = v.findViewById(R.id.boton1);
        boton_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_promociones.class);
                startActivity(intent);
            }
        });

        ImageView imagen1 = v.findViewById(R.id.imagenInicial2);
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Para el tercer reto se abrirá otra actividad", Toast.LENGTH_LONG).show();
            }
        });

        ImageView imagen2 = v.findViewById(R.id.imagenInicial3);
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Para el tercer reto se abrirá otra actividad", Toast.LENGTH_LONG).show();
            }
        });

        return  v;
    }
}