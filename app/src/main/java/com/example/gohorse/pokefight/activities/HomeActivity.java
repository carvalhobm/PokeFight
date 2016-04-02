package com.example.gohorse.pokefight.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.gohorse.pokefight.R;
import com.example.gohorse.pokefight.fragments.AboutFragment;
import com.example.gohorse.pokefight.fragments.BuscarFragment;
import com.example.gohorse.pokefight.fragments.BuscarInitFragment;
import com.example.gohorse.pokefight.fragments.CameraFragment;
import com.example.gohorse.pokefight.fragments.PreJogoFragment;

public class HomeActivity extends ActionBarActivity {

    public static ProgressDialog progressDialog;
    public static ProgressDialog progressDialogSalvandoBd;

    private InputMethodManager imm;

    public static RelativeLayout relativeLayoutToolbar;
    public DrawerLayout mDrawerLayout;

    public ActionBarDrawerToggle mDrawerToggle;
    public Toolbar toolbar;
    public Button btnInicio;
    public Button btnJogar;
    public Button btnBuscar;
    public Button btnSobre;
    public Button btnSair;
    public Button btnCamera;
    public static RecyclerView rv;
    public static EditText editTextToolbar;
    public static View view;
    public FrameLayout fl;

    private BuscarInitFragment buscarInitFragment = new BuscarInitFragment();
    private PreJogoFragment preJogoFragment = new PreJogoFragment();
    private BuscarFragment buscarFragment = new BuscarFragment();
    private AboutFragment aboutFragment = new AboutFragment();
    private CameraFragment cameraFragment = new CameraFragment();

    private FragmentTransaction fragmentTransaction;

    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        if (Integer.valueOf(Build.VERSION.SDK_INT) >= 21) {
            setStatusBarColor(this);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialogSalvandoBd = new ProgressDialog(this);
        progressDialogSalvandoBd.setMessage("Creating base for pokemon...");
        progressDialogSalvandoBd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialogSalvandoBd.show();

        sharedPreferences = getSharedPreferences("Generation", Context.MODE_PRIVATE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        btnInicio = (Button) findViewById(R.id.btnInicio);
        btnJogar = (Button) findViewById(R.id.btnJogarMenuLateral);
        btnBuscar = (Button) findViewById(R.id.btnBuscarMenuLateral);
        btnSobre = (Button) findViewById(R.id.btnSobreMenuLateral);
        btnSair = (Button) findViewById(R.id.btnSair);
        btnCamera = (Button) findViewById(R.id.btnCamera);

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
                relativeLayoutToolbar.setVisibility(View.GONE);
                getTelaBuscar();
                mDrawerLayout.closeDrawers();
                closeKeyboard();
            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutToolbar.setVisibility(View.GONE);
                getTelaAbout();
                mDrawerLayout.closeDrawers();
                closeKeyboard();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutToolbar.setVisibility(View.GONE);
                getTelaCamera();
                mDrawerLayout.closeDrawers();
                closeKeyboard();
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

    public void getTelaAbout(){
        buscarFragment = new BuscarFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, aboutFragment, "sobre");
        fragmentTransaction.commit();
    }

    public void getTelaCamera(){
        cameraFragment = new CameraFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, cameraFragment, "camera");
        fragmentTransaction.commit();
    }

    public void closeKeyboard(){
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(HomeActivity.editTextToolbar.getWindowToken(), 0);
    }

    @TargetApi(value = 21)
    public void setStatusBarColor(Activity activity){
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(activity.getResources().getColor(R.color.status_bar));
    }

    public void sair(){
        finish();
        System.exit(0);
    }

}
