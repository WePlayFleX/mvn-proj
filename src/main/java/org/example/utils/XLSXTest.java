package org.example.utils;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.example.model.Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class XLSXTest {

    public static void main(String[] args) {

        String path = "D:\\WPF\\less3\\mvn-proj\\src\\main\\resources\\file_example_XLS_5000.xls";
        List<Client> clients = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(path)) {
            HSSFWorkbook workbook = new HSSFWorkbook(fis);

            for (Sheet sheet : workbook) {
                System.out.println("Processing tab: " + sheet.getSheetName());
                for (Row row :sheet){
                    if(row.getRowNum() == 0){
                        continue; // skip headers
                    }
                    System.out.println("Processing row #" + row.getRowNum());
                    Client client = new Client();
                    for (Cell cell : row) {
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex){
                            case 1: {
                                client.setFirstName(cell.getStringCellValue());
                                break;
                            }
                            case 2: {
                                client.setLastName(cell.getStringCellValue());
                                break;
                            }
                            case 3: {
                                client.setGender(cell.getStringCellValue());
                                break;
                            }
                            case 4: {
                                client.setCountry(cell.getStringCellValue());
                                break;
                            }

                            case 5: {
                                client.setAge((int)cell.getNumericCellValue());
                                break;
                            }

                            case 6: {
                                client.setDate(cell.getStringCellValue());
                                break;
                            }
                            case 7: {
                                client.setId((int)cell.getNumericCellValue());
                                break;
                            }

                        }
                        // PRINT
                        if(cell.getCellType() == CellType.NUMERIC){
                            double d = cell.getNumericCellValue();
                            System.out.print(d + " ");
                        } else if(cell.getCellType() == CellType.STRING) {
                            String val = cell.getStringCellValue();
                            System.out.print(val + " ");
                        }
                        clients.add(client); // save client
                    }
                    System.out.println();

                }
            }




        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // HTML TABLE
        HTMLTableBuilder htb = new HTMLTableBuilder("CLIENTS", true, 7, 10, 10, 10);
        htb.addTableHeader("First Name", "Last Name", "Gender", "Country", "Age", "Date", "Id");

        for (Client client : clients) {
            htb.addRowValues(client.getFirstName(), client.getLastName(), client.getGender(), client.getCountry(), client.getAge() + "", client.getDate(), client.getId()+ "");
        }

        MailUtils.send(AppConstants.EMAIL_ACC, "CLIENTS", htb.build(), "D:/test/example.txt");
    }
}