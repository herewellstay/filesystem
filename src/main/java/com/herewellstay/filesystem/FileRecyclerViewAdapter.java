package com.herewellstay.filesystem;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;



class FileRecyclerViewAdapter extends Adapter<FileRecyclerViewAdapter.FileViewHolder> {

    private static final int TYPE_FILE = 1;
    private static final int TYPE_DIRECTORY = 2;
    private List<File> files = new ArrayList<>();
    private Context context;
    private View.OnClickListener onClickListener;
    private LayoutInflater inflater;

    public FileRecyclerViewAdapter(Context context) {
        inflater= LayoutInflater.from(context);

        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(files.get(position).isDirectory()){
            return TYPE_DIRECTORY;
        }
        else{
            return TYPE_FILE;
        }
    }

    public void setOnClickListener(View.OnClickListener callback) {
        onClickListener = callback;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType==TYPE_DIRECTORY){
            itemView=inflater.inflate(R.layout.folder_recycler_view_item, parent, false);

        }
        else{

           itemView=inflater.inflate(R.layout.file_recycler_view_item, parent, false);
        }
        return new FileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        File file = files.get(position);
        CachedFiles filesystem = new CachedFiles(new LocalFilesystem(), new FilesInDatabase(Database.getDatabase(context.getApplicationContext())));
        if(file.isDirectory()){
            final TextView counterTextView = holder.itemView.findViewById(R.id.counterTextView);
            TextView nameTextView = holder.itemView.findViewById(R.id.nameTextView);
            counterTextView.setText(String.valueOf(new FilesInMemory(filesystem.byParent(file.getPath())).byFilter(new IsVideoFileFilter()).size()));
            nameTextView.setText(file.getName());
        } else {
            ImageView fileImageView = holder.itemView.findViewById(R.id.fileImageView);
            TextView nameTextView = holder.itemView.findViewById(R.id.nameTextView);
            nameTextView.setText(file.getName());
            int thumbnailSize = (int) context.getResources().getDimension(R.dimen.thumbnail_size);

            Glide
                    .with(context)
                    .load(Uri.fromFile(new java.io.File(file.getPath())))
                    .apply(new RequestOptions().override(thumbnailSize, thumbnailSize))
                    .centerCrop()
                    .into(fileImageView);
        }

    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public List<File> getFiles() {

        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
        notifyDataSetChanged();
    }

    class FileViewHolder extends RecyclerView.ViewHolder {
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v);
                }
            });
        }
    }
}
