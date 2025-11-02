package tech.sascha.skinshard_virtualizer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageDownloadService {


    private final Path imageDir;

    public ImageDownloadService(@Value("${lol.image-dir}") String imageDir) {
        this.imageDir = Path.of(imageDir);
    }

    public String download(String url, String fileName) {
        try {
            if(!Files.exists(imageDir)) {
                Files.createDirectories(imageDir);
            }
            Path target = imageDir.resolve(fileName);

            if(Files.exists(target)) return target.toString();

            try(InputStream in = new URL(url).openStream()) {
                Files.copy(in, target);
            }
            return target.toString();
        } catch (Exception e) {
            System.err.println("Could not download image from " + url + ": " + e.getMessage());
            return null;
        }
    }
}
