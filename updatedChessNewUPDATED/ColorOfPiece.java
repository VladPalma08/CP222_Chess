public enum ColorOfPiece {
    WHITE{
        @Override
        public Player choosePlayer(whitePlayer wp, blackPlayer bp) {
            return wp;
        }
    },
    BLACK {
        @Override
        public Player choosePlayer(whitePlayer wp, blackPlayer bp) {
            return bp;
        }
    };

    public abstract Player choosePlayer(whitePlayer wp, blackPlayer bp);
}
