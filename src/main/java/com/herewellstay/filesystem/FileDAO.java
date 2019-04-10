package com.herewellstay.filesystem;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FileDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(File file);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<File> file);
    @Query("DELETE FROM files")
    void deleteAll();

    @Query("SELECT * from files ORDER BY name ASC")
    List<File> getAll();
    @Query("SELECT * from files WHERE parent=:path ORDER BY name ASC")
    List<File> getByParent(String path);
    @Query("SELECT * from files WHERE path=:path  ORDER BY name ASC  LIMIT 1")
    File getByPath(String path);
}
