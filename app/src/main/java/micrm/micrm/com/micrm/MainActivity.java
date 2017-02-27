package micrm.micrm.com.micrm;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Home_frag.OnFragmentInteractionListener,
        Personas_frag.OnFragmentInteractionListener, Negocio_frag.OnFragmentInteractionListener,
        Organizacion_frag.OnFragmentInteractionListener, Actividades_frag.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Fragment fragment = new Home_frag();
        getSupportFragmentManager().beginTransaction().replace(R.id.Replace_Me, fragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //Menu de opciones en item selecccionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        switch (id){
            case R.id.action_settings:
                finish();
                System.exit(0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Fragment fragment = null;
        boolean FragmentTransaction = false;

        if (id == R.id.home) {
            toolbar.setTitle("MiCRM");
            fragment = new Home_frag();
            FragmentTransaction = true;

        } else if (id == R.id.personas) {
            toolbar.setTitle("Personas");
            fragment = new Personas_frag();
            FragmentTransaction = true;

        } else if (id == R.id.organizacion) {
            toolbar.setTitle("Organizacion");

            fragment = new Organizacion_frag();
            FragmentTransaction = true;
        } else if (id == R.id.negocio) {
            toolbar.setTitle("Negocio");
            fragment = new Negocio_frag();
            FragmentTransaction = true;
        } else if (id == R.id.actividades) {
            toolbar.setTitle("Actividades");
            fragment = new Actividades_frag();
            FragmentTransaction = true;
        }

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (FragmentTransaction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Replace_Me, fragment).commit();
            item.setChecked(true);
        }

        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
