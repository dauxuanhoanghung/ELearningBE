package com.dxhh.elearning.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface YoutubeService {
    String uploadFile(File file, String title, String description) throws IOException;
}
