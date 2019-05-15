package me.kamilkorzeniewski.conference.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Service
public class EmailService {

    @Value("${app.email.file}")
    private String fileName;

    private FileOutputStream getEmailFileOutputStream() throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        return new FileOutputStream(file, true);
    }

    public void saveEmail(Email email) {
        try (OutputStreamWriter osw = new OutputStreamWriter(getEmailFileOutputStream())) {
            String content = email.toString() + "\n";
            osw.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
