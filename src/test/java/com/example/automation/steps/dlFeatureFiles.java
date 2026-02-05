package com.example.automation.steps;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class dlFeatureFiles {

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MDEyMToyNTFlNzRkOC05M2E4LTQyNWItYTk3NC02NTBiMjg3YTI0NmQiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTc3MDI4MjkwMCwiZXhwIjoxNzcwMzY5MzAwLCJhdWQiOiJFNkRGMEUxQjJDRDM0RjdFQUE3Q0ZBQUMwNjJFOThEQyIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IkU2REYwRTFCMkNEMzRGN0VBQTdDRkFBQzA2MkU5OERDIn0.ecoqzLO5VQuaNovCrU4LCKKMF1lark97KyOfcelnHBo";
    String testKeys = "POEI2-713"
    public static void downloadFeatureFiles(String token, String testKeys) {
        try {
            URL url = new URL("https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=" + testKeys);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Content-Type", "application/json");

            InputStream inputStream = conn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream("/target/features.zip");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.err.println("Erreur lors du téléchargement des fichiers feature: " + e.getMessage());
        }
    }

    @Test
    public void xport(){
        downloadFeatureFiles(token, );
    }
}
