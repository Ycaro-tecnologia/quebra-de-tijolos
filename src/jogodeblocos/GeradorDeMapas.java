
package jogodeblocos;

import java.awt.*;


public class GeradorDeMapas {
    
    public int mapa[][];
    public int brickWidth; // tijolo largura
    public int brickHeight; // tijolo altura
    
    public GeradorDeMapas(int linha, int col) { // contrutor GeradorDeMapas pra um detreminado número de tijolos
        mapa = new int[linha][col];
        for (int i = 0; i < mapa.length; i++) { // 
            for (int j=0; j < mapa[1].length; j++) {
                mapa[i][j] = 1;
            }
        }
        
        brickWidth = 540/col;
        brickHeight = 150/linha;
        
    }
    
    public void draw(Graphics2D g) { // Método para desenhar os tijolos
        for (int i = 0; i < mapa.length; i++) {
            for (int j=0; j<mapa[0].length; j++) {
                if (mapa[i][j] > 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
        
    }
    
    public void setBrickValue(int value, int row, int col) {
        mapa[row][col] = value;
    }
}

