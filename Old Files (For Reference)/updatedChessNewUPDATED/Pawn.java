import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class Pawn extends Piece {
    private BufferedImage image;

    public Pawn(ColorOfPiece color, int position) {
        super(color, position);
        try {
            this.image = ImageIO.read(new File("./"+color+"pawn.png"));
        }catch (Exception e){
            System.out.println("could not find image");
        }
    }
    @Override
    public ArrayList<Move> legalMove(Game board) {
        int position = this.position;
        Piece[][] tile = board.getBoard();
        ArrayList<Move> possibleMoves= new ArrayList<>();
        int forward;
        int sid1;
        int sid2;
        int advance=0; //advance will be used for pawn ability to move foward two spaces in the beginning
        if (this.color == ColorOfPiece.WHITE) {
            sid1 = (position - 9);
            forward = (position - 8);
            sid2 = (position - 7);
            if(position/8==6){
                advance=position-16;
            }
            else {
                advance = 0;
            }
        } else {
            sid1 = (position + 9);
            forward = (position + 8);
            sid2 = (position + 7);
            if(position/8==1){
                advance=position+16;
            }
            else {
                advance = 0;
            }
        }
        //if pawn has not been moved then it has option to move to tile two spaces ahead:
        if (advance!=0) {
            if (advance < 64 && advance >= 0 && tile[(advance / 8)][(advance % 8)] == null) {
                possibleMoves.add(new regularMove(board, this, advance));
            }
        }
        //for moving forward a tile:
        if (forward < 64 && forward >= 0 && tile[(forward / 8)][(forward % 8)] == null) {
            possibleMoves.add(new regularMove(board, this, forward));
        }
        //detecting possible pawn attacks (bc pawns attack diagonally):
        if (tile[(sid1 / 8)][(sid1 % 8)] != null) {
            if(sid1 < 64 && sid1 >= 0 && tile[(sid1 / 8)][(sid1 % 8)].getColor() != this.color) {
                Piece pieceAttacked = board.getPiece(sid1);
                possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
            }
        }
        if (tile[(sid2 / 8)][(sid2 % 8)] != null) {
            if(sid2 < 64 && sid2 >= 0 && tile[(sid2 / 8)][(sid2 % 8)].getColor() != this.color){
                Piece pieceAttacked = board.getPiece(sid2);
                possibleMoves.add(new attackMove(board, this, position, pieceAttacked));
            }
        }
        return possibleMoves;
    }

    @Override
    public Image getImage() {
        return image;
    }
    public Piece movePiece(Move move) {
        return new Pawn(move.getMovePiece().getColor(), move.movePosition);
    } //creating new piece to represent new move

}
