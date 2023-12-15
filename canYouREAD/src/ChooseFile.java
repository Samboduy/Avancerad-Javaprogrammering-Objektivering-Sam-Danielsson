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

    int secondCount;

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
                    System.out.println(keyList);
                }



                System.out.println(jsonArray.length());
                String[] item = new String[keyList.size()];
                for (int i = 0; i<jsonArray.length();i++) {
                    this.count = 0;
                    for (String key : keyList) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String keyItem = jsonObject.getString(key);
                        item[count]=keyItem;
                        //System.out.println(Arrays.toString(item));

                        count++;
                        if (count == keyLenght){
                            String[] help = new String[keyLenght];
                            secondCount=0;
                            for (String valueOF:item){
                                help[secondCount]=valueOF;
                                secondCount++;
                            }
                            wholeList.add(help);
                            System.out.println(Arrays.deepToString(help) + " HELP");
                        }
                        if (i ==0 && count ==keyList.size()){
                            /* title.add(Arrays.toString(item));
                                System.out.println(title + " TITLE");*/
                            title.addAll(Arrays.asList(item));
                            System.out.println(title + "TITLE");

                        }
                    }

                }


               // System.out.println(this.page);
                wholeList.removeFirst();
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
