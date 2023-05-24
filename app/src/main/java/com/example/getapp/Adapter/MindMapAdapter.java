package com.example.getapp.Adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.getapp.MindMapPage;
import com.example.getapp.Model.MindMapModel;
import com.example.getapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MindMapAdapter extends RecyclerView.Adapter<MindMapAdapter.MyViewHolder> {
    private List<MindMapModel> mindMapList;
    private MindMapPage activity;
    private FirebaseFirestore firestore;

    public MindMapAdapter(MindMapPage mindMapPage, List<MindMapModel> mindMaplist) {
        this.mindMapList = mindMaplist;
        activity = mindMapPage;
    }

    @NonNull
    @Override
    public MindMapAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.drawer_mindmap_instance, parent, false);
        firestore = FirebaseFirestore.getInstance();
        return new MindMapAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MindMapAdapter.MyViewHolder holder, int position) {
        MindMapModel mindMapModel = mindMapList.get(position);
        holder.bindData(mindMapModel);
    }

//    private byte[] convertImageToByteArray(Drawable imageDrawable, Bitmap.CompressFormat compressFormat) {
//        Bitmap bitmap = drawableToBitmap(imageDrawable);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }
//
//    private Bitmap drawableToBitmap(Drawable drawable) {
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable) drawable).getBitmap();
//        }
//
//        int width = drawable.getIntrinsicWidth();
//        int height = drawable.getIntrinsicHeight();
//
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawable.draw(canvas);
//
//        return bitmap;
//    }

    private boolean toBoolean(int status){
        return status != 0;
    }

    @Override
    public int getItemCount() {
        return mindMapList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView taskName;
//        AppCompatImageView map;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.taskName);
//            map = itemView.findViewById(R.id.mindMapImg);
        }

        public void bindData(MindMapModel mindMapModel) {
            taskName.setText(mindMapModel.getTask());

            // Set the Drawable to the ImageView
//            map.setImageDrawable(mindMapModel.getMap());
        }
    }
}
