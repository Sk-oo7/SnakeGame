package Game;

import javax.swing.*;
import java.awt.*;
public class Splash{
    JFrame frame;
    JLabel image=new JLabel(new ImageIcon("load.jpg"));
    JProgressBar progressBar=new JProgressBar();
    JLabel message=new JLabel();//Crating a JLabel for displaying the message
    Splash()
    {
        createGUI();
        addImage();
        addProgressBar();
        runningPBar();
    }
    public void createGUI(){
        frame=new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new java.awt.Color(33, 47, 60));
        frame.setVisible(true);
 
    }
    public void addImage(){
        image.setSize(800,560);
        frame.add(image);
    }

    public void addProgressBar(){
        progressBar.setBounds(50,530,700,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.white);
        progressBar.setForeground(new java.awt.Color(0, 122, 194));
        progressBar.setValue(0);
        frame.add(progressBar);
    }
    public void runningPBar(){
        int i=0;//Creating an integer variable and intializing it to 0
 
        while( i<=100)
        {
            try{
                Thread.sleep(50);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                message.setText("LOADING "+Integer.toString(i)+"%");//Setting text of the message JLabel
                i+=2;
                if(i==100)
                    frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
            }
 
 
 
        }
    }
}

