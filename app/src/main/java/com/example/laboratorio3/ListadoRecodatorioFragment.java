package com.example.laboratorio3;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Entity.Recordatorio;
import com.example.laboratorio3.Repository.RecordatorioRepository;
import com.example.laboratorio3.SharePreferences.RecordatorioPreferencesDataSource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ListadoRecodatorioFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecordatorioViewHolder recordatorioViewHolder;
    private List<Recordatorio> listRecordatorio;
    private RecordatorioRepository repository;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton btonAddFloting;


    public ListadoRecodatorioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listado_recodatorio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCompenentes(view);
        init();
        crearRecordatorio();
    }

    private void crearRecordatorio() {
        btonAddFloting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragm = getActivity().getSupportFragmentManager();
                CreacionRecordatorioFragment creacion=new CreacionRecordatorioFragment();
                fragm.beginTransaction().replace(R.id.contenido,creacion).addToBackStack(null).commit();
            }
        });
    }


    private void setCompenentes(View view) {
        recyclerView = view.findViewById(R.id.recyclerRecordatorio);
        btonAddFloting=view.findViewById(R.id.btonAddFloting);

    }
    private void init() {
        listRecordatorio=new ArrayList<Recordatorio>();
        recordatorioViewHolder= new RecordatorioViewHolder(listRecordatorio);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        repository= new RecordatorioRepository(new RecordatorioPreferencesDataSource(getContext()));
        recyclerView.setLayoutManager(manager);

        repository.getRecordatorios(new RecordatorioDataSource.RecuperarRecordatorioCallback() {
            @Override
            public void resultado(boolean exito, List<Recordatorio> recordatorios) {
                if(recordatorios!=null && listRecordatorio.isEmpty()){
                    if(!exito){
                        Toast.makeText(getContext(),"NO hay Recordatorios", Toast.LENGTH_LONG).show();
                    }
                    else {
                        recordatorioViewHolder = new RecordatorioViewHolder(recordatorios);
                        recyclerView.setAdapter(recordatorioViewHolder);
                        if (recordatorios.isEmpty()) {
                            Toast.makeText(getContext(),"No hay Recordatorios", Toast.LENGTH_LONG).show();
                        }
                    }
                }


                }

            });


    }
}
