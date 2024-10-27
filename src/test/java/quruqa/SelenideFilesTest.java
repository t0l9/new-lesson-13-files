package quruqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTest {

    @Test
    @DisplayName("Загрузка файлов со страницы")
    void selenideDownloadTest() {
        open("https://github.com/t0l9/new-lesson-1/blob/main/README.md");
        File download = $("[data-testid=\"raw-button\"]").download();

    }
}
