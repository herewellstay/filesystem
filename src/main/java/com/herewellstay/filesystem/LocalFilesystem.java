package com.herewellstay.filesystem;

import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LocalFilesystem implements Filesystem {
    private java.io.File root = new java.io.File(Environment.getExternalStorageDirectory().getAbsolutePath());

    public LocalFilesystem() {

    }

    @Override
    public void add(File file) {
        if (!(contains(file))) {
            if (file.isDirectory()) {
                new java.io.File(file.getPath()).mkdirs();
            } else {
                String filepath = file.getPath();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(filepath);
                    byte[] buffer = "".getBytes();
                    fos.write(buffer, 0, buffer.length);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    @Override
    public void addAll(List<File> files) {
        for (File file : files) {
            add(file);
        }
    }

    @Override
    public List<File> all() {
        return byFilter(new NoFilter());
    }

    @Override
    public boolean contains(File file) {
        return new java.io.File(file.getPath()).exists();
    }

    @Override
    public File byPath(String path) {
        return null;
    }

    @Override
    public List<File> byParent(String path) {
        List<File> result = new ArrayList<>();
        java.io.File[] rawFiles = new java.io.File(path).listFiles();
        if (rawFiles == null) {
            return result;
        }
        for (java.io.File rawFile : rawFiles) {
            result.add(new File(rawFile.getName(), rawFile.getAbsolutePath(), rawFile.getParentFile().getAbsolutePath(), rawFile.isDirectory()));
        }
        return result;
    }

    public List<File> byFilter(Filter filter) {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.addAll(byParent(root.getAbsolutePath()));
        while (!queue.isEmpty()) {
            File file = queue.remove();

            if (file.isDirectory()) {
                queue.addAll(byParent(file.getPath()));
            }
            if (filter.accepts(file)) {
                result.add(file);
            }
        }
        return result;
    }

    @Override
    public void clear() {
        root.delete();
    }


}
