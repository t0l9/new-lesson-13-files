package quruqa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quruqa.model.Glossary;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesParsingTest {

    //Для чтения xls создаем класслоудер
    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @DisplayName("чтение пдфки")
    @Test
    void pdfParseTest() throws Exception{
        open("https://junit.org/junit5/docs/current/user-guide/");

        File downloadedPdf = $("a[href=\"junit-user-guide-5.11.3.pdf\"]").download();
        PDF content = new PDF(downloadedPdf);
        assertThat(content.author).contains("Sam Brannen");
    }

    @DisplayName("чтение exel")
    @Test
    void xlsParseTest() throws Exception{

        try (InputStream resourceAsStream = cl.getResourceAsStream("pdf/price.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            System.out.println();
            assertThat(content.excel.getSheetAt(0).getRow(0).getCell(14).getStringCellValue()).contains("10");
        }
    }

    @DisplayName("Чтение csv file")
    @Test
    void csvTest() throws Exception {
        try (
                InputStream resourceAsStream = cl.getResourceAsStream("csv/example.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(resourceAsStream))
        ) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[1]).contains("lesson");
        }
    }


    @DisplayName("JSON parse test")
    @Test
    void JsonTest() throws Exception {

        //создаем объект gson
        Gson gson = new Gson();
        try (
                InputStream resourceAsStream = cl.getResourceAsStream("json/ex.json");
                InputStreamReader reader = new InputStreamReader(resourceAsStream)
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("title")
                    .getAsString()).isEqualTo("example glossary");
        }


    }

    @DisplayName("JSON parse test with javaclasses")
    @Test
    void JsonParseTestImprooved() throws Exception {

        //создаем объект gson
        Gson gson = new Gson();
        try (
                InputStream resourceAsStream = cl.getResourceAsStream("json/ex.json");
                InputStreamReader reader = new InputStreamReader(resourceAsStream)
        ) {
            //Здесь обхект json описан через класс Glossary in the packege model DTO
            Glossary jsonObject = gson.fromJson(reader, Glossary.class);
            assertThat(jsonObject.title).isEqualTo("example glossary");
            assertThat(jsonObject.glossDiv.title).isEqualTo("S");
            assertThat(jsonObject.glossDiv.flag).isTrue();
        }


    }
}

