package com.example.demandeachatservice.Services.DemandeAchat.Article;

import com.example.demandeachatservice.Entities.Article;
import com.example.demandeachatservice.Repository.ArticleRepository;
import com.itextpdf.text.BaseColor;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor

public class ArticlePdfExporter {

    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    public ByteArrayInputStream generatePDFReport() throws Exception {
        List<Article> articles = getAllArticles();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();
        PdfPTable table = new PdfPTable(5);
        addTableHeader(table);
        addRows(table, articles);
        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Nom", "Description", "Prix Estimé", "Quantité")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(Color.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<Article> articles) {
        for (Article article : articles) {
            table.addCell(String.valueOf(article.getIdarticle()));
            table.addCell(article.getNomarticle());
            table.addCell(article.getDescriptionarticle());
            table.addCell(String.valueOf(article.getPrixestime()));
            table.addCell(String.valueOf(article.getQuantite()));
        }
    }}
