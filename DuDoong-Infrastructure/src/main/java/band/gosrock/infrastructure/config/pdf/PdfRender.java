package band.gosrock.infrastructure.config.pdf;


import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
@RequiredArgsConstructor
@Slf4j
public class PdfRender {

    public ByteArrayOutputStream generatePdfFromHtml(String html)
            throws DocumentException, IOException {
        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //        OutputStream outputStream = new FileOutputStream(outputFolder);
        log.info(outputFolder);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver()
                .addFont(
                        new ClassPathResource("/templates/NanumBarunGothic.ttf")
                                .getURL()
                                .toString(),
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED);
        renderer.setDocumentFromString(html);
        renderer.layout();

        renderer.createPDF(outputStream);

        outputStream.close();
        return outputStream;
    }
}