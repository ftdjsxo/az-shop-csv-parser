package com.example.azshopcsvparser.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PDFTableGenerator {

    private static final String BASE_PATH = "src/main/resources/";
    public static final String INPUT = BASE_PATH + "pdf_in.pdf";
    public static final String DEST = BASE_PATH + "pdf_table.pdf";
    public static final String FONT = BASE_PATH + "zrnic.ttf";

    public static void main(String[] args) throws IOException {
        PDDocument document = PDDocument.load(new File(INPUT));
        //PDPage page = new PDPage(PDRectangle.A4);
        //document.addPage(page);
        PDPage page = document.getPage(0);

        PDType0Font font = PDType0Font.load(document, new File(FONT));

        float[] columnWidths = {150, 50, 50, 50, 100}; // Personalizza le larghezze delle colonne qui
        float marginTop = 450;
        float startX = (PDRectangle.A4.getWidth() - (sum(columnWidths) + (columnWidths.length - 1) * 2)) / 2;

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
            contentStream.setFont(font, 12);

            String[] headers = {"225/40 R19 - 255/35 R19", "Quantità", "Prezzo", "Subtotale", "Note"};
            String[][] rows = {
                    {"Modello A", "5", "100", "500", "Nessuna"},
                    {"Modello B", "3", "150", "450", "Sconto 10%"}
            };

            drawTable(contentStream, font, startX, marginTop, columnWidths, headers, rows);
        }

        document.save(new File(DEST));
        document.close();
    }

    private static void drawTable(PDPageContentStream contentStream, PDType0Font font, float x, float y, float[] columnWidths,
                                  String[] headers, String[][] rows) throws IOException {
        float tableHeight = 20 + 20 * rows.length;
        float nextX = x;

        // Draw headers
        contentStream.setLineWidth(1);
        contentStream.setFont(font, 10);
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.setStrokingColor(Color.BLACK);
        for (int i = 0; i < headers.length; i++) {
            float textWidth = font.getStringWidth(headers[i]) / 1000 * 10;
            float centeredX = nextX + (columnWidths[i] - textWidth) / 2;
            contentStream.addRect(nextX, y, columnWidths[i], 20);
            contentStream.fill();
            contentStream.setNonStrokingColor(Color.WHITE);
            contentStream.beginText();
            contentStream.newLineAtOffset(centeredX, y + 6);
            contentStream.showText(headers[i]);
            contentStream.endText();
            contentStream.setNonStrokingColor(Color.BLACK);
            nextX += columnWidths[i] + 0;
        }

        // Draw column separators
        contentStream.setStrokingColor(Color.BLACK);
        contentStream.setLineWidth(0.5f);
        float nextSeparatorX = x + columnWidths[0];
        for (int i = 1; i < headers.length; i++) {
            contentStream.moveTo(nextSeparatorX, y);
            contentStream.lineTo(nextSeparatorX, y - tableHeight +20);
            contentStream.stroke();
            nextSeparatorX += columnWidths[i];
        }

        // Draw rows
        float nextY = y - 20;
        contentStream.setFont(font, 10);
        for (String[] row : rows) {
            nextX = x;
            for (int i = 0; i < row.length; i++) {
                contentStream.saveGraphicsState();
                contentStream.addRect(nextX, nextY, columnWidths[i], 20);
                contentStream.clip();

                // Calculate text width and adjust X position for centered text
                float textWidth = font.getStringWidth(row[i]) / 1000 * 10;
                float centeredX = nextX + (columnWidths[i] - textWidth) / 2;

                contentStream.beginText();
                if (i == 0) {
                    contentStream.newLineAtOffset(nextX + 4, nextY + 4);
                }else {
                    contentStream.newLineAtOffset(centeredX, nextY + 4);
                }
                contentStream.showText(row[i]);
                contentStream.endText();
                contentStream.restoreGraphicsState();

                nextX += columnWidths[i];
            }
            nextY -= 20;
        }

        // Draw outer border
        contentStream.setLineWidth(1);
        contentStream.addRect(x, y - 20 * rows.length, sum(columnWidths), 20 * (rows.length + 1));
        contentStream.stroke();
    }

    private static float sum(float[] array) {
        float sum = 0;
        for (float v : array) {
            sum += v;
        }
        return sum;
    }
}
