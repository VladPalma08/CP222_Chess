public class updateMoves {
    //this class will be used to update the board after a move is successfully made
    private Game updatedBoard;
    private Move move;
    private moveStatus moveStatus;

    public updateMoves(Game updatedBoard, Move move, moveStatus moveStatus){
        this.updatedBoard = updatedBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public Game getUpdatedBoard(){return this.updatedBoard;}

    public moveStatus getMoveStatus(){
        return moveStatus;
    }



}
