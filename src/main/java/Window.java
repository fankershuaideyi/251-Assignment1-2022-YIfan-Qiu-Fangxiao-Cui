import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Window extends JFrame {
    JFrame jf = new JFrame();
    JTabbedPane tabbedPane = new JTabbedPane();
    int textAreaIndex = 1;
    Window(int w,int h){
        init(w,h);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


    }
    void init(int w,int h){
        //设置主页面
        jf.setTitle("Text Editor");
        jf.setBounds((w-500)/2,(h-700)/2,500,700);

        JTextPane workArea = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(workArea);
        //初始化菜单
        initMenuBar(workArea);

        //初始化页签


        //将字体变蓝
        workArea.setForeground(Color.BLUE);

        //字体大小变粗等
        workArea.setFont(new Font("eag fawdawrawr awr aw rawr ", Font.BOLD, 20));

        //加入到主页面中
        jf.add(scrollPane);

//        initJTabbedPane(tabbedPane);
//        jf.getContentPane().add(tabbedPane);
    }
    void initMenuBar(JTextPane workArea){
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
        fileItem_save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
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
        fileItem_new.addActionListener(e -> addNewTabbedPane());
    }

    void initJTabbedPane(JTabbedPane tabbedPane) {
        //新标签的title
        String title;
        if (textAreaIndex == 1) {
            title = "文本文档.txt";
        } else {
            title = "文本文档" + (textAreaIndex) + ".txt";
        }

        //创建workArea
        JTextPane workArea = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(workArea);


        //加到标签栏中
        tabbedPane.addTab(title, null, scrollPane, null);

        //获取当前选项卡的位置
        int tabCount = tabbedPane.getTabCount();

        //将选项卡切换到新创建的一页上
        tabbedPane.setSelectedIndex(tabCount - 1);

        //实现关闭选项卡的功能通过ButtonTabComponent类
        tabbedPane.setTabComponentAt(tabCount-1,new ButtonTabComponent(tabbedPane));

        //页签的页码加1
        textAreaIndex++;
    }

    //创建一个新的标签
    void addNewTabbedPane(){
//        initJTabbedPane(tabbedPane);
        RunHere runHere = new RunHere();

        Window window1 = new Window(RunHere.width+200, RunHere.height+100);
    }
    void saveasPdf(){
        PDDocument document = new PDDocument();
        PDPage my_page=new PDPage();
    }

}
