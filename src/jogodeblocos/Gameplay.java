
package jogodeblocos;


import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

public class Gameplay  extends JPanel implements KeyListener, ActionListener {
    
 // Variáveis   
    
 private boolean toque = false;
 private int ponto = 0;
 
 private int tijolosTotais = 21;
 
 private Timer cronometro;
 private int demora = 8;
 
 private int jogadorX = 310;
 
 private int bolaposX = 120;
 private int bolaposY = 350;
 private int bolaXdir = -1;
 private int bolaYdir = -2;
 
 private GeradorDeMapas map;
 
 
 // Construtor
 public Gameplay() {
     
     map = new GeradorDeMapas(3, 7);
     addKeyListener(this);
     setFocusable(true);
     setFocusTraversalKeysEnabled(false);
      cronometro = new Timer(demora, this);
      cronometro.start();
     
 }
 
 public void paint(Graphics g) {
     
     // Fundo
     
     g.setColor(Color.black);
     g.fillRect(1, 1, 692, 592);
     
     // Mapa Do Desenho
     map.draw((Graphics2D)g);
     
     // Fronteiras
     g.setColor(Color.yellow);
     g.fillRect(0, 0, 3, 592);
     g.fillRect(0, 0, 692, 3);
     g.fillRect(691, 0, 3, 592);
     
     // Pontuações
     
     g.setColor(Color.white);
     g.setFont(new Font("serif", Font.BOLD, 25));
     g.drawString(""+ponto, 590, 30);
    
     
     // A Raquete
     g.setColor(Color.CYAN);
     g.fillRect(jogadorX, 550, 100, 8);
     
     // A Bola
     g.setColor(Color.yellow);
     g.fillRect(bolaposX, bolaposY, 20, 20);
     
      
     // Informações Quando For Vencido O Jogo
     if (tijolosTotais <= 0 ) {
         toque = false;
         bolaXdir = 0;
         bolaYdir = 0;
         g.setColor(Color.red);
         g.setFont(new Font("serif" , Font.BOLD, 20));
         g.drawString("Você Ganhou", 260, 300);
         
          g.setFont(new Font("serif" , Font.BOLD, 20));
          g.drawString("Pressione Enter Para Reiniciar, Pontuações: ", 230, 350);
     }
     
     // Informações Quando Fim De Jogo
     if (bolaposY > 570) {
         toque = false;
         bolaXdir = 0;
         bolaYdir = 0;
         g.setColor(Color.red);
         g.setFont(new Font("serif" , Font.BOLD, 20));
         g.drawString("Fim De Jogo, Pontuações: ", 190, 300);
         
          g.setFont(new Font("serif" , Font.BOLD, 20));
          g.drawString("Pressione Enter Para Reiniciar, Pontuações: ", 230, 350);
     }
     
     
     g.dispose(); 
    
 }
 
   public void actionPerformed(ActionEvent e) { //Ação
       cronometro.start();
       
       if (toque) {
           if (new Rectangle(bolaposX, bolaposY, 20, 20).intersects(new Rectangle(jogadorX, 550, 100, 8))) { // Realização Da Interação Da Raquete Com A Bola
               bolaYdir = -bolaYdir; // Adicionando A Bola
           }
           
           A:  for (int i=0; i<map.mapa.length; i++) {
               for (int j=0; j<map.mapa[0].length ; j++) {
                   if (map.mapa[i][j] > 0) {
                       int brickX = j*map.brickWidth + 80;
                       int brickY = i * map.brickHeight + 50;
                       int brickWidth = map.brickWidth;
                       int brickHeight = map.brickHeight;
                       
                       Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                       Rectangle ballRect = new Rectangle(bolaposX, bolaposY, 20, 20);
                       Rectangle brickRect = rect;
                       
                       if (ballRect.intersects(brickRect)) {
                           map.setBrickValue(0, i, j);
                           tijolosTotais--;
                           ponto += 5;
                           
                           if (bolaposX + 19 <= brickRect.x || bolaposX + 1 >= brickRect.x + brickRect.width) {
                               bolaXdir = -bolaXdir;
                           } else {
                              bolaYdir = -bolaYdir; 
                           }
                           
                           break A;
                           
                           
                       }
                   }
               }
           }
            
           
           bolaposX += bolaXdir;
           bolaposY += bolaYdir;
           if (bolaposX < 0) {
               bolaXdir = -bolaXdir;
           }
           if (bolaposY < 0) {
               bolaYdir = -bolaYdir;
           }
           if (bolaposX > 670) {
               bolaXdir = -bolaXdir;
           }
       }
       repaint();
       
   }
 
    

    @Override
    public void keyTyped(KeyEvent e) {
          
    
    
    
    
    }
    
    
    @Override
    public void keyReleased(KeyEvent e) {
              
           
    }

    
    
    @Override
    public void keyPressed(KeyEvent e) { //Evento De Pressionar A Tecla
          if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
              if (jogadorX >=600) {
                  jogadorX = 600;
              } else {
                  moveRight();
              }
        }
          if (e.getKeyCode() == KeyEvent.VK_LEFT) {
              if (jogadorX < 10) {
                  jogadorX = 10;
              } else {
                  moveLeft();
              }
        }
          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              if (!toque) {
                  toque = true;
                  bolaposX = 120;
                  bolaposY = 350;
                  bolaXdir = -1;
                  bolaYdir = -2;
                  ponto = 0;
                  tijolosTotais = 21;
                  map = new GeradorDeMapas(3, 7);
                  
                  repaint();
              }
        }
    
    }
    
    public void moveRight() {
        toque = true;
        jogadorX+=20;
    }
    
    public void moveLeft() {
        toque = true;
        jogadorX-=20;
    }

    
}
    
