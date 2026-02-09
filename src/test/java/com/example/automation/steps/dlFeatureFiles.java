package com.example.automation.steps;

import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.openqa.selenium.io.Zip.unzipFile;

public class dlFeatureFiles {

    importResultsToXray importResultsToXray = new importResultsToXray();
    String testKeys = "POEI2-713";

    public static void downloadFeatureFiles(String token, String testKeys) {
        try {
            URL url = new URL("https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=" + testKeys);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Content-Type", "application/json");

            InputStream inputStream = conn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream("features.zip");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            UnzipFile("./features.zip");
        } catch (Exception e) {
            System.err.println("Erreur lors du téléchargement des fichiers feature: " + e.getMessage());
        }
    }


    // source : https://www.baeldung.com/java-compress-and-uncompress

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }


    public static void UnzipFile(String output) throws IOException {
            File destDir = new File("src/test/resources/features");

            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(output));
            ZipEntry zipEntry = zis.getNextEntry();
                while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
            }
            zipEntry = zis.getNextEntry();
        }

            zis.closeEntry();
            zis.close();
    }


    @Test
    public void xport(){
        downloadFeatureFiles(importResultsToXray.token,testKeys);

        // je veux unzip et le mettre dans un dossier cible
    }


}
