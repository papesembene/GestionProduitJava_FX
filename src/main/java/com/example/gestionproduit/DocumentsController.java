package com.example.gestionproduit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DocumentsController {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @FXML
    void btnexcel(ActionEvent event) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Product Data");
            int rownum = 0;
            while (resultSet.next()) {
                Row row = sheet.createRow(rownum++);
                row.createCell(0).setCellValue(resultSet.getInt("id"));
                row.createCell(1).setCellValue(resultSet.getString("name"));
                row.createCell(2).setCellValue(resultSet.getInt("quantity"));
                row.createCell(3).setCellValue(resultSet.getDouble("price"));
                row.createCell(4).setCellValue(resultSet.getInt("category_id"));
            }
            FileOutputStream fileOut = new FileOutputStream("products.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnpdf(ActionEvent event) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("products.pdf"));
            document.open();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
            while (resultSet.next()) {
                document.add(new Paragraph("ID: " + resultSet.getInt("id")));
                document.add(new Paragraph("Name: " + resultSet.getString("name")));
                document.add(new Paragraph("Quantity: " + resultSet.getInt("quantity")));
                document.add(new Paragraph("Price: " + resultSet.getDouble("price")));
                document.add(new Paragraph("Category ID: " + resultSet.getInt("category_id")));
                document.add(new Paragraph("\n"));
            }
            document.close();
        } catch (SQLException | IOException | com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        }
    }
}

