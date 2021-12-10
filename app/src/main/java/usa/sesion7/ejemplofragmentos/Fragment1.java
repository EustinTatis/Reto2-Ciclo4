package usa.sesion7.ejemplofragmentos;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        ImageView flecha1 = v.findViewById(R.id.flecha1_productos);
        flecha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_burger.class);
                startActivity(intent);
            }
        });

        ImageView flecha2 = v.findViewById(R.id.flecha2_productos);
        flecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_perro.class);
                startActivity(intent);
            }
        });

        ImageView flecha3 = v.findViewById(R.id.flecha3_productos);
        flecha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_pizza.class);
                startActivity(intent);
            }
        });

        ImageView flecha4 = v.findViewById(R.id.flecha4_productos);
        flecha4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_salchipapa.class);
                startActivity(intent);
            }
        });

        ImageView flecha5 = v.findViewById(R.id.flecha5_productos);
        flecha5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_desgranados.class);
                startActivity(intent);
            }
        });

        ImageView flecha6 = v.findViewById(R.id.flecha6_productos);
        flecha6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_picadas.class);
                startActivity(intent);
            }
        });

        ImageView flecha7 = v.findViewById(R.id.flecha7_productos);
        flecha7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_asados.class);
                startActivity(intent);
            }
        });

        ImageView flecha8 = v.findViewById(R.id.flecha8_productos);
        flecha8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_pinchos.class);
                startActivity(intent);
            }
        });

        ImageView flecha9 = v.findViewById(R.id.flecha9_productos);
        flecha9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_bebidas.class);
                startActivity(intent);
            }
        });

        return  v;
    }
}