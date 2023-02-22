import java.util.ArrayList;

public abstract class Player {
    protected Game board;
    private King king;
    private ArrayList<Integer> legalMoves, enemyMoves, canTake; //or whatever will be used to store the possible moves
    private Piece piece;

    public Player(Game board, ArrayList<Integer> legalMoves, ArrayList<Integer> enemyMoves) {
        this.board = board;
        this.legalMoves = legalMoves;
        this.enemyMoves = enemyMoves;
    }
}
