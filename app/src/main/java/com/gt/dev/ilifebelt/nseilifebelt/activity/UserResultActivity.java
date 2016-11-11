package com.gt.dev.ilifebelt.nseilifebelt.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.gt.dev.ilifebelt.nseilifebelt.R;
import com.gt.dev.ilifebelt.nseilifebelt.model.Results;

public class UserResultActivity extends AppCompatActivity implements View.OnClickListener {

    private String name, email, nse;
    private FloatingActionButton fab;
    private TextView tvName, tvEmail, tvNSE;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_result);
        startVars();

        getSource();
    }

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
        }
    }

    private void deleteItem() {
        new Delete().from(Results.class).where("Name = ?", name).execute();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_delete_item:
                deleteItem();
                break;
        }
    }
}