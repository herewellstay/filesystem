package com.herewellstay.filesystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilesInMemory implements Files {
    private List<File> files;

    public FilesInMemory(List<File> files) {
        this.files = files;
    }

    @Override
    public void add(File file) {
        files.add(file);
    }

    @Override
    public void addAll(List<File> files) {
        files.addAll(files);
    }

    @Override
    public List<File> all() {
        return files;
    }

    @Override
    public boolean contains(File file) {
        return files.contains(file);
    }

    @Override
    public File byPath(String path) {
        for (Iterator<File> it = files.iterator(); it.hasNext(); ) {
            File file = it.next();
            if(file.getPath().equals(path));
            return file;

        }
        return null;
    }

    @Override
    public List<File> byParent(String path) {
        List<File> result=new ArrayList<>();
        for (Iterator<File> it = files.iterator(); it.hasNext(); ) {
            File file = it.next();
            if(file.getParent().equals(path)){
                result.add(file);
            }
        }
        return result;
    }

    @Override
    public List<File> byFilter(Filter filter) {
        List<File> result= new ArrayList<>();
        for (File file:files){
            if(filter.accepts(file)){
                result.add(file);
            }
        }
        return result;
    }

    @Override
    public void clear() {
        files.clear();
    }
}
