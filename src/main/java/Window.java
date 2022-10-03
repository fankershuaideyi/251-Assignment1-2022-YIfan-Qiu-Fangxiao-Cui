import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
public class Window extends JFrame {

    private static int width_screen = RunHere.width;
    private static int height_screen = RunHere.height;
    private JTextArea workArea;
    private JScrollPane scrollPane;
    private JFrame jf = new JFrame();


    Window() {
        init(width_screen, height_screen);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    void init(int w, int h) {
        //设置主页面
        jf.setTitle("Text Editor");
        jf.setBounds((w - 500) / 2, (h - 700) / 2, 500, 700);

        width_screen += 200;
        height_screen += 100;

        //初始化页签
        workArea = new JTextArea();
        scrollPane = new JScrollPane(workArea);

        jf.add(scrollPane);

        //初始化菜单
        initMenuBar(workArea);

        //将字体变蓝
//        workArea.setForeground(Color.BLUE);

        //字体大小变粗等
//        workArea.setFont(new Font("Cui", Font.BOLD, 20));
    }





    void initMenuBar(JTextArea workArea){
        JMenuBar menuBar = new JMenuBar();
        jf.setJMenuBar(menuBar);

        //菜单栏
        JMenu menu_file = new JMenu("  File  ");
        JMenu menu_search = new JMenu("  Search  ");
        JMenu menu_view = new JMenu("  View  ");
        JMenu menu_help = new JMenu("  Help  ");
        menuBar.add(menu_file);
        menuBar.add(menu_search);
        menuBar.add(menu_view);
        menuBar.add(menu_help);

        //File 菜单
        JMenuItem fileItem_new = new JMenuItem("new");
        JMenuItem fileItem_open = new JMenuItem("open");
        JMenuItem fileItem_save = new JMenuItem("save");


        FileDialog saveDia;
        saveDia = new FileDialog(this,"save as(A)",FileDialog.SAVE);
        // a funtion for save
        fileItem_save.addActionListener(e -> {
            File fileS = null;
            if(fileS == null){
                saveDia.setVisible(true);
                String dirPath = saveDia.getDirectory();
                String fileName = saveDia.getFile();

                if(dirPath == null || fileName == null) {
                    return;
                }
                fileS = new File(dirPath,fileName);
            }

            try{
                BufferedWriter bufw = new BufferedWriter(new FileWriter(fileS));
                String text = workArea.getText();
                bufw.write(text);
                bufw.close();
            }catch(IOException er){
                throw new RuntimeException("file saved failed");
            }
        });


        JMenuItem fileItem_print = new JMenuItem("print");
        JMenuItem fileItem_exit = new JMenuItem("exit");
        menu_file.add(fileItem_new);
        menu_file.add(fileItem_open);
        menu_file.add(fileItem_save);
        menu_file.add(fileItem_print);
        menu_file.add(fileItem_exit);

        //Search 菜单


        //View 菜单
        JMenuItem viewItem_TD = new JMenuItem("Time and Date");
        menu_view.add(viewItem_TD);


        //Help 菜单
        JMenuItem helpItem_about = new JMenuItem("about");
        menu_help.add(helpItem_about);

        //new
        fileItem_new.addActionListener(e -> New());

        //open
        fileItem_open.addActionListener(e -> openFile());

        //about
        helpItem_about.addActionListener(e -> about());
    }


    //创建一个新的标签
    void New() {
        new Window();
        width_screen += 200;
        height_screen += 100;
    }

    void openFile() {
        JFileChooser jFileChooser = new JFileChooser();
        int chose = jFileChooser.showOpenDialog(null);
        if (chose == JFileChooser.CANCEL_OPTION) {
            return;
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

    void about(){
        JOptionPane.showMessageDialog(null,"Qiu Yifan\n" +
                "Cui Fangxiao is handsome","About Us",JOptionPane.PLAIN_MESSAGE);
    }

    void saveasPdf(){
        PDDocument document = new PDDocument();
        PDPage my_page=new PDPage();
    }

}
