import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import static java.lang.System.currentTimeMillis;

class Queen extends Piece {
    private BufferedImage image;
    public Queen(ColorOfPiece color ,int postion) {
        super(color,postion);
        try {
            this.image = ImageIO.read(new File("./"+color+"queen.png"));
        }catch (Exception e){
            System.out.println("could not find image queen");
//            Long starttimee=currentTimeMillis();
//            System.out.println(p1.getClass());
//            Long endtimee=currentTimeMillis();
//            System.out.println("w"+(endtimee-starttimee));
        }
    }
    @Override
    public ArrayList legalMove(Piece[][] board) {
        int postion = this.postion;
        ArrayList possiblemoves= new ArrayList<Integer>();
        for (int i = (postion + 9); i%8>0; i += 9) {
            if ((i) < 64 && (int) (i) >= 0 && board[((i) / 8)][((i) % 8)] == null) {
                possiblemoves.add(i);
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (board[((i) / 8)][((i) % 8)].getColor() != color) {
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i = (postion - 7); i%8>0; i += -7) {
            if ((i) < 64 && (int) (i) >= 0 && board[((i) / 8)][((i) % 8)] == null) {
                possiblemoves.add(i);
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (board[((i) / 8)][((i) % 8)].getColor() !=color) {
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i = (postion + 7); i%8!=7; i += +7) {
            if ((i) < 64 && (int) (i) >= 0 && board[((i) / 8)][((i) % 8)] == null) {
                possiblemoves.add(i);
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (board[((i) / 8)][((i) % 8)].getColor() != color) {
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i = (postion - 9); i%8!=7; i += -9) {
            if ((i) < 64 && (int) (i) >= 0 && board[((i) / 8)][((i) % 8)] == null) {
                possiblemoves.add(i);
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (board[((i) / 8)][((i) % 8)].getColor() != color) {
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i =(postion+8); i<64; i+=8){
            if( (i)<64&&(int) (i)>=0&&board[((i)/8)][((i)%8)]==null){
                possiblemoves.add(i);
            }else if( (i)<64&&(int) (i)>=0){
                if(board[((i)/8)][((i)%8)].getColor()!=color){
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i =(postion-8); i<64; i+=-8){
            if( (i)<64&&(int) (i)>=0&&board[((i)/8)][((i)%8)]==null){
                possiblemoves.add(i);
            }else if( (i)<64&&(int) (i)>=0){
                if(board[((i)/8)][((i)%8)].getColor()!=color){
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i =(postion+1); i/8==postion/8; i+=1){
            if( (i)<64&&(int) (i)>=0&&board[((i)/8)][((i)%8)]==null){
                possiblemoves.add(i);
            }else if( (i)<64&&(int) (i)>=0){
                if(board[((i)/8)][((i)%8)].getColor()!=color){
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i =(postion-1); i/8==postion/8; i+=-1){
            if( (i)<64&&(int) (i)>=0&&board[((i)/8)][((i)%8)]==null){
                possiblemoves.add(i);
            }else if( (i)<64&&(int) (i)>=0){
                if(board[((i)/8)][((i)%8)].getColor()!=color){
                    possiblemoves.add(i);
                }
                break;
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
        Piece copy=new Queen(color,postion);
        return copy;
    }
}