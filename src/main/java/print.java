import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.*;

class Print {


    public static class PrintClass implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {

            //print string
            String str = Window.workArea.getText();
            //turn Graphics2D
            Graphics2D g2 = (Graphics2D) graphics;
            //set black
            g2.setColor(Color.black);
            //zuo biao
            double x = pageFormat.getImageableX();
            double y = pageFormat.getImageableY();

            if (pageIndex == 0) {

                Font font = new Font("Arial", Font.PLAIN, 9);
                //set font
                g2.setFont(font);

                float[] dash1 = {2.0f};
                //Sets the properties of the print line.
                //Parameter 1 is the line width 4 is the width of the blank, 5 is the width of the dashed line, 6 is the offset
                g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
                //set height
                float height = font.getSize2D();

                String[] sp=str.split(String.valueOf('\n'));
                int line=0;
                for(String s : sp) {
                    g2.drawString(s, (float) x, (float) y + line * height+13);
                    line++;
                }
                return PAGE_EXISTS;
            }
            return NO_SUCH_PAGE;
        }
    }
    public Print() {
        //new一个book
        Book book = new Book();
        //Set to vertical typing
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);
        //Set the margins and printable area of the page with Paper. Must match the actual printable paper size.
        Paper p = new Paper();
        //Paper size
        p.setSize(570, 880);
        //A4 (595 X 842) set the print area, the default X,Y margins of A4 paper is 72
        p.setImageableArea(69, 69, 595, 842);

        pf.setPaper(p);

        //Deployment Components
        book.append(new PrintClass(), pf);

        //Obtain print service recipients
        PrinterJob job = PrinterJob.getPrinterJob();

        //Set Print Class
        job.setPageable(book);

        try {
            //You can use printDialog to display the print dialog and print after user confirmation; you can also print directly
            boolean a = job.printDialog();
            if (a) {
                job.print();
            } else {
                job.cancel();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

}
