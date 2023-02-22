import java.util.HashMap;

public class regularMove extends Move {
    public regularMove(Game board, Piece pieceToMove, int movePosition) {
        super(board, pieceToMove, movePosition);
    }

    @Override
    public HashMap<Piece,Integer> execute() { //setting value of all the pieces and the piece moves in a hashmap
        //hashmap represents an "imaginary board" with all the pieces positions and new position of moved piece
        for(Piece piece: board.getCurrentPlayer().getPieces()){
            if (pieceToMove !=  piece){
                board.setPiece(piece);
            }
        }
        for(Piece piece: board.getCurrentPlayer().getOpponent().getPieces()){ //for each piece in current player's opponent's pieces
            board.setPiece(piece);
        }

        board.setPiece(pieceToMove.movePiece(this));
        board.setMoveMaker(board.getCurrentPlayer().getOpponent().getColor()); //change to next color
        return board.boardConfig;
    }
}
