import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame {
    private char currentSymbol;
    private JButton[] buttons;

    public TicTacToe() {
        currentSymbol = 'X';
        buttons = new JButton[9];
        
        setLayout(new GridLayout(3,3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setTitle("TicTacToe");

        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton)e.getSource();
                    button.setText(String.valueOf(currentSymbol));
                    button.setEnabled(false);
                    checkGame();
                    switchPlayer();
                }
            });
            add(buttons[i]);
        }

        setVisible(true);
    }

    public void switchPlayer() {
        currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
    }

    public void checkGame() {
        //check horizontal lines
        for (int i = 0; i < 9; i += 3)
            if (checkLine(i, i+1, i+2))
                endGame(buttons[i].getText());

        //check vertical lines
        for (int i = 0; i < 3; ++i)
            if (checkLine(i, i+3, i+6))
                endGame(buttons[i].getText());

        //check the diagonals
        if (checkLine(0, 4, 8) || checkLine(2, 4, 6))
            endGame(buttons[4].getText());
    }

    public boolean checkLine(int a, int b, int c) {
        return !buttons[a].getText().equals("") &&
                buttons[a].getText().equals(buttons[b].getText()) &&
                buttons[b].getText().equals(buttons[c].getText());
    }

    public void endGame(String winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!", "Winner", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}