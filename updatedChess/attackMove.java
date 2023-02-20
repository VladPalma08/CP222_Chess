import java.util.HashMap;

public class attackMove extends Move{
    Piece attacked;

    public attackMove(Game board, Piece pieceToMove, int movePosition, Piece attacked) {
        super(board, pieceToMove, movePosition);
        this.attacked = attacked;
    }

    @Override
    public HashMap<Piece, Integer> execute() {
        return null;
    }
}
