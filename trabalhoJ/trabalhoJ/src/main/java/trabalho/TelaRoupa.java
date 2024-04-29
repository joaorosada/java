package trabalho;

import ex.CalculadoraException;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;


public class TelaRoupa extends JFrame {
    private static final String CAMPO_1 = "Tipo de roupa: ";
    private static final String CAMPO_2 = "Marca: ";
    private static final String CAMPO_3 = "Tamanho: ";
    private static final String CAMPO_4 = "Cor: ";
    private static final String CAMPO_5 = "Telefone: ";

    // variaveis do campo. campoV siginifca campo valor;
    private JTextField campoV1;
    private JTextField campoV2;
    private JTextField campoV3;
    private JTextField campoV4;
    private JTextField campoV5;
    private JButton botaoEnviar;
    private Boolean falha = Boolean.FALSE;

    public TelaRoupa() {
        setTitle("tela de montagem de roupa");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        var labelValor1 = new JLabel(CAMPO_1);
        constraints.gridx = 0;
        constraints.gridy = 0;
        painel.add(labelValor1, constraints);

        campoV1 = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painel.add(campoV1, constraints);

        var labelValor2 = new JLabel(CAMPO_2);
        constraints.gridx = 0;
        constraints.gridy = 1;
        painel.add(labelValor2, constraints);

        campoV2 = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painel.add(campoV2, constraints);

        var labelValor3 = new JLabel(CAMPO_3);
        constraints.gridx = 0;
        constraints.gridy = 2;
        painel.add(labelValor3, constraints);

        campoV3 = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painel.add(campoV3, constraints);

        var labelValor4 = new JLabel(CAMPO_4);
        constraints.gridx = 0;
        constraints.gridy = 3;
        painel.add(labelValor4, constraints);

        campoV4 = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painel.add(campoV4, constraints);

        var labelValor5 = new JLabel(CAMPO_5);
        constraints.gridx = 0;
        constraints.gridy = 4;
        painel.add(labelValor5, constraints);

        campoV5 = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painel.add(campoV5, constraints);

        botaoEnviar = new JButton("Enviar");
        botaoEnviar.addActionListener(e -> enviar());
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        painel.add(botaoEnviar, constraints);

        add(painel);
        setLocationRelativeTo(null);

    }
    private boolean verificarString(String valorStr) {
        return valorStr.matches("[a-zA-Z\\s]+");
    }
    public String verificarVazio(String valorStr, String campo) {
        try {
            if (valorStr.isEmpty()) throw new RuntimeException("O valor do campo " + campo + " não pode ser vazio!");
            if (valorStr.isBlank()) throw new RuntimeException("O valor do " + campo + " não pode ser espaços vazios no campo");
            if (!verificarString(valorStr)) throw new RuntimeException("O valor do campo " + campo + " não é válido. Informe apenas texto.");

            return valorStr;
        } catch (RuntimeException e) {
            showMessageDialog(this, e.getMessage());
            this.falha = Boolean.TRUE;
        }
        return valorStr;
    }

    public Integer converter(String valorStr, String campo) {
        try {
            if (valorStr.isEmpty()) throw new RuntimeException("O valor do " + campo + " não pode ser vazio!");
            if (valorStr.isBlank()) throw new RuntimeException("O valor do " + campo + " não pode ser espaços vazios");

            return Integer.parseInt(valorStr);
        } catch (NumberFormatException n) {
            showMessageDialog(this, "O valor do " + campo + " não é valido, informar números inteiros", "erro", JOptionPane.ERROR_MESSAGE);
            this.falha = Boolean.TRUE;
            return 0;
        }
    }
    private void enviar() {
        try {
            var campo5 = converter(campoV5.getText(), CAMPO_5);
            if (this.falha) {
                this.falha = Boolean.FALSE;
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        showMessageDialog(this, "Resultado: " + campoV1);

    }
}


