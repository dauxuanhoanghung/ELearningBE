package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.services.YoutubeService;
import com.google.api.services.youtube.model.VideoSnippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.YouTube;

import java.io.*;
import java.io.File;

@Service
@Lazy
public class YoutubeServiceImpl implements YoutubeService {

    private final YouTube youtubeService;

    @Autowired
    public YoutubeServiceImpl(YouTube youTubeService) {
        this.youtubeService = youTubeService;
    }

    @Override
    public String uploadFile(File file, String title, String description) {
        // TODO: For this request to work, you must replace "YOUR_FILE"
        //       with a pointer to the actual file you are uploading.
        //       The maximum file size for this operation is 274877906944.
        Video video = new Video();
        VideoSnippet snippet = new VideoSnippet();
        snippet.setCategoryId("22");
        snippet.setDescription(description);
        snippet.setTitle(title);
        video.setSnippet(snippet);
        try {
            InputStreamContent mediaContent = new InputStreamContent("application/octet-stream",
                    new BufferedInputStream(new FileInputStream(file)));
            mediaContent.setLength(file.length());

            // Define and execute the API request
            YouTube.Videos.Insert request = youtubeService.videos()
                    .insert("snippet,status", video, mediaContent);
            Video response = request.execute();
            file.delete();
            return String.format("https://www.youtube.com/watch?v=%s", response.getId());
        } catch (IOException e) {
            return null;
        }
    }
}
