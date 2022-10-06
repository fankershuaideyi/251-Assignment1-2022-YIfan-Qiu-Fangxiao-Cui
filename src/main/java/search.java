import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.sql.SQLOutput;

public class search extends JDialog {
    JDialog jDialog = new JDialog();
    JLabel lookFor;
    JTextField lookFor_field;
    JButton jb1;
    JLabel replace;
    JTextField replace_field;
    JButton jb2;

    private int index;

    public search(int w,int h){
        jDialog.setBounds((w-400)/2,(h-150)/2,400,150);
        //初始化jDialog
        jDialogInit();
        jDialog.setVisible(true);
        jDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    void  jDialogInit(){
        jDialog.setTitle("Search");
        jDialog.setLayout(null);
        //创建一个盘子
        JPanel pane =new JPanel();
        //将盘子加入框中
        jDialog.add(pane);
        //设置pane的布局
        pane.setLayout(new FlowLayout(FlowLayout.LEFT));
        pane.setBounds(0,0,400,70);
        //盘子内容
        //查找
        lookFor = new JLabel("Find  content    ");
        lookFor_field = new JTextField(17);
        Document dt = lookFor_field.getDocument();
        dt.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                index=0;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                index=0;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                index=0;
            }
        });

        jb1 = new JButton("Find Next");

        //替换
        replace = new JLabel("replace content");
        replace_field = new JTextField(17);
        jb2 = new JButton("Replace  ");

        //将元素添加到pane中
        pane.add(lookFor);
        pane.add(lookFor_field);
        pane.add(jb1);
        pane.add(replace);
        pane.add(replace_field);
        pane.add(jb2);

        //添加pane2
        JPanel panel2 = new JPanel();
        jDialog.add(panel2);

        //设置pane2的布局
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.setBounds(0,70,400,50);

        //初始化两个按钮
        JButton replaceAll = new JButton("Replace All");
        JButton exit = new JButton("Cancel");

        //将这两个按钮添加到panel2中
        panel2.add(replaceAll);
        panel2.add(exit);


        //给按钮添加监听
        jb1.addActionListener(e -> search_word());
        jb2.addActionListener(e -> replace_word());
        replaceAll.addActionListener(e -> replace_All());
        exit.addActionListener(e -> Cancel());
    }


    void replace_word(){
        //获取两个输入框的文字
        String new_word = replace_field.getText();
        String old_word = lookFor_field.getText();

        //获取workArea里面的字体
        String demo = Window.workArea.getText();

        //替换
        StringBuilder sb = new StringBuilder(demo);
        sb.replace(index-old_word.length(),index,new_word);

        //将字符串加入到workArea中
        Window.workArea.setText(sb.toString());
    }

    void replace_All(){
        String new_word = replace_field.getText();
        String old_word = lookFor_field.getText();
        String demo = Window.workArea.getText();
        Window.workArea.setText(demo.replace(old_word,new_word));
    }

    void search_word(){
        String word = lookFor_field.getText();
        int x = Window.workArea.getText().indexOf(word,index);
        if(x == -1) {
            JOptionPane.showMessageDialog(null,"No searching","Warning Message",JOptionPane.PLAIN_MESSAGE);
            return;
        }
        int len = word.length();
        index = x + len;
        Window.workArea.setSelectionStart(x);
        Window.workArea.setSelectionEnd(x+len);
    }



    void Cancel(){
        jDialog.dispose();
    }
}
