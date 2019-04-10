package com.herewellstay.filesystem;

public class AndFilter implements Filter {
    private Filter left;
    private Filter right;

    public AndFilter(Filter left, Filter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean accepts(File file) {
        return left.accepts(file)&&right.accepts(file);
    }
}
