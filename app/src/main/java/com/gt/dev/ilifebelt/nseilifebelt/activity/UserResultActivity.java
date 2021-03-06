package com.gt.dev.ilifebelt.nseilifebelt.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.gt.dev.ilifebelt.nseilifebelt.R;
import com.gt.dev.ilifebelt.nseilifebelt.controller.MyApplication;
import com.gt.dev.ilifebelt.nseilifebelt.model.Results;

/**
 * Actividad que muestra los detalles del resultado.
 */
public class UserResultActivity extends AppCompatActivity implements View.OnClickListener {

    private String name, email, nse;
    private FloatingActionButton fab;
    private TextView tvName, tvEmail, tvNSE;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private MyApplication myApp;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_result);
        startVars();

        getSource();

        startAnalytics();
    }

    /**
     * Metodo que inicializa todas las variables.
     */
    private void startVars() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FloatinActionButton Delete Item
        fab = (FloatingActionButton) findViewById(R.id.fab_delete_item);
        fab.setOnClickListener(this);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_result);
        tvName = (TextView) findViewById(R.id.tv_user_name);
        tvEmail = (TextView) findViewById(R.id.tv_user_email);
        tvNSE = (TextView) findViewById(R.id.tv_user_mean);
    }

    /**
     * Metodo que obtiene la data que ha seleccionado
     * el usuario con anterioridad.
     */
    private void getSource() {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        email = bundle.getString("email");
        nse = bundle.getString("nse");

        collapsingToolbarLayout.setTitle("NSE: " + nse);
        tvName.setText(name);
        tvEmail.setText(email);

        if (nse.equals("E")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_e);
            tvNSE.setText(getString(R.string.e_nse_text));
        } else if (nse.equals("A/B")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_ab);
            tvNSE.setText(getString(R.string.ab_nse_text));
        } else if (nse.equals("C")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_c);
            tvNSE.setText(getString(R.string.c_nse_text));
        } else if (nse.equals("C+")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_cplus);
            tvNSE.setText(getString(R.string.cplus_nse_text));
        } else if (nse.equals("D")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_d);
            tvNSE.setText(getString(R.string.d_nse_text));
        } else if (nse.equals("D+")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_dplus);
            tvNSE.setText(getString(R.string.dplus_nse_text));
        } else if (nse.equals("C-")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_cless);
            tvNSE.setText(getString(R.string.cless_nse_text));
        } else if (nse.equals("D2")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_e);
            tvNSE.setText(getString(R.string.e_nse_text));
        } else if (nse.equals("D1")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_d);
            tvNSE.setText(getString(R.string.e_nse_text));
        } else if (nse.equals("C3")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_dplus);
            tvNSE.setText(getString(R.string.d_nse_text));
            tvNSE.setText(getString(R.string.dplus_nse_text));
        } else if (nse.equals("C2")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_cless);
            tvNSE.setText(getString(R.string.cless_nse_text));
        } else if (nse.equals("C1")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_c);
            tvNSE.setText(getString(R.string.c_nse_text));
        } else if (nse.equals("B")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_cplus);
            tvNSE.setText(getString(R.string.cplus_nse_text));
        } else if (nse.equals("A")) {
            collapsingToolbarLayout.setBackgroundResource(R.drawable.nivel_ab);
            tvNSE.setText(getString(R.string.ab_nse_text));
        }
    }

    private void startAnalytics() {
        myApp = (MyApplication) getApplication();
        mTracker = myApp.getDefaultTracker();
    }

    /**
     * Metodo para eliminar al usuario.
     */
    private void deleteItem() {
        if (name != null) {
            new Delete().from(Results.class).where("Name = ?", name).execute();
            Toast.makeText(this, getString(R.string.item_deleted), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, getString(R.string.item_cant_deleted), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Metodo donde muestra si esta seguro de eliminar al usuario.
     */
    private void deleteDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        deleteItem();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(UserResultActivity.this, getString(R.string.no_changues_delete), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.question_delete)).setPositiveButton(getString(R.string.yes_answer), dialogClickListener)
                .setNegativeButton(getString(R.string.no_answer), dialogClickListener).show();
    }

    /**
     * Metodo nativo que controla los botones del toolbar.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo nativo implmementado por la interfaz,
     * donde indica la funcionalidad del FloatingActionButton.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_delete_item:
                deleteDialog();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}