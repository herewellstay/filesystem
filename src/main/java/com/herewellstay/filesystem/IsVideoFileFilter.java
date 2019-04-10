package com.herewellstay.filesystem;

import com.herewellstay.text.EndsWithFilter;

public class IsVideoFileFilter  implements Filter{
    private final static String[] VIDEO_EXTENSIONS={
            ".mov", ".qt", ".wmv", ".yuv", ".rm", ".rmvb", ".asf", ".amv", ".mp4", ".m4p", ".m4v", ".mpg", ".mp2", ".mpeg",
            ".mpe", ".mpv", ".mpg", ".mpeg", ".m2v", ".m4v", ".svi", ".3gp", ".3g2", ".mxf",
            ".roq", ".nsv", ".flv", ".f4v", ".f4p", ".f4a", ".f4b"
    };
    @Override
    public boolean accepts(File file) {
        return  new EndsWithFilter(VIDEO_EXTENSIONS).accepts(file.getName());
    }
}
