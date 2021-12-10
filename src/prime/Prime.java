package prime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

abstract class Element
{
  JButton ok;
  JButton cancel;
  JButton remove;

  JLabel l;
  JLabel ans;

  JTextField f;

  JPanel south;
  JPanel north;

  protected abstract void addElement();

}


class GUI extends Element implements ActionListener
{
  JFrame frame;

  public GUI( )
   {

     frame = new JFrame("Prime"); //  frame constructor with title ...
     frame.setSize(400,400); // set size of window
     frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // default close window button

     //frame.setLayout(null); // no layout because i use my special layout in use function setBounds(x,y,width, height)
     //frame.setResizable(false); // for stop resizable windo

     //JFrame Center in screen
     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
     frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

     south= new JPanel();
     south.setBorder(BorderFactory.createLineBorder(Color.RED));
     frame.add(south,BorderLayout.SOUTH);


     north= new JPanel();
     north.setBorder(BorderFactory.createTitledBorder("please do the foloowing"));
     frame.add(north,BorderLayout.NORTH);

     addElement();

     frame.addWindowListener(new WindowAdapter()
     {

     @Override
     public void windowOpened(WindowEvent we) { frame.setIconImage( new ImageIcon("icons0/p.png").getImage()); }

     @Override
     public void windowClosing(WindowEvent we)
     {
       int ans=showConfirmDialog(null,"Do you want to exit","Exit",YES_NO_CANCEL_OPTION,1,new ImageIcon("icons0/can.png"));
       if (ans==YES_OPTION ) frame.dispose();
     }

     @Override
     public void windowClosed(WindowEvent we) { JOptionPane.showMessageDialog(null, "thank you for use my program ;) ","good bye",1,new ImageIcon("icons0/7.png")); }

     @Override
      public void windowIconified(WindowEvent we) { frame.setIconImage( new ImageIcon("icons0/pl.png").getImage()); }

      @Override
      public void windowDeiconified(WindowEvent we) { frame.setIconImage( new ImageIcon("icons0/p.png").getImage()); }

     });

     f.addKeyListener(new KeyAdapter()
     {

     @Override
      public void keyTyped(KeyEvent ke) { if (!(ke.getKeyChar() >='0'  && ke.getKeyChar()<='9')) ke.consume(); }

     });

     f.getDocument().addDocumentListener(new DocumentListener()
     {

      @Override
      public void insertUpdate(DocumentEvent de) { ok.doClick(); }

     @Override
     public void removeUpdate(DocumentEvent de) { ok.doClick(); }

     @Override
      public void changedUpdate(DocumentEvent de) {/*...*/ }

     });

     frame.setBackground(Color.yellow);

     //frame_.pack(); // delete space to best form

     frame.setVisible(true);

  }

  @Override
  protected final void addElement()
  {
     ok= new JButton("ok",new ImageIcon("icons0/ok.png"));
     ok.setMnemonic('K');
     ok.addActionListener(this);
     ok.setBackground(Color.GREEN);
     south.add(ok);

     remove = new JButton("remove",new ImageIcon("icons0/rem.png"));
     remove.setMnemonic('r');
     remove.addActionListener(this);
     remove.setBackground(Color.ORANGE);
     south.add(remove);

     cancel = new JButton("cancel",new ImageIcon("icons0/can.png"));
     cancel.setMnemonic('L');
     cancel.addActionListener(this);
     cancel.setBackground(Color.RED);
     south.add(cancel);
     south.setBackground(Color.CYAN);

     l= new JLabel(" enter a positive integer number : ");
     l.setIcon(new ImageIcon("D:/icons0/l.png"));
     north.add(l);
     north.setBackground(Color.yellow);

     ans= new JLabel("result : ");
     ans.setFont(new Font("Sanserif", Font.ITALIC, 0x12));
     ans.setHorizontalAlignment( SwingConstants.CENTER );

     f= new JTextField(10);
     f.addActionListener(this);
     north.add(f);

     frame.add(ans,BorderLayout.CENTER);
  }

    @Override
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==cancel)
    {
      int asa=showConfirmDialog(null,"Do you want to exit","Exit",YES_NO_CANCEL_OPTION,1,new ImageIcon("icons0/can.png"));
       if (asa==YES_OPTION ) frame.dispose();
    }
    else
    if(e.getSource()==remove){ f.setText(""); ans.setText(" result : "); ans.setForeground(Color.BLACK);}
    else
    if(e.getSource()==f){ ok.doClick(); }
    else
    if(e.getSource()==ok)
    {
      String s=f.getText();
      if (!s.isEmpty())
      {
       long n=  Long.parseLong(s);
       ans.setText("result : " + "the number "+ n +((Prime.isPrime(n)) ? " is Prime ":" is not Prime "));
       ans.setForeground(Color.BLUE);
      }
      else
      {
       ans.setText("------------------- no input is provided ....................");
       ans.setForeground(Color.RED);
      }

   }

 }


}



public class Prime {

    public static boolean isPrime(long num)
    {
       if(num==0||num==1) return false;

       int f=0;

        long n=num/2;

          for(int i=2; (i<=n) && (f == 0) ;i++)
          {
              if(num % i==0)f=1;
          }

           return (f == 0);

    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GUI gui = new GUI( );

    }

}