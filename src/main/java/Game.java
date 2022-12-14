import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    Arena arena = new Arena(80, 24);

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() {
        while (true) {
            try {
                arena.retrieveCoins();
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                } else if (key.getKeyType() == KeyType.EOF) break;
                if (arena.verifyMonsterCollisions()) {
                    System.out.println("YOU DIED");
                    screen.close();
                }
                arena.moveMonsters();
                if (arena.verifyMonsterCollisions()) {
                    System.out.println("YOU DIED");
                    screen.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
