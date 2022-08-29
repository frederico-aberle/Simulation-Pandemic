// 
// Decompiled by Procyon v0.5.36
// 

package terminal;

public class SemiGraphicsPattern
{
    private static final int SYMBOL_OFFSET = 61440;
    private static SemiGraphicsPattern m_instance;
    private Rectangle[][] m_rectTable;
    private int m_cellW;
    private int m_cellH;
    
    static {
        SemiGraphicsPattern.m_instance = null;
    }
    
    private SemiGraphicsPattern() {
        this.m_rectTable = new Rectangle[25][];
        if (DrawingArea.getFontSize() == 20) {
            this.m_cellW = 12;
            this.m_cellH = 23;
            (this.m_rectTable[0] = new Rectangle[1])[0] = new Rectangle(3, 4, 6, 15);
            (this.m_rectTable[1] = new Rectangle[1])[0] = new Rectangle(1, 1, 10, 21);
            (this.m_rectTable[2] = new Rectangle[1])[0] = new Rectangle(0, 0, 12, 23);
            (this.m_rectTable[3] = new Rectangle[1])[0] = new Rectangle(0, 0, 6, 23);
            (this.m_rectTable[4] = new Rectangle[1])[0] = new Rectangle(6, 0, 6, 23);
            (this.m_rectTable[5] = new Rectangle[1])[0] = new Rectangle(0, 0, 12, 12);
            (this.m_rectTable[6] = new Rectangle[1])[0] = new Rectangle(0, 12, 12, 12);
            (this.m_rectTable[7] = new Rectangle[1])[0] = new Rectangle(5, 0, 2, 23);
            (this.m_rectTable[8] = new Rectangle[1])[0] = new Rectangle(0, 11, 12, 2);
            (this.m_rectTable[9] = new Rectangle[2])[0] = new Rectangle(5, 11, 2, 12);
            this.m_rectTable[9][1] = new Rectangle(7, 11, 5, 2);
            (this.m_rectTable[10] = new Rectangle[2])[0] = new Rectangle(5, 11, 2, 12);
            this.m_rectTable[10][1] = new Rectangle(0, 11, 6, 2);
            (this.m_rectTable[11] = new Rectangle[2])[0] = new Rectangle(5, 0, 2, 13);
            this.m_rectTable[11][1] = new Rectangle(7, 11, 5, 2);
            (this.m_rectTable[12] = new Rectangle[2])[0] = new Rectangle(5, 0, 2, 13);
            this.m_rectTable[12][1] = new Rectangle(0, 11, 6, 2);
            (this.m_rectTable[13] = new Rectangle[2])[0] = new Rectangle(5, 0, 2, 23);
            this.m_rectTable[13][1] = new Rectangle(0, 11, 12, 2);
            (this.m_rectTable[14] = new Rectangle[2])[0] = new Rectangle(5, 0, 2, 23);
            this.m_rectTable[14][1] = new Rectangle(7, 11, 5, 2);
            (this.m_rectTable[15] = new Rectangle[2])[0] = new Rectangle(5, 0, 2, 23);
            this.m_rectTable[15][1] = new Rectangle(0, 11, 6, 2);
            (this.m_rectTable[16] = new Rectangle[2])[0] = new Rectangle(5, 11, 2, 12);
            this.m_rectTable[16][1] = new Rectangle(0, 11, 12, 2);
            (this.m_rectTable[17] = new Rectangle[2])[0] = new Rectangle(5, 0, 2, 13);
            this.m_rectTable[17][1] = new Rectangle(0, 11, 12, 2);
            (this.m_rectTable[18] = new Rectangle[8])[0] = new Rectangle(2, 4, 1, 15);
            this.m_rectTable[18][1] = new Rectangle(3, 5, 1, 13);
            this.m_rectTable[18][2] = new Rectangle(4, 6, 1, 11);
            this.m_rectTable[18][3] = new Rectangle(5, 7, 1, 9);
            this.m_rectTable[18][4] = new Rectangle(6, 8, 1, 7);
            this.m_rectTable[18][5] = new Rectangle(7, 9, 1, 5);
            this.m_rectTable[18][6] = new Rectangle(8, 10, 1, 3);
            this.m_rectTable[18][7] = new Rectangle(9, 11, 1, 1);
            (this.m_rectTable[19] = new Rectangle[8])[0] = new Rectangle(9, 4, 1, 15);
            this.m_rectTable[19][1] = new Rectangle(8, 5, 1, 13);
            this.m_rectTable[19][2] = new Rectangle(7, 6, 1, 11);
            this.m_rectTable[19][3] = new Rectangle(6, 7, 1, 9);
            this.m_rectTable[19][4] = new Rectangle(5, 8, 1, 7);
            this.m_rectTable[19][5] = new Rectangle(4, 9, 1, 5);
            this.m_rectTable[19][6] = new Rectangle(3, 10, 1, 3);
            this.m_rectTable[19][7] = new Rectangle(2, 11, 1, 1);
            (this.m_rectTable[20] = new Rectangle[5])[0] = new Rectangle(1, 7, 10, 2);
            this.m_rectTable[20][1] = new Rectangle(2, 9, 8, 2);
            this.m_rectTable[20][2] = new Rectangle(3, 11, 6, 2);
            this.m_rectTable[20][3] = new Rectangle(4, 13, 4, 2);
            this.m_rectTable[20][4] = new Rectangle(5, 15, 2, 2);
            (this.m_rectTable[21] = new Rectangle[5])[0] = new Rectangle(1, 15, 10, 2);
            this.m_rectTable[21][1] = new Rectangle(2, 13, 8, 2);
            this.m_rectTable[21][2] = new Rectangle(3, 11, 6, 2);
            this.m_rectTable[21][3] = new Rectangle(4, 9, 4, 2);
            this.m_rectTable[21][4] = new Rectangle(5, 7, 2, 2);
            (this.m_rectTable[22] = new Rectangle[3])[0] = new Rectangle(2, 10, 8, 4);
            this.m_rectTable[22][1] = new Rectangle(3, 9, 6, 6);
            this.m_rectTable[22][2] = new Rectangle(4, 8, 4, 8);
            (this.m_rectTable[23] = new Rectangle[5])[0] = new Rectangle(1, 11, 10, 1);
            this.m_rectTable[23][1] = new Rectangle(2, 10, 8, 3);
            this.m_rectTable[23][2] = new Rectangle(3, 9, 6, 5);
            this.m_rectTable[23][3] = new Rectangle(4, 8, 4, 7);
            this.m_rectTable[23][4] = new Rectangle(5, 7, 2, 9);
            (this.m_rectTable[24] = new Rectangle[2])[0] = new Rectangle(1, 15, 3, 3);
            this.m_rectTable[24][1] = new Rectangle(6, 15, 3, 3);
        }
        if (DrawingArea.getFontSize() == 16) {
            this.m_cellW = 10;
            this.m_cellH = 19;
            (this.m_rectTable[0] = new Rectangle[1])[0] = new Rectangle(3, 4, 4, 11);
            (this.m_rectTable[1] = new Rectangle[1])[0] = new Rectangle(1, 1, 8, 17);
            (this.m_rectTable[2] = new Rectangle[1])[0] = new Rectangle(0, 0, 10, 19);
            (this.m_rectTable[3] = new Rectangle[1])[0] = new Rectangle(0, 0, 5, 19);
            (this.m_rectTable[4] = new Rectangle[1])[0] = new Rectangle(5, 0, 5, 19);
            (this.m_rectTable[5] = new Rectangle[1])[0] = new Rectangle(0, 0, 10, 9);
            (this.m_rectTable[6] = new Rectangle[1])[0] = new Rectangle(0, 9, 10, 10);
            (this.m_rectTable[7] = new Rectangle[1])[0] = new Rectangle(4, 0, 2, 19);
            (this.m_rectTable[8] = new Rectangle[1])[0] = new Rectangle(0, 9, 10, 2);
            (this.m_rectTable[9] = new Rectangle[2])[0] = new Rectangle(4, 9, 2, 10);
            this.m_rectTable[9][1] = new Rectangle(6, 9, 4, 2);
            (this.m_rectTable[10] = new Rectangle[2])[0] = new Rectangle(4, 9, 2, 10);
            this.m_rectTable[10][1] = new Rectangle(0, 9, 4, 2);
            (this.m_rectTable[11] = new Rectangle[2])[0] = new Rectangle(4, 0, 2, 11);
            this.m_rectTable[11][1] = new Rectangle(6, 9, 4, 2);
            (this.m_rectTable[12] = new Rectangle[2])[0] = new Rectangle(4, 0, 2, 11);
            this.m_rectTable[12][1] = new Rectangle(0, 9, 4, 2);
            (this.m_rectTable[13] = new Rectangle[2])[0] = new Rectangle(4, 0, 2, 19);
            this.m_rectTable[13][1] = new Rectangle(0, 9, 10, 2);
            (this.m_rectTable[14] = new Rectangle[2])[0] = new Rectangle(4, 0, 2, 19);
            this.m_rectTable[14][1] = new Rectangle(6, 9, 4, 2);
            (this.m_rectTable[15] = new Rectangle[2])[0] = new Rectangle(4, 0, 2, 19);
            this.m_rectTable[15][1] = new Rectangle(0, 9, 4, 2);
            (this.m_rectTable[16] = new Rectangle[2])[0] = new Rectangle(4, 9, 2, 10);
            this.m_rectTable[16][1] = new Rectangle(0, 9, 10, 2);
            (this.m_rectTable[17] = new Rectangle[2])[0] = new Rectangle(4, 0, 2, 11);
            this.m_rectTable[17][1] = new Rectangle(0, 9, 10, 2);
            (this.m_rectTable[18] = new Rectangle[6])[0] = new Rectangle(2, 4, 1, 12);
            this.m_rectTable[18][1] = new Rectangle(3, 5, 1, 10);
            this.m_rectTable[18][2] = new Rectangle(4, 6, 1, 8);
            this.m_rectTable[18][3] = new Rectangle(5, 7, 1, 6);
            this.m_rectTable[18][4] = new Rectangle(6, 8, 1, 4);
            this.m_rectTable[18][5] = new Rectangle(7, 9, 1, 2);
            (this.m_rectTable[19] = new Rectangle[6])[0] = new Rectangle(7, 4, 1, 12);
            this.m_rectTable[19][1] = new Rectangle(6, 5, 1, 10);
            this.m_rectTable[19][2] = new Rectangle(5, 6, 1, 8);
            this.m_rectTable[19][3] = new Rectangle(4, 7, 1, 6);
            this.m_rectTable[19][4] = new Rectangle(3, 8, 1, 4);
            this.m_rectTable[19][5] = new Rectangle(2, 9, 1, 2);
            (this.m_rectTable[20] = new Rectangle[4])[0] = new Rectangle(1, 6, 8, 2);
            this.m_rectTable[20][1] = new Rectangle(2, 8, 6, 2);
            this.m_rectTable[20][2] = new Rectangle(3, 10, 4, 2);
            this.m_rectTable[20][3] = new Rectangle(4, 12, 2, 2);
            (this.m_rectTable[21] = new Rectangle[4])[0] = new Rectangle(1, 12, 8, 2);
            this.m_rectTable[21][1] = new Rectangle(2, 10, 6, 2);
            this.m_rectTable[21][2] = new Rectangle(3, 8, 4, 2);
            this.m_rectTable[21][3] = new Rectangle(4, 6, 2, 2);
            (this.m_rectTable[22] = new Rectangle[3])[0] = new Rectangle(1, 9, 7, 3);
            this.m_rectTable[22][1] = new Rectangle(2, 8, 5, 5);
            this.m_rectTable[22][2] = new Rectangle(3, 7, 3, 7);
            (this.m_rectTable[23] = new Rectangle[5])[0] = new Rectangle(0, 10, 9, 1);
            this.m_rectTable[23][1] = new Rectangle(1, 9, 7, 3);
            this.m_rectTable[23][2] = new Rectangle(2, 8, 5, 5);
            this.m_rectTable[23][3] = new Rectangle(3, 7, 3, 7);
            this.m_rectTable[23][4] = new Rectangle(4, 6, 1, 9);
            (this.m_rectTable[24] = new Rectangle[2])[0] = new Rectangle(1, 14, 2, 2);
            this.m_rectTable[24][1] = new Rectangle(5, 14, 2, 2);
        }
        if (DrawingArea.getFontSize() == 12) {
            this.m_cellW = 7;
            this.m_cellH = 14;
            (this.m_rectTable[0] = new Rectangle[1])[0] = new Rectangle(2, 3, 3, 8);
            (this.m_rectTable[1] = new Rectangle[1])[0] = new Rectangle(1, 1, 5, 12);
            (this.m_rectTable[2] = new Rectangle[1])[0] = new Rectangle(0, 0, 7, 14);
            (this.m_rectTable[3] = new Rectangle[1])[0] = new Rectangle(0, 0, 4, 14);
            (this.m_rectTable[4] = new Rectangle[1])[0] = new Rectangle(4, 0, 3, 14);
            (this.m_rectTable[5] = new Rectangle[1])[0] = new Rectangle(0, 0, 7, 7);
            (this.m_rectTable[6] = new Rectangle[1])[0] = new Rectangle(0, 7, 7, 7);
            (this.m_rectTable[7] = new Rectangle[1])[0] = new Rectangle(2, 0, 2, 14);
            (this.m_rectTable[8] = new Rectangle[1])[0] = new Rectangle(0, 7, 7, 2);
            (this.m_rectTable[9] = new Rectangle[2])[0] = new Rectangle(2, 9, 2, 5);
            this.m_rectTable[9][1] = new Rectangle(2, 7, 5, 2);
            (this.m_rectTable[10] = new Rectangle[2])[0] = new Rectangle(2, 9, 2, 5);
            this.m_rectTable[10][1] = new Rectangle(0, 7, 4, 2);
            (this.m_rectTable[11] = new Rectangle[2])[0] = new Rectangle(2, 0, 2, 7);
            this.m_rectTable[11][1] = new Rectangle(2, 7, 5, 2);
            (this.m_rectTable[12] = new Rectangle[2])[0] = new Rectangle(2, 0, 2, 7);
            this.m_rectTable[12][1] = new Rectangle(0, 7, 4, 2);
            (this.m_rectTable[13] = new Rectangle[2])[0] = new Rectangle(2, 0, 2, 14);
            this.m_rectTable[13][1] = new Rectangle(0, 7, 7, 2);
            (this.m_rectTable[14] = new Rectangle[2])[0] = new Rectangle(2, 0, 2, 14);
            this.m_rectTable[14][1] = new Rectangle(2, 7, 5, 2);
            (this.m_rectTable[15] = new Rectangle[2])[0] = new Rectangle(2, 0, 2, 14);
            this.m_rectTable[15][1] = new Rectangle(0, 7, 4, 2);
            (this.m_rectTable[16] = new Rectangle[2])[0] = new Rectangle(2, 9, 2, 5);
            this.m_rectTable[16][1] = new Rectangle(0, 7, 7, 2);
            (this.m_rectTable[17] = new Rectangle[2])[0] = new Rectangle(2, 0, 2, 7);
            this.m_rectTable[17][1] = new Rectangle(0, 7, 7, 2);
            (this.m_rectTable[18] = new Rectangle[5])[0] = new Rectangle(1, 2, 1, 10);
            this.m_rectTable[18][1] = new Rectangle(2, 3, 1, 8);
            this.m_rectTable[18][2] = new Rectangle(3, 4, 1, 6);
            this.m_rectTable[18][3] = new Rectangle(4, 5, 1, 4);
            this.m_rectTable[18][4] = new Rectangle(5, 6, 1, 2);
            (this.m_rectTable[19] = new Rectangle[5])[0] = new Rectangle(5, 2, 1, 10);
            this.m_rectTable[19][1] = new Rectangle(4, 3, 1, 8);
            this.m_rectTable[19][2] = new Rectangle(3, 4, 1, 6);
            this.m_rectTable[19][3] = new Rectangle(2, 5, 1, 4);
            this.m_rectTable[19][4] = new Rectangle(1, 6, 1, 2);
            (this.m_rectTable[20] = new Rectangle[4])[0] = new Rectangle(0, 3, 7, 2);
            this.m_rectTable[20][1] = new Rectangle(1, 5, 5, 2);
            this.m_rectTable[20][2] = new Rectangle(2, 7, 3, 2);
            this.m_rectTable[20][3] = new Rectangle(3, 9, 1, 2);
            (this.m_rectTable[21] = new Rectangle[4])[0] = new Rectangle(0, 9, 7, 2);
            this.m_rectTable[21][1] = new Rectangle(1, 7, 5, 2);
            this.m_rectTable[21][2] = new Rectangle(2, 5, 3, 2);
            this.m_rectTable[21][3] = new Rectangle(3, 3, 1, 2);
            (this.m_rectTable[22] = new Rectangle[2])[0] = new Rectangle(1, 6, 5, 3);
            this.m_rectTable[22][1] = new Rectangle(2, 5, 3, 5);
            (this.m_rectTable[23] = new Rectangle[4])[0] = new Rectangle(0, 7, 7, 1);
            this.m_rectTable[23][1] = new Rectangle(1, 6, 5, 3);
            this.m_rectTable[23][2] = new Rectangle(2, 5, 3, 5);
            this.m_rectTable[23][3] = new Rectangle(3, 4, 1, 7);
            (this.m_rectTable[24] = new Rectangle[2])[0] = new Rectangle(1, 10, 2, 2);
            this.m_rectTable[24][1] = new Rectangle(4, 10, 2, 2);
        }
    }
    
    public static SemiGraphicsPattern getInstance() {
        if (SemiGraphicsPattern.m_instance == null) {
            SemiGraphicsPattern.m_instance = new SemiGraphicsPattern();
        }
        return SemiGraphicsPattern.m_instance;
    }
    
    public int x(final int index, final int rect) {
        return this.m_rectTable[index][rect].m_x;
    }
    
    public int y(final int index, final int rect) {
        return this.m_rectTable[index][rect].m_y;
    }
    
    public int w(final int index, final int rect) {
        return this.m_rectTable[index][rect].m_w;
    }
    
    public int h(final int index, final int rect) {
        return this.m_rectTable[index][rect].m_h;
    }
    
    public int getSize() {
        return this.m_rectTable.length;
    }
    
    public int getNumberOfRectangles(final int index) {
        return this.m_rectTable[index].length;
    }
    
    public int getCellWitdh() {
        return this.m_cellW;
    }
    
    public int getCellHeight() {
        return this.m_cellH;
    }
    
    public int getSymbolCharacter(final char vTermChar) {
        if ((vTermChar > '\u0180' && vTermChar < '\u019b') || (vTermChar > '\u01a0' && vTermChar < '\u01bb')) {
            return vTermChar + '\uf000' + 64 - 384;
        }
        switch (vTermChar) {
            case '\u0180': {
                return 61603;
            }
            case '\u019b': {
                return 61605;
            }
            case '\u019c': {
                return 61606;
            }
            case '\u019d': {
                return 61625;
            }
            case '\u019e': {
                return 61627;
            }
            case '\u019f': {
                return 61626;
            }
            case '\u01a0': {
                return 61619;
            }
            case '\u01bb': {
                return 61612;
            }
            case '\u01bc': {
                return 61614;
            }
            case '\u01bd': {
                return 61613;
            }
            case '\u01be': {
                return 61615;
            }
            case '\u01bf': {
                return 61654;
            }
            default: {
                return 0;
            }
        }
    }
    
    private class Rectangle
    {
        private int m_x;
        private int m_y;
        private int m_w;
        private int m_h;
        
        public Rectangle(final int x, final int y, final int w, final int h) {
            this.m_x = x;
            this.m_y = y;
            this.m_w = w;
            this.m_h = h;
        }
    }
}
