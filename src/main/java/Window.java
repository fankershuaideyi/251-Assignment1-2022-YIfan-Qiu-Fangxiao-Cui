import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.print.PrintService;
import java.awt.print.PrinterJob;

import org.apache.pdfbox.pdmodel.PDDocument;


import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class Window extends JFrame {
    private static int width_screen = RunHere.width;
    private static int height_screen = RunHere.height;
    public static JTextArea workArea;
    private JScrollPane scrollPane;
    private JFrame jf = new JFrame();
    public static File file;
    private FileDialog saveDia;



    Window() {

        init(width_screen, height_screen);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    void init(int w, int h) {
        //set main table
        jf.setTitle("Text Editor");
        jf.setBounds((w - 500) / 2, (h - 700) / 2, 500, 700);

        width_screen += 200;
        height_screen += 100;

        //Initialize the tab
        workArea = new JTextArea();
        scrollPane = new JScrollPane(workArea);

        jf.add(scrollPane);

        //Initialize the menu
        initMenuBar();

        //将字体变蓝
//        workArea.setForeground(Color.BLUE);

        //字体大小变粗等
        workArea.setFont(new Font("Cui", Font.PLAIN, 20));
    }





    void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        jf.setJMenuBar(menuBar);

        //菜单栏
        JMenu menu_file = new JMenu("  File  ");
        JMenu menu_edit = new JMenu("  Edit  ");
        JMenu menu_view = new JMenu("  View  ");
        JMenu menu_help = new JMenu("  Help  ");
        menuBar.add(menu_file);
        menuBar.add(menu_edit);
        menuBar.add(menu_view);
        menuBar.add(menu_help);

        //File
        JMenuItem fileItem_new = new JMenuItem("new");
        JMenuItem fileItem_open = new JMenuItem("open");
        JMenuItem fileItem_print = new JMenuItem("print");
        JMenuItem fileItem_exit = new JMenuItem("exit");
        //save 具体
        JMenu file_save_menu = new JMenu("save");
        JMenuItem savetxt = new JMenuItem("save as '.txt'");
        JMenuItem savepdf = new JMenuItem("save as '.pdf'");
        file_save_menu.add(savetxt);
        file_save_menu.add(savepdf);


        menu_file.add(fileItem_new);
        menu_file.add(fileItem_open);
        menu_file.add(file_save_menu);
        menu_file.add(fileItem_print);
        menu_file.add(fileItem_exit);

        //Edit
        JMenuItem editItem_search = new JMenuItem("search");
        JMenuItem editItem_copy = new JMenuItem("copy");
        JMenuItem editItem_paste = new JMenuItem("paste");
        JMenuItem editItem_cut = new JMenuItem("cut");
        menu_edit.add(editItem_search);
        menu_edit.add(editItem_copy);
        menu_edit.add(editItem_paste);
        menu_edit.add(editItem_cut);

        //View 菜单
        JMenuItem viewItem_TD = new JMenuItem("Time and Date");
        menu_view.add(viewItem_TD);


        //Help 菜单
        JMenuItem helpItem_about = new JMenuItem("about");
        menu_help.add(helpItem_about);

        //new
        fileItem_new.addActionListener(e -> New());

        //open
        fileItem_open.addActionListener(e -> open());

        //exit
        fileItem_exit.addActionListener(e -> exit());

        //about
        helpItem_about.addActionListener(e -> about());

        //save
        savetxt.addActionListener(e -> saveAstxt());
        savepdf.addActionListener(e -> {
            try {
                saveAspdf();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //copy
        editItem_copy.addActionListener(e -> Copy());

        //paste
        editItem_paste.addActionListener(e -> Paste());

        //cut
        editItem_cut.addActionListener(e -> Cut());


        //search
        editItem_search.addActionListener(e -> Search());

        //print


        fileItem_print.addActionListener(e -> {
            try {
                printer();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        //Time and Date
        viewItem_TD.addActionListener(e -> TD());
    }

    void Search(){
        new search(RunHere.width,RunHere.height);
    }

    void TD(){

    }

    void Print(){
    }

    void New() {
        new Window();
        width_screen += 200;
        height_screen += 100;
    }

    void open() {
        JFileChooser jFileChooser = new JFileChooser();
        int chose = jFileChooser.showOpenDialog(null);
        if (chose == JFileChooser.CANCEL_OPTION) {
            return ;
        }
        File F = jFileChooser.getSelectedFile();
        if (F != null) {
            jf.setTitle(F.getName());
            try {
                BufferedReader br = new BufferedReader(new FileReader(F));
                String line;
                while((line = br.readLine()) != null){
                    workArea.append(line + "\r\n");
                }
            }catch (IOException er1){
                throw new RuntimeException("Failed!！");
            }
        }
    }

    void exit(){
        jf.dispose();
    }

    void about(){
        JOptionPane.showMessageDialog(null,"Qiu Yifan\n  Cui Fangxiao is handsome","About Us",JOptionPane.PLAIN_MESSAGE);
    }

    void Cut(){
        workArea.cut();

    }

    void Copy(){
        workArea.copy();
    }

    void Paste(){
        workArea.paste();
    }




    void saveAstxt(){
        saveDia = new FileDialog(this,"save as(A)",FileDialog.SAVE);
        File fileS = null;

        saveDia.setVisible(true);
        String dirPath = saveDia.getDirectory();
        String fileName = saveDia.getFile();
        if (!fileName.contains(".txt")) {
            fileName += ".txt";
        }
        if(dirPath == null || fileName == null) {
            return;
        }
        fileS = new File(dirPath,fileName);

        try{
            BufferedWriter bufw = new BufferedWriter(new FileWriter(fileS));
            String text = workArea.getText();
            bufw.write(text);
            bufw.close();
        }catch(IOException er){
            throw new RuntimeException("file saved failed");
        }
    }


    void saveAspdf() throws Exception{
        saveDia = new FileDialog(this,"save as(B)",FileDialog.SAVE);
        File fileS1 = null;

        saveDia.setVisible(true);
        String dirPath = saveDia.getDirectory();
        String fileName = saveDia.getFile();
        if (!fileName.contains(".pdf")) {
            fileName += ".pdf";
        }
        if(dirPath == null || fileName == null) {
            return;
        }
        fileS1 = new File(dirPath,fileName);
        try {
            createpdf(fileS1);
        }catch (IOException er){
            throw new RuntimeException("file saved failed");
        }

    }

    public PDDocument createpdf(File fileS1) throws Exception {
        String s = workArea.getText();
        String[] strings = s.split("\n");
        PDDocument document=new PDDocument();
        PDPage my_page=new PDPage(PDRectangle.A4);
        document.addPage(my_page);
        PDFont font= PDType0Font.load(document, new File("C:/Windows/Fonts/Arial.ttf"));
        PDPageContentStream contentStream = new PDPageContentStream(document,my_page);
        my_page.getResources().add(font);
        //set font for pdf
        workArea.getText(0,1);
        for(int i=0;i<strings.length;i++){
            contentStream.beginText();
            contentStream.setFont(font,10);
            contentStream.newLineAtOffset(10,  820-i*20);
            contentStream.showText(strings[i]);
            contentStream.endText();
        }
        contentStream.close();
        document.save(fileS1);
        document.close();

        return document;
    }
    void printer() throws Exception {
        file=new File("D:","a.pdf");
        createpdf(file);
       print print=new print();
       print.PDFprint();
      if(file.exists()&&file.isFile()){
          System.out.println(file.delete());
       }

    }
}
