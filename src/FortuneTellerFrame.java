import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Random;

import static java.awt.BorderLayout.NORTH;

public class FortuneTellerFrame extends JFrame
{
    JPanel mainPnl, titlePnl, displayPnl, buttonPnl;
    JLabel titleLbl;
    ImageIcon icon;
    JScrollPane scroll;
    JTextArea fortuneTextArea;
    JButton quitBtn, fortuneBtn;

    int currentFortune = -1;
    int newDex = 1;

    String[] fortunes = {
            "You will one day eat a fortune cookie",
            "You will dislike this fortune and press the 'Get Fortune' button again",
            "You will trip over your untied shoelace",
            "You will hit the quit button",
            "You will get stopped by a red light on your way home",
            "You will find a nickel on the ground",
            "You will forget to finish one of your assignments",
            "You will be stopped by a train on your way home",
            "You will meet someone new today",
            "You will forget to turn the light off when you leave"
    };

    Random rnd = new Random();
    public FortuneTellerFrame()
    {
        setTitle("Fortune Teller");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        createTitlePanel();
        createDisplayPanel();
        createCommandPanel();

        setVisible(true);
    }

    public void createTitlePanel()
    {
        titlePnl = new JPanel();
        icon = new ImageIcon("src\\fortuneteller.jpg", "fortune teller image");
        this.setIconImage(icon.getImage());
        titleLbl = new JLabel("Fortune Teller", icon, JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(new Font("Comic Sans Ms", Font.BOLD, 30));
        mainPnl.add(titleLbl, NORTH);
    }

    public void createCommandPanel()
    {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 2));

        quitBtn = new JButton("Quit");
        fortuneBtn = new JButton("Get Fortune");
        fortuneBtn.addActionListener((ActionEvent ae) ->
        {
            do
            {
                newDex = rnd.nextInt(fortunes.length);
            }
            while (newDex == currentFortune);

            currentFortune = newDex;
            fortuneTextArea.append(fortunes[currentFortune] + "\n");
        });

        buttonPnl.add(fortuneBtn);
        buttonPnl.add(quitBtn);
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        mainPnl.add(buttonPnl, BorderLayout.SOUTH);
    }

    private void createDisplayPanel()
    {
        displayPnl = new JPanel();

        fortuneTextArea = new JTextArea(10, 60);
        scroll = new JScrollPane(fortuneTextArea);

        displayPnl.add(scroll);

        mainPnl.add(displayPnl, BorderLayout.CENTER);
    }
}
