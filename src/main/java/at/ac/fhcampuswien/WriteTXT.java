package at.ac.fhcampuswien;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WriteTXT {

    FileOutputStream fos = null;
    File file;

    public void write(List<Article> lines) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/resources/files/originalarticles.txt"); //could go wrong -> only write in try what in really necessary!
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Article str: lines) {
            try {
                writer.write(str + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //made new constructor for downloader
    public void write(List<Article> lines, String path) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Article str: lines) {
            try {
                writer.write(str + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

