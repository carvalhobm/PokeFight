package com.example.gohorse.pokefight.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.fragments.BuscarFragment;
import com.example.gohorse.pokefight.fragments.JogoFragment;
import com.example.gohorse.pokefight.fragments.PreJogoFragment;

public class HomeActivity extends ActionBarActivity {

    //----Layouts---------------------------------------------------------------------------------------
    public static RelativeLayout relativeLayoutToolbar;
    public DrawerLayout mDrawerLayout;
//--------------------------------------------------------------------------------------------------

    public ActionBarDrawerToggle mDrawerToggle;
    public Toolbar toolbar;
    public Button btnJogar;
    public Button btnBuscar;
    public Button btnConfiguracoes;
    public Button btnSair;
    public static EditText editTextToolbar;
    public static View view;

    //----Fragments-------------------------------------------------------------------------------------
    private BuscarFragment buscarFragment = new BuscarFragment();
    private PreJogoFragment preJogoFragment = new PreJogoFragment();
    private JogoFragment jogoFragment = new JogoFragment();

    private FragmentTransaction fragmentTransaction;

//--------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        btnJogar = (Button) findViewById(R.id.btnJogarMenuLateral);
        btnBuscar = (Button) findViewById(R.id.btnBuscarMenuLateral);
        btnConfiguracoes = (Button) findViewById(R.id.btnConfiguracoesMenuLateral);
        btnSair = (Button) findViewById(R.id.btnSair);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        relativeLayoutToolbar = (RelativeLayout) findViewById(R.id.relativeLayoutToolbar);
        editTextToolbar = (EditText) findViewById(R.id.editTextToolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutToolbar.setVisibility(View.GONE);
                getTelaPreJogo();
                mDrawerLayout.closeDrawers();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTelaBuscar();
                mDrawerLayout.closeDrawers();
            }
        });

        btnConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutToolbar.setVisibility(View.GONE);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    public void getTelaPreJogo(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, preJogoFragment, "preJogo");
        fragmentTransaction.commit();
    }

    public void getTelaBuscar(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, buscarFragment, "buscar");
        fragmentTransaction.commit();
    }

    public void getTelaJogo(){
        fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, jogoFragment, "jogo");
        fragmentTransaction.commit();
    }

    public void sair(){
        finish();
        System.exit(0);
    }
}
