package dasturlash.uz.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachService {
    public Object uploadAttach(MultipartFile file) {
        File folder = new File("attaches");
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("attaches/" + file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getName();
        } catch (IOException e) {
            throw new IllegalArgumentException("File is not found");
        }
    }
}
