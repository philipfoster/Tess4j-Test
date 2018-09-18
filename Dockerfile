FROM philipfoster/tess4j

COPY tessdata/ tessdata

#COPY src/main/resources/tessdata tessdata
COPY build/libs/tess4j-test-all-1.0-SNAPSHOT.jar app.jar
COPY src/main/resources/test_letter.pdf testfile.pdf

ENV OCR_ME=testfile.pdf
ENV TESSDATA_PREFIX=tessdata/

CMD ["java", "-jar", "app.jar"]

