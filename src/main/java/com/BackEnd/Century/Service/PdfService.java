package com.BackEnd.Century.Service;

import com.BackEnd.Century.Model.Categoria;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class PdfService {

    public void generarReporteDeCategorias(HttpServletResponse response, List<Categoria> categorias) throws IOException {
        //config
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        //fuente
        PdfFont timesNewRoman = PdfFontFactory.createFont("Times-Roman");
        document.setFont(timesNewRoman);

        //logo
        String logoPath = "src/main/resources/static/img/5.png";
        ImageData imageData = ImageDataFactory.create(logoPath);
        Image logo = new Image(imageData).scaleToFit(100, 100).setFixedPosition(50, 750);
        document.add(logo);

        //Encabezado
        document.add(new Paragraph("Reporte de Categorías")
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(50));

        //tabla
        float[] columnWidths = {50F, 150F}; 
        Table table = new Table(UnitValue.createPercentArray(columnWidths))
                .useAllAvailableWidth()
                .setTextAlignment(TextAlignment.CENTER);

        //colores
        Color headerColor = new DeviceRgb(200, 76, 233); 
        Color headerFontColor = new DeviceRgb(250, 250, 250); 

        //Encabezado de tabla
        table.addHeaderCell(new Paragraph("ID").setBackgroundColor(headerColor).setFontColor(headerFontColor).setTextAlignment(TextAlignment.CENTER));
        table.addHeaderCell(new Paragraph("Nombre").setBackgroundColor(headerColor).setFontColor(headerFontColor).setTextAlignment(TextAlignment.CENTER));

        //Datos
        for (Categoria categoria : categorias) {
            table.addCell(new Paragraph(String.valueOf(categoria.getCategoriaId())).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Paragraph(categoria.getName()).setTextAlignment(TextAlignment.CENTER));
        }

        
        document.add(table);

        document.close();
    }
}
 