public class Robot {

    private int xPosition , yPostion ;
    private boolean isPenUp ; // false = down ; true = up
    private int direction ;

    // Getters
    public int getxPosition() {return xPosition;}
    public int getyPosition() {return yPostion;}
    public boolean isPenUp() {return isPenUp;}
    public int getDirection() {return direction;}

    //Setters
    public void setxPosition(int xPosition) {this.xPosition = xPosition;}
    public void setyPosition(int yPostion) {this.yPostion = yPostion;}
    public void setPenUp(boolean penUp) {isPenUp = penUp;}
    public void setDirection(int direction) {this.direction = direction;}
}
