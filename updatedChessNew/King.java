import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class King extends Piece {
    private BufferedImage image;
    public King(ColorOfPiece color,int position) {
        super(color,position);
        try {
            this.image = ImageIO.read(new File("./"+color+"king.png"));
        }catch (Exception e){
            System.out.println("could not find image king");
        }
    }

    @Override
    public ArrayList<Move> legalMove(Game board) {
        int position = this.position;
        Piece[][] tile = board.getBoard();
        ArrayList<Move> possibleMoves= new ArrayList<>();

        ArrayList mightAdd = new ArrayList<Integer>();
            mightAdd.add(position+7);
            mightAdd.add(position+8);
            mightAdd.add(position+9);
            mightAdd.add(position+1);
            mightAdd.add(position-1);
            mightAdd.add(position-7);
            mightAdd.add(position-8);
            mightAdd.add(position-9);
            for (int i =0; i<=mightAdd.size()-1; i++){
                if((int) mightAdd.get(i)<64&&(int) mightAdd.get(i)>=0&&tile[((int)mightAdd.get(i)/8)][((int)mightAdd.get(i)%8)]==null){
                    int newPosition = (int) mightAdd.get(i);
                    possibleMoves.add(new regularMove(board, this, newPosition));
                }else if((int) mightAdd.get(i)<64&&(int) mightAdd.get(i)>=0&&tile[((int)mightAdd.get(i)/8)][((int)mightAdd.get(i)%8)].getColor()!=this.color
                        &&tile[((int)mightAdd.get(i)/8)][((int)mightAdd.get(i)%8)].isKing() == false){
                    int newPosition = (int)mightAdd.get(i);
                    Piece pieceAttacked = board.getPiece(newPosition);
                    possibleMoves.add(new attackMove(board, this, newPosition, pieceAttacked));
                }
            }

        return possibleMoves;
    }

    @Override
    public boolean isKing(){
        return true;
    }

    @Override
    public Image getImage() {
        return image;
    }
    public Piece movePiece(Move move) {
        return new King(move.getMovePiece().getColor(), move.movePosition);
    } //creating new piece to represent new move
}
