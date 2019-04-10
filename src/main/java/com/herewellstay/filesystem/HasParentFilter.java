package com.herewellstay.filesystem;

public class HasParentFilter implements Filter {
    private String parent;

    public HasParentFilter(String parent) {
        this.parent = parent;
    }

    @Override
    public boolean accepts(File file) {
        return file.getParent().equals(parent);
    }
}
