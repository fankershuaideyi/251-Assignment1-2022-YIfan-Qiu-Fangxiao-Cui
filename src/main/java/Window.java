import javax.swing.*;


public class Window extends JFrame {
    JFrame jf = new JFrame();

    Window(int w,int h){
        init(w,h);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    void init(int w,int h){


        //设置主页面
        jf.setTitle("Text Editor");
        jf.setBounds((w-500)/2,(h-700)/2,500,700);

        //初始化菜单
        initMenuBar();


    }
    void initMenuBar(){
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
        JMenuItem fileItem_print = new JMenuItem("print");
        JMenuItem fileItem_exit = new JMenuItem("exit");
        menu_file.add(fileItem_new);
        menu_file.add(fileItem_open);
        menu_file.add(fileItem_save);
        menu_file.add(fileItem_print);
        menu_file.add(fileItem_exit);

        //Search 菜单
        JMenuItem searchItem_ = new JMenuItem();


        //View 菜单
        JMenuItem viewItem_TD = new JMenuItem("Time and Date");
        menu_view.add(viewItem_TD);


        //Help 菜单
        JMenuItem helpItem_about = new JMenuItem("about");
        menu_help.add(helpItem_about);

    }
}
