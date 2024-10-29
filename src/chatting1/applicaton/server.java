
package chatting1.applicaton;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.util.*;
import java.text.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.net.*;
import javax.swing.JList;



public class server  implements ActionListener{
    JTextField text;
     JPanel a1;
     static DataOutputStream dout;
     static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    server(){
        
        f.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel back = new  JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
            
        });
        
                ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/5646.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);

        JLabel profile = new  JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);
        
         ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);

        JLabel video = new  JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);
        
        
         ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);

        JLabel phone = new  JLabel(i12);
        phone.setBounds(360,20,35,30);
        p1.add(phone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);

        JLabel morevert = new  JLabel(i15);
        morevert.setBounds(420,20,10,25);
        p1.add(morevert);
        
        
        JLabel name = new JLabel("snehal");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p1.add(name);
        
        JLabel status = new JLabel("Active Now");
        status.setBounds(110,41,100,10);
        status.setForeground(Color.white);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,11));
        p1.add(status);
        
        
       a1 = new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);
        
        
        
        
        
        
         text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        
           f.add(text);
           
           JButton send = new JButton("send");
           send.setBounds(320, 655, 123, 40);
           send.setBackground(new Color(7,94,84));
           send.setForeground(Color.white);
           send.addActionListener(this);
           send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));

           f.add(send);
           
        f.setSize(450,700);
        f.setLocation(200, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
       String out = text.getText();
       
  
       
       JPanel p2 = fomatlable(out);
      
//        System.out.println(out);
a1.setLayout(new BorderLayout());
JPanel right = new JPanel(new BorderLayout());
right.add(p2 , BorderLayout.LINE_END);
vertical.add(right);
vertical.add(Box.createVerticalStrut(15));
a1.add(vertical , BorderLayout.PAGE_START);
dout.writeUTF(out);


text.setText("");
f.repaint();
f.invalidate();
f.validate();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
    public static JPanel fomatlable(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width: 150px\">"+out+"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setBorder(new EmptyBorder(15,15,15,50));
        output.setOpaque(true);
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        
        return panel;
    }
    
    
    public static void main(String[] args) {
        new server();
        try {
            ServerSocket skt  = new ServerSocket(6384);
            while (true) {                
               Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                 dout = new DataOutputStream(s.getOutputStream());
                
                while(true){
                   String msg = din.readUTF();
                   JPanel panel = fomatlable(msg);
                   JPanel left = new JPanel(new BorderLayout());
                   left.add(panel, BorderLayout.LINE_START);
                   vertical.add(left);
                   f.validate();
                   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
