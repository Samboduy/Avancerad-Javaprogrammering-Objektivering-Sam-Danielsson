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
        //splittar fram filnamnet
        splitter = fileName.split("\\.");
        this.setFileName(splitter[1]);
        //om filnamnet är json läser den in var filen är och gör den till en jsonArray
        if (getFileName().equals("json")) {
            try {
                json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                JSONArray jsonArray = new JSONArray(json);

                String value;
                //Tar fram nycklarna så att man kan få fram värdena i filen
                for(int i = 0; i < this.getKeyLenght(); ++i) {
                    // kollar i första delen av arrayn och tar fram JSOnobject
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //keys Array sparar alla nycklar
                    JSONArray keys = jsonObject.names();
                    this.setKeyLenght(keys.length());
                    //value är en nyckel i taget som sen blir sparad i keylist
                    value = keys.getString(i);
                    this.keyList.add(value);
                }



                System.out.println(jsonArray.length());
                String[] item = new String[keyList.size()];
                for (int i = 0; i<jsonArray.length();i++) {
                    this.count = 0;

                    for (String key : keyList) {
                        // kollar i första delen av arrayn och tar fram JSOnobject
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //tar fram ett value ur jsonobject och sparar det som en string
                        String keyItem = jsonObject.getString(key);
                        //sparar keyitem i item
                        item[count]=keyItem;
                        count++;
                        /*när count blir lika  med int keyLenght så görs en ny string array oneValue med samma storlek
                         som keyLenght*/
                        if (count == keyLenght){
                            String[] oneValue = new String[keyLenght];
                            secondCount=0;
                            for (String valueOF:item){
                                //sparar varje värde item Arrayn har i sig i oneValue
                                oneValue[secondCount]=valueOF;
                                secondCount++;
                            };
                            //sparar allt oneValue hade i sig
                            wholeList.add(oneValue);
                        }
                        //sparar titlarna en gång i en arrayList
                        if (i ==0 && count ==keyList.size()){

                            title.addAll(Arrays.asList(item));
                        }
                    }

                }


                //tar bort första eftersom man inte vill ha titlarna i tabellen
                wholeList.removeFirst();
                //gör om arraylistan till en 2dArray med all information kvar
                table2DArray = (String[][])this.wholeList.toArray(String[][]::new);

            } catch (Exception var10) {
                System.out.println("Error " + var10.getMessage());
            }
        } else if (getFileName().equals("csv")) {
            try {
                // skappar en scanner som går igenom filen man har valt
                this.scan = new Scanner(file);
                //väljer nästa del av filen
                String line = this.scan.nextLine();
                // sparar titlen i en string Array
                header = line.split(",");
                //scannar igenom hela filen och splittar ut all information som ska sparas i splitter string Array
                while(this.scan.hasNext()) {
                    String newLine = this.scan.nextLine();
                    splitter = newLine.split(",", 0);
                    //arraylistan sparar string Arrayn splitter
                    this.wholeList.add(splitter);
                }
                //stänger scanner
                this.scan.close();
                //gör om arraylistan till en 2dArray med all information kvar
                table2DArray = (String[][])this.wholeList.toArray((x$0) -> {
                    return new String[x$0][];
                });
            } catch (Exception var9) {
                System.out.println("Error " + var9.getMessage());
            }
        }

    }
}
