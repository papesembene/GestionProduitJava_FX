package com.example.gestionproduit;

import com.example.gestionproduit.model.Db;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
public class DocumentsController implements Initializable {

     Connection connection = null;

    @FXML
    void btnexcel(ActionEvent event) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Product Data");

            // Créer une ligne pour les en-têtes
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Name", "Quantity", "Price", "Category ID"};
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rownum = 1;
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

            // Afficher un message de succès
            showAlert("Exportation Excel réussie !");
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
            showAlert("Exportation PDF réussie !");
        } catch (SQLException | IOException | com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Db db = new Db();
        connection = db.getConnection();
    }
}

