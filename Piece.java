import java.awt.*;
import java.util.ArrayList;

abstract class Piece {

        public ColorOfPiece color;
        public  int postion;

        public Piece(ColorOfPiece color, int postion) {
            this.color = color;
            this.postion=postion;
        }

        public void setPostion(int newpos){
            this.postion=newpos;
        }
        public ColorOfPiece getColor() {
            return color;
        }
        public abstract ArrayList legalMove(Piece[][] board);
        public abstract Image getImage();
        public int getPosition(){
            return postion;
        }
        abstract Piece copyof();

    }
