package com.example.laboratorio3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laboratorio3.Model.Recordatorio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecordatorioViewHolder extends RecyclerView.Adapter<RecordatorioViewHolder.ViewHolder>implements View.OnClickListener {
    private List<Recordatorio> mDataSet;
    private View.OnClickListener listener;

    public RecordatorioViewHolder(List<Recordatorio> dataSet) {

        this.mDataSet=dataSet;
    }


    public void add(Recordatorio e){
        if(mDataSet==null){
            mDataSet= new ArrayList<Recordatorio>();
        }
        mDataSet.add(e);
    }

    @Override
    public void onClick(View v) {
        if(this.listener!=null){
            this.listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFecha,tvRecordatorio;
        private ImageView imgCalendar,imgTime,imgComent;
        private CardView cv;


        public ViewHolder( View view) {
            super(view);
            tvFecha =view.findViewById(R.id.tvFecha);
            tvRecordatorio =view.findViewById(R.id.tvRecordatorio);
            //imgCalendar=view.findViewById(R.id.imgCalendar);
            //imgTime=view.findViewById(R.id.imgCalendar);
            //imgComent=view.findViewById(R.id.imgComent);
            //cv=itemView.findViewById(R.id.cardView);

        }
    }

    @NonNull
    @Override
    public RecordatorioViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element,parent,false);
        view.setOnClickListener(this);
        return new RecordatorioViewHolder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordatorioViewHolder.ViewHolder holder, int position) {
    Recordatorio recordatorio=mDataSet.get(position);
    holder.tvFecha.setTag(position);
    String date;
    SimpleDateFormat formatoF = new SimpleDateFormat("dd-MM-yyyy - hh:mm");
    date = formatoF.format(recordatorio.getFecha());
        Log.d("LLEGUE","llegue");
    holder.tvFecha.setText("Fecha: "+ date);
    holder.tvRecordatorio.setText(" "+recordatorio.getTexto().toString());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
