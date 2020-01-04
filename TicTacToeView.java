import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;

public class TicTacToeView extends JFrame {
    private AudioClip audio;
    private ImageIcon icon;
    private BorderLayout border;
    private JButton board[][]; // 3x3 array representing the board
    private JPanel buttonPanel;
    private JLabel label;
    private Container content;
    private JMenuBar menuList;
    private JMenuItem reset;
    private JMenuItem quit;
    private JMenuItem swap;
    private JMenuItem clear;
    private JMenu menu;
    private JLabel score;
    public TicTacToeView() {

        JFrame f = new JFrame("Josh's MVC TicTacToe");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(300, 300));

        content = f.getContentPane();
        border = new BorderLayout();
        content.setLayout(border);

        //Initialize buttonPanel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        //Initialize menu and menu items
        menuList = new JMenuBar();
        f.setJMenuBar(menuList);
        menu = new JMenu("Options");
        menuList.add(menu);
        reset = new JMenuItem("Reset Game (X Starts)");
        menu.add(reset);
        swap = new JMenuItem("Reset Game (O Starts)");
        menu.add(swap);
        quit = new JMenuItem("Quit");
        menu.add(quit);

        //Initialize the Jbutton board
        board = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton(); //initialize each button on the board
                buttonPanel.add(board[i][j]);
            }
        }

        //Initialize labels
        label = new JLabel();
        label.setText("Game Started: Player X starts");
        label.setFont(new Font(null, Font.BOLD, 15));
        score = new JLabel();

        content.add(label, BorderLayout.SOUTH);
        content.add(buttonPanel, BorderLayout.CENTER);
        content.add(score, BorderLayout.NORTH);

        f.setVisible(true);
        f.setResizable(false);
        f.pack();
    }


    public Icon getPic(int row, int col) {
        return board[row][col].getIcon();
    }

    public JButton[][] getBoard() {
        return board;
    }

    void addResetListener(ActionListener listenForResetButton) {
        reset.addActionListener(listenForResetButton);
    }

    void addSwapListener(ActionListener listenForSwapButton) {
        swap.addActionListener(listenForSwapButton);
    }

    void addQuitListener(ActionListener listenForQuitButton) {
        quit.addActionListener(listenForQuitButton);
    }

    void addBoardListener(ActionListener listenForBoardButton) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].addActionListener(listenForBoardButton);

            }
        }
    }
    public void playAudio(boolean tie, String player) {
        if (tie) {
            audio = Applet.newAudioClip(TicTacToeView.class.getResource("/Resources/tie.wav"));
            audio.play();
        } else if (player.equals("x")) {
            audio = Applet.newAudioClip(TicTacToeView.class.getResource("/Resources/winner.wav"));
            audio.play();
        } else if (player.equals("o")) {
            audio = Applet.newAudioClip(TicTacToeView.class.getResource("/Resources/winnero.wav"));
            audio.play();
        }
    }

    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setIcon(null);
                board[i][j].setEnabled(true);
            }
        }
    }
    public void placeImage(int i, int j, String player) {
        if (player.equals("x")) {
            icon = new ImageIcon(TicTacToeModel.class.getResource("/Resources/X.png"));

        } else {
            icon = new ImageIcon(TicTacToeModel.class.getResource("/Resources/O.png"));

        }
        board[i][j].setIcon(icon);
        board[i][j].setDisabledIcon(icon);
        board[i][j].setEnabled(false);
    }

    public void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setEnabled(false);
            }
        }
    }

    public void setLabel(String player) {
        label.setText("Game in Progress: " + player + "'s Turn");
    }

    public void setScoreLabel(int xScore, int oScore, int tieScore) {
        score.setText("The Current Score is    X: " + xScore + "   O: " + oScore + "     Ties: " + tieScore);
    }

    public void setWinningLabel(String player, boolean tie) {
        if (tie) label.setText("Game ended as a tie");
        else label.setText(player + " Wins");
    }

    public void setStartingLabel(String player) {
        label.setText("Game Starting: " + player + " Begins");
    }
}