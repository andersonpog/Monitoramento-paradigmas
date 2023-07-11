package UI;

import app.Infra;
import app.UsoFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbaAddEquip extends JPanel {

    public AbaAddEquip(){


        super(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);


        JLabel labelNome = new JLabel("Nome:");
        JTextField textFieldNome = new JTextField(20);

        JLabel labelMAC = new JLabel("MAC:");
        JTextField textFieldMAC = new JTextField(20);

        JLabel labelIP = new JLabel("IP:");
        JTextField textFieldIP = new JTextField(20);

        JLabel labelTipo = new JLabel("Tipo:");
        JComboBox<String> comboBoxTipo = new JComboBox<>(new String[]{"Infra", "Uso Final"});

        JButton buttonEnviar = new JButton("Enviar");

        // Adiciona os componentes
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelNome, constraints);

        constraints.gridx = 1;
        add(textFieldNome, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelMAC, constraints);

        constraints.gridx = 1;
        add(textFieldMAC, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(labelIP, constraints);

        constraints.gridx = 1;
        add(textFieldIP, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(labelTipo, constraints);

        constraints.gridx = 1;
        add(comboBoxTipo, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        add(buttonEnviar, constraints);


        // Configura botão Enviar
        buttonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos
                String nome = textFieldNome.getText();
                String mac = textFieldMAC.getText();
                String ip = textFieldIP.getText();
                int tipo = comboBoxTipo.getSelectedIndex();

                Boolean branco = nome.isEmpty()||mac.isEmpty();

                if(branco)
                    JOptionPane.showMessageDialog(null, "Preencha Nome e MAC do equipamento!", "Aviso", JOptionPane.WARNING_MESSAGE);

                if(tipo==0&&!branco){
                    int s = Infra.salvarNovo(nome,mac,ip);
                    if(s>0){
                        JOptionPane.showMessageDialog(null, "Operação feita com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        textFieldNome.setText("");
                        textFieldMAC.setText("");
                        textFieldIP.setText("");
                        comboBoxTipo.setSelectedIndex(0);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erro desconhecido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                }

                if(tipo==1&&!branco){
                    int s = UsoFinal.salvarNovo(nome,mac,ip);
                    if(s>0){
                        JOptionPane.showMessageDialog(null, "Operação feita com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        textFieldNome.setText("");
                        textFieldMAC.setText("");
                        textFieldIP.setText("");
                        comboBoxTipo.setSelectedIndex(0);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erro desconhecido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

    }
}
