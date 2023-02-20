import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class Knight extends Piece {
    private BufferedImage image;
    public Knight(ColorOfPiece color, int position) {
        super(color,position);
        try {
            this.image = ImageIO.read(new File("./"+color+"knight.png"));
        }catch (Exception e){
            System.out.println("could not find image knight");
        }
    }
    @Override
    public ArrayList<Move> legalMove(Game board) {
        int position = this.position;
        Piece[][] tile = board.getBoard();
        ArrayList<Move> possibleMoves= new ArrayList<>();
        ArrayList<Integer> mightAdd= new ArrayList<>();
            mightAdd.add(position+6);
            mightAdd.add(position+10);
            mightAdd.add(position+15);
            mightAdd.add(position+17);
            mightAdd.add(position-6);
            mightAdd.add(position-10);
            mightAdd.add(position-15);
            mightAdd.add(position-17);
            for (int i =0; i<=mightAdd.size()-1; i++){
                if((int) mightAdd.get(i) < 64 && (int) mightAdd.get(i) >= 0 &&
                        tile[((int)mightAdd.get(i)/8)][((int)mightAdd.get(i)%8)] == null){
                    int newPosition = mightAdd.get(i);
                    possibleMoves.add(new regularMove(board, this, newPosition));
                }else if((int) mightAdd.get(i) < 64 && (int) mightAdd.get(i) >= 0 &&
                        tile[((int)mightAdd.get(i)/8)][((int)mightAdd.get(i)%8)].getColor() != this.color){
                    int newPosition = mightAdd.get(i);
                    Piece pieceAttacked = board.getPiece(newPosition);
                    possibleMoves.add(new attackMove(board, this, newPosition, pieceAttacked));
                }
            }
        return possibleMoves;
    }
    @Override
    public Image getImage() {
        return image;
    }

    public Piece movePiece(Move move) {
        return new Knight(move.getMovePiece().getColor(), move.movePosition);
    } //creating new piece to represent new move
}
