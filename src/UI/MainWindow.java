package UI;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
//    JFrame frame = new JFrame("Monitoramento");
//    frame.setSize(1920,1800);
//    frame.setDefa

    public MainWindow(){
        super("Monitoramento");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        JButton botao = new JButton("Clique aqui");
//        add(botao);

//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        // Cria os painéis que serão adicionados às abas
//        JPanel painel1 = new JPanel();
//        JPanel painel2 = new JPanel();
//        JPanel painel3 = new JPanel();
//
//        // Adiciona os painéis às abas
//        tabbedPane.addTab("Aba 1", painel1);
//        tabbedPane.addTab("Aba 2", painel2);
//        tabbedPane.addTab("Aba 3", painel3);
        Abas abas = new Abas();

        // Adiciona o JTabbedPane à janela
        add(abas, BorderLayout.CENTER);

        setVisible(true);
    }
}
