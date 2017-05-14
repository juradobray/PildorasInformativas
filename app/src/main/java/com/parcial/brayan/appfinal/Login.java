package com.parcial.brayan.appfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.parcial.brayan.com.parcial.brayan.database.UsuarioDao;
import com.parcial.brayan.com.parcial.brayan.entity.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText emailLogin, passwordLogin;
    private Usuario user;
    private Usuario userLogin;
    private StringRequest request;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailLogin = (EditText) findViewById(R.id.email_login);
        passwordLogin = (EditText) findViewById(R.id.password_login);
        user = new Usuario();
        userLogin = new Usuario();
        //  userDao = new UsuarioDao();
    }

    public void login(View v) {
        if (emailLogin.getText().toString() == null || emailLogin.getText().toString().equals("") || passwordLogin.getText().toString() == null || passwordLogin.getText().toString().equals("")) {
            Toast.makeText(this, "Los campos Email y Contraseña son obligatorios", Toast.LENGTH_LONG).show();
        } else {
            user.setEmail(emailLogin.getText().toString());
            user.setContrasena(passwordLogin.getText().toString());
            //Llamar método para loguearse en la aplicación


            requestQueue = Volley.newRequestQueue(this);
            String URL = "https://bd-android-juradobray1.c9users.io/usario.php";
            request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        System.out.print(jsonObject.toString());
                        userLogin.setId_usuario( jsonObject.getInt("id_usuario"));
                        userLogin.setNombre_completo( jsonObject.getString("nombre_completo"));
                        userLogin.setId_perfil( jsonObject.getInt("tipo_usuario_id"));
                        userLogin.setId_facultad(jsonObject.getInt("facultad_id"));

                        if (userLogin.getId_usuario() == -10) {
                            Toast.makeText(Login.this, "Credenciales no válidas", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intentPantallaBienvenida = new Intent(Login.this, MainActivity.class);
                            intentPantallaBienvenida.putExtra("usuario_login", userLogin);
                            startActivity(intentPantallaBienvenida);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Login.this, "Se presentó una excepción al decodificar el JSON", Toast.LENGTH_LONG).show();
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
                    map.put("nombre", "");
                    map.put("correo", user.getEmail());
                    map.put("tipo_usuario_id", user.getId_perfil() + "");
                    map.put("facultad_id", user.getId_facultad() + "");
                    map.put("contrasena", user.getContrasena());

                    return map;
                }
            };

            requestQueue.add(request);


        }


    }
}
