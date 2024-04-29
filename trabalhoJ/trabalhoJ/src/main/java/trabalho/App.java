package trabalho;

import javax.swing.*;

public class App {
    public  static  void  main (String[] args) {
        SwingUtilities.invokeLater(() ->{
            var telaRoupa = new TelaRoupa();
            telaRoupa.setVisible(true);
        });
    }
}