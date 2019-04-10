package com.herewellstay.filesystem;

public class NoFilter implements Filter {

    @Override
    public boolean accepts(File file) {
        return true;
    }
}
