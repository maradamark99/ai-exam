package maradamark99.egyszemelyes.maze;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class MazeCell {

    private boolean top;
    private boolean right;
    private boolean bottom;
    private boolean left;

    @Setter
    private Character content;

    public MazeCell() {
        top = false;
        bottom = false;
        left = false;
        right = false;
        content = ' ';
    }

    public void setTop() {
        this.top = true;
    }

    public void setBottom() {
        this.bottom = true;
    }

    public void setLeft() {
        this.left = true;
    }

    public void setRight() {
        this.right = true;
    }

}
