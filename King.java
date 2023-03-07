import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class King extends Piece {
    private BufferedImage image;
    public King(ColorOfPiece color,int postion) {
        super(color,postion);
        try {
            this.image = ImageIO.read(new File("./"+color+"king.png"));
        }catch (Exception e){
            System.out.println("could not find image king");
        }
    }

    @Override
    public ArrayList legalMove(Piece[][] board) {
        int postion = this.postion;
        ArrayList possiblemoves = new ArrayList<Integer>();
        ArrayList mightadd = new ArrayList<Integer>();
            mightadd.add(postion+7);
            mightadd.add(postion+8);
            mightadd.add(postion+9);
            mightadd.add(postion+1);
            mightadd.add(postion-1);
            mightadd.add(postion-7);
            mightadd.add(postion-8);
            mightadd.add(postion-9);
            for (int i =0; i<=mightadd.size()-1; i++){
                if((int) mightadd.get(i)<64&&(int) mightadd.get(i)>=0&&board[((int)mightadd.get(i)/8)][((int)mightadd.get(i)%8)]==null){
                    possiblemoves.add(mightadd.get(i));
                }else if((int) mightadd.get(i)<64&&(int) mightadd.get(i)>=0&&board[((int)mightadd.get(i)/8)][((int)mightadd.get(i)%8)].getColor()!=this.color
                        &&board[((int)mightadd.get(i)/8)][((int)mightadd.get(i)%8)].getClass()!=King.class){
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
        Piece copy=new King(color,postion);
        return copy;
    }
}
