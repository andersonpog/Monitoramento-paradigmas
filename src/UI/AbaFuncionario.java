package UI;

import app.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbaFuncionario extends JPanel {

    public AbaFuncionario(){

        super(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);


        JLabel labelNome = new JLabel("Nome:");
        JTextField textFieldNome = new JTextField(20);

        JLabel labelCPF = new JLabel("CPF:");
        JTextField textFieldCPF = new JTextField(20);

        JLabel labelCargo = new JLabel("Cargo:");
        JTextField textFieldCargo = new JTextField(20);


        JButton buttonEnviar = new JButton("Enviar");

        // Adiciona os componentes
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelNome, constraints);

        constraints.gridx = 1;
        add(textFieldNome, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelCPF, constraints);

        constraints.gridx = 1;
        add(textFieldCPF, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(labelCargo, constraints);

        constraints.gridx = 1;
        add(textFieldCargo, constraints);



        constraints.gridx = 1;
        constraints.gridy = 3;
        add(buttonEnviar, constraints);

        // Configura a ação do botão Enviar
        buttonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos
                String nome = textFieldNome.getText();
                String cpf = textFieldCPF.getText();
                String cargo = textFieldCargo.getText();


                if(nome.isEmpty()||cpf.isEmpty()||cargo.isEmpty())
                    JOptionPane.showMessageDialog(null, "Preencha Nome, CPF e Cargo do Funcionário!", "Aviso", JOptionPane.WARNING_MESSAGE);
                else{
                    int s = Funcionario.SalvarNovo(cpf, nome, cargo);
                    if(s>0){
                        JOptionPane.showMessageDialog(null, "Operação feita com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        textFieldNome.setText("");
                        textFieldCPF.setText("");
                        textFieldCargo.setText("");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erro desconhecido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });
    }
}
