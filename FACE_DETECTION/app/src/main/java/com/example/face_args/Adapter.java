package com.example.face_args;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<FaceModel> faceList;

    public Adapter(List<FaceModel> fList ) {
        faceList=fList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        FaceModel data = faceList.get(position);
        holder.FaceID.setText(Integer.toString(data.getFaceId()));
        holder.XRotView.setText(Double.toString(data.getAngleX()));
        holder.YRotView.setText(Double.toString(data.getAngleY()));
        holder.ZRotView.setText(Double.toString(data.getAngleZ()));
        holder.SmileView.setText(Double.toString(data.getSmile()));
        holder.SmileView.setText(Double.toString(data.getSmile()));
        holder.LEyeView.setText(Double.toString(data.getLeftEye()));
        holder.REyeView.setText(Double.toString(data.getRightEye()));
    }

    @Override
    public int getItemCount() {
        if(faceList==null)
            faceList=new ArrayList<>();
        return faceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView FaceID;
        TextView XRotView;
        TextView YRotView;
        TextView ZRotView;
        TextView SmileView;
        TextView LEyeView;
        TextView REyeView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FaceID = itemView.findViewById(R.id.fid_TV);
            XRotView = itemView.findViewById(R.id.xrot_TV);
            YRotView = itemView.findViewById(R.id.yrot_TV);
            ZRotView = itemView.findViewById(R.id.zrot_TV);
            SmileView = itemView.findViewById(R.id.smile_TV);
            LEyeView = itemView.findViewById(R.id.leye_TV);
            REyeView = itemView.findViewById(R.id.reye_TV);
        }
    }
}
