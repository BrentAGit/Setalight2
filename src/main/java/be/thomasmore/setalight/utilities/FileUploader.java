package be.thomasmore.setalight.utilities;

import be.thomasmore.setalight.models.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploader {
    @Value("${upload.images.dir}")
    private String uploadImagesDirString;
    public String fileUpload(Profile profile, MultipartFile picture) {
        String name = picture.getOriginalFilename();
        File imageFileDir = new File(uploadImagesDirString);
        if (!imageFileDir.exists()) {
            imageFileDir.mkdirs();
        }
        File imageFile = new File(uploadImagesDirString, name);
        try {
            picture.transferTo(imageFile);

            return "/" + name;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
