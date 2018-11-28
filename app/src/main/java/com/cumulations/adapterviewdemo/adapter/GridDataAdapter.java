package com.cumulations.adapterviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cumulations.adapterviewdemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridDataAdapter extends BaseAdapter {

    Context applicationContext;
    ArrayList<String> names;
    ArrayList<String> teams;
    ArrayList<String> imageurls;

    LayoutInflater inflater;

    public GridDataAdapter(Context applicationContext, ArrayList<String> names, ArrayList<String> teams, ArrayList<String> imageurls) {
        this.applicationContext = applicationContext;
        this.names = names;
        this.teams = teams;
        this.imageurls = imageurls;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_grid_item, parent,false);

            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.textViewName = convertView.findViewById(R.id.textView4);
            holder.textViewTeam = convertView.findViewById(R.id.textView5);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.get().load(imageurls.get(position)).into(holder.imageView);
        holder.textViewName.setText(names.get(position));
        holder.textViewTeam.setText(teams.get(position));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(applicationContext,holder.textViewName.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewTeam;
    }
}
