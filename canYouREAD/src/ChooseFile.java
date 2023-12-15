//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JTable;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChooseFile {
    static String[] splitter;
    String page = " ";
    Scanner scan;
    private int row;
    private int keyLenght = 1;
    ArrayList<String> keyList = new ArrayList();
    private int column;
    private static String fileName;
    static String[] header;
    private String[] jsonHeader;
    ArrayList<String[]> wholeList = new ArrayList();
    static ArrayList<String> title = new ArrayList();
    static String[][] table2DArray;
    static String json;
    int count;
    JTable table;
    int d2Count = 0;

    public static String[] getSplitter() {
        return splitter;
    }

    public static String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        ChooseFile.fileName = fileName;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getKeyLenght() {
        return this.keyLenght;
    }

    public void setKeyLenght(int keyLenght) {
        this.keyLenght = keyLenght;
    }

    public String[] getJsonHeader() {
        return this.jsonHeader;
    }

    public void setJsonHeader(String[] jsonHeader) {
        this.jsonHeader = jsonHeader;
    }

    ChooseFile(File file, String fileName) {
        splitter = fileName.split("\\.");
        this.setFileName(splitter[1]);
        if (getFileName().equals("json")) {
            try {
                json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                JSONArray jsonArray = new JSONArray(json);

                String value;
                for(int i = 0; i < this.getKeyLenght(); ++i) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONArray keys = jsonObject.names();
                    this.setKeyLenght(keys.length());
                    value = keys.getString(i);
                    jsonObject.getString(value);
                    this.keyList.add(value);
                }

                for(Iterator var12 = this.keyList.iterator(); var12.hasNext(); this.count = 0) {
                    String k = (String)var12.next();

                    for(this.count = 0; this.count < jsonArray.length(); ++this.count) {
                        JSONObject jsonObject = jsonArray.getJSONObject(this.count);

                        System.out.println(jsonObject);
                        value = jsonObject.get(k).toString();
                        if (this.count == 0) {
                            title.add(value);
                        }

                        String[] item = new String[8];
                        item[0]= jsonObject.getString("A");
                        item[1]= jsonObject.getString("B");
                        item[2]= jsonObject.getString("C");
                        item[3]= jsonObject.getString("D");
                        item[4]= jsonObject.getString("E");
                        item[5]= jsonObject.getString("F");
                        item[6]= jsonObject.getString("G");
                        item[7]= jsonObject.getString("H");

                   /*     if (this.count > 0) {
                            item[0] = value;
                            this.wholeList.add(item);
                        }*/
                        for (String[] as: wholeList ) {
                            System.out.println(Arrays.deepToString(as));
                        }
                      //  System.out.println(wholeList);
                    }
                    wholeList.add(item);
                }

               // System.out.println(this.page);
                table2DArray = (String[][])this.wholeList.toArray(String[][]::new);
               // System.out.println(Arrays.deepToString(table2DArray));
            } catch (Exception var10) {
                System.out.println("Error " + var10.getMessage());
            }
        } else if (getFileName().equals("csv")) {
            try {
                this.scan = new Scanner(file);
                String line = this.scan.nextLine();
                header = line.split(",");
              /* System.out.println(Arrays.deepToString(header));
                System.out.println(this.getRow());*/

                while(this.scan.hasNext()) {
                    String newLine = this.scan.nextLine();
                    splitter = newLine.split(",", 0);
                    this.wholeList.add(splitter);
                }

                this.scan.close();
               // System.out.println(this.wholeList.size());
                table2DArray = (String[][])this.wholeList.toArray((x$0) -> {
                    return new String[x$0][];
                });
               // System.out.println(Arrays.deepToString(table2DArray));
            } catch (Exception var9) {
                System.out.println("Error " + var9.getMessage());
            }
        }

    }
}
