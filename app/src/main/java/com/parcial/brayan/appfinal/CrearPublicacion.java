package com.parcial.brayan.appfinal;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;


public class CrearPublicacion extends Fragment {
    /**
     * Etiqueta para depuración
     */


    private static final String TAG = CrearPublicacion.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /*
    Controles
    */
    EditText titulo_input;
    EditText descripcion_input;
    DatePicker fecha;

    public CrearPublicacion() {
    }


    public static CrearPublicacion newInstance(String param1, String param2) {
        CrearPublicacion fragment = new CrearPublicacion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilitar al fragmento para contribuir en la action bar
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflando layout del fragmento
        View v = inflater.inflate(R.layout.fragment_crear_publicacion, container, false);

        // Obtención de instancias controles
        titulo_input = (EditText) v.findViewById(R.id.titulo_input);
        descripcion_input = (EditText) v.findViewById(R.id.descripcion_input);
        fecha=(DatePicker)v.findViewById(R.id.date_pick_crear);
        fecha.setCalendarViewShown(false);
//        fecha. setBackgroundColor(Color.parseColor("0xffff0000"));
        fecha.setClickable(true);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha.setCalendarViewShown(true);
            }
        });
       /* fecha_text = (TextView) v.findViewById(R.id.fecha_ejemplo_text);
        categoria_spinner = (Spinner) v.findViewById(R.id.categoria_spinner);
        prioridad_spinner = (Spinner) v.findViewById(R.id.prioridad_spinner);
*/


        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Remover el action button de borrar

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    /**
     * Guarda los cambios de una meta editada.
     * <p>
     * Si está en modo inserción, entonces crea una nueva
     * meta en la base de datos
     */
    public void guardarMeta() {

    }

    /**
     * Procesa la respuesta obtenida desde el sevidor
     *
     * @param response Objeto Json
     */
    private void procesarRespuesta(JSONObject response) {


    }


    public boolean camposVacios() {
        String titulo = titulo_input.getText().toString();
        String descripcion = descripcion_input.getText().toString();

        return (titulo.isEmpty() || descripcion.isEmpty());
    }




    /**
     * Muestra un diálogo de confirmación
     */
    public void mostrarDialogo() {

    }


}
