import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class Bishop extends Piece {
    private BufferedImage image;
    public Bishop(ColorOfPiece color,int postion) {
        super(color, postion);
        try {
            this.image = ImageIO.read(new File("./"+color+"bishop.png"));
        }catch (Exception e){
            System.out.println("could not find image");
        }
    }
    @Override
    public ArrayList<Move> legalMove(Game board) {
        int position = this.position;
        Piece[][] tile = board.getBoard();
        ArrayList<Move> possibleMoves= new ArrayList<>();
        Piece pieceAttacked = board.getPiece(position);
        for (int i = (position + 9); i%8>0; i += 9) {
            if ((i) < 64 && (int) (i) >= 0 && tile[((i) / 8)][((i) % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, position));
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (tile[((i) / 8)][((i) % 8)].getColor() != this.color) {
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i = (position - 7); i%8>0; i += -7) {
            if ((i) < 64 && (int) (i) >= 0 && tile[((i) / 8)][((i) % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, position));
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (tile[((i) / 8)][((i) % 8)].getColor() != this.color) {
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i = (position + 7); i%8!=7; i += +7) {
            if ((i) < 64 && (int) (i) >= 0 && tile[((i) / 8)][((i) % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, position));
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (tile[((i) / 8)][((i) % 8)].getColor() != this.color) {
                    possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
                }
                break;
            }
        }
        for (int i = (position - 9); i%8!=7; i += -9) {
            if ((i) < 64 && (int) (i) >= 0 && tile[((i) / 8)][((i) % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, position));
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (tile[((i) / 8)][((i) % 8)].getColor() != this.color) {
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

    @Override
    public Piece movePiece(Move move) {
        return new Bishop(move.getMovePiece().getColor(), move.movePosition);
    } //creating new piece to represent new move
}
