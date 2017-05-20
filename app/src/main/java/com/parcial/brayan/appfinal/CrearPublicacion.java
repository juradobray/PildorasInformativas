package com.parcial.brayan.appfinal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.parcial.brayan.com.parcial.brayan.entity.Publicacion;
import com.parcial.brayan.com.parcial.brayan.util.EnviarMails;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class CrearPublicacion extends Fragment {
    /**
     * Etiqueta para depuración
     */
    private int id_facultad=0;
    private StringRequest request;
    private RequestQueue requestQueue;
    private Publicacion publicacion= new Publicacion();
    Address[] listaEmailsFacultad;

    String URL = "https://bd-android-juradobray1.c9users.io/create_publicacion.php";
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

    public void crearPublicacion(View v){

        requestQueue = Volley.newRequestQueue(getContext());
        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    System.out.print(jsonObject.toString());
                   int resp=( jsonObject.getInt("response"));


                    if (resp == -10) {
                        Toast.makeText(getActivity(),"Se presentó un error guardando la publicación",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(),"Publicación guardada satisfactoriamente",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Se presentó una excepción al decodificar el JSON", Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("titulo", publicacion.getTitulo_publicacion());
                map.put("descripcion", publicacion.getContenido_publicacion());
                map.put("imagen", publicacion.getImagen());
                map.put("usuario_id", publicacion.getId_usuario()+"");
                map.put("fecha_inicio", publicacion.getFecha_inicio().toString());
                map.put("fecha_fin", publicacion.getFecha_fin().toString());
                map.put("tipo_publicacion_id", publicacion.getId_tipo_publicacion() + "");
                map.put("method","CREAR_PUBLICACION");
                map.put("id_facultad",id_facultad+"");


                return map;
            }
        };

        requestQueue.add(request);


    }

    public void consultaEmailsFacultad(final int id_facultad){
        requestQueue = Volley.newRequestQueue(getContext());

        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    System.out.print(jsonObject.toString());
                    try {
                        separarEmails(jsonObject.getString("emails"));
                        enviarNotificacionesEmail();
                    }catch (Exception e){
                        Toast.makeText(getActivity(),"error "+e,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Se presentó una excepción al decodificar el JSON", Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("titulo","");
                map.put("descripcion","");
                map.put("imagen", "");
                map.put("usuario_id", "");
                map.put("fecha_inicio", "");
                map.put("fecha_fin", "");
                map.put("tipo_publicacion_id", "");
                map.put("method","CONSULTA_EMAIL");
                map.put("id_facultad",id_facultad+"");


                return map;
            }
        };

        requestQueue.add(request);
    }

    public void enviarNotificacionesEmail(){

        EnviarMails mails= new EnviarMails(getContext(),listaEmailsFacultad,publicacion.getTitulo_publicacion(),publicacion. getContenido_publicacion());
        mails.execute();
    }

    public void enviarNotificacionFirebase(){

    }

    private void separarEmails(String value) throws AddressException {
        String [] resultado=value.split(",");
        Address[] mails= new Address[resultado.length];
        for(int i =0; i<resultado.length;i++){
            mails[i]= new InternetAddress(resultado[i]);
        }
    }


    /**
     * Muestra un diálogo de confirmación
     */
    public void mostrarDialogo() {

    }


}
