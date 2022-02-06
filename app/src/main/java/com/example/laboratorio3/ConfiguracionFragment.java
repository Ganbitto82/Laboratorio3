package com.example.laboratorio3;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class ConfiguracionFragment extends PreferenceFragmentCompat {


    public ConfiguracionFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
         setPreferencesFromResource(R.xml.configuracion,rootKey);

     


    }



}