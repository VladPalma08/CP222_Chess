import java.util.ArrayList;

public class blackPlayer extends Player{
    Game board; //TODO: ASK WHY U HAVE TO INITIALIZE THIS TO ACCESS BOARD WHEN ITS INITIALIZED IN SUPER??
    public blackPlayer(Game board, ArrayList<Move> moves, ArrayList<Move> opponentMoves){
        super(board, moves, opponentMoves);

    }

    @Override
    public ColorOfPiece getColor() {
        return ColorOfPiece.BLACK;
    }

    @Override
    public ArrayList<Piece> getPieces() {
        return board.getBlackPieces(); //TODO: create method in board that gets all current black pieces
    }

    @Override
    public Player getOpponent() {
        return board.getWhitePlayer();
    }
}
