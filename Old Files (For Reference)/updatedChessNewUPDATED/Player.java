
import java.util.ArrayList;
import java.util.HashMap;


public abstract class Player {
    private Game board;
    private King king;
    private ArrayList<Move> moves, enemyMoves, canTake; //or whatever will be used to store the possible moves
    private Piece piece;
    private boolean check,canEscape;

    //TODO: Create Moves class that will store piece's possible moves

    public Player(Game board, ArrayList<Move> moves, ArrayList<Move> enemyMoves){
        this.board = board;
        this.moves = moves;


        this.king = getKing();
        this.check = isInCheck();
        this.canEscape = canEscape();


    }

    //override in black and white class to get all pieces of both sides
    public abstract ArrayList<Piece> getPieces(); // to get current pieces on board for black and white side
    public abstract Player getOpponent();
    public abstract ColorOfPiece getColor();

    public King getKing(){
        for(Piece piece: getPieces()){
            if (piece.isKing()) { //have boolean in piece abstract class that is used for determining king
                return king;
            }
        }
        return king;
        //since we are creating every piece as their own object, we can keep track of the king's position to check for things like check,checkmate, etc
    }

    public ArrayList<Move> getLegalMoves(){
        return this.moves;
    }


    //this method will be used to determine check, checkmate, stalemate, etc
    public ArrayList<Move> allPossibleAttacks(int piecePosition, ArrayList<Move> enemyMoves){ /*enemyMoves will correspond to current side's enemy (so if ur working
                                                                                                 on Black, then enemyMove should be all possible moves of White)*/
        canTake = new ArrayList<>();
        for (Move moves: enemyMoves ){ //(for every move in arraylist 'enemyMoves')
            if(piecePosition == moves.getAttacks()){ //TODO: in Moves class, create method that will get possible attacks of all pieces
                //if the piecePosition is equal to any position that enemy can move to then add it to canTake
                canTake.add(moves);
            }
        }
        return canTake;
    }
    public boolean detectLegalMove(Move move){
        return moves.contains(move);
    }

    public boolean isInCheck(){
        if(allPossibleAttacks(this.king.position, enemyMoves).isEmpty()){
            //if any of enemy moves can go to king's position, then allPossibleAttacks would have some moves stored in it
            return false;
        }
        else {
            return true;
        }
    }

    public boolean canEscape(){
        for(Move moves: moves){
            updateMoves newBoard = makeMove(moves);
            if (newBoard.getMoveStatus() == moveStatus.DONE){
                return true;
            }

        }
        return false;
    }

    public boolean isInCheckMate(){
            return isInCheck() && canEscape() == false;

    }

    public boolean isInStaleMate(){
        return !isInCheck() && canEscape() == false;
    }

    public updateMoves makeMove(Move move){ //
        updateMoves update;
        HashMap<Piece, Integer> afterMove = move.execute();
        ArrayList<Move> kingAttacks = new ArrayList<>();
        for (Piece piece: afterMove.keySet()){
            if (piece.isKing()) {
                Piece king = piece;
                kingAttacks = this.allPossibleAttacks(board.getCurrentPlayer().getOpponent().getKing().getPosition(),
                        board.getCurrentPlayer().getLegalMoves());
            }
        }

        if(!detectLegalMove(move)){
            update = new updateMoves(this.board, move, moveStatus.ILLEGALMOVE);
        }else if(!kingAttacks.isEmpty()) {
            update = new updateMoves(this.board, move, moveStatus.PUTS_IN_CHECK);  //this is to make sure player can't make move that would put their king in check
        }else{
            update = new updateMoves(this.board, move, moveStatus.DONE);
        }
        return update;

    }






}
