package quruqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SelenideFilesTest {

    //Если нет атрибута href
//    static {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//    }

    @Test
    @DisplayName("Загрузка файлов со страницы")
    void selenideDownloadTest() throws IOException {

        open("https://github.com/t0l9/new-lesson-1/blob/main/README.md");
        File download = $("[data-testid='raw-button']").download();

        //За чтение файлов отвечает инпут стрим

        //Делаем контрукцию трай и помещаем в скобки то что нам нужно закрыть после выполнения
        //В данном случаем мы поместили туда инпутстрим
        try (InputStream is = new FileInputStream(download)) {
            //Создаем массив данных
            byte[] bytes = is.readAllBytes();
            //помещаем массив байтов в стрингу
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            //делаем проверку
            assertThat(textContent).contains("первый урок ");
        }

        System.out.println();

    }

    @Test
    @DisplayName("Загрузка файлов на сайт")
    void selenideUploadFile() {
        open("https://ps.uci.edu/~franklin/doc/file_upload.html");
        $("input[type='file']").uploadFromClasspath("images/1.png");
        System.out.println();
    }
}
