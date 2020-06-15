import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class frame extends JFrame
{
    frame()
    {
        setVisible(true);
        setBounds(200,0,990,735);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snakes and Ladders");
        setResizable(false);
        panel p=new panel();
        add(p);
    }
}
class panel extends JPanel implements ActionListener
{
    ImageIcon im1,im2,im3,im4,im5,im6,im7,im8,im9;
    Image board,logo,start,dice,p1,p2;
    JButton roll,reset,about,st;
    JTextField tx1,tx2,tx3;
    int xp[]={10,10},yp[]={360,290},pos1=0,pos2=0,x=0,turn=0,row=1,j[]={0,0},k[]={0,0};
    ImageIcon img[]=new ImageIcon[6];
    Image d[]=new Image[6];
    String s;
    panel()
    {
        setBackground(Color.black);
        setLayout(null);
        Font f=new Font("Chiller",Font.BOLD,20);
        im1=new ImageIcon("board.jpg");
        board=im1.getImage();
        im2=new ImageIcon("logo.png");
        logo=im2.getImage();
        im3=new ImageIcon("start.jpg");
        start=im3.getImage();
        im4=new ImageIcon("dice.gif");
        dice=im4.getImage();
        im5=new ImageIcon("p1.png");
        p1=im5.getImage();
        im6=new ImageIcon("p2.png");
        p2=im6.getImage();
        im7=new ImageIcon("reset.png");
        im8=new ImageIcon("about.gif");
        im9=new ImageIcon("start.gif");
        
        for(int i=0;i<6;i++)
        {
            img[i]=new ImageIcon(i+1+".png");
            d[i]=img[i].getImage();
        }
        
        roll=new JButton("Roll");
        roll.setSize(100,30);
        roll.setLocation(60,510);
        roll.setFont(f);
        add(roll);
        
        reset=new JButton(im7);
        reset.setSize(100,35);
        reset.setLocation(40,161);
        reset.setBackground(new Color(200,181,255));
        reset.addActionListener(this);
        add(reset);
        
        about=new JButton(im8);
        about.setSize(100,30);
        about.setLocation(40,91);
        about.setBackground(new Color(200,181,255));
        add(about);
        
        st=new JButton(im9);
        st.setSize(150,60);
        st.setLocation(35,568);
        //st.setFont(f);
        st.addActionListener(this);
        add(st);
        
        tx1=new JTextField("Player One");
        tx1.setSize(160,30);
        tx1.setLocation(40,371);
        tx1.setFont(f);
        tx1.setBackground(new Color(220,201,255));
        tx1.setForeground(new Color(25,25,25));
        add(tx1);
        
        tx2=new JTextField("Player Two");
        tx2.setSize(160,30);
        tx2.setLocation(40,301);
        tx2.setFont(f);
        tx2.setForeground(Color.white);
        tx2.setBackground(new Color(150,150,150));
        add(tx2);
        
        tx3=new JTextField("");
        tx3.setSize(160,30);
        tx3.setLocation(40,231);
        tx3.setFont(f);
        tx3.setEditable(false);
        tx3.setBackground(new Color(200,181,255));
        add(tx3);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(board,220,0,this);
        g.drawImage(logo,930,30,this);
        g.setColor(new Color(200,181,255));
        g.fillRect(0,0,220,735);
        g.drawImage(start,35,560,this);
        g.drawImage(dice,30,430,this);
        g.setColor(Color.red);
        g.fillRoundRect(120,440,49,49,20,20);
        g.drawImage(p1,xp[0],yp[0],this);
        g.drawImage(p2,xp[1],yp[1],this);
        if(x!=0)
        g.drawImage(d[x-1],120,440,this);
        //g.drawString(s,300,200);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==st)
        {
         xp[0]=165;
         xp[1]=185;
         yp[0]=yp[1]=640;
         st.removeActionListener(this);
         roll.addActionListener(this);
         tx1.setEditable(false);
         tx2.setEditable(false);
         tx3.setText(tx1.getText()+"'s turn");
         repaint();
        }
        if(e.getSource()==reset)
        {
         xp[1]=10;
         xp[0]=10;
         yp[0]=360;
         yp[1]=290;
         pos1=pos2=j[0]=k[0]=j[1]=k[1]=0;
         st.addActionListener(this);
         roll.removeActionListener(this);
         tx1.setEditable(true);
         tx2.setEditable(true);
         tx3.setText("");
         turn=0;
         x=0;
         repaint();
        }
        if(e.getSource()==roll)
        {
            x=(int)(Math.random()*6)+1;
            int a=x;
       
            if(turn%2==0)
            {
                if(pos1+x>=100)
                {
                    xp[0]=255;
                    yp[0]=10;
                    s=tx1.getText()+" wins";
                    tx3.setText(s);
                }
                else
                {
                if(x!=6)
                {
                 tx3.setText(tx2.getText()+"'s turn");
                 turn++;
                }
                 pos1=pos1+x;
                for(;j[0]<10;j[0]++)
                {
                    for(;k[0]<10;k[0]++)
                    {
                        if(a>0)
                        {
                        if(j[0]%2==0)
                         xp[0]+=70;
                         else
                         xp[0]-=70;
                        
                         a--;
                        }
                        else{
                        break;
                       }
                    }
                    if(a==0)
                    break;
                    k[0]=0;
                    if(j[0]%2==0)
                    xp[0]+=70;
                    else
                    xp[0]-=70;
                    yp[0]-=70;
                }
                repaint();
                if(pos1==4)
                {
                    yp[0]-=140;
                    xp[0]+=70;
                    pos1=25;
                    j[0]+=2;
                    k[0]+=1;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==13)
                {
                 yp[0]-=210;
                 xp[0]-=140;
                 pos1=46;
                 j[0]+=3;
                 k[0]=5;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==42)
                {
                 yp[0]-=140;
                 xp[0]+=70;
                 pos1=63;
                 j[0]+=2;
                 k[0]+=1;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==50)
                {
                 yp[0]-=140;
                 xp[0]-=70;
                 pos1=69;
                 j[0]+=2;
                 k[0]-=1;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==62)
                {
                 yp[0]-=140;
                 xp[0]-=70;
                 pos1=82;
                 j[0]+=2;
                 k[0]-=1;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==74)
                {
                 yp[0]-=140;
                 xp[0]+=140;
                 pos1=92;
                 j[0]+=2;
                 k[0]=1;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                
                
                if(pos1==27)
                {
                    yp[0]+=140;
                    xp[0]-=140;
                    pos1=5;
                    j[0]-=2;
                    k[0]=4;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==40)
                {
                    yp[0]+=210;
                    xp[0]-=140;
                    pos1=3;
                    j[0]-=3;
                    k[0]=2;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==43)
                {
                    yp[0]+=210;
                    pos1=18;
                    j[0]-=3;
                    k[0]=7;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==54)
                {
                    yp[0]+=140;
                    xp[0]+=210;
                    pos1=31;
                    j[0]-=2;
                    k[0]=0;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==66)
                {
                    yp[0]+=140;
                    xp[0]-=70;
                    pos1=45;
                    j[0]-=2;
                    k[0]=4;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==76)
                {
                    yp[0]+=140;
                    xp[0]-=140;
                    pos1=58;
                    j[0]-=2;
                    k[0]=7;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==89)
                {
                    yp[0]+=210;
                    xp[0]-=70;
                    pos1=53;
                    j[0]-=3;
                    k[0]=2;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos1==99)
                {
                    yp[0]+=350;
                    xp[0]-=70;
                    pos1=41;
                    j[0]=4;
                    k[0]=0;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
            }
        }
            else
            {
                if(pos2+x>=100)
                {
                    xp[1]=255;
                    yp[1]=10;
                    s=tx2.getText()+" wins";
                    tx3.setText(s);
                }
              else
              {
                  pos2=pos2+x;
             if(x!=6)
             {
              turn++;
              tx3.setText(tx1.getText()+"'s turn");
            }
              for(;j[1]<10;j[1]++)
                {
                    for(;k[1]<10;k[1]++)
                    {
                        if(a>0)
                        {
                        if(j[1]%2==0)
                         xp[1]+=70;
                         else
                         xp[1]-=70;
                         
                         a--;
                        }
                        else break;
                    }
                    if(a==0)
                    break;
                    k[1]=0;
                    if(j[1]%2==0)
                    xp[1]+=70;
                    else
                    xp[1]-=70;
                    yp[1]-=70;
                }
                repaint();
                if(pos2==4)
                {
                    yp[1]-=140;
                    xp[1]+=70;
                    pos2=25;
                    j[1]+=2;
                    k[1]+=1;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==13)
                {
                 yp[1]-=210;
                 xp[1]-=140;
                 pos2=46;
                 j[1]+=3;
                 k[1]=5;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==42)
                {
                 yp[1]-=140;
                 xp[1]+=70;
                 pos2=63;
                 j[1]+=2;
                 k[1]+=1;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==50)
                {
                 yp[1]-=140;
                 xp[1]-=70;
                 pos2=69;
                 j[1]+=2;
                 k[1]-=1;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==62)
                {
                 yp[1]-=140;
                 xp[1]-=70;
                 pos2=82;
                 j[1]+=2;
                 k[1]-=1;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==74)
                {
                 yp[1]-=140;
                 xp[1]+=140;
                 pos2=92;
                 j[1]+=2;
                 k[1]=1;
                 try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                
                
                if(pos2==27)
                {
                    yp[1]+=140;
                    xp[1]-=140;
                    pos2=5;
                    j[1]-=2;
                    k[1]=4;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==40)
                {
                    yp[1]+=210;
                    xp[1]-=140;
                    pos2=3;
                    j[1]-=3;
                    k[1]=2;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==43)
                {
                    yp[1]+=210;
                    pos2=18;
                    j[1]-=3;
                    k[1]=7;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==54)
                {
                    yp[1]+=140;
                    xp[1]+=210;
                    pos2=31;
                    j[1]-=2;
                    k[1]=0;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==66)
                {
                    yp[1]+=140;
                    xp[1]-=70;
                    pos2=45;
                    j[1]-=2;
                    k[1]=4;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==76)
                {
                    yp[1]+=140;
                    xp[1]-=140;
                    pos2=58;
                    j[1]-=2;
                    k[1]=7;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==89)
                {
                    yp[1]+=210;
                    xp[1]-=70;
                    pos2=53;
                    j[1]-=3;
                    k[1]=2;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }
                if(pos2==99)
                {
                    yp[1]+=350;
                    xp[1]-=70;
                    pos2=41;
                    j[1]=4;
                    k[1]=0;
                    try{
                     Thread.sleep(500);
                    }
                    catch(Exception exc){}
                }

            }
            }
            
            repaint();
        }
    }
}
public class SnakesAndLadders
{
    public static void main( String args[])
    {
        frame main=new frame();
    }
}