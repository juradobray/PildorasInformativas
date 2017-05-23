package com.parcial.brayan.appfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

//import com.parcial.brayan.com.parcial.brayan.database.UsuarioDao;
import com.google.firebase.iid.FirebaseInstanceId;
import com.parcial.brayan.com.parcial.brayan.entity.Usuario;
import com.parcial.brayan.com.parcial.brayan.util.EnviarMails;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Calendar.OnFragmentInteractionListener {

    private Usuario user= new Usuario();
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_main);
        //  setTitle("Informativo UCC");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  //  setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



       Menu m = navigationView.getMenu();
        user= (Usuario)(getIntent().getExtras().get("usuario_login"));
        if(user.getId_perfil()==1){
            //muestro opcion de agregar noticia
        }else{//oculto opcion de agregar noticia
            //linea que desaparece opciones para decanos!!!!!
            MenuItem itemDisab=m.getItem(0);
            itemDisab.setVisible(false);
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem m = menu.getItem(0);
        m.setTitle("hola mundo");
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_crear_publicacion) {
            // Handle the camera action
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            try {
            fragment = CrearPublicacion.newInstance(user.getId_usuario()+"", user.getId_facultad()+"");


            } catch (Exception e) {
                e.printStackTrace();
            }
                setTitle("Consulta de publicaciones");
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();


        } else if (id == R.id.nav_consulta_publicaciones) {
            try {
                fragment = Calendar.newInstance("", "");


            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
        } else if (id == R.id.nav_cerrar_Sesion) {
            Intent inte= new Intent(this,Login.class);
            inte.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inte);
            Toast.makeText(this,"Cerrar sesión",Toast.LENGTH_LONG).show();
         //   EnviarMails em = new EnviarMails(this,"kattrendon17@gmail.com","prueba desde android","hola mi amor!! te amo !!");
           // em.execute();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
