import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class Pawn extends Piece {
    private BufferedImage image;

    public Pawn(ColorOfPiece color, int postion) {
        super(color, postion);
        try {
            this.image = ImageIO.read(new File("./"+color+"pawn.png"));
        }catch (Exception e){
            System.out.println("could not find image");
        }
    }
    @Override
    public ArrayList legalMove(Piece[][] board) {
        int postion = this.postion;
        ArrayList possiblemoves= new ArrayList<Integer>();
        ArrayList mightadd= new ArrayList<Integer>();
        int forward;
        int sid1;
        int sid2;
        int advance=0;
        if (this.color == ColorOfPiece.WHITE) {
            sid1 = (postion - 9);
            forward = (postion - 8);
            sid2 = (postion - 7);
            if(postion/8==6){
                advance=postion-16;
            }
        } else {
            sid1 = (postion + 9);
            forward = (postion + 8);
            sid2 = (postion + 7);
            if(postion/8==1){
                advance=postion+16;
            }
        }
        if (advance!=0) {
            if (advance < 64 && advance >= 0 && board[(advance / 8)][(advance % 8)] == null) {
                possiblemoves.add(advance);
            }
        }
        if (forward < 64 && forward >= 0 && board[(forward / 8)][(forward % 8)] == null) {
            possiblemoves.add(forward);
        }if (sid1 < 64 && sid1 >= 0 &&board[(sid1 / 8)][(sid1 % 8)]!= null) {
            if( board[(sid1 / 8)][(sid1 % 8)].getColor() != this.color) {
                possiblemoves.add(sid1);
            }
        }if (sid2 < 64 && sid2 >= 0 &&board[(sid2 / 8)][(sid2 % 8)] != null) {
            if( board[(sid2 / 8)][(sid2 % 8)].getColor() != this.color){
                possiblemoves.add(sid2);
            }
        }
        return possiblemoves;
    }

    @Override
    public Image getImage() {
        return image;
    }
    @Override
    public Piece copyof() {
        Piece copy=new Pawn(color,postion);
        return copy;
    }

}
