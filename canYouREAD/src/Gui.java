//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Gui {
    JPanel mainPanel = new JPanel();
    JPanel bottom = new JPanel();
    JFrame frame = new JFrame();
    DefaultTableModel model;

    Gui() {
        //skapar tabellen
        this.frame.setTitle("Table");
        this.frame.setLayout(new BorderLayout());
        // skapar en file explorer
        JFileChooser chooser = new JFileChooser("canYouREAD/src/Files");
        chooser.showOpenDialog((Component)null);
        //valda filen blir sparad
        String f = String.valueOf(chooser.getSelectedFile());
        File file = new File(f);
        new ChooseFile(file, chooser.getSelectedFile().getName());
        ChooseFile.splitter = file.getName().split("\\.");
        this.frame.setDefaultCloseOperation(3);
        this.frame.setExtendedState(6);
        this.mainPanel.setLayout(new BorderLayout());
        //om den valda filen är json så kallas TabelJson metoden.
        if (ChooseFile.getFileName().equals("json")) {
            this.TabelJson();
            //om det är csv så kallas Tabel metoden
        } else if (ChooseFile.getFileName().equals("csv")) {
            this.Tabel();
        }

        this.frame.add(this.mainPanel, "Center");
        this.frame.setVisible(true);
    }

    private void Tabel() {
        // model tar emot en 2d array som är information och header blir då själva headersarna på tabellen
        this.model = new DefaultTableModel(ChooseFile.table2DArray, ChooseFile.header);
        JTable table = new JTable(this.model);
        //sorterar tabellen
        table.setAutoCreateRowSorter(true);
        //lägger till tabellen i mainPanel
        this.mainPanel.add(new JScrollPane(table), "Center");
    }
    //Samma som ovanför
    private void TabelJson() {
        this.model = new DefaultTableModel(ChooseFile.table2DArray, ChooseFile.title.toArray());
        JTable table = new JTable(this.model);
        table.setAutoCreateRowSorter(true);
        this.mainPanel.add(new JScrollPane(table), "Center");
    }
}
