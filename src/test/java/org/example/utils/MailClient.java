package org.example.utils;

public class MailClient {
    public static void main(String[] args) {
//        MailUtils.send(AppConstants.EMAIL_ACC,"This is spam!", "This is spam!");

//        MailUtils.send(AppConstants.EMAIL_ACC, "File plus HTMl Al Tr", HTML_TABLE, "D:/test/example.txt");

        int fontHeaderSize = 10;
        HTMLTableBuilder table = new  HTMLTableBuilder("Cars", true, 4, fontHeaderSize, fontHeaderSize, fontHeaderSize);
        table.addTableHeader("MODEL", "V ENGINE", "COLOR", "PRICE");
        table.addRowValues("AUDI Q5", "3.5", "WHITE", "60000");
        table.addRowValues("AUDI Q3", "3.5", "WHITE", "40000");
        table.addRowValues("AUDI Q7", "3.5", "WHITE", "80000");
        table.addRowValues("AUDI Q2", "3.5", "WHITE", "30000");
        table.addRowValues("AUDI Q1", "3.5", "WHITE", "50000");
        table.addRowValues("AUDI Q4", "3.5", "WHITE", "90000");

        MailUtils.send(AppConstants.EMAIL_ACC, "CARS TABLE IS HERE", table.toString(), "D:/test/example.txt");
    }


    private static String HTML_TABLE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table {\n" +
            "  font-family: arial, sans-serif;\n" +
            "  border-collapse: collapse;\n" +
            "  width: 100%;\n" +
            "}\n" +
            "\n" +
            "td, th {\n" +
            "  border: 1px solid #dddddd;\n" +
            "  text-align: left;\n" +
            "  padding: 8px;\n" +
            "}\n" +
            "\n" +
            "tr:nth-child(even) {\n" +
            "  background-color: #dddddd;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<h2>HTML Table</h2>\n" +
            "\n" +
            "<table>\n" +
            "  <tr>\n" +
            "    <th>Company</th>\n" +
            "    <th>Contact</th>\n" +
            "    <th>Country</th>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td>Alfreds Futterkiste</td>\n" +
            "    <td>Maria Anders</td>\n" +
            "    <td>Germany</td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td>Centro comercial Moctezuma</td>\n" +
            "    <td>Francisco Chang</td>\n" +
            "    <td>Mexico</td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td>Ernst Handel</td>\n" +
            "    <td>Roland Mendel</td>\n" +
            "    <td>Austria</td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td>Island Trading</td>\n" +
            "    <td>Helen Bennett</td>\n" +
            "    <td>UK</td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td>Laughing Bacchus Winecellars</td>\n" +
            "    <td>Yoshi Tannamuri</td>\n" +
            "    <td>Canada</td>\n" +
            "  </tr>\n" +
            "  <tr>\n" +
            "    <td>Magazzini Alimentari Riuniti</td>\n" +
            "    <td>Giovanni Rovelli</td>\n" +
            "    <td>Italy</td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n" +
            "\n";

}
