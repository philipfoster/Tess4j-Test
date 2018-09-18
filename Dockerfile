FROM ubuntu:18.04

# Using the standard openjdk image does not work in this case.
# tesseract-ocr and ghostscript is not found in the repository.
# TODO: Move basic setup to base image and publish to docker hub for faster initial builds
RUN apt-get update && \
    apt-get install -y openjdk-11-jre-headless git

RUN apt-get install -y libtesseract-dev tesseract-ocr ghostscript

RUN git clone https://github.com/tesseract-ocr/tessdata.git

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

#COPY src/main/resources/tessdata tessdata
COPY build/libs/tess4j-test-all-1.0-SNAPSHOT.jar app.jar
COPY src/main/resources/test_letter.pdf testfile.pdf

ENV OCR_ME=testfile.pdf
ENV TESSDATA_PREFIX=tessdata/

CMD ["java", "-jar", "app.jar"]

