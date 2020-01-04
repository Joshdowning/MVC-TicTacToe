
public class MVCTicTacToe
{
    public static void main(String[]args){
        TicTacToeView theView = new TicTacToeView();
        TicTacToeModel theModel = new TicTacToeModel();
        TicTacToeController theController = new TicTacToeController(theView,theModel);        
    }    
}
