package dasturlash.uz.service;

import dasturlash.uz.entity.Attach;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Service
public class AttachService {

    @Value("${attache.folder}")
    private String attacheFolder;

    @Autowired
    private AttachRepository attachRepository;

    public boolean upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppBadException("File not found");
        }

        try {
            String pathFolder = getYmDString();
            String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));

            // 1-avval entity yarating va saqlang -> id avtomatik generatsiya bo'ladi
            Attach entity = new Attach();
            entity.setPath(pathFolder);
            entity.setSize(file.getSize());
            entity.setOriginalName(file.getOriginalFilename());
            entity.setExtension(extension);
            attachRepository.save(entity); // endi id == null -> persist() chaqiriladi, to'g'ri ishlaydi

            String key = entity.getId(); // Hibernate generatsiya qilgan UUID

            // 2-fayl nomini shu ID asosida saqlang
            File folder = new File(attacheFolder + "/" + pathFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(attacheFolder + "/" + pathFolder + "/" + key + "." + extension);
            Files.write(path, bytes);

            return true;
        } catch (IOException e) {
            throw new AppBadException("Upload went wrong");
        }
    }


    private String getYmDString() {
        LocalDate now = LocalDate.now();
        return now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
    }

    private String getExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "unknown"; // yoki exception tashlash mumkin
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
