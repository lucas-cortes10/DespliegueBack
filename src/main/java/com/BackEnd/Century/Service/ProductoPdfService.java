package com.BackEnd.Century.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BackEnd.Century.Model.Productos;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ProductoPdfService {
    
    public void generarReporteDeProveedores(HttpServletResponse response, List<Productos> producto) throws IOException {
        // Configuración
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Fuente
        PdfFont timesNewRoman = PdfFontFactory.createFont("Times-Roman");
        document.setFont(timesNewRoman);

        // Logo
        String logoPath = "src/main/resources/static/img/5.png";
        ImageData imageData = ImageDataFactory.create(logoPath);
        Image logo = new Image(imageData).scaleToFit(100, 100).setFixedPosition(50, 750);
        document.add(logo);

        // Encabezado
        document.add(new Paragraph("Reporte de Productos")
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(50));

        // Tabla con anchos ajustados
        float[] columnWidths = {1, 2, 2, 3, 1, 1};
        Table table = new Table(UnitValue.createPercentArray(columnWidths))
                .useAllAvailableWidth()
                .setTextAlignment(TextAlignment.CENTER);

        // Colores
        Color headerColor = new DeviceRgb(200, 76, 233);
        Color headerFontColor = new DeviceRgb(250, 250, 250);

        // Encabezados de tabla
        String[] headers = {"ID", "Nombre", "Imagen", "Descripción", "Stock", "Precio"};
        for (String header : headers) {
            table.addHeaderCell(new Cell()
                    .add(new Paragraph(header))
                    .setBackgroundColor(headerColor)
                    .setFontColor(headerFontColor)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        // Datos
        for (Productos productos : producto) {
            // ID
            table.addCell(new Cell()
                    .add(new Paragraph(String.valueOf(productos.getId())))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
            
            // Nombre
            table.addCell(new Cell()
                    .add(new Paragraph(productos.getName()))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
            
            // Imagen
            try {
                ImageData productImageData = ImageDataFactory.create(productos.getImagen());
                Image productImage = new Image(productImageData)
                        .setAutoScale(true)
                        .setWidth(UnitValue.createPercentValue(80)); // Ajusta el ancho al 80% de la celda
                table.addCell(new Cell()
                        .add(productImage)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setPadding(5));
            } catch (Exception e) {
                table.addCell(new Cell()
                        .add(new Paragraph("No disponible"))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE));
            }
            
            // Descripción
            table.addCell(new Cell()
                    .add(new Paragraph(productos.getDescripcion()))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
            
            // Stock
            table.addCell(new Cell()
                    .add(new Paragraph(String.valueOf(productos.getStock())))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
            
            // Precio
            table.addCell(new Cell()
                    .add(new Paragraph(String.valueOf(productos.getPrecio())))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        document.add(table);
        document.close();
    }
}