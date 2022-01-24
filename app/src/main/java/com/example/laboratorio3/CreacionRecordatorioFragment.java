package com.example.laboratorio3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Entity.Recordatorio;
import com.example.laboratorio3.Repository.RecordatorioRepository;
import com.example.laboratorio3.SharePreferences.RecordatorioPreferencesDataSource;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CreacionRecordatorioFragment extends Fragment implements View.OnClickListener {
    private TextInputEditText editTextRecordatorio;
    private EditText editTextDate, editTextHora;
    private Button btonDate, btonHora, btonGuardar;
    private DatePickerDialog.OnDateSetListener dataSetListener;
    public static String RECORDATORIO = "com.example.laboratorio3";
    private  RecordatorioDataSource datasource;

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
                                editTextDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                            }
                        }, year, month, day);
        datePickerDialog.show();

    }

    private Date conversor(EditText editTextDate) throws Exception {
    String sDate=editTextDate.toString();
    Date date= new SimpleDateFormat("dd/mm/yyyy").parse(sDate);
    return date;
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
                        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm a");
                        String formatedDate = timeformat.format(calendar.getTime());
                        editTextHora.setText(formatedDate);
                        starAlarm(calendar);
                    }

                }, hora, minutos, false);
        timePickerDialog.show();

    }


    private void starAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(getContext().ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setAction(RECORDATORIO);
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);

        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(getContext().ALARM_SERVICE);
        Intent intent = new Intent(getContext(), RecordotorioReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    public void guardar(View view) {
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
        if (flag[0] == 0) {
       /* Toast.makeText (getContext(),"Se guarda",Toast.LENGTH_LONG).show();
        btonGuardar.setEnabled(false);*/


            RecordatorioRepository recordatorioRepository = new RecordatorioRepository(datasource = new RecordatorioDataSource() {
                @Override
                public void guardarRecordatorio(Recordatorio recordatorio, GuardarRecordatorioCallback callback) {
                    Toast.makeText(getContext(), "llegue", Toast.LENGTH_LONG).show();
                }

                @Override
                public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {

                }
            });
            RecordatorioPreferencesDataSource rp = new RecordatorioPreferencesDataSource(getContext());
            Recordatorio r = new Recordatorio();
            r.setTexto(editTextRecordatorio.getText().toString());
            try {
                r.setFecha(conversor(editTextDate));

            } catch (Exception e) {
                e.printStackTrace();
            }

            recordatorioRepository.save(r, rp);

        }

    }



}





