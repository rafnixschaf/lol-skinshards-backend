package tech.sascha.skinshard_virtualizer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LockFileService {

    private final Path lockFilePath;

    public LockFileService(@Value("${lol.lockfile-path}") String lockfilePath) {
        this.lockFilePath = Path.of(lockfilePath);
    }

    public LcuAuth readLockFile() {
        try {
            String line = Files.readString(lockFilePath).trim();
            String[] parts = line.split(":");

            String port = parts[2];
            String password = parts[3];
            String protocol = parts[4];

            return new LcuAuth("riot", password, port, protocol);
        }catch (IOException e) {
            throw new RuntimeException("Could not read lockfile at " + lockFilePath, e);
        }
    }


    public record LcuAuth(String username, String password, String port, String protocol) {}
}
