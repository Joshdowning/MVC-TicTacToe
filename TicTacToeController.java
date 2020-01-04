import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Image;


public class TicTacToeController {
    private TicTacToeModel theModel;
    private TicTacToeView theView;
    private ImageIcon icon;


    public TicTacToeController(TicTacToeView theView, TicTacToeModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addQuitListener(new QuitListener());
        this.theView.addSwapListener(new SwapListener());
        this.theView.addBoardListener(new BoardListener());
        this.theView.addResetListener(new ResetListener());
    }

    class SwapListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.clearBoard();
            theModel.clearData();
            theModel.setPlayer("o");
            theView.setStartingLabel(theModel.getPlayer());
            theModel.resetNumFreeSquares();
        }
    }

    class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theView.clearBoard();
            theModel.clearData();
            theModel.setPlayer("x");
            theView.setStartingLabel(theModel.getPlayer());
            theModel.resetNumFreeSquares();
        }
    }

    class BoardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj instanceof JButton) { //elem clicked was a jbutton
                JButton b1 = (JButton) obj;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) { // use for loops to find the button was pressed
                        if (b1 == theView.getBoard()[i][j]) {
                            theView.placeImage(i, j, theModel.getPlayer());
                            theModel.addData(i, j);
                            theModel.decreaseNumSquares();
                            System.out.println(theModel.getNumFreeSquares());

                            if (theModel.haveWinner(i, j)) {
                                theView.playAudio(false, theModel.getPlayer());
                                theView.disableButtons();
                                theModel.increaseScore(false);
                                theView.setScoreLabel(theModel.getXScore(), theModel.getOScore(), theModel.getTieScore());
                                theView.setWinningLabel(theModel.getPlayer(), false);

                            } else if (theModel.getNumFreeSquares() == 0) {
                                theView.playAudio(true, null);
                                theModel.increaseScore(true);
                                theView.setScoreLabel(theModel.getXScore(), theModel.getOScore(), theModel.getTieScore());
                                theView.setWinningLabel(null, true);
                            } else {
                                theModel.swapPlayer();
                                theView.setLabel(theModel.getPlayer());
                            }
                        }
                    }
                }
            }
        }
    }
}