package com.tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import static javax.swing.SwingConstants.CENTER;

public class Game extends JFrame implements ActionListener, MouseListener{
   private JButton button[][] = new JButton[3][3];
   private  JPanel panel1,panel2;
   private JTextField field;
   private boolean xturn;
   private Random random = new Random();
   private Font f = new Font("Nirmala UI", Font.BOLD, 48);
   private Font buttonfont = new Font("Nirmala UI", Font.BOLD, 48);

    Game(){
        //frame set
        this.setTitle("TicTacToe");
        this.setSize(500,500);
        this.setBounds(250,200,500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //panel set
        this.setLayout(null);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.setBounds(0,0,485,70);
        panel2.setBounds(0,70,485,395);
        
        //turn 
        turn();
        
        //field set
        field = new JTextField();
        field.addMouseListener(this);
        field.setHorizontalAlignment(CENTER);
        if(xturn){
            field.setText("X Turn");
        }
        else{
            field.setText("O Turn");
        }
        field.setEditable(false);
        panel1.setLayout(new BorderLayout());
        panel1.add(field,BorderLayout.CENTER);
        field.setFont(f);
        field.setForeground(Color.RED);
        
        //buttons
        panel2.setLayout(new GridLayout(3,3));
        for(int i = 0 ; i<3;i++){
            for(int j = 0;j<3;j++)
            {
             button[i][j] = new JButton("");
             button[i][j].addActionListener(this);
             button[i][j].setFocusable(false);
             button[i][j].setFont(buttonfont);
             panel2.add(button[i][j]);
            }
        }
       
        
      //adding and exposing  
        this.add(panel1);
        this.add(panel2);
        this.setVisible(true);
    }
    
    //randomly picks a turn
 final void turn(){
      int number = random.nextInt();
      if(number%2==0){
          xturn = true;
      }
      else{
          xturn = false;
      }
  }
  
  //checks for matches
 boolean checkwin(){
        //Row Matches
        for (int row = 0; row < 3; row++) {
            if (button[row][0].getText().equals(button[row][1].getText()) &&
                button[row][1].getText().equals(button[row][2].getText()) &&
                !button[row][0].getText().equals("")) {
                field.setText(button[row][0].getText() + " WINS");
                button[row][0].setBackground(Color.GREEN);
                button[row][1].setBackground(Color.GREEN);
                button[row][2].setBackground(Color.GREEN);
                field.setForeground(Color.GREEN);
                return true;
            }
        }

        //Column Matches
        for (int col = 0; col < 3; col++) {
            if (button[0][col].getText().equals(button[1][col].getText()) &&
                button[1][col].getText().equals(button[2][col].getText()) &&
                !button[0][col].getText().equals("")) {
                field.setText(button[0][col].getText()  + " WINS");
                button[0][col].setBackground(Color.GREEN);
                button[1][col].setBackground(Color.GREEN);
                button[2][col].setBackground(Color.GREEN);
                field.setForeground(Color.GREEN);
                return true;
            }
        }

        //Diagonal Matches
        if (button[0][0].getText().equals(button[1][1].getText()) &&
            button[1][1].getText().equals(button[2][2].getText()) &&
            !button[0][0].getText().equals("")) {
            field.setText(button[0][0].getText() + " WINS");
            button[0][0].setBackground(Color.GREEN);
            button[1][1].setBackground(Color.GREEN);
            button[2][2].setBackground(Color.GREEN);
            field.setForeground(Color.GREEN);
            return true;
        }
        if (button[0][2].getText().equals(button[1][1].getText()) &&
            button[1][1].getText().equals(button[2][0].getText()) &&
            !button[0][2].getText().equals("")) {
            field.setText(button[0][2].getText() + " WINS");
            button[0][2].setBackground(Color.GREEN);
            button[1][1].setBackground(Color.GREEN);
            button[2][0].setBackground(Color.GREEN);
            field.setForeground(Color.GREEN);
            return true;
        }
       return false;
 }

 //check to see if there's draw
 boolean draw(){
     for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (button[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
     field.setText("DRAW");
     field.addMouseListener(this);
     return true;
 }

 //method to restart the game
 void restart(){
     for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                button[row][col].setText("");
                button[row][col].setIcon(null);
                button[row][col].setBackground(Color.WHITE);
                button[row][col].setEnabled(true);
            }
        }
        //calls the random turn method
        turn();
        if(xturn){
            field.setText("X Turn");
        }
        else{
            field.setText("O Turn");
        }
        field.setForeground(Color.red);
 }
 
    
    //actions performed if a button is clickec (simplified by 2-D array)
    @Override
    public void actionPerformed(ActionEvent e) {
        //nested loop checks every button
        for(int i = 0;i<3;i++){
            for(int j= 0;j<3;j++){
                if(e.getSource()==button[i][j]){ //checks which button got pressed

                    if(xturn){ //if x turn is true then marks the button with X
                         button[i][j].setText("X");
                       // button[i][j].setEnabled(false);
                    field.setText("O Turn");
                    xturn = false;
                    
                } 
                //if x is not in turn, marks the button with O
                    else{
                      //  button[i][j].setEnabled(false);
                        button[i][j].setText("O");
                        field.setText("X Turn");
                        xturn = true;
                        
                    }
                //after each turn, looks for wins or draws
                    if(checkwin()){
                        for(int a = 0;a<3;a++){
                            for(int b=0;b<3;b++){
                               // button[a][b].setEnabled(false);
                            }
                        }
                        checkwin();
                    }
                //not using else because it will execute every time it isnt a win (every turn)
                    else if(draw()){
                        draw();
                    }
                }
                
            }
        }
    }

    @Override
    //to restart the game (click the field)
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==field){
            restart();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
