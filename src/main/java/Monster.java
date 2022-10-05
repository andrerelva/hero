import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Element{
    public Monster(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#fC1C03"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "ඞ");
    }

    public void move(Position position) {
        this.position = position;
    }
}
