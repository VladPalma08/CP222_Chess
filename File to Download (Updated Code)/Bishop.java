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
    public ArrayList legalMove(Piece[][] board) {
        int postion = this.postion;
        ArrayList possiblemoves= new ArrayList<Integer>();
        for (int i = (postion + 9); i%8>0; i += 9) {
            if ((i) < 64 && (int) (i) >= 0 && board[((i) / 8)][((i) % 8)] == null) {
                possiblemoves.add(i);
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (board[((i) / 8)][((i) % 8)].getColor() != this.color) {
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i = (postion - 7); i%8>0; i += -7) {
            if ((i) < 64 && (int) (i) >= 0 && board[((i) / 8)][((i) % 8)] == null) {
                possiblemoves.add(i);
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (board[((i) / 8)][((i) % 8)].getColor() != this.color) {
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i = (postion + 7); i%8!=7; i += +7) {
            if ((i) < 64 && (int) (i) >= 0 && board[((i) / 8)][((i) % 8)] == null) {
                possiblemoves.add(i);
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (board[((i) / 8)][((i) % 8)].getColor() != this.color) {
                    possiblemoves.add(i);
                }
                break;
            }
        }
        for (int i = (postion - 9); i%8!=7; i += -9) {
            if ((i) < 64 && (int) (i) >= 0 && board[((i) / 8)][((i) % 8)] == null) {
                possiblemoves.add(i);
            } else if ((i) < 64 && (int) (i) >= 0) {
                if (board[((i) / 8)][((i) % 8)].getColor() != this.color) {
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

    public Piece copyof() {
        Piece copy=new Bishop(color,postion);
        return copy;
    }
}
