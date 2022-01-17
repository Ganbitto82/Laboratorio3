package com.example.laboratorio3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;


import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.view.MenuItem;

import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String CANAL_MENSAJES_ID_1 ="1001" ;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    public static String RECORDATORIO ="com.example.laboratorio3";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecordotorioReceiver rec= new RecordotorioReceiver();
        IntentFilter intentFilter=new IntentFilter();
      //  intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
       // intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
       // intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(RECORDATORIO);
       // intentFilter.addAction(CANAL_MENSAJES_ID_1);
        this.registerReceiver(rec,intentFilter);
        createNotificationChannel();
        setComponentes();


    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            CharSequence name = getString(R.string.CANAL_1);
            NotificationChannel channel = new NotificationChannel(CANAL_MENSAJES_ID_1,name, importance);
            channel.setDescription("NOTIFICACIONES_1");
            NotificationManager notificationManager =getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    public void setComponentes() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        drawer = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        navigationView= findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        toggle= new ActionBarDrawerToggle(this,drawer,toolbar,R.string.drawer_open,R.string.drawer_closed);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


    }


    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectItemNav(item);
        return true;
    }

    private void selectItemNav(@NonNull MenuItem item) {
        FragmentManager fragm =getSupportFragmentManager();
       TextView titulo= findViewById(R.id.titulo_toolbar);
        switch (item.getItemId()){

            case R.id.listado_recordatorio:
                ListadoRecodatorioFragment listado=new ListadoRecodatorioFragment();
                fragm.beginTransaction().replace(R.id.contenido,listado).addToBackStack(null).commit();
                break;
            case R.id.creacion_recordatorio:
                CreacionRecordatorioFragment creacion= new CreacionRecordatorioFragment();
                fragm.beginTransaction().replace(R.id.contenido,creacion).addToBackStack(null).commit();
                break;
            case R.id.configuracion:
                ConfiguracionFragment configuracion= new ConfiguracionFragment();
                fragm.beginTransaction().replace(R.id.contenido,configuracion).addToBackStack(null).commit();
                break;

        }
      titulo.setText(item.getTitle());

      drawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if(toggle.onOptionsItemSelected(item))
        return true;
        return super.onOptionsItemSelected(item);
    }
}