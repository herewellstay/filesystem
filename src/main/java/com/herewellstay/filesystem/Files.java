package com.herewellstay.filesystem;

import java.util.List;

public interface Files {
    public void add(File file);
    public void addAll(List<File> files);
    public List<File> all() ;
    public boolean contains(File file);
    public File byPath(String path);
    public List<File> byParent(String path);
    public List<File> byFilter(Filter filter);
    public void clear();


}
