package com.purlieus.purlieus.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.FloatRange;
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
import android.widget.Switch;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;
import com.purlieus.purlieus.R;
import com.purlieus.purlieus.adapters.DonorAdapter;
import com.purlieus.purlieus.adapters.SeekAdapter;
import com.purlieus.purlieus.models.BD_Donate;
import com.purlieus.purlieus.models.BD_Seek;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by anurag on 4/10/16.
 */
public class DonateFragment extends Fragment {

    private Spinner spinner;
    public static final String PROFILE_DATA="profile";
    SharedPreferences sp;
    SwitchCompat switchCompat;
    private BD_Donate donor;
    ProgressDialog progressDialog;



    LinearLayout emptyListCondition;
    LinearLayout fullListCondition;

    public DonateFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getActivity().getSharedPreferences(PROFILE_DATA, Context.MODE_PRIVATE);
        donor = new BD_Donate();
        donor.setName(sp.getString("name",""));
        donor.setSex(sp.getString("sex",""));
        donor.setAge(sp.getInt("age",0));
        donor.setEmail(sp.getString("email",""));
        donor.setContactNumber(sp.getString("contact",""));
        donor.setLatitude(sp.getString("latitude",""));
        donor.setLongitude(sp.getString("longitude",""));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donate, container, false);

        emptyListCondition = (LinearLayout) view.findViewById(R.id.no_seekers_layout);
        fullListCondition = (LinearLayout) view.findViewById(R.id.donate_tab);

        spinner = (Spinner)view.findViewById(R.id.bg_donate_spinner);
        switchCompat = (SwitchCompat) view.findViewById(R.id.donor_private_switch);

        progressDialog = new ProgressDialog(getActivity());

        String[] groups = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, groups);
        spinner.setAdapter(adapter);

        
        final SharedPreferences.Editor editor = sp.edit();


        String seekerBG;
        if (sp.getString("bg_donate","").equals("")){
            seekerBG = sp.getString("bg", "");
        }
        else{
            seekerBG = sp.getString("bg_donate", "");
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
                editor.putString("bg_donate", parent.getItemAtPosition(position).toString());
                editor.apply();
                donor.setBloodGroup(parent.getItemAtPosition(position).toString());
                Log.d("Selected", parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) donor.setPrivate(true);
                else donor.setPrivate(false);
            }
        });

        return view;
    }

}
