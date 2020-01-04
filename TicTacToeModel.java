public class TicTacToeModel {
    private int numFreeSquares;
    private int xScore, oScore, tieScore;
    private String winner;
    private String player;
    private String[][] boardData;

    public TicTacToeModel() {
        player = "x";
        boardData = new String[3][3];
        clearData();
        xScore = oScore = tieScore = 0;
        numFreeSquares = 9;
    }

    public void increaseScore(boolean tie) {
        if (tie) {
            tieScore++;
        } else if (player.equals("x")) {
            xScore++;
        } else if (player.equals("o")) {
            oScore++;
        }
    }

    public void decreaseNumSquares() {
        numFreeSquares--;
    }

    public int getTieScore() {
        return tieScore;
    }

    public int getXScore() {
        return xScore;
    }

    public int getOScore() {
        return oScore;
    }

    public void resetNumFreeSquares() {
        numFreeSquares = 9;
    }

    public int getNumFreeSquares() {
        return numFreeSquares;
    }

    public void addData(int i, int j) {
        boardData[i][j] = player;
    }

    public void swapPlayer() {
        if (player.equals("x")) player = "o";
        else player = "x";
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean haveWinner(int row, int col) {
        //the earliest we can have a winner is after player X's 3rd move

        if (numFreeSquares > 4) return false;

        // check row "row"
        if (boardData[row][0].equals(boardData[row][1]) && boardData[row][0].equals(boardData[row][2])) return true;

        // check column "col"

        if (boardData[0][col].equals(boardData[1][col]) && boardData[0][col].equals(boardData[2][col])) return true;

        // if row=col check one diagonal

        if (row == col)
            if (boardData[0][0].equals(boardData[1][1]) && boardData[0][0].equals(boardData[2][2])) return true;

        // if row=2-col check other diagonal

        if (row == 2 - col)
            if (boardData[0][2].equals(boardData[1][1]) && boardData[0][2].equals(boardData[2][0])) return true;
        // no winner yet
        return false;
    }

    public void clearData() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardData[i][j] = "place_holder";
            }
        }
    }
}