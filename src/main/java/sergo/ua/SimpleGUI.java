package sergo.ua;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SimpleGUI extends JFrame {

    Font lab = new Font("Verdana", Font.BOLD, 16);
    Font val = new Font("Verdana", Font.ITALIC, 16);

    private JLabel wdayL = new JLabel("     Сегодня:");
    private JLabel heatL = new JLabel("     Темп-ра:");
    private JLabel fenL = new JLabel("     Погода:");
    private JLabel wday;
    private JLabel heat;
    private JLabel fen;

    public SimpleGUI(String wdayV, String heatV, String fenV, String imgS) throws IOException, URISyntaxException {
        super("Погода в Сумах");

        final URI uri;
        uri = new URL("https://sinoptik.ua").toURI();
        class OpenUrlAction implements ActionListener {
            @Override public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(uri);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        this.setBounds(1,1,400,290);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE);
        container.setLayout(null);

        wdayL.setFont(lab);
        wdayL.setBounds(0,0,175,20);
        container.add(wdayL);
        wday = new JLabel(wdayV);
        wday.setBounds(140,0,200,20);
        wday.setFont(val);
        container.add(wday);
        heatL.setBounds(0,25,200,20);
        heatL.setFont(lab);
        container.add(heatL);
        heat=new JLabel(heatV);
        heat.setBounds(140,25,200,20);
        heat.setFont(val);
        container.add(heat);
        fenL.setBounds(0,50,200,20);
        fenL.setFont(lab);
        container.add(fenL);
        fen=new JLabel(fenV);
        fen.setBounds(140,50,200,20);
        fen.setFont(val);
        container.add(fen);
        URL url = new URL("https:"+imgS);
        final Image image = ImageIO.read(url);
        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0,150,150,null);
            }
        };
        pane.setBounds(110,70,150,150);
        container.add(pane);

        JPanel footer = new JPanel();
        JLabel source = new JLabel();
        footer.setBounds(-10,220,400,30);
        source.setBounds(0,220,400,30);
        footer.setBackground(Color.ORANGE);
        source.setText("<html><a href="+uri+">"+uri+"</a></html>.");
        //footer.setPage("https://sinoptik.ua");
        footer.add(source);
        container.add(footer);
        footer.addMouseListener(new CustomListener());
        //Desktop.getDesktop().browse(uri);


    }
}
