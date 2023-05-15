package com.example.demandeachatservice.Services.DemandeAchat.Comment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BadWord {


        public static final String BADWORDS_FILENAME = "badwords.txt"; // le nom du fichier contenant les badwords

        public static String filterBadWords(String text) throws IOException {
            String[] badWords = loadBadWords();
            for (String badWord : badWords) {
                text = text.replaceAll("(?i)" + badWord, "*".repeat(badWord.length()));
            }
            return text;
        }

        public static String[] loadBadWords() throws IOException {
            File file = new File("C:\\Users\\Kouki\\Desktop\\PiDev\\Backend\\PiBackendFinal\\jdid\\BridgeBuyerMicroService\\demandeachat-service\\src\\main\\resources\\badwords.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line.trim()).append("|");
            }
            reader.close();
            return builder.toString().split("\\|");
        }
}
