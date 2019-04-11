package com.purlieus.purlieus.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.purlieus.purlieus.R;
//import com.purlieus.purlieus.models.BD_Donate;
import com.purlieus.purlieus.adapters.SeekAdapter;
import com.purlieus.purlieus.models.BD_Donate;
import com.purlieus.purlieus.models.BD_Seek;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.google.android.gms.analytics.internal.zzy.co;

/**
 * Created by anurag on 4/10/16.
 */
public class SeekFragment extends Fragment {

    private Spinner spinner;
    public static final String PROFILE_DATA="profile";
    ProgressDialog progressDialog;

    LinearLayout emptyListCondition;
    LinearLayout fullListCondition;
    
    private SharedPreferences sp;
    private BD_Seek seeker;

    public SeekFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences(PROFILE_DATA, Context.MODE_PRIVATE);
        seeker=new BD_Seek();
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seek, container, false);

        emptyListCondition = (LinearLayout) view.findViewById(R.id.no_donors_layout);
        fullListCondition = (LinearLayout) view.findViewById(R.id.seek_tab);

        progressDialog = new ProgressDialog(getActivity());

        spinner = (Spinner)view.findViewById(R.id.bg_spinner);
        String[] groups = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, groups);
        spinner.setAdapter(adapter);

        SwitchCompat sc = (SwitchCompat)view.findViewById(R.id.urgent_seek_switch);

        String seekerBG;
        if (sp.getString("bg_seek","").equals("")){
            seekerBG = sp.getString("bg", "");
        }
        else{
            seekerBG = sp.getString("bg_seek", "");
        }

        int selectBG=0;
        for (int i=0; i<groups.length; i++){
            if (seekerBG.equals(groups[i])){
                selectBG=i;
            }
        }
        spinner.setSelection(selectBG);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("bg_seek", parent.getItemAtPosition(position).toString());
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) seeker.setUrgent(true);
                else seeker.setUrgent(false);
            }
        });

       return view;
    }

}
