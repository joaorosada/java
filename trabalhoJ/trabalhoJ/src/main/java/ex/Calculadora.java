package ex;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static javax.swing.JOptionPane.showMessageDialog;

public class Calculadora extends JFrame {
    private static final String CAMPO_1 = "Campo 1";
    private static final String CAMPO_2 = "Campo 2";
    private JTextField campoValor1;
    private JTextField campoValor2;
    private JButton buttonSomar;
    private Boolean falha = Boolean.FALSE;


    public Calculadora() {
        setTitle("Calculadora que só soma");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        var labelValor1 = new JLabel(CAMPO_1);
        constraints.gridx = 0;
        constraints.gridy = 0;
        painel.add(labelValor1, constraints);

        campoValor1 = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painel.add(campoValor1, constraints);

        var labelValor2 = new JLabel(CAMPO_2);
        constraints.gridx = 2;
        constraints.gridy = 0;
        painel.add(labelValor2, constraints);

        campoValor2 = new JTextField(10);
        constraints.gridx = 3;
        constraints.gridy = 0;
        painel.add(campoValor2, constraints);

        buttonSomar = new JButton("Somar");
        buttonSomar.addActionListener(e -> somar());
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        painel.add(buttonSomar, constraints);

        add(painel);
        setLocationRelativeTo(null);
    }

    private Integer converter(String valorStr, String campo) {
        try {
            if (valorStr.isEmpty()) throw new CalculadoraException("O valor do campo " + campo + " não pode ser vazio");
            if (valorStr.isBlank())
                throw new CalculadoraException("O valor do %s não pode ser espaços vazios no campo ".formatted(campo));

            return Integer.parseInt((valorStr));

        } catch (NumberFormatException n) {
            showMessageDialog(this, "O valor do campo %s não é valido, informar apenas números inteiros".formatted(campo));
            this.falha = Boolean.TRUE;
            return 0;
        } catch (CalculadoraException e) {
            this.falha = e.isFalha();
            showMessageDialog(this, e.getMessage());
            return 0;
        }

    }

    private void somar() {

        try {
            var valor1 = converter(campoValor1.getText(), CAMPO_1);
            var valor2 = converter(campoValor2.getText(), CAMPO_2);
            if (this.falha) {
                this.falha = Boolean.FALSE;
                return;
            }


            Integer total = valor1 + valor2;
            salvar(total.toString());

            showMessageDialog(this, "Resultado: " + total);
        } catch (NumberFormatException e) {
            showMessageDialog(this, "Apenas Números");

        } catch (RuntimeException re) {
            showMessageDialog(this, re.getMessage());
        }

    }

    private void salvar(String valor) {
        var diretorioProjeto = System.getProperty("user.dir");
        var nomeArquivo = "//Calculadora.txt";
        var arquivo = new File(diretorioProjeto, nomeArquivo);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            writer.newLine();
            writer.write(valor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
