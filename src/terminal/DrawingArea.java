// 
// Decompiled by Procyon v0.5.36
// 

package terminal;

import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public final class DrawingArea extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 210L;
    private static final char NON_PRINTABLE = '\0';
    private static final int CURSOR_BLINK_RATE = 250;
    private static final Color DEFAULT_COLOR;
    protected static final int X_OFFSET = 15;
    protected static final int Y_OFFSET = 25;
    private Font m_sFont;
    private Font m_xFont;
    private char[][] m_buffer;
    private int m_charCols;
    private int m_charRows;
    private int m_cursorX;
    private int m_cursorY;
    private Color[] m_Clut;
    private short m_currentColor;
    private Timer m_timer;
    private boolean m_blinkingStatus;
    private SemiGraphicsPattern m_sg;
    private static int c_fontSize;
    private int m_cellH;
    private int m_cellW;
    
    static {
        DEFAULT_COLOR = new Color(0.1f, 0.2f, 0.2f);
        DrawingArea.c_fontSize = 0;
    }
    
    protected DrawingArea(final int columns, final int rows, final int fontSize, final int style) {
        if (columns < 10 || rows < 10 || fontSize < 10) {
            throw new IndexOutOfBoundsException();
        }
        this.m_charCols = columns;
        this.m_charRows = rows;
        DrawingArea.c_fontSize = fontSize;
        this.initializeColors();
        this.m_currentColor = 7;
        final Border bor1 = new SoftBevelBorder(1);
        final Border bor2 = new EtchedBorder();
        this.setBorder(new CompoundBorder(bor2, bor1));
        this.setBackground(DrawingArea.DEFAULT_COLOR);
        switch (style) {
            case 0: {
                this.m_sFont = new Font("Courier New", 1, fontSize);
                break;
            }
            case 1: {
                this.m_sFont = new Font("Lucida Console", 0, fontSize);
                break;
            }
            case 2: {
                this.m_sFont = new Font("Century Schoolbook Monospace BT", 0, fontSize);
                break;
            }
            default: {
                this.m_sFont = new Font("Courier New", 1, fontSize);
                break;
            }
        }
        this.m_xFont = new Font("Symbol", 0, fontSize - 1);
        this.m_buffer = new char[this.m_charRows][this.m_charCols];
        this.eraseBuffer();
        (this.m_timer = new Timer(250, this)).start();
        this.m_blinkingStatus = true;
        final int n = 0;
        this.m_cursorY = n;
        this.m_cursorX = n;
        this.m_sg = SemiGraphicsPattern.getInstance();
        this.m_cellW = this.m_sg.getCellWitdh();
        this.m_cellH = this.m_sg.getCellHeight();
    }
    
    @Override
    public void paint(final Graphics g) {
        final Graphics2D dc = (Graphics2D)g;
        if (dc != null) {
            final int nonPrintableWidth = 3 * this.m_cellW / 4;
            final int nonPrintableHeight = 5 * this.m_cellH / 10;
            final int nonPrintableOffsetY = 6 * this.m_cellH / 10;
            super.paint(dc);
            dc.setFont(this.m_sFont);
            final char[] temp = { '\0' };
            for (int r = 0; r < this.m_charRows; ++r) {
                for (int c = 0; c < this.m_charCols; ++c) {
                    temp[0] = (char)(this.m_buffer[r][c] & '\u1fff');
                    dc.setColor(this.m_Clut[temp[0] >>> 9]);
                    final char[] array = temp;
                    final int n = 0;
                    array[n] &= '\u01ff';
                    if (temp[0] == '\0') {
                        final int x1 = 13 + c * this.m_cellW;
                        final int y1 = 25 - nonPrintableOffsetY + r * this.m_cellH;
                        dc.setColor(this.m_Clut[2]);
                        dc.fillRect(x1 + 1, y1, nonPrintableWidth - 2, nonPrintableHeight + 1);
                        dc.setColor(this.m_Clut[7]);
                        dc.drawRect(x1 + 1, y1, nonPrintableWidth - 2, nonPrintableHeight + 1);
                    }
                    else if (temp[0] != ' ') {
                        if (temp[0] < '\u0100') {
                            dc.drawChars(temp, 0, 1, 12 + c * this.m_cellW, 25 + r * this.m_cellH);
                        }
                        else if (temp[0] < '\u0180') {
                            for (int i = 0; i < this.m_sg.getNumberOfRectangles(temp[0] - '\u0100'); ++i) {
                                dc.fillRect(12 + c * this.m_cellW + this.m_sg.x(temp[0] - '\u0100', i), 25 + (r - 1) * this.m_cellH + this.m_cellH / 4 + this.m_sg.y(temp[0] - '\u0100', i), this.m_sg.w(temp[0] - '\u0100', i), this.m_sg.h(temp[0] - '\u0100', i));
                            }
                        }
                        else {
                            dc.setFont(this.m_xFont);
                            temp[0] = (char)this.m_sg.getSymbolCharacter(temp[0]);
                            if (temp[0] != '\0') {
                                dc.drawChars(temp, 0, 1, 12 + c * this.m_cellW, 25 + r * this.m_cellH);
                            }
                            dc.setFont(this.m_sFont);
                        }
                    }
                }
            }
            if (this.m_blinkingStatus) {
                dc.setColor(this.m_Clut[7]);
                dc.drawRect(12 + this.m_cursorX * this.m_cellW, 25 + this.m_cursorY * this.m_cellH, this.m_cellW - 1, 2);
            }
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        this.m_blinkingStatus = !this.m_blinkingStatus;
        this.repaint(11 + this.m_cursorX * this.m_cellW, 25 + this.m_cursorY * this.m_cellH - 1, this.m_cellW + 1, 4);
    }
    
    protected void printCharAt(char ch, final int y, final int x) {
        if (x < 0 || x >= this.m_charCols || y < 0 || y >= this.m_charRows) {
            throw new IndexOutOfBoundsException("DrawingArea error : invalid cell address");
        }
        this.m_buffer[y][x] = (char)(this.m_currentColor << 9);
        ch &= '\u01ff';
        if (ch < ' ' || (ch >= '\u007f' && ch < ' ') || (ch > 255 + this.m_sg.getSize() && ch < '\u0180') || ch > '\u01bf') {
            this.m_buffer[y][x] |= '\0';
        }
        else {
            this.m_buffer[y][x] |= ch;
        }
        this.repaint();
    }
    
    protected void printStringAt(final CharSequence s, int y, int x) {
        if (x < 0 || x >= this.m_charCols || y < 0 || y >= this.m_charRows) {
            throw new IndexOutOfBoundsException("DrawingArea error : invalid cell address");
        }
        for (int curr = 0; curr < s.length() && y < this.m_charRows; y = (y + 1) % this.m_charRows, x = 0) {
            final char ch = s.charAt(curr);
            this.m_buffer[y][x] = (char)(this.m_currentColor << 9);
            if (ch < ' ' || (ch >= '\u007f' && ch < ' ') || (ch > 255 + this.m_sg.getSize() && ch < '\u0180') || ch > '\u01bf') {
                this.m_buffer[y][x] |= '\0';
            }
            else {
                this.m_buffer[y][x] |= ch;
            }
            ++x;
            ++curr;
            if (x == this.m_charCols) {}
        }
        this.repaint();
    }
    
    protected void scroll() {
        final char[] temp = this.m_buffer[0];
        for (int i = 1; i < this.m_charRows; ++i) {
            this.m_buffer[i - 1] = this.m_buffer[i];
        }
        this.m_buffer[this.m_charRows - 1] = temp;
        for (int i = 0; i < this.m_charCols; ++i) {
            this.m_buffer[this.m_charRows - 1][i] = ' ';
        }
        this.repaint();
    }
    
    protected void setColor(final short c) {
        if (c < 0 || c >= this.m_Clut.length) {
            throw new IndexOutOfBoundsException("DrawingArea error : invalid cell address");
        }
        this.m_currentColor = c;
    }
    
    protected void setCursorVisibility(final boolean s) {
        if (s) {
            this.m_timer.start();
        }
        else {
            this.m_timer.stop();
            this.m_blinkingStatus = false;
        }
        this.repaint();
    }
    
    protected void eraseBuffer() {
        for (int r = 0; r < this.m_charRows; ++r) {
            for (int c = 0; c < this.m_charCols; ++c) {
                this.m_buffer[r][c] = ' ';
            }
        }
        this.repaint();
    }
    
    protected int getTextHeight() {
        return this.m_charRows * this.m_sg.getCellHeight() + 50;
    }
    
    protected int getTextWidth() {
        return this.m_charCols * this.m_sg.getCellWitdh() + 30;
    }
    
    protected void setCursorLocation(final int y, final int x) {
        if (x < 0 || x >= this.m_charCols || y < 0 || y >= this.m_charRows) {
            throw new IndexOutOfBoundsException("DrawingArea error : invalid cell address");
        }
        this.m_cursorY = y;
        this.m_cursorX = x;
        this.repaint();
    }
    
    protected char getCellContents(final int y, final int x) {
        if (x < 0 || x >= this.m_charCols || y < 0 || y >= this.m_charRows) {
            throw new IndexOutOfBoundsException("DrawingArea error : invalid cell address");
        }
        return this.m_buffer[y][x];
    }
    
    protected void putCellContents(final int y, final int x, final char c) {
        if (x < 0 || x >= this.m_charCols || y < 0 || y >= this.m_charRows) {
            throw new IndexOutOfBoundsException("DrawingArea error : invalid cell address");
        }
        this.m_buffer[y][x] = c;
    }
    
    protected void updateDisplay() {
        this.repaint();
    }
    
    protected int getNumberOfColors() {
        return this.m_Clut.length;
    }
    
    protected static int getFontSize() {
        return DrawingArea.c_fontSize;
    }
    
    protected String getVersion() {
        return "14";
    }
    
    protected void setColorDefinition(final int nr, final int R, final int G, final int B) {
        if (nr >= 0 && nr < this.getNumberOfColors()) {
            this.m_Clut[nr] = new Color(((R & 0xFF) << 16) + ((G & 0xFF) << 8) + (B & 0xFF));
        }
    }
    
    protected int getColorDefinition(final int nr) {
        return (this.m_Clut[nr].getRed() << 16) + (this.m_Clut[nr].getGreen() << 8) + this.m_Clut[nr].getBlue();
    }
    
    private void initializeColors() {
        (this.m_Clut = new Color[16])[0] = new Color(15263744);
        this.m_Clut[1] = new Color(15765520);
        this.m_Clut[2] = new Color(15740960);
        this.m_Clut[3] = new Color(12525567);
        this.m_Clut[4] = new Color(4153343);
        this.m_Clut[5] = new Color(48896);
        this.m_Clut[6] = new Color(16777215);
        this.m_Clut[7] = new Color(12634312);
        this.m_Clut[8] = new Color(7368816);
        this.m_Clut[9] = new Color(16756912);
        this.m_Clut[11] = new Color(7389439);
        this.m_Clut[12] = new Color(8450176);
        this.m_Clut[10] = new Color(12620016);
        this.m_Clut[13] = new Color(16777215);
        this.m_Clut[14] = new Color(16777215);
        this.m_Clut[15] = new Color(16777215);
    }
}
