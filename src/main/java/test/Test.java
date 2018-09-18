package test;

import net.sourceforge.tess4j.Tesseract;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;

public class Test {

    public Test() {
        System.out.printf("env = %s\n", System.getenv("TESSDATA_PREFIX"));
        System.out.printf("file = %s\n", System.getenv("OCR_ME"));

        @SuppressWarnings("ConstantConditions")
        File pdfFile = null;
        try {
            pdfFile = new File(System.getenv("OCR_ME"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            assert pdfFile != null;
            System.out.printf("exists = %b, size = %d KB, path = %s\n", pdfFile.exists(), pdfFile.length()/1024,  pdfFile.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tesseract instance = new Tesseract();

        instance.setDatapath(System.getenv("TESSDATA_PREFIX") );
        instance.setLanguage("eng");

        try {
            Instant start = Instant.now();
            String result = instance.doOCR(pdfFile);
            Instant end = Instant.now();
            System.out.printf("Tesseract took %s ms to parse the file\n", Duration.between(start, end).toString());

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        new Test();
    }

}
