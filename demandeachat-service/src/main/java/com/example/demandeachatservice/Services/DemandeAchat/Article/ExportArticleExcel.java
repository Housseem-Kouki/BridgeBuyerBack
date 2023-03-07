package com.example.demandeachatservice.Services.DemandeAchat.Article;


import com.example.demandeachatservice.Entities.Article;
import com.example.demandeachatservice.Repository.ArticleRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ExportArticleExcel {
    private final ArticleRepository articleRepository;

    public ExportArticleExcel(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ByteArrayInputStream exportArticles() throws IOException {
        String[] columns = {"Id", "Nom", "Description", "Prix", "Quantité"};

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Articles");

        // Header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Data rows
        List<Article> articles = articleRepository.findAll();
        int rowNum = 1;
        for (Article article : articles) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(article.getIdarticle());
            row.createCell(1).setCellValue(article.getNomarticle());
            row.createCell(2).setCellValue(article.getDescriptionarticle());
            row.createCell(3).setCellValue(article.getPrixestime());
            row.createCell(4).setCellValue(article.getQuantite());
        }

        // Write to stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        out.close();

        return in;
    }

}
