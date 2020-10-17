package net.ivanvega.audiolibros2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * Metodo donde se crea la vista de la aplicacion, hace uso del SelectorFragment para verificar los fragmentos que tiene que mostrar
     * aqui se usa la libreria FragmentManager para gestionar los fragmentos y realizar validaciones. ademas de hacer uso de transacciones.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SelectorFragment selectorFragment = new SelectorFragment();
        if ( findViewById(R.id.contenedor_pequeno) != null &&  getSupportFragmentManager().findFragmentById(R.id.contenedor_pequeno)== null ){
            getSupportFragmentManager().beginTransaction().add(R.id.contenedor_pequeno, selectorFragment).commit();
        }

    }

    /**
     * En esta funcion lo que se realiza es simplemente mostrar el DetalleFragment con los datos de cada libro al dar clic
     * en la imagen que corresponde al libro.
     * @param index Representa el indice donde se encuentra el libro seleccionado en el vector
     */
    public void mostrarDetalle(int index){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.detalle_fragment)!=null && getSupportFragmentManager().findFragmentById(R.id.detalle_fragment)!=null){
            DetalleFragment fragment = (DetalleFragment) fragmentManager.findFragmentById(R.id.detalle_fragment);
            fragment.ponInfoLibro(index);
        }else{
            DetalleFragment detalleFragment = new DetalleFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(DetalleFragment.ARG_ID_LIBRO, index);
            detalleFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.contenedor_pequeno, detalleFragment).addToBackStack(null).commit();
        }

    }
}