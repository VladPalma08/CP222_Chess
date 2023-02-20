import java.awt.*;
import java.util.ArrayList;

abstract class Piece {
        public ColorOfPiece color;
        public  int position;


    public Piece(ColorOfPiece color, int position) {
            this.color = color;
            this.position = position;
        }
        public void setPosition(Piece piece){
            this.position=position;
        }
        public ColorOfPiece getColor() {
            return color;
        }
        public abstract ArrayList<Move> legalMove(Game board);
        public abstract Image getImage();
        public int getPosition(){
            return position;
        }
        public abstract Piece movePiece(Move move); //will apply move to existing piece and return new piece with the updated position

        public boolean isKing(){
            return false;
        } //every piece except king will return false for this
     public void put(Piece p) {
        p.setPosition(this); //when we call this method we will set its position to whatever the piece position is
    }

    public void setpostion(int newpos){
        this.position=newpos;
    }
}
