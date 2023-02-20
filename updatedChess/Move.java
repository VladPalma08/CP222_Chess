import java.util.ArrayList;
import java.util.HashMap;

public abstract class Move {
    Game board;
    Piece pieceToMove;
    int movePosition;

    Move(Game board, Piece pieceToMove, int movePosition){
        this.board = board;
        this.pieceToMove = pieceToMove;
        this.movePosition = movePosition;
    }

    public int getAttacks(){
        return movePosition;
    }
    public int getPosition(){
        return pieceToMove.getPosition();
    }

    public Piece getMovePiece(){return pieceToMove;}
    public boolean isAttack(){
        return false;
    }
    public Piece getAttackedPiece(){
        return null;
    }



    public abstract HashMap<Piece, Integer> execute();
    /*when you execute a move, this method will be creating a new version of the board
    with the updated moves*/

    public class moveCreator{
        public Move createMove(Game board, int position, int movePosition){
            for (Move move: board.getAllLegalMoves()){
                if(move.getPosition() == position && move.getAttacks() == movePosition){
                    return move;
                }
            }
            return null;
        }
    }

}
