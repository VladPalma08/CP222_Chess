import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;


public class Game {
    public Piece[][] board;

    private whitePlayer whitePlayer;
    private blackPlayer blackPlayer;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    ArrayList<Move> whiteLegalMoves, blackLegalMoves;
    private Player currentPlayer;
    HashMap<Piece, Integer> boardConfig;
    ColorOfPiece nextMove;

    public Game(){
        this.board = new Piece[8][8];
        fillBoard();




    }



    public Piece[][] getBoard() { //will return the location of current piece in the array
        return this.board;
    }
    public Player getWhitePlayer(){return this.whitePlayer;}
    public Player getBlackPlayer(){
        return this.blackPlayer;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Piece getPiece(int position){
        Piece p2 = null;
        for (Piece[] p : this.board) {
            for (Piece p1 : p) {
                if (p1.getPosition() == position) {
                    p2 = p1;
                }
            }
        }
        return p2;
    }
    public HashMap<Piece, Integer> setPiece(Piece piece){
        this.boardConfig = new HashMap<>();
      boardConfig.put(piece, piece.getPosition());
      return boardConfig;
    }
    public ColorOfPiece setMoveMaker(ColorOfPiece nextMove){ //setting next move
        this.nextMove = nextMove;
        return nextMove;
    }




    public ArrayList<Piece> getWhitePieces(){
        return this.whitePieces;
    }
    public ArrayList<Piece> getBlackPieces(){
        return this.blackPieces;
    }



    public int getTile(Piece piece){
        return piece.getPosition();
    }




    public void fillBoard() { //filling the rows of the board
        /* Piece[][] represents the tiles of the board
        [0][0++] = {Rook, Knight, Bishop, ...}
        [1][0++] = {pawns ... }
        if a tile isn't filled, it has value of "null" */
        this.board[0][0] = new Rook(ColorOfPiece.BLACK, 0);
        this.board[0][1] = new Knight(ColorOfPiece.BLACK, 1);
        this.board[0][2] = new Bishop(ColorOfPiece.BLACK, 2);
        this.board[0][3] = new Queen(ColorOfPiece.BLACK, 3);
        this.board[0][4] = new King(ColorOfPiece.BLACK, 4);
        this.board[0][5] = new Bishop(ColorOfPiece.BLACK, 5);
        this.board[0][6] = new Knight(ColorOfPiece.BLACK, 6);
        this.board[0][7] = new Rook(ColorOfPiece.BLACK, 7);

        this.board[7][0] = new Rook(ColorOfPiece.WHITE, 56);
        this.board[7][1] = new Knight(ColorOfPiece.WHITE, 57);
        this.board[7][2] = new Bishop(ColorOfPiece.WHITE, 58);
        this.board[7][3] = new Queen(ColorOfPiece.WHITE, 59);
        this.board[7][4] = new King(ColorOfPiece.WHITE, 60);
        this.board[7][5] = new Bishop(ColorOfPiece.WHITE, 61);
        this.board[7][6] = new Knight(ColorOfPiece.WHITE, 62);
        this.board[7][7] = new Rook(ColorOfPiece.WHITE, 63);
        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(ColorOfPiece.BLACK, i + 8);
            this.board[6][i] = new Pawn(ColorOfPiece.WHITE, i + 48);
        }

    }




    public ArrayList<Move> getLegalMoves(ArrayList<Piece> pieces){
        ArrayList<Move> legalMoves = new ArrayList<>();
        for (Piece piece: pieces){
            legalMoves.addAll(piece.legalMove(this));
        }
        return legalMoves;
    }


    //IN ORDER TO GET PIECES THAT ARE CURRENTLY ON THE BOARD:
    public ArrayList<Piece> getCurrentPieces(Piece[][] board, ColorOfPiece color){
        ArrayList<Piece> currentPieces = new ArrayList<>();
        for (Piece[] p : board){
            for (Piece p1: p){ //need nested for loop bc it needs to iterate through array then indivd. objects in the array
                if (p1 != null){
                    Piece currentPiece = p1;
                    if (currentPiece.getColor() == color){
                        currentPieces.add(currentPiece);
                    }
                }
            }
        }
        return currentPieces;
    }


    public ArrayList<Move> getAllLegalMoves() {
        ArrayList<Move> allLegalMoves;
        whiteLegalMoves.addAll(blackLegalMoves);
        allLegalMoves = whiteLegalMoves;
        return allLegalMoves;

    }


}



