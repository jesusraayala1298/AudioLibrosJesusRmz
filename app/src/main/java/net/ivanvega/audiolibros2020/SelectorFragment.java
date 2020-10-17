package net.ivanvega.audiolibros2020;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycler;
    private GridLayoutManager layoutManager;

    MainActivity mainActivity;

    public SelectorFragment() {
        // Required empty public constructor
    }

    /**
     * La siguiente funcion es usada para crear una nueva instacion de
     * SelectorFragment usando los parametros que recibe
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return Una nueva instancia de SelectorFragment.
     */
    public static SelectorFragment newInstance(String param1, String param2) {
        SelectorFragment fragment = new SelectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * En el metodo OnCreateView es usado para crear la vista deacuerdo al tamaño de la pantalla
     * ademas se le añade el comportamiento los elementos que va a conterner en este caso al momento de dar clic
     * a algun libro mostrado
     * @param inflater Es un LayoutInflater usado para
     * @param container Es el contenedor de las vistas
     * @param savedInstanceState
     * @return Retorna la vista creada
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_selector, container, false);
        recycler =   (RecyclerView)v.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(getActivity(),2);
        recycler.setLayoutManager(layoutManager);
        AdaptadorLibros adaptadorLibros = new AdaptadorLibros(getActivity() , Libro.ejemploLibros());

            adaptadorLibros.setOnclickListener(
                    vl -> {
                        mainActivity.mostrarDetalle(recycler.getChildAdapterPosition(vl));
                    }
            );
            adaptadorLibros.setOnLongClickListener(view-> {
                AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(mainActivity);
                cuadroDialogo.setTitle("Selecciona la opcion");
                cuadroDialogo.setItems(new String[]{"Compartir", "Eliminar", "Agregar"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(),"Opcion seleccionado;"+i,Toast.LENGTH_LONG).show();
                    switch(i){
                    }
                    }
                });
                cuadroDialogo.setPositiveButton("OK",
                        ((dialogInterface, i) -> {
                        })
                        );
                cuadroDialogo.create().show();
                        return false;
                    }
            );
        recycler.setAdapter(adaptadorLibros);
        return v;
    }
}