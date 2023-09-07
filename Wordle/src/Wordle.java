import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.util.*;
import java.io.*;

public class Wordle extends Canvas implements KeyListener, Runnable {

    private BufferedImage back;
    private List<String> words = new ArrayList<String>(), word = new ArrayList<String>(), guess = new ArrayList<String>();

    private int tries = 1;
    private boolean notWord = false, game = true;

    private Color let1 = Color.WHITE, let2 = Color.WHITE, let3 = Color.WHITE, let4 = Color.WHITE, let5 = Color.WHITE;

    private Graphics graphToBack;

    private Map<String, Integer> gmap = new HashMap<String, Integer>();
    
    public Wordle() {
        try {
            Scanner file = new Scanner(new File("C:/Users/Kevin/Desktop/Projects/Wordle/src/dictionary.txt"));
            
            while(file.hasNext()) {
                String str = file.nextLine();

                if(str.length() == 5) {
                    words.add(str);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        setBackground(Color.GRAY);
        setVisible(true);

        new Thread(this).start();
        addKeyListener(this);

        start();
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D)window;

        if(back == null)
            back = (BufferedImage)(createImage(getWidth(), getHeight()));

        graphToBack = back.createGraphics();

        if(game) {
            graphToBack.setColor(Color.GRAY);
            graphToBack.fillRect(0, 75 + (tries * 60) - 50, 640, 70);

            //code
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
            graphToBack.setColor(Color.WHITE);
            graphToBack.setFont(font);
            graphToBack.drawString("WORDLE", 213, 60);
            
            if(guess.size() > 0)
                graphToBack.drawString(guess.get(0), 170, 75 + (tries * 60));
            else
                graphToBack.drawString("_", 170, 75 + (tries * 60));

            if(guess.size() > 1)
                graphToBack.drawString(guess.get(1), 235, 75 + (tries * 60));
            else
                graphToBack.drawString("_", 235, 75 + (tries * 60));

            if(guess.size() > 2)
                graphToBack.drawString(guess.get(2), 300, 75 + (tries * 60));
            else
                graphToBack.drawString("_", 300, 75 + (tries * 60));

            if(guess.size() > 3)
                graphToBack.drawString(guess.get(3), 365, 75 + (tries * 60));
            else
                graphToBack.drawString("_", 365, 75 + (tries * 60));
                
            if(guess.size() > 4)
                graphToBack.drawString(guess.get(4), 430, 75 + (tries * 60));
            else
                graphToBack.drawString("_", 430, 75 + (tries * 60));

            if(notWord) {
                graphToBack.setColor(Color.RED);
                graphToBack.drawString("NOT A WORD", 160, 75 + (tries * 60));
            }
        }

        twoDGraph.drawImage(back, null, 0, 0);
    }

    public void compareWord() {
        for(int i = 0; i < 5; i++) {
            if(!gmap.containsKey(word.get(i)))
                gmap.put(word.get(i), 0);
            gmap.put(word.get(i), gmap.get(word.get(i)) + 1);
        }

        if(word.get(0).equals(guess.get(0))) {
            let1 = Color.GREEN;
            gmap.put(guess.get(0), gmap.get(guess.get(0)) - 1);
        }
        if(word.get(1).equals(guess.get(1))) {
            let2 = Color.GREEN;
            gmap.put(guess.get(1), gmap.get(guess.get(1)) - 1);
        }
        if(word.get(2).equals(guess.get(2))) {
            let3 = Color.GREEN;
            gmap.put(guess.get(2), gmap.get(guess.get(2)) - 1);
        }
        if(word.get(3).equals(guess.get(3))) {
            let4 = Color.GREEN;
            gmap.put(guess.get(3), gmap.get(guess.get(3)) - 1);
        }
        if(word.get(4).equals(guess.get(4))) {
            let5 = Color.GREEN;
            gmap.put(guess.get(4), gmap.get(guess.get(4)) - 1);
        }

        if(word.contains(guess.get(0))) {
            if(gmap.get(guess.get(0)) > 0 && let1 != Color.GREEN) {
                let1 = Color.YELLOW;
                gmap.put(guess.get(0), gmap.get(guess.get(0)) - 1);
            }
        }
        if(word.contains(guess.get(1))) {
            if(gmap.get(guess.get(1)) > 0 && let2 != Color.GREEN) {
                let2 = Color.YELLOW;
                gmap.put(guess.get(1), gmap.get(guess.get(1)) - 1);
            }
        }
        if(word.contains(guess.get(2))) {
            if(gmap.get(guess.get(2)) > 0 && let3 != Color.GREEN) {
                let3 = Color.YELLOW;
                gmap.put(guess.get(2), gmap.get(guess.get(2)) - 1);
            }
        }
        if(word.contains(guess.get(3))) {
            if(gmap.get(guess.get(3)) > 0 && let4 != Color.GREEN) {
                let4 = Color.YELLOW;
                gmap.put(guess.get(3), gmap.get(guess.get(3)) - 1);
            }
        }
        if(word.contains(guess.get(4))) {
            if(gmap.get(guess.get(4)) > 0 && let5 != Color.GREEN) {
                let5 = Color.YELLOW;
                gmap.put(guess.get(4), gmap.get(guess.get(4)) - 1);
            }
        }

        graphToBack.setColor(let1);
        graphToBack.drawString(guess.get(0), 170, 75 + (tries * 60));
        graphToBack.setColor(let2);
        graphToBack.drawString(guess.get(1), 235, 75 + (tries * 60));
        graphToBack.setColor(let3);
        graphToBack.drawString(guess.get(2), 300, 75 + (tries * 60));
        graphToBack.setColor(let4);
        graphToBack.drawString(guess.get(3), 365, 75 + (tries * 60));
        graphToBack.setColor(let5);
        graphToBack.drawString(guess.get(4), 430, 75 + (tries * 60));

        let1 = Color.WHITE;
        let2 = Color.WHITE;
        let3 = Color.WHITE;
        let4 = Color.WHITE;
        let5 = Color.WHITE;

        if(guess.toString().equals(word.toString())) {
            graphToBack.setColor(Color.BLUE);
            graphToBack.drawString("YOU WIN", 213, 265);

            game = false;
        }
        else if(tries == 6) {
            graphToBack.setColor(Color.RED);
            graphToBack.drawString("YOU LOSE", 192, 240);
            
            graphToBack.drawString(word.get(0), 170, 290);
            graphToBack.drawString(word.get(1), 235, 290);
            graphToBack.drawString(word.get(2), 300, 290);
            graphToBack.drawString(word.get(3), 365, 290);
            graphToBack.drawString(word.get(4), 430, 290);

            game = false;
        }

        gmap.clear();
    }

    public void start() {
        String str = words.get((int)(Math.random() * (words.size() - 1)));

        for(int i = 0; i < 5; i++) {
            word.add(str.substring(i, i + 1).toUpperCase());
        }

        //System.out.println(str);
    }

    public void addLetter(String let) {
        if(guess.size() < 5) {
            guess.add(let);
        }
    }

    public void keyPressed(KeyEvent e) {
        if(!game) {
            tries = 1;
            graphToBack.setColor(Color.GRAY);
            graphToBack.fillRect(0, 0, 640, 480);
            word.clear();
            guess.clear();
            gmap.clear();
            start();
            game = true;
        }
        if(e.getKeyCode() == 8 && guess.size() > 0) {
            guess.remove(guess.size() - 1);
            notWord = false;
        }
        if(e.getKeyCode() == 10 && guess.size() == 5) {
            if(words.contains(("" + guess.get(0) + guess.get(1) + guess.get(2) + guess.get(3) + guess.get(4)).toLowerCase())) {
                compareWord();
                guess.clear();
                tries++;
            }
            else if(guess.size() == 5) {
                notWord = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) {
        switch(("" + e.getKeyChar()).toUpperCase()) {
            case "A" : addLetter("A"); break;
            case "B" : addLetter("B"); break;
            case "C" : addLetter("C"); break;
            case "D" : addLetter("D"); break;
            case "E" : addLetter("E"); break;
            case "F" : addLetter("F"); break;
            case "G" : addLetter("G"); break;
            case "H" : addLetter("H"); break;
            case "I" : addLetter("I"); break;
            case "J" : addLetter("J"); break;
            case "K" : addLetter("K"); break;
            case "L" : addLetter("L"); break;
            case "M" : addLetter("M"); break;
            case "N" : addLetter("N"); break;
            case "O" : addLetter("O"); break;
            case "P" : addLetter("P"); break;
            case "Q" : addLetter("Q"); break;
            case "R" : addLetter("R"); break;
            case "S" : addLetter("S"); break;
            case "T" : addLetter("T"); break;
            case "U" : addLetter("U"); break;
            case "V" : addLetter("V"); break;
            case "W" : addLetter("W"); break;
            case "X" : addLetter("X"); break;
            case "Y" : addLetter("Y"); break;
            case "Z" : addLetter("Z"); break;
        }
    }

    public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(8);
				repaint();
			}
		}
        catch(Exception e) {
            e.printStackTrace();
        }
	}
}
