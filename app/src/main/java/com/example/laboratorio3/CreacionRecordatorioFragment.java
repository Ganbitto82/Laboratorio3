package com.example.laboratorio3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Model.Recordatorio;
import com.example.laboratorio3.Repository.RecordatorioRepository;
import com.example.laboratorio3.SharePreferences.RecordatorioPreferencesDataSource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CreacionRecordatorioFragment extends Fragment implements View.OnClickListener {
    private TextInputEditText editTextRecordatorio;
    private EditText editTextDate, editTextHora;
    private Button btonDate, btonHora, btonGuardar,btonSi,btonNo;
    private DatePickerDialog.OnDateSetListener dataSetListener;
    public static String RECORDATORIO = "com.example.laboratorio3";
    private  RecordatorioDataSource datasource;
    private RecordatorioRepository  repo;
    private FloatingActionButton btonFloting;





    public CreacionRecordatorioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_creacion_recordatorio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setcomponentes(view);

    }

    private void setcomponentes(View view) {
        editTextRecordatorio = (TextInputEditText) view.findViewById(R.id.editTextRecordatorio);
        editTextDate = (EditText) view.findViewById(R.id.editTextFecha);
        btonDate = (Button) view.findViewById(R.id.btonDate);
        editTextHora = (EditText) view.findViewById(R.id.editTextHora);
        btonFloting= (FloatingActionButton) view.findViewById(R.id.btonFloting);

        btonHora = (Button) view.findViewById(R.id.btonHora);
        btonGuardar = (Button) view.findViewById(R.id.btonGuardar);
        btonDate.setOnClickListener(this);
        btonHora.setOnClickListener(this);
        btonGuardar.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btonDate:
                //  Toast.makeText (getContext(),"Fecha",Toast.LENGTH_LONG).show();
                setFecha();


                break;
            case R.id.btonHora:

                //Toast.makeText (getContext(),"hora",Toast.LENGTH_LONG).show();

                setHora();
                break;

            case R.id.btonGuardar:
                guardar(v);
                break;
        }

    }


    private void setFecha() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String dia,mes,anio;
                                if(dayOfMonth< 10)
                                    dia="0"+dayOfMonth;
                                else
                                    dia= String.valueOf(dayOfMonth);
                                if(month+1 < 10)
                                    mes="0"+ (month+1);
                                else
                                    mes= String.valueOf(month+1);
                                anio= String.valueOf(year);
                                editTextDate.setText(dia+"-"+mes+"-"+anio);

                            }
                        }, year, month, day);
        datePickerDialog.show();

    }


    private void setHora() {
        Calendar calendar = Calendar.getInstance();
        final int hora = calendar.get(Calendar.HOUR_OF_DAY);
        final int minutos = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 00);
                        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
                        String formatedDate = timeformat.format(calendar.getTime());
                        editTextHora.setText(formatedDate);
                        starAlarm(calendar);
                    }

                }, hora, minutos, false);
        timePickerDialog.show();

    }


    private void starAlarm(Calendar calendar) {
       // PreferenceManager.setDefaultValues(getContext(),R.xml.configuracion,false);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());

        boolean sp = preferences.getBoolean("switchPreferences", true);
        if (sp){
            AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(getContext().ALARM_SERVICE);
            Intent intent = new Intent();
            intent.setAction(RECORDATORIO);
            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }else{
            Toast.makeText(getActivity(), "Su notificacion no será mostrada , si desea mostrala dirijase a configuracion ", Toast.LENGTH_SHORT).show();
        }


    }



    public void guardar(View view) {
     /*----------------------------------------------------------------------------*/
        //Valida los datos de entrada

        final int[] flag = {0};

        //Toast.makeText (getContext(),"editTextRecordatorio" + editTextRecordatorio.getText().toString() ,Toast.LENGTH_LONG).show();
        if (editTextRecordatorio.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Debe ingresar recordatorio", Toast.LENGTH_LONG).show();
            flag[0] = 1;
        } else {
            flag[0] = 0;
        }

        if (editTextDate.getText().toString().equals("Fecha")) {

            Toast.makeText(getContext(), "Debe seleccionar una fecha", Toast.LENGTH_LONG).show();
            flag[0] = 1;
        } else {
            flag[0] = 0;

        }
        if (editTextHora.getText().toString().equals("Hora")) {
            Toast.makeText(getContext(), "Debe seleccionar una hora", Toast.LENGTH_LONG).show();
            flag[0] = 1;

        } else {
            flag[0] = 0;

        }
        /*----------------------------------------------------------------------------*/

        //Guarda la preferencia
        if (flag[0] == 0) {

            Recordatorio r = new Recordatorio();
            r.setTexto(editTextRecordatorio.getText().toString());
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");

                String dateInString = editTextDate.getText()+" "+ editTextHora.getText() ;
                Date date = sdf.parse(dateInString);
                Log.d("date",date.toString());
                r.setFecha(date);

               // Toast.makeText(getContext(),"dateString" + dateInString,Toast.LENGTH_LONG).show();

            } catch (Exception e) {

                e.printStackTrace();
            }


            repo= new RecordatorioRepository(new RecordatorioPreferencesDataSource(getContext()));
            if( repo.saveRecordatorio(r)){
                Toast.makeText(getContext(), "Se guardo", Toast.LENGTH_LONG).show();
                btonGuardar.setVisibility(View.GONE);
                btonFloting.setVisibility(View.VISIBLE);
            }
            else{
                Toast.makeText(getContext(), "No Se guardo", Toast.LENGTH_SHORT).show();
            }

              btonFloting.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      FragmentManager fragm = getActivity().getSupportFragmentManager();
                      ListadoRecodatorioFragment listado=new ListadoRecodatorioFragment();
                      fragm.beginTransaction().replace(R.id.contenido,listado).addToBackStack(null).commit();
                  }
              });

            }
        }

    }









