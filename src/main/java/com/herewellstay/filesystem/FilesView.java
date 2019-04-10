package com.herewellstay.filesystem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FilesView extends RecyclerView {
    private final FileRecyclerViewAdapter fileRecyclerViewAdapter;
    private List<File> files;

    public FilesView(@NonNull Context context, AttributeSet attributes) {
        super(context, attributes);
        fileRecyclerViewAdapter=new FileRecyclerViewAdapter(context);
        this.setAdapter(fileRecyclerViewAdapter);
        this.setLayoutManager(new LinearLayoutManager(context));
    }
    public void setOnClickListener(OnClickListener listener){
        fileRecyclerViewAdapter.setOnClickListener(listener);
    }

    public void setFiles(List<File> files) {

        this.files = files;
        this.fileRecyclerViewAdapter.setFiles(files);
    }

    public List<File> getFiles() {
        return files;
    }

    public File getFile(View view) {
        int position=getChildLayoutPosition(view);
        return files.get(position);
    }
}
