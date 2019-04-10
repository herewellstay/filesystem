package com.herewellstay.filesystem;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "files")
public class File implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "path")
    private String path;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "parent")
    private String parent;
    @NonNull
    @ColumnInfo(name = "is_directory")
    private Boolean isDirectory;


    public File(String name, String path, String parent, Boolean isDirectory) {
        this.name = name;
        this.path = path;
        this.parent = parent;
        this.isDirectory = isDirectory;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPath() {
        return path;
    }

    @NonNull
    public String getParent() {
        return parent;
    }

    @NonNull
    public Boolean isDirectory() {
        return isDirectory;
    }



}
