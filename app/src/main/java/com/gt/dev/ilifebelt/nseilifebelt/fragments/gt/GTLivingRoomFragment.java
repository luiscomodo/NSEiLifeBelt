package com.gt.dev.ilifebelt.nseilifebelt.fragments.gt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.gt.dev.ilifebelt.nseilifebelt.R;

/**
 * Created by Community on 25/01/17.
 */

public class GTLivingRoomFragment extends AbstractStep implements View.OnClickListener {

    private Button btnYes, btnNo;
    private int click;
    public static int living;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.gt_livingroom_fragment, container, false);

        btnYes = (Button) v.findViewById(R.id.btn_si_livingroom);
        btnNo = (Button) v.findViewById(R.id.btn_no_livingroom);

        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        return v;
    }

    @Override
    public String name() {
        return "Sala";
    }

    @Override
    public boolean nextIf() {
        return click > 0;
    }

    @Override
    public String error() {
        return getString(R.string.validate_click);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_si_livingroom:
                living = 758;
                click++;
                break;
            case R.id.btn_no_livingroom:
                living = 0;
                click++;
                break;
        }
    }
}