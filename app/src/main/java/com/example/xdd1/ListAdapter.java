package com.example.xdd1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElements> mData;
    private LayoutInflater mInflater;
    private OnItemClickListener listener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(ListElements item);
    }

    public ListAdapter(List<ListElements> itemList, Context context, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = itemList;
        this.listener = listener;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.contactos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, tel, id;
        ImageView llamarIcon;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nom);
            tel = itemView.findViewById(R.id.te);
            id = itemView.findViewById(R.id.id);
            llamarIcon = itemView.findViewById(R.id.xd);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(mData.get(getAdapterPosition()));
                }
            });

            llamarIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = mData.get(getAdapterPosition()).getTel();
                    llamar(phoneNumber);
                }
            });
        }

        void bindData(final ListElements item) {
            nombre.setText(item.getNombre());
            tel.setText(item.getTel());
            id.setText(item.getNum());
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void llamar(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }

    public void updateList(List<ListElements> newList) {
        mData = newList;
        notifyDataSetChanged();
    }
}
