package quruqa;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesParsingTest {

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
        open("https://junit.org/junit5/docs/current/user-guide/");

        File downloadedPdf = $("a[href=\"junit-user-guide-5.11.3.pdf\"]").download();
        PDF content = new PDF(downloadedPdf);
        assertThat(content.author).contains("Sam Brannen");
    }
}
