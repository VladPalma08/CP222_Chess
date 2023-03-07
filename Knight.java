import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class Knight extends Piece {
    private BufferedImage image;
    public Knight(ColorOfPiece color, int postion) {
        super(color, postion);
        try {
            this.image = ImageIO.read(new File("./"+color+"knight.png"));
        }catch (Exception e){
            System.out.println("could not find image knight");
        }
    }

    @Override
    public ArrayList legalMove(Piece[][] board) {
        int postion = this.postion;
        ArrayList possiblemoves= new ArrayList<Integer>();
        ArrayList mightadd= new ArrayList<Integer>();
            mightadd.add(postion+15);
            mightadd.add(postion+17);
            mightadd.add(postion-15);
            mightadd.add(postion-17);
            for (int i =0; i<=mightadd.size()-1; i++){
                if((int) mightadd.get(i)<64&&(int) mightadd.get(i)>=0&&board[((int)mightadd.get(i)/8)][((int)mightadd.get(i)%8)]==null){
                    possiblemoves.add(mightadd.get(i));
                }else if((int) mightadd.get(i)<64&&(int) mightadd.get(i)>=0&&board[((int)mightadd.get(i)/8)][((int)mightadd.get(i)%8)].getColor()!=this.color){
                    possiblemoves.add(mightadd.get(i));
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
        Piece copy=new Knight(color,postion);
        return copy;
    }
}
