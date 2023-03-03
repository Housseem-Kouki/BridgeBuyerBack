package com.example.demandeachatservice.Services.DemandeAchat.Article;


import com.example.demandeachatservice.Entities.Article;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ExportArticleExcel {
    public ByteArrayInputStream articleExcelFile(List<Article> articles) {
        String[] columns = {"Idarticle", "Nomarticle" , "Descriptionarticle","Prixestime"};
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper creationHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("articles");
            sheet.autoSizeColumn(columns.length);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(headerFont);

            ////Row for header
            Row headerRow = sheet.createRow(0);

            /////// header
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(cellStyle);
            }
            CellStyle cellStyle1 = workbook.createCellStyle();
            cellStyle1.setDataFormat(creationHelper.createDataFormat().getFormat("#"));

            int rowIndex = 1;
            for (Article article : articles) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(article.getIdarticle());
                row.createCell(1).setCellValue(article.getNomarticle());
                row.createCell(2).setCellValue(article.getDescriptionarticle());
                row.createCell(3).setCellValue(article.getPrixestime());


            }
            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
