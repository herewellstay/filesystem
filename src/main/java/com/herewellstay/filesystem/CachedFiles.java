package com.herewellstay.filesystem;

import java.util.List;

public class CachedFiles implements Files {
    private final Files cache;
    private final Files origin;

    public CachedFiles(Files origin, Files cache) {
        this.cache = cache;
        this.origin = origin;
    }

    @Override
    public void add(File file) {
        cache.add(file);
    }

    @Override
    public void addAll(List<File> files) {
        cache.addAll(files);
    }

    @Override
    public List<File> all() {
        List<File> result = cache.all();
        if(result!=null && !result.isEmpty()){
            return result;
        }
        result=origin.all();
        cache.addAll(result);
        return result;
    }

    @Override
    public boolean contains(File file) {
        return cache.contains(file);
    }

    @Override
    public File byPath(String path) {
        File result = cache.byPath(path);
        if(result!=null){
            return result;
        }
        result=origin.byPath(path);
        cache.add(result);
        return result;
    }

    @Override
    public List<File> byParent(String path) {
        List<File> result = cache.byParent(path);
        if(result!=null && !result.isEmpty()){
            return result;
        }
        result=origin.byParent(path);
        cache.addAll(result);
        return result;
    }

    @Override
    public List<File> byFilter(Filter filter) {
        List<File> result = cache.byFilter(filter);
        if(result!=null && !result.isEmpty()){
            return result;
        }
        result=origin.byFilter(filter);
        cache.addAll(result);
        return result;
    }

    @Override
    public void clear() {
        cache.clear();
    }


}
