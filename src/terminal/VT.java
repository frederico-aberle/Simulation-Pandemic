// 
// Decompiled by Procyon v0.5.36
// 

package terminal;

public interface VT
{
    public static final short YELLOW = 0;
    public static final short ORANGE = 1;
    public static final short RED = 2;
    public static final short PURPLE = 3;
    public static final short BLUE = 4;
    public static final short GREEN = 5;
    public static final short WHITE = 6;
    public static final short SILVER = 7;
    public static final short GREY = 8;
    public static final short ROSE = 9;
    public static final short MAUVE = 10;
    public static final short SKY = 11;
    public static final short MINT = 12;
    public static final short USER_1 = 13;
    public static final short USER_2 = 14;
    public static final short USER_3 = 15;
    public static final char SG_BLOCK_SMALL = '\u0100';
    public static final char SG_BLOCK_MEDIUM = '\u0101';
    public static final char SG_BLOCK_FULL = '\u0102';
    public static final char SG_BLOCK_LEFT = '\u0103';
    public static final char SG_BLOCK_RIGHT = '\u0104';
    public static final char SG_BLOCK_UPPER = '\u0105';
    public static final char SG_BLOCK_LOWER = '\u0106';
    public static final char SG_LINE_VERT = '\u0107';
    public static final char SG_LINE_HORIZ = '\u0108';
    public static final char SG_CORNER_LT = '\u0109';
    public static final char SG_CORNER_RT = '\u010a';
    public static final char SG_CORNER_LB = '\u010b';
    public static final char SG_CORNER_RB = '\u010c';
    public static final char SG_CROSS = '\u010d';
    public static final char SG_TBONE_LEFT = '\u010e';
    public static final char SG_TBONE_RIGHT = '\u010f';
    public static final char SG_TBONE_UPPER = '\u0110';
    public static final char SG_TBONE_LOWER = '\u0111';
    public static final char SG_ARROWTIP_R = '\u0112';
    public static final char SG_ARROWTIP_L = '\u0113';
    public static final char SG_ARROWTIP_D = '\u0114';
    public static final char SG_ARROWTIP_U = '\u0115';
    public static final char SG_BULLET = '\u0116';
    public static final char SG_RHOMBUS = '\u0117';
    public static final char SG_RANGE = '\u0118';
    public static final char SY_LESS_EQ = '\u0180';
    public static final char SY_INFINITY = '\u019b';
    public static final char SY_FUNCTION = '\u019c';
    public static final char SY_NOT_EQUAL = '\u019d';
    public static final char SY_APPROX = '\u019e';
    public static final char SY_IDENTITY = '\u019f';
    public static final char SY_GREATER_EQ = '\u01a0';
    public static final char SY_ARROW_LEFT = '\u01bb';
    public static final char SY_ARROW_RIGHT = '\u01bc';
    public static final char SY_ARROW_UP = '\u01bd';
    public static final char SY_ARROW_DOWN = '\u01be';
    public static final char SY_ROOT = '\u01bf';
    public static final int CS_STANDARD = 20;
    public static final int CS_SMALL = 16;
    public static final int CS_TINY = 12;
    public static final int CS_STYLE_COUR = 0;
    public static final int CS_STYLE_SANS = 1;
    public static final int CS_STYLE_ANTQ = 2;
    public static final int K_UP = 294;
    public static final int K_DOWN = 296;
    public static final int K_LEFT = 293;
    public static final int K_RIGHT = 295;
    public static final int K_HOME = 292;
    public static final int K_END = 291;
    public static final int K_PGUP = 289;
    public static final int K_PGDN = 290;
    public static final int K_INSERT = 411;
    public static final int K_F1 = 368;
    public static final int K_F2 = 369;
    public static final int K_F3 = 370;
    public static final int K_F4 = 371;
    public static final int K_F5 = 372;
    public static final int K_F6 = 373;
    public static final int K_F7 = 374;
    public static final int K_F8 = 375;
    public static final int K_F9 = 376;
    public static final int K_F10 = 377;
    public static final int K_F11 = 378;
    public static final int K_F12 = 379;
    public static final int K_SHIFT = 272;
    public static final int K_CTRL = 273;
    public static final int K_ALT = 274;
    public static final int K_CAPS = 276;
    public static final int K_PRTSCR = 410;
    public static final int K_ESCAPE = 27;
    public static final int K_BACKSP = 8;
    public static final int K_ENTER = 10;
    public static final int K_DELETE = 127;
    public static final int K_TAB = 9;
    
    void setCursor(final int p0, final int p1);
    
    void setCursor(final boolean p0);
    
    void setColor(final short p0);
    
    void setCnC(final int p0, final int p1, final short p2);
    
    void print(final CharSequence p0);
    
    void print(final char p0);
    
    void println(final CharSequence p0);
    
    void println();
    
    void print(final int p0, final int p1);
    
    void backspace();
    
    void printFrame(final int p0, final int p1, final int p2, final int p3);
    
    void printHLine(final int p0, final int p1, final int p2, final boolean p3);
    
    void printVLine(final int p0, final int p1, final int p2, final boolean p3);
    
    void clearScreen();
    
    void clearArea(final int p0, final int p1, final int p2, final int p3);
    
    void scroll();
    
    void scrollArea(final int p0, final int p1, final int p2, final int p3);
    
    void colorizeArea(final int p0, final int p1, final int p2, final int p3, final short p4);
    
    char readChar();
    
    int readKey();
    
    boolean readBoolean();
    
    String readString(final int p0);
    
    void flush();
    
    void readyToExit(final boolean p0);
    
    void delay(final int p0);
    
    int getX();
    
    int getY();
    
    String getVersion();
    
    int getNumberOfColors();
    
    int getNumberOfColumns();
    
    int getNumberOfRows();
    
    char peekCellChar(final int p0, final int p1);
    
    short peekCellColor(final int p0, final int p1);
    
    void setUserColorDefinition(final short p0, final int p1, final int p2, final int p3);
    
    char greek(final char p0);
    
    char greek(final int p0);
}
