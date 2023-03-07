import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;

public class Game {
    public Piece[][] board;
    public ArrayList<Piece> whitePieces, blackPieces;

    public Game(){
        this.board = new Piece[8][8];
        fillBoard();
    }

    public Game(Piece[][] existinggame){
        this.board = new Piece[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8;j++) {
                if(existinggame[i][j]!=null) {
                    board[i][j] = existinggame[i][j].copyof();
                }
            }
        }
    }
    public int getking(ColorOfPiece color) {
        for (Piece[] p : this.board) {
            for (Piece p1 : p) {
                if (p1!=null&& p1.getClass() == King.class&&p1.getColor()==color) {
                    return p1.postion;
                }
            }
        }
        return 0;
    }



    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public void fillBoard() {
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

    public ArrayList getWhiteMoves() {
        ArrayList<Integer> whiteMoves = new ArrayList<Integer>();
        for (Piece[] p : this.board) {
            for (Piece p1 : p) {
                if (p1 != null) {
                    if (p1.getColor() == ColorOfPiece.WHITE) {

                        ArrayList<Integer> toadd = p1.legalMove(board);
                        for (int i : toadd) {
                            if (!whiteMoves.contains(i)) {
                                whiteMoves.add(i);
                            }
                        }
                    }
                }
            }
        }
        return whiteMoves;
    }

    public ArrayList getBlackMoves() {
        ArrayList<Integer> blackMoves = new ArrayList<Integer>();
        for (Piece[] p : this.board) {
            for (Piece p1 : p) {
                if (p1 != null) {
                    if (p1.getColor() == ColorOfPiece.BLACK) {
                        ArrayList<Integer> toAdd = p1.legalMove(board);
                        for (int i : toAdd) {
                            if (!blackMoves.contains(i)) {
                                blackMoves.add(i);

                            }
                        }
                    }
                }
            }
        }

        return blackMoves;
    }
    public ArrayList<Piece> getBlackPieces(){
        blackPieces = new ArrayList<>();
        for (Piece[] p:this.board){
            for (Piece p1: p) {
                if (p1!=null&&p1.getColor() == ColorOfPiece.BLACK){
                    blackPieces.add(p1);
                }
            }
        }
        return blackPieces;
    }

}



