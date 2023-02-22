import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class Queen extends Piece {
    private BufferedImage image;
    public Queen(ColorOfPiece color ,int position) {
        super(color,position);
        try {
            this.image = ImageIO.read(new File("./"+color+"queen.jpg"));
        }catch (Exception e){
            System.out.println("could not find image queen");
        }
    }
    @Override
    public ArrayList<Move> legalMove(Game board) {
        int position = this.position;
        Piece[][] tile = board.getBoard();
        ArrayList<Move> possibleMoves= new ArrayList<>();
        Piece pieceAttacked = board.getPiece(position);
        for (int i = (position + 9); i%8>0; i += 9) {
            if (i < 64 && i >= 0 && tile[(i / 8)][(i % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, position));
            } else if (i < 64 && i >= 0) {
                if (tile[(i / 8)][(i % 8)].getColor() != this.color) {
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i = (position - 7); i%8>0; i += -7) {
            if (i < 64 && i >= 0 && tile[(i / 8)][(i % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, position));
            } else if (i < 64 && i >= 0) {
                if (tile[(i / 8)][(i % 8)].getColor() != this.color) {
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i = (position + 7); i%8!=7; i += +7) {
            if (i < 64 && i >= 0 && tile[(i / 8)][(i % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, position));
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (tile[((i) / 8)][((i) % 8)].getColor() != this.color) {
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i = (position - 9); i%8 != 7; i += -9) {
            if (i < 64 && i >= 0 && tile[(i / 8)][(i % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, position));
            } else if (i < 64 && i >= 0) {
                if (tile[(i / 8)][(i % 8)].getColor() != this.color) {
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i =(position+8); i<64; i+=8){
            if( (i)<64&&(int) (i)>=0&&tile[((i)/8)][((i)%8)]==null){
                possibleMoves.add(new regularMove(board, this, position));
            }else if( (i)<64&&(int) (i)>=0){
                if(tile[((i)/8)][((i)%8)].getColor()!=this.color){
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i =(position-8); i<64; i+=-8){
            if( (i)<64&&(int) (i)>=0&&tile[((i)/8)][((i)%8)]==null){
                possibleMoves.add(new regularMove(board, this, position));
            }else if( (i)<64&&(int) (i)>=0){
                if(tile[((i)/8)][((i)%8)].getColor()!=this.color){
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i =(position+1); i/8==position/8; i+=1){
            if( (i)<64&&(int) (i)>=0&&tile[((i)/8)][((i)%8)]==null){
                possibleMoves.add(new regularMove(board, this, position));
            }else if( (i)<64&&(int) (i)>=0){
                if(tile[((i)/8)][((i)%8)].getColor()!=this.color){
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i =(position-1); i/8==position/8; i+=-1){
            if( (i)<64&&(int) (i)>=0&&tile[((i)/8)][((i)%8)]==null){
                possibleMoves.add(new regularMove(board, this, position));
            }else if( (i)<64&&(int) (i)>=0){
                if(tile[((i)/8)][((i)%8)].getColor()!=this.color){
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }

        return possibleMoves;
    }

    @Override
    public Image getImage() {
        return image;
    }
    public Piece movePiece(Move move) {
        return new Queen(move.getMovePiece().getColor(), move.movePosition);
    } //creating new piece to represent new move
}