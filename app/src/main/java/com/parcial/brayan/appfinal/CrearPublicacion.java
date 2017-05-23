package com.parcial.brayan.appfinal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class CrearPublicacion extends Fragment {
    /**
     * Etiqueta para depuración
     */

    private StringRequest request;
    private RequestQueue requestQueue;
    private Publicacion publicacion= new Publicacion();
    Address[] listaEmailsFacultad;
    Button btnGuardar;

    String URL = "https://bd-android-juradobray1.c9users.io/create_publicacion.php";
    private static final String TAG = CrearPublicacion.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    private int id_user;
    private int  id_facu;



    /*
    Controles
    */
    EditText titulo_input;
    EditText descripcion_input;
    DatePicker fecha;


    public CrearPublicacion() {

    }


    public static CrearPublicacion newInstance(String id_usuario, String id_facultad) {
        CrearPublicacion fragment = new CrearPublicacion();
        Bundle args = new Bundle();
                args.putString("id_facultad", id_facultad);
        args.putString("id_usuario", id_usuario );
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

Bundle bund=getArguments();
         id_user=Integer.parseInt(bund.getString("id_usuario"));
        id_facu=Integer.parseInt( bund.getString("id_facultad"));
        Toast.makeText(getActivity(),id_user+" - "+id_facu,Toast.LENGTH_LONG).show();
        // Obtención de instancias controles
        titulo_input = (EditText) v.findViewById(R.id.titulo_input);
        descripcion_input = (EditText) v.findViewById(R.id.descripcion_input);
        fecha=(DatePicker)v.findViewById(R.id.date_pick_crear);
        //fecha.onDateChangedListener
        btnGuardar=(Button)v.findViewById(R.id.save_button);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearPublicacion();
            }
        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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




    public boolean camposVacios() {

        String titulo = titulo_input.getText().toString();
        String descripcion = descripcion_input.getText().toString();

        return (titulo.isEmpty() || descripcion.isEmpty());
    }

    public void crearPublicacion(){

        if(camposVacios()){
       Toast.makeText(getActivity(),"Título de publicación y descripción obligatorios ",Toast.LENGTH_LONG).show();

        }else {
            publicacion.setId_usuario(id_user);
            publicacion.setContenido_publicacion(descripcion_input.getText().toString());
            publicacion.setImagen("jajaja");
            publicacion.setTitulo_publicacion(titulo_input.getText().toString());
            int day = fecha.getDayOfMonth();
            int month = fecha.getMonth() + 1;
            int year = fecha.getYear();

            Date date = new GregorianCalendar(year, month, day).getTime();

            publicacion.setFecha_inicio(date);
            publicacion.setFecha_fin(date);
            publicacion.setId_tipo_publicacion(1);
            requestQueue = Volley.newRequestQueue(getActivity());
            request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        System.out.print(jsonObject.toString());
                        int resp = (jsonObject.getInt("response"));


                        if (resp == -10) {
                            Toast.makeText(getActivity(), "Se presentó un error guardando la publicación", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Publicación guardada satisfactoriamente", Toast.LENGTH_LONG).show();
                            consultaEmailsFacultad(id_facu);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Se presentó una excepción al decodificar el JSON", Toast.LENGTH_LONG).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams()  {
                    HashMap<String, String> map = new HashMap<String, String>();
                    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
                    String fecha=sdf.format(publicacion.getFecha_inicio());
                    map.put("titulo", publicacion.getTitulo_publicacion());
                    map.put("descripcion", publicacion.getContenido_publicacion());
                    map.put("imagen", publicacion.getImagen());
                    map.put("usuario_id", publicacion.getId_usuario() + "");
                    map.put("fecha_inicio",fecha );
                    map.put("fecha_fin", fecha);
                    map.put("tipo_publicacion_id", publicacion.getId_tipo_publicacion() + "");
                    map.put("method", "CREAR_PUBLICACION");
                    map.put("id_facultad", id_facu + "");
                    return map;
                }
            };

            requestQueue.add(request);

        }
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
                        if(jsonObject.getString("emails").equals("null")){

                        }else{
                            separarEmails(jsonObject.getString("emails"));
                            enviarNotificacionesEmail();
                        }

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
            protected Map<String, String> getParams() {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("titulo","");
                map.put("descripcion","");
                map.put("imagen", "");
                map.put("usuario_id", id_user+"");
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

        EnviarMails mails= new EnviarMails(getContext(),listaEmailsFacultad,publicacion.getTitulo_publicacion(),"Se ha añadido un nuevo evento para la fecha: "+publicacion.getFecha_inicio()+"\n Información de la publicacion: \n"+publicacion. getContenido_publicacion()+"\n\n Mensaje automático del sistema...\nNo contestar!!");
        mails.execute();
    }

    public void enviarNotificacionFirebase(){

    }

    private Address[] separarEmails(String value) throws AddressException {
        String [] resultado=value.split(",");
        Address[] mails= new Address[resultado.length];
        System.out.println(resultado.length+" tamaño de resultado!!!");
        for(int i =0; i<resultado.length;i++){

            mails[i]= new InternetAddress(resultado[i]);
        }
        listaEmailsFacultad=mails;
     return mails;
    }


    /**
     * Muestra un diálogo de confirmación
     */
    public void mostrarDialogo() {

    }


}
