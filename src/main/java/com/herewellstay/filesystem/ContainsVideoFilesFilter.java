package com.herewellstay.filesystem;


public class ContainsVideoFilesFilter implements Filter{
    private Files files;

    public ContainsVideoFilesFilter(Files files) {
        this.files = files;
    }

    @Override
    public boolean accepts(File file) {
        for (File child:files.byParent(file.getPath())) {
            if(!new IsDirectoryFilter().accepts(child) && new IsVideoFileFilter().accepts(child)){
                return true;
            }
        }
        return false;

    }
}
