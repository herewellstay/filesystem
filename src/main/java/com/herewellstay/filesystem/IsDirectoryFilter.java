package com.herewellstay.filesystem;

public class IsDirectoryFilter implements Filter {

    public IsDirectoryFilter() {
    }

    @Override
    public boolean accepts(File file) {
        return file.isDirectory();
    }
}
