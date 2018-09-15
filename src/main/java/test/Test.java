package test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class Test {

    public static void main(String[] args) {
        System.out.printf("env = %s\n", System.getenv("TESSDATA_PREFIX"));

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        @SuppressWarnings("ConstantConditions")
        File pdfFile = new  File(classLoader.getResource("test_letter-page-001.jpg").getFile());
//        File pdfFile = new File(classLoader.getResource("test_letter.pdf").getFile());

        ITesseract instance = new Tesseract();

        instance.setDatapath(System.getenv("TESSDATA_PREFIX") );
        instance.setLanguage("eng");


        try {
            Instant start = Instant.now();
            String result = instance.doOCR(pdfFile);
            Instant end = Instant.now();
            System.out.printf("Tesseract took %s ms to parse the file\n", Duration.between(start, end).toString());


            System.out.println(result);

        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

}
