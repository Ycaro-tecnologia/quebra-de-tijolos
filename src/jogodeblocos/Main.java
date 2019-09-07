
package jogodeblocos;

import javax.swing.*;

public class Main {

    
    public static void main(String[] args) {
          
        JFrame obj = new JFrame(); // novo objeto
        Gameplay gamePlay = new Gameplay(); //Instanciando Classe Gameplay ao main
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Bola De Fuga");
        obj.setResizable(false); // redimensionável
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay); // Adicionando o novo Objeto gamePlay ao JFrame


    }
    
}
