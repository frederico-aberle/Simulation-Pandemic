// 
// Decompiled by Procyon v0.5.36
// 

package terminal;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.awt.Component;
import java.awt.AWTKeyStroke;
import java.util.Set;
import java.util.Collections;
import javax.swing.Timer;
import java.util.concurrent.LinkedBlockingQueue;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class VTerm extends JFrame implements VT, ActionListener
{
    private static final long serialVersionUID = 103L;
    private static final byte DEAD_SHIFT = 1;
    private static final byte DEAD_CTRL = 2;
    private static final byte DEAD_ALT = 4;
    private static final char GREEK_OFFSET = '\u0140';
    private DrawingArea m_area;
    private char[] m_spaceBar;
    private int m_width;
    private int m_height;
    private int m_currentY;
    private int m_currentX;
    private LinkedBlockingQueue<Character> m_inputBuffer;
    private LinkedBlockingQueue<Character> m_delaySync;
    private Timer m_delayTimer;
    private static VTerm c_singleInstance;
    private char m_partialKeyType;
    private int m_partialKeyCode;
    private byte m_deadKeyStatusVector;
    private byte m_deadKeyMemoryVector;
    
    static {
        VTerm.c_singleInstance = null;
    }
    
    private VTerm() {
        this.m_partialKeyType = '\0';
        this.m_partialKeyCode = 0;
        this.m_deadKeyStatusVector = 0;
        this.m_deadKeyMemoryVector = 0;
    }
    
    private VTerm(final String title, final int width, final int height, final int console, final int style) {
        super(title);
        this.m_partialKeyType = '\0';
        this.m_partialKeyCode = 0;
        this.m_deadKeyStatusVector = 0;
        this.m_deadKeyMemoryVector = 0;
        if (console != 12 && console != 16 && console != 20) {
            throw new IndexOutOfBoundsException("VTerm error : invalid size of console");
        }
        this.m_width = width;
        this.m_height = height;
        this.m_currentY = 0;
        this.m_currentX = 0;
        this.m_inputBuffer = new LinkedBlockingQueue<Character>();
        this.m_delaySync = new LinkedBlockingQueue<Character>();
        this.m_spaceBar = new char[width];
        for (int i = 0; i < width; ++i) {
            this.m_spaceBar[i] = ' ';
        }
        this.setFocusTraversalKeys(0, Collections.EMPTY_SET);
        this.setFocusTraversalKeys(1, Collections.EMPTY_SET);
        this.setVisible(true);
        this.m_area = new DrawingArea(width, height, console, style);
        this.getContentPane().add(this.m_area);
        this.setSize(this.m_area.getTextWidth(), this.m_area.getTextHeight());
        this.setLocation(30, 20);
        this.setResizable(false);
        this.enableEvents(8L);
        (this.m_delayTimer = new Timer(1000, this)).setRepeats(false);
        this.setDefaultCloseOperation(0);
    }
    
    public static VTerm getInstance(final int lines, final int width, final String title) {
        return getInstance(lines, width, title, 20, 0);
    }
    
    public static VTerm getInstance(final int lines, final int width, final String title, final int size) {
        return getInstance(lines, width, title, size, 0);
    }
    
    public static VTerm getInstance(final int lines, final int width, final String title, final int size, final int style) {
        if (VTerm.c_singleInstance == null) {
            VTerm.c_singleInstance = new VTerm(title, width, lines, size, style);
        }
        else {
            VTerm.c_singleInstance.setTitle(title);
        }
        return VTerm.c_singleInstance;
    }
    
    @Override
    public void setCursor(final int y, final int x) {
        this.m_area.setCursorLocation(y, x);
        this.m_currentY = y;
        this.m_currentX = x;
    }
    
    @Override
    public void setCursor(final boolean visible) {
        this.m_area.setCursorVisibility(visible);
    }
    
    @Override
    public void setColor(final short color) {
        this.m_area.setColor(color);
    }
    
    @Override
    public void setCnC(final int y, final int x, final short color) {
        this.m_area.setCursorLocation(y, x);
        this.m_currentY = y;
        this.m_currentX = x;
        this.m_area.setColor(color);
    }
    
    @Override
    public void print(final CharSequence s) {
        this.m_area.printStringAt(s, this.m_currentY, this.m_currentX);
        this.m_currentX += s.length();
        while (this.m_currentX >= this.m_width) {
            ++this.m_currentY;
            this.m_currentX -= this.m_width;
        }
        this.m_currentY %= this.m_height;
        this.m_area.setCursorLocation(this.m_currentY, this.m_currentX);
    }
    
    @Override
    public void print(final char c) {
        this.m_area.printCharAt(c, this.m_currentY, this.m_currentX++);
        if (this.m_currentX == this.m_width) {
            this.m_currentX = 0;
            ++this.m_currentY;
            this.m_currentY %= this.m_height;
        }
        this.m_area.setCursorLocation(this.m_currentY, this.m_currentX);
    }
    
    @Override
    public void print(final int i, final int width) {
        if (width > 0) {
            this.print(String.format("%1$" + width + "d", i));
        }
        else {
            this.print(Integer.toString(i));
        }
    }
    
    @Override
    public void println(final CharSequence s) {
        this.print(s);
        this.m_currentX = 0;
        ++this.m_currentY;
        this.m_currentY %= this.m_height;
        this.m_area.setCursorLocation(this.m_currentY, this.m_currentX);
    }
    
    @Override
    public void println() {
        this.m_currentX = 0;
        this.m_currentY = (this.m_currentY + 1) % this.m_height;
        this.m_area.setCursorLocation(this.m_currentY, this.m_currentX);
    }
    
    @Override
    public void backspace() {
        if (this.m_currentX > 0 || this.m_currentY > 0) {
            int newY = this.m_currentY;
            int newX = this.m_currentX - 1;
            if (newX < 0) {
                newX = this.m_width - 1;
                --newY;
            }
            this.m_area.printCharAt(' ', newY, newX);
            this.m_currentX = newX;
            this.m_currentY = newY;
        }
        this.m_area.setCursorLocation(this.m_currentY, this.m_currentX);
    }
    
    @Override
    public void clearScreen() {
        this.m_area.setColor((short)6);
        this.m_area.eraseBuffer();
        final int n = 0;
        this.m_currentY = n;
        this.m_currentX = n;
        this.m_area.setCursorLocation(this.m_currentY, this.m_currentX);
    }
    
    @Override
    public void clearArea(final int y1, final int x1, int y2, int x2) {
        if (x1 < 0 || x1 >= this.m_width || y1 < 0 || y1 >= this.m_height) {
            throw new IndexOutOfBoundsException("VTerm.clearArea() error : invalid coordinate");
        }
        if (x2 >= this.m_width) {
            x2 = this.m_width - 1;
        }
        if (y2 >= this.m_height) {
            y2 = this.m_height - 1;
        }
        final int saveX = this.m_currentX;
        final int saveY = this.m_currentY;
        final String s = new String(this.m_spaceBar, 0, x2 - x1 + 1);
        for (int i = y1; i <= y2; ++i) {
            this.m_area.printStringAt(s, i, x1);
        }
        this.m_area.setCursorLocation(saveY, saveX);
    }
    
    @Override
    public void scrollArea(final int y1, final int x1, int y2, int x2) {
        if (x1 < 0 || x1 >= this.m_width || y1 < 0 || y1 >= this.m_height || y2 < y1 || x2 < x1) {
            throw new IndexOutOfBoundsException("VTerm.scrollArea() error : invalid coordinate(s)");
        }
        if (x2 >= this.m_width) {
            x2 = this.m_width - 1;
        }
        if (y2 >= this.m_height) {
            y2 = this.m_height - 1;
        }
        final int saveX = this.m_currentX;
        final int saveY = this.m_currentY;
        for (int row = y1 + 1; row <= y2; ++row) {
            for (int col = x1; col <= x2; ++col) {
                this.m_area.putCellContents(row - 1, col, this.m_area.getCellContents(row, col));
            }
        }
        for (int col2 = x1; col2 <= x2; ++col2) {
            this.m_area.putCellContents(y2, col2, ' ');
        }
        this.m_area.updateDisplay();
        this.m_area.setCursorLocation(saveY, saveX);
    }
    
    @Override
    public void scroll() {
        this.m_area.scroll();
    }
    
    @Override
    public void colorizeArea(final int y1, final int x1, int y2, int x2, final short color) {
        if (x1 < 0 || x1 >= this.m_width || y1 < 0 || y1 >= this.m_height) {
            throw new IndexOutOfBoundsException("VTerm.colorizeArea() error : invalid coordinate");
        }
        if (x2 >= this.m_width) {
            x2 = this.m_width - 1;
        }
        if (y2 >= this.m_height) {
            y2 = this.m_height - 1;
        }
        final int saveX = this.m_currentX;
        final int saveY = this.m_currentY;
        for (int i = y1; i <= y2; ++i) {
            for (int j = x1; j <= x2; ++j) {
                char current = this.m_area.getCellContents(i, j);
                current &= '\ue1ff';
                current |= (char)(color << 9);
                this.m_area.putCellContents(i, j, current);
            }
        }
        this.m_area.setCursorLocation(saveY, saveX);
        this.m_area.updateDisplay();
    }
    
    @Override
    public void printFrame(final int y1, final int x1, final int y2, final int x2) {
        this.setCursor(y1, x1);
        this.print('\u0109');
        this.setCursor(y1, x2);
        this.print('\u010a');
        this.setCursor(y2, x1);
        this.print('\u010b');
        this.setCursor(y2, x2);
        this.print('\u010c');
        for (int c = x1 + 1; c < x2; ++c) {
            this.setCursor(y1, c);
            this.print('\u0108');
            this.setCursor(y2, c);
            this.print('\u0108');
        }
        for (int r = y1 + 1; r < y2; ++r) {
            this.setCursor(r, x1);
            this.print('\u0107');
            this.setCursor(r, x2);
            this.print('\u0107');
        }
    }
    
    @Override
    public void printHLine(final int y, final int x1, final int x2, final boolean table) {
        for (int c = x1 + 1; c < x2; ++c) {
            this.setCursor(y, c);
            if (this.peekCellChar(y, c) == '\u0107') {
                this.print('\u010d');
            }
            else {
                this.print('\u0108');
            }
        }
        this.setCursor(y, x1);
        this.print(table ? '\u010e' : '\u0108');
        this.setCursor(y, x2);
        this.print(table ? '\u010f' : '\u0108');
    }
    
    @Override
    public void printVLine(final int x, final int y1, final int y2, final boolean table) {
        for (int r = y1 + 1; r < y2; ++r) {
            this.setCursor(r, x);
            if (this.peekCellChar(r, x) == '\u0108') {
                this.print('\u010d');
            }
            else {
                this.print('\u0107');
            }
        }
        this.setCursor(y1, x);
        this.print(table ? '\u0110' : '\u0107');
        this.setCursor(y2, x);
        this.print(table ? '\u0111' : '\u0107');
    }
    
    @Override
    public char greek(final char latin) {
        return (char)(latin + '\u0140');
    }
    
    @Override
    public char greek(final int latin) {
        return (char)(latin + 320);
    }
    
    @Override
    public char readChar() {
        char ch = '\u0100';
        while (ch >= '\u0100') {
            try {
                ch = this.m_inputBuffer.poll(1000L, TimeUnit.HOURS);
            }
            catch (Exception e) {
                System.out.println("VTerm.readChar() error : can't read");
            }
        }
        return ch;
    }
    
    @Override
    public int readKey() {
        int code = 0;
        try {
            code = this.m_inputBuffer.poll(1000L, TimeUnit.HOURS);
        }
        catch (Exception e) {
            System.out.println("VTerm.readKey() error : can't read");
        }
        return code;
    }
    
    @Override
    public boolean readBoolean() {
        final String VALIDATOR = "tTyYjJ1fFnN0";
        final String TRUE = "tTyYjJ1";
        char k;
        for (k = 'x'; "tTyYjJ1fFnN0".indexOf(k) == -1; k = this.readChar()) {}
        return "tTyYjJ1".indexOf(k) >= 0;
    }
    
    @Override
    public String readString(final int maxSize) {
        final char[] buffer = new char[maxSize];
        int inPointer = 0;
        char ch = '\0';
        while (ch != '\n') {
            ch = this.readChar();
            switch (ch) {
                case '\b': {
                    if (inPointer > 0) {
                        this.backspace();
                        --inPointer;
                        continue;
                    }
                    continue;
                }
                case '\u001b': {
                    for (int i = inPointer; i > 0; --i) {
                        this.backspace();
                    }
                    inPointer = 0;
                    continue;
                }
                case '\n': {
                    continue;
                }
                default: {
                    if (inPointer < maxSize) {
                        this.print(buffer[inPointer++] = ch);
                        continue;
                    }
                    continue;
                }
            }
        }
        return new String(buffer, 0, inPointer);
    }
    
    @Override
    public void flush() {
        this.m_inputBuffer.clear();
    }
    
    @Override
    public void readyToExit(final boolean wait) {
        this.m_inputBuffer.clear();
        if (wait) {
            this.readChar();
        }
        System.exit(0);
    }
    
    @Override
    public String getVersion() {
        return "1.4pre." + this.m_area.getVersion();
    }
    
    @Override
    public int getX() {
        return this.m_currentX;
    }
    
    @Override
    public int getY() {
        return this.m_currentY;
    }
    
    @Override
    public int getNumberOfColors() {
        return this.m_area.getNumberOfColors();
    }
    
    @Override
    public int getNumberOfColumns() {
        return this.m_width;
    }
    
    @Override
    public int getNumberOfRows() {
        return this.m_height;
    }
    
    @Override
    public void delay(final int ms) {
        this.m_delayTimer.setInitialDelay(ms);
        this.m_delayTimer.start();
        try {
            this.m_delaySync.poll(1000L, TimeUnit.HOURS);
        }
        catch (Exception e) {
            System.out.println("readChar(): read error.");
        }
    }
    
    @Override
    public char peekCellChar(final int y, final int x) {
        return (char)(this.m_area.getCellContents(y, x) & '\u01ff');
    }
    
    @Override
    public short peekCellColor(final int y, final int x) {
        return (short)((this.m_area.getCellContents(y, x) & '\u1e00') >> 9);
    }
    
    @Override
    public void setUserColorDefinition(final short nr, final int r, final int g, final int b) {
        if (nr >= 13 && nr <= 15) {
            this.m_area.setColorDefinition(nr, r, g, b);
        }
    }
    
    public void processKeyEvent(final KeyEvent e) {
        if (e.getID() == 401) {
            switch (e.getKeyCode()) {
                case 16: {
                    this.m_deadKeyStatusVector |= 0x1;
                    break;
                }
                case 17: {
                    this.m_deadKeyStatusVector |= 0x2;
                    break;
                }
                case 18: {
                    this.m_deadKeyStatusVector |= 0x4;
                    break;
                }
                default: {
                    this.m_deadKeyMemoryVector = this.m_deadKeyStatusVector;
                    if (this.m_partialKeyCode != e.getKeyCode()) {
                        this.m_partialKeyCode = e.getKeyCode();
                        break;
                    }
                    if (this.m_partialKeyType == '\0') {
                        this.putCharToBuffer((char)(this.m_partialKeyCode | 0x100));
                        break;
                    }
                    this.putCharToBuffer(this.m_partialKeyType);
                    break;
                }
            }
        }
        if (e.getID() == 400) {
            this.m_partialKeyType = e.getKeyChar();
        }
        if (e.getID() == 402) {
            switch (e.getKeyCode()) {
                case 16: {
                    this.m_deadKeyStatusVector &= 0xFFFFFFFE;
                    break;
                }
                case 17: {
                    this.m_deadKeyStatusVector &= 0xFFFFFFFD;
                    break;
                }
                case 18: {
                    this.m_deadKeyStatusVector &= 0xFFFFFFFB;
                    break;
                }
                default: {
                    if (this.m_partialKeyType == '\0') {
                        this.m_partialKeyType = (char)(this.m_partialKeyCode | 0x100);
                    }
                    this.putCharToBuffer(this.m_partialKeyType);
                    this.m_partialKeyType = '\0';
                    this.m_partialKeyCode = 0;
                    break;
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            this.m_delaySync.put('x');
        }
        catch (Exception x) {
            System.out.println("VTerm.timerEvent error: can't write char");
        }
    }
    
    private void putCharToBuffer(final char c) {
        try {
            if ((this.m_deadKeyMemoryVector & 0x1) != 0x0) {
                this.m_inputBuffer.put('\u0110');
            }
            if ((this.m_deadKeyMemoryVector & 0x2) != 0x0) {
                this.m_inputBuffer.put('\u0111');
            }
            if ((this.m_deadKeyMemoryVector & 0x4) != 0x0) {
                this.m_inputBuffer.put('\u0112');
            }
            this.m_inputBuffer.put(c);
        }
        catch (Exception x) {
            System.out.println("VTerm.processKeyEvent() error : can't write char");
        }
    }
}
