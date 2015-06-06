package com.example.gohorse.pokefight.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.fragments.BuscarFragment;
import com.example.gohorse.pokefight.fragments.BuscarInitFragment;
import com.example.gohorse.pokefight.fragments.PreJogoFragment;
import com.example.gohorse.pokefight.model.Person;

import java.util.List;

public class HomeActivity extends ActionBarActivity {
////////////////////////////////////////////////

    public static ProgressDialog progressDialog;
    public static ProgressDialog progressDialogSalvandoBd;



    ///////////////////////////////////////
    private InputMethodManager imm;

    //----Layouts---------------------------------------------------------------------------------------
    public static RelativeLayout relativeLayoutToolbar;
    public DrawerLayout mDrawerLayout;
//--------------------------------------------------------------------------------------------------

    public ActionBarDrawerToggle mDrawerToggle;
    public Toolbar toolbar;
    public Button btnInicio;
    public Button btnJogar;
    public Button btnBuscar;
    public Button btnConfiguracoes;
    public Button btnSair;
    public static RecyclerView rv;
    public static EditText editTextToolbar;
    public static View view;
    public FrameLayout fl;

    //----Fragments-------------------------------------------------------------------------------------
    private BuscarFragment buscarFragment = new BuscarFragment();
    private PreJogoFragment preJogoFragment = new PreJogoFragment();
    private BuscarInitFragment buscarInitFragment = new BuscarInitFragment();

    private FragmentTransaction fragmentTransaction;

//--------------------------------------------------------------------------------------------------

    public static SharedPreferences sharedPreferences;
    private List<Person> persons;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialogSalvandoBd = new ProgressDialog(this);
        progressDialogSalvandoBd.setMessage("Creating base for pokemon...");
        progressDialogSalvandoBd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        sharedPreferences = getSharedPreferences("Generation", Context.MODE_PRIVATE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        btnInicio = (Button) findViewById(R.id.btnInicio);
        btnJogar = (Button) findViewById(R.id.btnJogarMenuLateral);
        btnBuscar = (Button) findViewById(R.id.btnBuscarMenuLateral);
        btnConfiguracoes = (Button) findViewById(R.id.btnConfiguracoesMenuLateral);
        btnSair = (Button) findViewById(R.id.btnSair);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        relativeLayoutToolbar = (RelativeLayout) findViewById(R.id.relativeLayoutToolbar);
        editTextToolbar = (EditText) findViewById(R.id.editTextToolbar);
        rv = (RecyclerView) findViewById(R.id.rv);
        fl = (FrameLayout) findViewById(R.id.frameLayout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                closeKeyboard();
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutToolbar.setVisibility(View.GONE);
                getTelaInit();
                mDrawerLayout.closeDrawers();
                closeKeyboard();
            }
        });

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutToolbar.setVisibility(View.GONE);
                getTelaPreJogo();
                mDrawerLayout.closeDrawers();
                closeKeyboard();
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
                closeKeyboard();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });

        getTelaInit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    public void getTelaInit(){
        buscarInitFragment = new BuscarInitFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, buscarInitFragment, "buscarInit");
        fragmentTransaction.commit();
    }

    public void getTelaPreJogo(){
        preJogoFragment = new PreJogoFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, preJogoFragment, "preJogo");
        fragmentTransaction.commit();
    }

    public void getTelaBuscar(){
        buscarFragment = new BuscarFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, buscarFragment, "buscar");
        fragmentTransaction.commit();
    }

    public void closeKeyboard(){
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(HomeActivity.editTextToolbar.getWindowToken(), 0);
    }

    public void sair(){
        finish();
        System.exit(0);
    }

}
