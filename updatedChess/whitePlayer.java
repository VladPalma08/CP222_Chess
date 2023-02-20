import java.util.ArrayList;

public class whitePlayer extends Player {
    Game board;
    public whitePlayer(Game board, ArrayList<Move> moves, ArrayList<Move> opponentMoves){
        super(board, moves, opponentMoves);

    }

    @Override
    public ColorOfPiece getColor() {
        return ColorOfPiece.WHITE;
    }

    @Override
    public ArrayList<Piece> getPieces() {
        return board.getWhitePieces();
    }

    @Override
    public Player getOpponent() {
        return board.getBlackPlayer();
    }
}
