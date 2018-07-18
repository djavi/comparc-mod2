/* 
 * COMPARC S11
 * Caguiat, Emmil Joshua
 * Cheng, John Miko
 * Javier, Daniela Abigail
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Toolkit;
import java.lang.String;
import java.util.Scanner;
import java.util.regex.*;
import javax.swing.JTable;

public class MOD205 {
	
	//GUI Variables
	public static JPanel panel;
	public static JLabel lblNewLabel;
	public static JTabbedPane tabbedPane;
	public static JPanel panel_1;
	public static JPanel panel_3;
	public static JLabel lblEnterCodeBelow;
	public static JButton button;
	public static JScrollPane scrollPane_1;
	public static JTextArea textArea;
	public static JPanel panel_4;
	public static JTabbedPane tabbedPane_1;
	public static JPanel panel_6;
	public static JScrollPane scrollPane_2;
	public static JTextArea textArea_1;
	public static JPanel panel_7;
	public static JScrollPane scrollPane_3;
	public static JTextArea textArea_2;
	public static JPanel panel_2;
	public static JTabbedPane tabbedPane_2;
	public static JPanel panel_5;
	public static JScrollPane scrollPane_4;
	public static JTextPane txtpndataIbytebyte;
	public static JPanel panel_8;
	public static JScrollPane scrollPane;
	public static JTextPane txtpnAvailableInstructions;
	public static String[] cols = {"register", "data"};
	//public static String[][] data = {{"R1", "0000 0000 0000 0000"},{"R2", "0000 0000 0000 0004"}};
	private JFrame frmUmips;
	
	
	
	
	//ErrorCheck Variables
	public static String reg[][] = {
	        {"R0","0000000000000000"},{"R1","0000000000000000"},{"R2","0000000000000000"},{"R3","0000000000000000"},
	        {"R4","0000000000000000"},{"R5","0000000000000000"},{"R6","0000000000000000"},{"R7","0000000000000000"},
	        {"R8","0000000000000000"},{"R9","0000000000000000"},{"R10","0000000000000000"},{"R11","0000000000000000"},
	        {"R12","0000000000000000"},{"R13","0000000000000000"},{"R14","0000000000000000"},{"R15","0000000000000000"},
	        {"R16","0000000000000000"},{"R17","0000000000000000"},{"R18","0000000000000000"},{"R19","0000000000000000"},
	        {"R20","0000000000000000"},{"R21","0000000000000000"},{"R22","0000000000000000"},{"R23","0000000000000000"},
	        {"R24","0000000000000000"},{"R25","0000000000000000"},{"R26","0000000000000000"},{"R27","0000000000000000"},
	        {"R28","0000000000000000"},{"R29","0000000000000000"},{"R30","0000000000000000"},{"R31","0000000000000000"},
	        };
	        
	public static String ins[] = {"DADDIU","LD", "SD", "DADDU", "SLT", "NOP" , "BC", "BGEC", "DAUI"};
	        
	public static String inputCode;
	        
	public static String[] breakCode; //seperates the inputCode
	public static int insError = 0; //use it nalang if 1 sha then error if 0 then no
	public static int firstRegError = 0; // if register error
	public static int secRegError = 0; // if register error
	public static int branchError = 0; // if register error
	public static int thirdError = 0; // if immError/reg3Error
	
	
	//Opcode Variables
	String opcodeBin;
    int opcodeA;
    int opcodeB;
    int opcodeImm;
    String finalOp;
    String hexOp = "";
    String aBin, bBin, immBin;
    String toConv[];
    int j = 0;
    private JPanel panel_9;
    private JScrollPane scrollPane_5;
    private JPanel panel_10;
    private JPanel panel_11;
    private JScrollPane scrollPane_7;
    private JTable table;

    
    //module 2
    public static DefaultTableModel model = new DefaultTableModel(reg, cols);
    
    //mem locs 0000 - 0FFF
    public static String memory[][] = {
    		  {"0000", "0000000000"}, {"0004", "0000000000"},
    	      {"0008", "0000000000"}, {"000C", "0000000000"},
    	      {"0010", "0000000000"}, {"0014", "0000000000"},
    	      {"0018", "0000000000"}, {"001C", "0000000000"},
    	      {"0020", "0000000000"}, {"0024", "0000000000"},
    	      {"0028", "0000000000"}, {"002C", "0000000000"},
    	      {"0030", "0000000000"}, {"0034", "0000000000"},
    	      {"0038", "0000000000"}, {"003C", "0000000000"},
    	      {"0040", "0000000000"}, {"0044", "0000000000"},
    	      {"0048", "0000000000"}, {"004C", "0000000000"},
    	      {"0050", "0000000000"}, {"0054", "0000000000"},
    	      {"0058", "0000000000"}, {"005C", "0000000000"},
    	      {"0060", "0000000000"}, {"0064", "0000000000"},
    	      {"0068", "0000000000"}, {"006C", "0000000000"},
    	      {"0070", "0000000000"}, {"0074", "0000000000"},
    	      {"0078", "0000000000"}, {"007C", "0000000000"},
    	      {"0080", "0000000000"}, {"0084", "0000000000"},
    	      {"0088", "0000000000"}, {"008C", "0000000000"},
    	      {"0090", "0000000000"}, {"0094", "0000000000"},
    	      {"0098", "0000000000"}, {"009C", "0000000000"},
    	      {"00A0", "0000000000"}, {"00A4", "0000000000"},
    	      {"00A8", "0000000000"}, {"00AC", "0000000000"},
    	      {"00B0", "0000000000"}, {"00B4", "0000000000"},
    	      {"00B8", "0000000000"}, {"00BC", "0000000000"},
    	      {"00C0", "0000000000"}, {"00C4", "0000000000"},
    	      {"00C8", "0000000000"}, {"00CC", "0000000000"},
    	      {"00D0", "0000000000"}, {"00D4", "0000000000"},
    	      {"00D8", "0000000000"}, {"00DC", "0000000000"},
    	      {"00E0", "0000000000"}, {"00E4", "0000000000"},
    	      {"00E8", "0000000000"}, {"00EC", "0000000000"},
    	      {"00F0", "0000000000"}, {"00F4", "0000000000"},
    	      {"00F8", "0000000000"}, {"00FC", "0000000000"},
    	      {"0100", "0000000000"}, {"0104", "0000000000"},
    	      {"0108", "0000000000"}, {"010C", "0000000000"},
    	      {"0110", "0000000000"}, {"0114", "0000000000"},
    	      {"0118", "0000000000"}, {"011C", "0000000000"},
    	      {"0120", "0000000000"}, {"0124", "0000000000"},
    	      {"0128", "0000000000"}, {"012C", "0000000000"},
    	      {"0130", "0000000000"}, {"0134", "0000000000"},
    	      {"0138", "0000000000"}, {"013C", "0000000000"},
    	      {"0140", "0000000000"}, {"0144", "0000000000"},
    	      {"0148", "0000000000"}, {"014C", "0000000000"},
    	      {"0150", "0000000000"}, {"0154", "0000000000"},
    	      {"0158", "0000000000"}, {"015C", "0000000000"},
    	      {"0160", "0000000000"}, {"0164", "0000000000"},
    	      {"0168", "0000000000"}, {"016C", "0000000000"},
    	      {"0170", "0000000000"}, {"0174", "0000000000"},
    	      {"0178", "0000000000"}, {"017C", "0000000000"},
    	      {"0180", "0000000000"}, {"0184", "0000000000"},
    	      {"0188", "0000000000"}, {"018C", "0000000000"},
    	      {"0190", "0000000000"}, {"0194", "0000000000"},
    	      {"0198", "0000000000"}, {"019C", "0000000000"},
    	      {"01A0", "0000000000"}, {"01A4", "0000000000"},
    	      {"01A8", "0000000000"}, {"01AC", "0000000000"},
    	      {"01B0", "0000000000"}, {"01B4", "0000000000"},
    	      {"01B8", "0000000000"}, {"01BC", "0000000000"},
    	      {"01C0", "0000000000"}, {"01C4", "0000000000"},
    	      {"01C8", "0000000000"}, {"01CC", "0000000000"},
    	      {"01D0", "0000000000"}, {"01D4", "0000000000"},
    	      {"01D8", "0000000000"}, {"01DC", "0000000000"},
    	      {"01E0", "0000000000"}, {"01E4", "0000000000"},
    	      {"01E8", "0000000000"}, {"01EC", "0000000000"},
    	      {"01F0", "0000000000"}, {"01F4", "0000000000"},
    	      {"01F8", "0000000000"}, {"01FC", "0000000000"},
    	      {"0200", "0000000000"}, {"0204", "0000000000"},
    	      {"0208", "0000000000"}, {"020C", "0000000000"},
    	      {"0210", "0000000000"}, {"0214", "0000000000"},
    	      {"0218", "0000000000"}, {"021C", "0000000000"},
    	      {"0220", "0000000000"}, {"0224", "0000000000"},
    	      {"0228", "0000000000"}, {"022C", "0000000000"},
    	      {"0230", "0000000000"}, {"0234", "0000000000"},
    	      {"0238", "0000000000"}, {"023C", "0000000000"},
    	      {"0240", "0000000000"}, {"0244", "0000000000"},
    	      {"0248", "0000000000"}, {"024C", "0000000000"},
    	      {"0250", "0000000000"}, {"0254", "0000000000"},
    	      {"0258", "0000000000"}, {"025C", "0000000000"},
    	      {"0260", "0000000000"}, {"0264", "0000000000"},
    	      {"0268", "0000000000"}, {"026C", "0000000000"},
    	      {"0270", "0000000000"}, {"0274", "0000000000"},
    	      {"0278", "0000000000"}, {"027C", "0000000000"},
    	      {"0280", "0000000000"}, {"0284", "0000000000"},
    	      {"0288", "0000000000"}, {"028C", "0000000000"},
    	      {"0290", "0000000000"}, {"0294", "0000000000"},
    	      {"0298", "0000000000"}, {"029C", "0000000000"},
    	      {"02A0", "0000000000"}, {"02A4", "0000000000"},
    	      {"02A8", "0000000000"}, {"02AC", "0000000000"},
    	      {"02B0", "0000000000"}, {"02B4", "0000000000"},
    	      {"02B8", "0000000000"}, {"02BC", "0000000000"},
    	      {"02C0", "0000000000"}, {"02C4", "0000000000"},
    	      {"02C8", "0000000000"}, {"02CC", "0000000000"},
    	      {"02D0", "0000000000"}, {"02D4", "0000000000"},
    	      {"02D8", "0000000000"}, {"02DC", "0000000000"},
    	      {"02E0", "0000000000"}, {"02E4", "0000000000"},
    	      {"02E8", "0000000000"}, {"02EC", "0000000000"},
    	      {"02F0", "0000000000"}, {"02F4", "0000000000"},
    	      {"02F8", "0000000000"}, {"02FC", "0000000000"},
    	      {"0300", "0000000000"}, {"0304", "0000000000"},
    	      {"0308", "0000000000"}, {"030C", "0000000000"},
    	      {"0310", "0000000000"}, {"0314", "0000000000"},
    	      {"0318", "0000000000"}, {"031C", "0000000000"},
    	      {"0320", "0000000000"}, {"0324", "0000000000"},
    	      {"0328", "0000000000"}, {"032C", "0000000000"},
    	      {"0330", "0000000000"}, {"0334", "0000000000"},
    	      {"0338", "0000000000"}, {"033C", "0000000000"},
    	      {"0340", "0000000000"}, {"0344", "0000000000"},
    	      {"0348", "0000000000"}, {"034C", "0000000000"},
    	      {"0350", "0000000000"}, {"0354", "0000000000"},
    	      {"0358", "0000000000"}, {"035C", "0000000000"},
    	      {"0360", "0000000000"}, {"0364", "0000000000"},
    	      {"0368", "0000000000"}, {"036C", "0000000000"},
    	      {"0370", "0000000000"}, {"0374", "0000000000"},
    	      {"0378", "0000000000"}, {"037C", "0000000000"},
    	      {"0380", "0000000000"}, {"0384", "0000000000"},
    	      {"0388", "0000000000"}, {"038C", "0000000000"},
    	      {"0390", "0000000000"}, {"0394", "0000000000"},
    	      {"0398", "0000000000"}, {"039C", "0000000000"},
    	      {"03A0", "0000000000"}, {"03A4", "0000000000"},
    	      {"03A8", "0000000000"}, {"03AC", "0000000000"},
    	      {"03B0", "0000000000"}, {"03B4", "0000000000"},
    	      {"03B8", "0000000000"}, {"03BC", "0000000000"},
    	      {"03C0", "0000000000"}, {"03C4", "0000000000"},
    	      {"03C8", "0000000000"}, {"03CC", "0000000000"},
    	      {"03D0", "0000000000"}, {"03D4", "0000000000"},
    	      {"03D8", "0000000000"}, {"03DC", "0000000000"},
    	      {"03E0", "0000000000"}, {"03E4", "0000000000"},
    	      {"03E8", "0000000000"}, {"03EC", "0000000000"},
    	      {"03F0", "0000000000"}, {"03F4", "0000000000"},
    	      {"03F8", "0000000000"}, {"03FC", "0000000000"},
    };
    
    
    //addr locs 1000 - 1FFF
    public static String qqq[] = {"address", "opcode"};
    public static String memorylocs[][] = {
    		{"1000", "0000000000"},	{"1004", "0000000000"},
    		{"1008", "0000000000"}, {"100C", "0000000000"},
    		{"1010", "0000000000"},	{"1014", "0000000000"},
    		{"1018", "0000000000"}, {"101C", "0000000000"},
    		{"1020", "0000000000"},	{"1024", "0000000000"},
    		{"1028", "0000000000"}, {"102C", "0000000000"},
    		{"1030", "0000000000"},	{"1034", "0000000000"},
    		{"1038", "0000000000"}, {"103C", "0000000000"},
    		{"1040", "0000000000"},	{"1044", "0000000000"},
    		{"1048", "0000000000"}, {"104C", "0000000000"},
    		{"1050", "0000000000"},	{"1054", "0000000000"},
    		{"1058", "0000000000"}, {"105C", "0000000000"},
    		{"1060", "0000000000"},	{"1064", "0000000000"},
    		{"1068", "0000000000"}, {"106C", "0000000000"},
    		{"1070", "0000000000"},	{"1074", "0000000000"},
    		{"1078", "0000000000"}, {"107C", "0000000000"},
    		{"1080", "0000000000"},	{"1084", "0000000000"},
    		{"1088", "0000000000"}, {"108C", "0000000000"},
    		{"1090", "0000000000"},	{"1094", "0000000000"},
    		{"1098", "0000000000"}, {"109C", "0000000000"},
    		{"10A0", "0000000000"},	{"10A4", "0000000000"},
    		{"10A8", "0000000000"}, {"10AC", "0000000000"},
    		{"10B0", "0000000000"},	{"10B4", "0000000000"},
    		{"10B8", "0000000000"}, {"10BC", "0000000000"},
    		{"10C0", "0000000000"},	{"10C4", "0000000000"},
    		{"10C8", "0000000000"}, {"10CC", "0000000000"},
    		{"10D0", "0000000000"},	{"10D4", "0000000000"},
    		{"10D8", "0000000000"}, {"10DC", "0000000000"},
    		{"10E0", "0000000000"},	{"10E4", "0000000000"},
    		{"10E8", "0000000000"}, {"10EC", "0000000000"},
    		{"10F0", "0000000000"},	{"10F4", "0000000000"},
    		{"10F8", "0000000000"}, {"10FC", "0000000000"},
    		{"1100", "0000000000"},	{"1104", "0000000000"},
    		{"1108", "0000000000"}, {"110C", "0000000000"},
    		{"1110", "0000000000"},	{"1114", "0000000000"},
    		{"1118", "0000000000"}, {"111C", "0000000000"},
    		{"1120", "0000000000"},	{"1124", "0000000000"},
    		{"1128", "0000000000"}, {"112C", "0000000000"},
    		{"1130", "0000000000"},	{"1134", "0000000000"},
    		{"1138", "0000000000"}, {"113C", "0000000000"},
    		{"1140", "0000000000"},	{"1144", "0000000000"},
    		{"1148", "0000000000"}, {"114C", "0000000000"},
    		{"1150", "0000000000"},	{"1154", "0000000000"},
    		{"1158", "0000000000"}, {"115C", "0000000000"},
    		{"1160", "0000000000"},	{"1164", "0000000000"},
    		{"1168", "0000000000"}, {"116C", "0000000000"},
    		{"1170", "0000000000"},	{"1174", "0000000000"},
    		{"1178", "0000000000"}, {"117C", "0000000000"},
    		{"1180", "0000000000"},	{"1184", "0000000000"},
    		{"1188", "0000000000"}, {"118C", "0000000000"},
    		{"1190", "0000000000"},	{"1194", "0000000000"},
    		{"1198", "0000000000"}, {"119C", "0000000000"},
    		{"11A0", "0000000000"},	{"11A4", "0000000000"},
    		{"11A8", "0000000000"}, {"11AC", "0000000000"},
    		{"11B0", "0000000000"},	{"11B4", "0000000000"},
    		{"11B8", "0000000000"}, {"11BC", "0000000000"},
    		{"11C0", "0000000000"},	{"11C4", "0000000000"},
    		{"11C8", "0000000000"}, {"11CC", "0000000000"},
    		{"11D0", "0000000000"},	{"11D4", "0000000000"},
    		{"11D8", "0000000000"}, {"11DC", "0000000000"},
    		{"11E0", "0000000000"},	{"11E4", "0000000000"},
    		{"11E8", "0000000000"}, {"11EC", "0000000000"},
    		{"11F0", "0000000000"},	{"11F4", "0000000000"},
    		{"11F8", "0000000000"}, {"11FC", "0000000000"},
    		{"1200", "0000000000"},	{"1204", "0000000000"},
    		{"1208", "0000000000"}, {"120C", "0000000000"},
    		{"1210", "0000000000"},	{"1214", "0000000000"},
    		{"1218", "0000000000"}, {"121C", "0000000000"},
    		{"1220", "0000000000"},	{"1224", "0000000000"},
    		{"1228", "0000000000"}, {"122C", "0000000000"},
    		{"1230", "0000000000"},	{"1234", "0000000000"},
    		{"1238", "0000000000"}, {"123C", "0000000000"},
    		{"1240", "0000000000"},	{"1244", "0000000000"},
    		{"1248", "0000000000"}, {"124C", "0000000000"},
    		{"1250", "0000000000"},	{"1254", "0000000000"},
    		{"1258", "0000000000"}, {"125C", "0000000000"},
    		{"1260", "0000000000"},	{"1264", "0000000000"},
    		{"1268", "0000000000"}, {"126C", "0000000000"},
    		{"1270", "0000000000"},	{"1274", "0000000000"},
    		{"1278", "0000000000"}, {"127C", "0000000000"},
    		{"1280", "0000000000"},	{"1284", "0000000000"},
    		{"1288", "0000000000"}, {"128C", "0000000000"},
    		{"1290", "0000000000"},	{"1294", "0000000000"},
    		{"1298", "0000000000"}, {"129C", "0000000000"},
    		{"12A0", "0000000000"},	{"12A4", "0000000000"},
    		{"12A8", "0000000000"}, {"12AC", "0000000000"},
    		{"12B0", "0000000000"},	{"12B4", "0000000000"},
    		{"12B8", "0000000000"}, {"12BC", "0000000000"},
    		{"12C0", "0000000000"},	{"12C4", "0000000000"},
    		{"12C8", "0000000000"}, {"12CC", "0000000000"},
    		{"12D0", "0000000000"},	{"12D4", "0000000000"},
    		{"12D8", "0000000000"}, {"12DC", "0000000000"},
    		{"12E0", "0000000000"},	{"12E4", "0000000000"},
    		{"12E8", "0000000000"}, {"12EC", "0000000000"},
    		{"12F0", "0000000000"},	{"12F4", "0000000000"},
    		{"12F8", "0000000000"}, {"12FC", "0000000000"},
    		{"1300", "0000000000"},	{"1304", "0000000000"},
    		{"1308", "0000000000"}, {"130C", "0000000000"},
    		{"1310", "0000000000"},	{"1314", "0000000000"},
    		{"1318", "0000000000"}, {"131C", "0000000000"},
    		{"1320", "0000000000"},	{"1324", "0000000000"},
    		{"1328", "0000000000"}, {"132C", "0000000000"},
    		{"1330", "0000000000"},	{"1334", "0000000000"},
    		{"1338", "0000000000"}, {"133C", "0000000000"},
    		{"1340", "0000000000"},	{"1344", "0000000000"},
    		{"1348", "0000000000"}, {"134C", "0000000000"},
    		{"1350", "0000000000"},	{"1354", "0000000000"},
    		{"1358", "0000000000"}, {"135C", "0000000000"},
    		{"1360", "0000000000"},	{"1364", "0000000000"},
    		{"1368", "0000000000"}, {"136C", "0000000000"},
    		{"1370", "0000000000"},	{"1374", "0000000000"},
    		{"1378", "0000000000"}, {"137C", "0000000000"},
    		{"1380", "0000000000"},	{"1384", "0000000000"},
    		{"1388", "0000000000"}, {"138C", "0000000000"},
    		{"1390", "0000000000"},	{"1394", "0000000000"},
    		{"1398", "0000000000"}, {"139C", "0000000000"},
    		{"13A0", "0000000000"},	{"13A4", "0000000000"},
    		{"13A8", "0000000000"}, {"13AC", "0000000000"},
    		{"13B0", "0000000000"},	{"13B4", "0000000000"},
    		{"13B8", "0000000000"}, {"13BC", "0000000000"},
    		{"13C0", "0000000000"},	{"13C4", "0000000000"},
    		{"13C8", "0000000000"}, {"13CC", "0000000000"},
    		{"13D0", "0000000000"},	{"13D4", "0000000000"},
    		{"13D8", "0000000000"}, {"13DC", "0000000000"},
    		{"13E0", "0000000000"},	{"13E4", "0000000000"},
    		{"13E8", "0000000000"}, {"13EC", "0000000000"},
    		{"13F0", "0000000000"},	{"13F4", "0000000000"},
    		{"13F8", "0000000000"}, {"13FC", "0000000000"},
    };
    
    public static DefaultTableModel memmodel = new DefaultTableModel(memorylocs, qqq);
    
    
    
    private JPanel panel_12;
    public static JTable table_1;
    public static JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MOD205 window = new MOD205();
					window.frmUmips.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MOD205() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUmips = new JFrame();
		frmUmips.getContentPane().setBackground(Color.PINK);
		frmUmips.setBackground(Color.PINK);
		frmUmips.setForeground(Color.PINK);
		frmUmips.setFont(new Font("Monospaced", Font.PLAIN, 15));
		frmUmips.setResizable(false);
		frmUmips.setType(Type.UTILITY);
		frmUmips.setTitle("MIPS64 simulator v1.0");
		frmUmips.setBounds(100, 100, 1090, 716);
		frmUmips.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUmips.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(15, 16, 1054, 33);
		frmUmips.getContentPane().add(panel);
		
		lblNewLabel = new JLabel("\u273Fmips64 simulator v1.0\u273F");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Monospaced", Font.ITALIC, 24));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tabbedPane.setBounds(15, 57, 1054, 603);
		frmUmips.getContentPane().add(tabbedPane);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("code", null, panel_1, null);
		panel_1.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 425, 567);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		lblEnterCodeBelow = new JLabel("enter code below:");
		lblEnterCodeBelow.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterCodeBelow.setBounds(0, 0, 425, 22);
		lblEnterCodeBelow.setFont(new Font("Monospaced", Font.PLAIN, 16));
		panel_3.add(lblEnterCodeBelow);
		
		button = new JButton("run");
		button.setBounds(97, 528, 99, 33);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetVar();
				//badumtsssssss run here
				String[] temp = textArea.getText().split("\n");
				int error = 0;
				int xd = temp.length;
				int cnt = 0;
				int errtype = 0; //1=insterror, 2=regerror, 3=immerror
				
				//check if may error
				while(error == 0 && cnt != xd){
					//set variable
					error = errorCheck(temp[cnt])[0];
					if(error == 1)
						if(errorCheck(temp[cnt])[1] == 1)
							errtype = 1;
						else if(errorCheck(temp[cnt])[2] == 1)
							errtype = 2;
						else
							errtype = 3;
					else
						cnt++;
				}
				
				//if no error continue to opcode, if error, alert "ERROR!"
				switch(error){
					case 0: 
						String henlo;
						//loop for opcode of each inst and print
						for(int q = 0; q < xd; q++){
							resetVar();
							if(temp[q].indexOf(':') == -1){
								henlo = wopCode(temp[q], q, temp);
								textArea_2.setText(textArea_2.getText() + "line # " + q + " - " + temp[q] + ": " + henlo + "\n\n");
								memorylocs[q][1] = henlo; //stores opcode in mem 
								System.out.println(memorylocs[q][1]);
							}
						}
						memmodel.fireTableDataChanged();
						table_2.repaint();
						textArea_1.setText("Congrats! No errors. Check generated opcode.");
						break;
						
					case 1:
						//alert error
						JOptionPane.showMessageDialog(null, "Error! Please reset and try again.", "Error!", 1);
						textArea_2.setText("Error!");
						
						//add errors to txt area 1.
						switch(errtype){
							case 1:
								textArea_1.setText("_Syntax Error_ on line" + (cnt+1));
								break;
							case 2:
								textArea_1.setText("_Register Error_ on line" + (cnt+1));
								break;
							case 3:
								textArea_1.setText("_Third Parameter/Immediate Error_ on line" + (cnt+1));
								break;
							default:
								//memmodel.fireTableChanged(new TableModelEvent(new GeneralTableModel()));
								memmodel.fireTableDataChanged();
								table_2.repaint();
								break;
						}
						break;
				}
			}
		});
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 27, 425, 489);
		panel_3.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setWrapStyleWord(true);
		scrollPane_1.setViewportView(textArea);
		button.setFont(new Font("Monospaced", Font.PLAIN, 18));
		panel_3.add(button);
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reset method reset all important variables
				reset();
			}
		});
		btnReset.setFont(new Font("Monospaced", Font.PLAIN, 18));
		btnReset.setBounds(228, 528, 99, 33);
		panel_3.add(btnReset);
		
		panel_4 = new JPanel();
		panel_4.setBounds(425, 0, 624, 567);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tabbedPane_1.setBounds(0, 0, 624, 567);
		panel_4.add(tabbedPane_1);
		
		panel_6 = new JPanel();
		tabbedPane_1.addTab("errors", null, panel_6, null);
		panel_6.setLayout(null);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(15, 16, 589, 499);
		panel_6.add(scrollPane_2);
		
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea_1.setEditable(false);
		textArea_1.setWrapStyleWord(true);
		scrollPane_2.setViewportView(textArea_1);
		
		panel_7 = new JPanel();
		tabbedPane_1.addTab("opcodes", null, panel_7, null);
		panel_7.setLayout(null);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(15, 16, 589, 499);
		panel_7.add(scrollPane_3);
		
		textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea_2.setEditable(false);
		textArea_2.setWrapStyleWord(true);
		scrollPane_3.setViewportView(textArea_2);
		
		panel_9 = new JPanel();
		tabbedPane_1.addTab("registers", null, panel_9, null);
		panel_9.setLayout(null);
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(15, 16, 589, 499);
		panel_9.add(scrollPane_5);
	
		table = new JTable(model)/*{
			@Override
			public boolean isCellEditable(int row, int column) {                
			  if(column == 0)
			     return false;
			  else return true;
			};
		}*/;
		
		table.setFont(new Font("Monospaced", Font.PLAIN, 16));
		table.setRowHeight(30);
		scrollPane_5.setViewportView(table);
		
		panel_10 = new JPanel();
		tabbedPane_1.addTab("memory", null, panel_10, null);
		panel_10.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tabbedPane_3.setBounds(15, 16, 589, 499);
		panel_10.add(tabbedPane_3);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		tabbedPane_3.addTab("data segments", null, scrollPane_6, null);
		
		table_1 = new JTable();
		scrollPane_6.setViewportView(table_1);
		
		panel_12 = new JPanel();
		tabbedPane_3.addTab("go-to memory", null, panel_12, null);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		tabbedPane_3.addTab("addresses", null, scrollPane_8, null);
		
		table_2 = new JTable(memmodel);
		table_2.setFont(new Font("Monospaced", Font.PLAIN, 16));
		table_2.setEnabled(false);
		scrollPane_8.setViewportView(table_2);
		
		panel_11 = new JPanel();
		panel_11.setLayout(null);
		tabbedPane_1.addTab("pipeline", null, panel_11, null);
		
		scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(15, 16, 589, 499);
		panel_11.add(scrollPane_7);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("help", null, panel_2, null);
		panel_2.setLayout(null);
		
		tabbedPane_2 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_2.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tabbedPane_2.setBounds(15, 16, 1019, 535);
		panel_2.add(tabbedPane_2);
		
		panel_5 = new JPanel();
		tabbedPane_2.addTab("sample code", null, panel_5, null);
		panel_5.setLayout(null);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 0, 878, 530);
		panel_5.add(scrollPane_4);
		
		txtpndataIbytebyte = new JTextPane();
		txtpndataIbytebyte.setText(".data\r\nibyte: .byte 0xF0\r\nibyte1: .byte 0x20, 0x30\r\n\r\ndouble: .double 4.0\r\ndouble1: .double 1.0, 2.0\r\n\r\nmessage: .asciiz \"hello, world\"\r\nmessage2: .asciiz \"hello COMPARC\"\r\n\r\n.code\r\nLD R2, double(R0)\r\nDADDIU R1, R0, #0000\r\nSD R3, double1(R0)");
		txtpndataIbytebyte.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtpndataIbytebyte.setEditable(false);
		scrollPane_4.setViewportView(txtpndataIbytebyte);
		
		panel_8 = new JPanel();
		tabbedPane_2.addTab("instructions", null, panel_8, null);
		panel_8.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 878, 530);
		panel_8.add(scrollPane);
		
		txtpnAvailableInstructions = new JTextPane();
		txtpnAvailableInstructions.setText("available instructions:\r\nLD R1, 1000(R0)\r\nSD R1, 1000(R0)\r\nDADDIU R2, R0, #1234\r\nDADDU R2, R0, R3\r\nSLT R1, R2, R3\r\nNOP\r\nBC LABEL1\r\nBGEC LABEL 2\r\nDAUI R2, R1, #1234\r\n\r\navailable registers:\r\nR0 - R31\r\n\r\nmemory locations:\r\n0000 - 0FFF\r\n\r\ninstruction addresses:\r\n1000 - 1FFF");
		txtpnAvailableInstructions.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtpnAvailableInstructions.setEditable(false);
		scrollPane.setViewportView(txtpnAvailableInstructions);
	}
	
	public int[] errorCheck(String yes){
		inputCode = yes;
		int[] myArray = new int[6];
		boolean loopCall = false;
		String[] breakCode = inputCode.split("(, )|( )|(,)"); //seperates the inputCode
		
		for(int i = 0; i < breakCode.length; i++)
        {
            breakCode[i] = breakCode[i].toUpperCase();
        }
		
		if(yes.matches("[a-zA-Z0-9]+[:]{1}"))
		{
			System.out.println("nice");
			loopCall = true;
		}
		
		 //to check if instruction is valid
	        for(int i = 0; i < ins.length && loopCall != true; i++)
	        {
	            if(breakCode[0].equals(ins[i])){
	                insError = 0;
	                break;
	            } else {
	            	insError = 1;
	            }
	        }
	        //seperate the instructions because these use a format of ins r1, r2, imm/r3
	        if(breakCode[0].equals("DADDIU")||breakCode[0].equals("DADDU")||breakCode[0].equals("SLT")||breakCode[0].equals("DAUI")||breakCode[0].equals("BGEC"))
	        {
	        	if(breakCode.length != 4)
	        		insError = 1;
	        	else {
		            //if register 1 is valid
		            for(int i = 0; i < reg.length; i++)
		            {
		                System.out.println(breakCode[1] + "         " + reg[i][0]);
		                if(breakCode[1].equals(reg[i][0]))
		                {
		                    firstRegError = 0;
		                    break;
		                }
		                else{
		                    firstRegError = 1;
		                }
		            }
	
		            //if register2 is valid
		            for(int i = 0; i < reg.length; i++)
		            {
		                if(breakCode[2].equals(reg[i][0]))
		                {
		                    secRegError = 0;
		                    break;
		                }
		                else{
		                    secRegError = 1;
		                }
		            }
		            
		            //checks what type of parameter the 3rd one is
		            if(breakCode[0].equals("DADDIU")||breakCode[0].equals("DAUI")) //commands with IMMEDIATE as their 3rd param
		            {
		                if(breakCode[3].charAt(0) == '#' && breakCode[3].length() ==  5) //checks if it starts with a # and is 5 chars long (# + hex)
		                    thirdError = 0;
		                else
		                    thirdError = 1;
		                   
		            }
		            
		            else if(breakCode[0].equals("DADDU")||breakCode[0].equals("SLT")) //commands with 3RD REGISTER as their 3rd param
		            {
		                for(int i = 0; i < reg.length; i++)
		                {
		                    if(breakCode[3].equals(reg[i][0]))
		                    {
		                        thirdError = 0;
		                        break;
		                    }
		                    else{
		                        thirdError = 1;
		                    }
		                }
		            }
		            else { //BGEC
		                for(int i = 0 ; i < reg.length; i++)
		                {
		                    if(breakCode[3].equals(reg[i][0]))
		                    {
		                        thirdError = 1;
		                        break;
		                    } 
		                    else
		                    {
		                        thirdError = 0;
		                    }
		                }
		                
		                if(!breakCode[3].matches("[A-Za-z0-9]+"))
		                {
		                    thirdError = 1;
		                }
		            }
	        	}
	        }

	        else if(breakCode[0].equals("LD")||breakCode[0].equals("SD")) //2 PARAMETER INSTRUCTIONS
	        {
	        	if(breakCode.length != 3)
	        		insError = 1;
	        	else {
		            if(breakCode[0].equals("LD"))
		            {    //checks first parameter
		                for(int i = 0; i < reg.length; i++)
		                {
		                    if(breakCode[1].equals(reg[i][0]) || breakCode[1].equals(null))
		                    {
		                        firstRegError = 0;
		                        break;
		                    }
		                    else{
		                        firstRegError = 1;
		                    }
		                }
	
		                if(!breakCode[2].matches("[A-Za-z0-9()]+")|| breakCode[2].equals(null))
		                {
		                        thirdError = 1;
		                }
		            }
		            else{
		                for(int i = 0; i < reg.length; i++)
		                {
		                    if(breakCode[1].equals(reg[i][0]))
		                    {
		                        firstRegError = 0;
		                        break;
		                    }
		                    else{
		                        firstRegError = 1;
		                    }
		                }
	
		                if(!breakCode[2].matches("[A-Za-z0-9()]+"))
		                {
		                        thirdError = 1;
		                }
		            }
	        	}
	        }
	        
	        else if(breakCode[0].equals("BC"))
	        {
	        	if(breakCode.length != 2)
	        		insError = 1;
	        	else {
		            if(breakCode[1].matches("[A-Za-z0-9]+"))
		            {
		                branchError = 0;
		            }
		            else{
		                branchError = 1;
		            }
	        	}
	        }
	        
	        else if (breakCode[0].equals("NOP")) { 
	        	//NOP instruction
	            //checks 1st parameter
	        	//make sure its not read as LABEL
	        	if(breakCode.length > 1)
	            	insError = 1;
	        }
	        
	        
	        //returns if error and error typez
	        if(insError == 0 && firstRegError == 0 && thirdError == 0 && secRegError == 0 && branchError == 0)
	        	myArray[0] = 0;
	        else
	        	myArray[0] = 1;
	        
	        myArray[1] = insError;
	        myArray[2] = firstRegError;
	        myArray[3] = thirdError;
	        myArray[4] = secRegError;
	        myArray[5] = branchError;
	        
	        return myArray;
	    }
	
	
	//OPCODEEEEEEEEEE
	public String wopCode(String yes, int offset, String[] yeet){
		inputCode = yes;
		breakCode = inputCode.split("(, )|( )|(,)");
		toConv = new String[8];
		
		for(int i = 0; i < breakCode.length; i++)
        {
            breakCode[i] = breakCode[i].toUpperCase();
        }
        
        if(breakCode[0].equals("DADDIU"))
        {
        	j = 0;
            String tempA = breakCode[1].substring(1);
            String tempB = breakCode[2].substring(1);
            String tempC = breakCode[3].substring(1);
            opcodeBin = "011001";
            opcodeA = Integer.parseInt(tempB); //rs
            opcodeB = Integer.parseInt(tempA); //rt
            opcodeImm = Integer.parseInt(tempC,16); //imm
            
            aBin = String.format("%5s", Integer.toBinaryString(opcodeA)).replace(' ', '0');
            bBin = String.format("%5s", Integer.toBinaryString(opcodeB)).replace(' ', '0');
            immBin = String.format("%16s", Integer.toBinaryString(opcodeImm)).replace(' ', '0');
            
            finalOp = opcodeBin + aBin + bBin + immBin;
            
            for(int i = 0; i < 8; i++)
            {
                toConv[i] = finalOp.substring(j, j+4);
                j += 4;
            }
            for(int i = 0; i < toConv.length; i++)
            {
                switch(toConv[i])
                {
                    case "0000" : hexOp += "0";
                        break;
                    case "0001" : hexOp += "1";
                        break;
                    case "0010" : hexOp += "2";
                        break;
                    case "0011" : hexOp += "3";
                        break;
                    case "0100" : hexOp += "4";
                        break;
                    case "0101" : hexOp += "5";
                        break;
                    case "0110" : hexOp += "6";
                        break;
                    case "0111" : hexOp += "7";
                        break;
                    case "1000" : hexOp += "8";
                        break;
                    case "1001" : hexOp += "9";
                        break;
                    case "1010" : hexOp += "A";
                        break;
                    case "1011" : hexOp += "B";
                        break;
                    case "1100" : hexOp += "C";
                        break;
                    case "1101" : hexOp += "D";
                        break;
                    case "1110" : hexOp += "E";
                        break;
                    case "1111" : hexOp += "F";
                        break;
                         
                }
                
            }
            
            System.out.println(hexOp);
        }
        else if(breakCode[0].equals("LD"))
        {
        	j = 0;
            opcodeBin = "110111";
            String tempA = breakCode[2].substring(6).substring(0,1);
            String tempB = breakCode[1].substring(1);
            String tempC = breakCode[2].substring(0,4);
            
            opcodeA = Integer.parseInt(tempA); //rs
            opcodeB = Integer.parseInt(tempB); //rt
            opcodeImm = Integer.parseInt(tempC , 16); //imm
            
            aBin = String.format("%5s", Integer.toBinaryString(opcodeA)).replace(' ', '0');
            bBin = String.format("%5s", Integer.toBinaryString(opcodeB)).replace(' ', '0');
            immBin = String.format("%16s", Integer.toBinaryString(opcodeImm)).replace(' ', '0');
            
            finalOp = opcodeBin + aBin + bBin + immBin;
            System.out.println(finalOp);
            
            for(int i = 0; i < 8; i++)
            {
                toConv[i] = finalOp.substring(j, j+4);
                j += 4;
            }
            for(int i = 0; i < toConv.length; i++)
            {
                switch(toConv[i])
                {
                    case "0000" : hexOp += "0";
                        break;
                    case "0001" : hexOp += "1";
                        break;
                    case "0010" : hexOp += "2";
                        break;
                    case "0011" : hexOp += "3";
                        break;
                    case "0100" : hexOp += "4";
                        break;
                    case "0101" : hexOp += "5";
                        break;
                    case "0110" : hexOp += "6";
                        break;
                    case "0111" : hexOp += "7";
                        break;
                    case "1000" : hexOp += "8";
                        break;
                    case "1001" : hexOp += "9";
                        break;
                    case "1010" : hexOp += "A";
                        break;
                    case "1011" : hexOp += "B";
                        break;
                    case "1100" : hexOp += "C";
                        break;
                    case "1101" : hexOp += "D";
                        break;
                    case "1110" : hexOp += "E";
                        break;
                    case "1111" : hexOp += "F";
                        break;
                         
                }
            }
        }
        else if(breakCode[0].equals("SD"))
        {
        	j = 0;
            opcodeBin = "111111";
            String tempA = breakCode[2].substring(6).substring(0,1);
            String tempB = breakCode[1].substring(1);
            String tempC = breakCode[2].substring(0,4);
            
            opcodeA = Integer.parseInt(tempA); //rs
            opcodeB = Integer.parseInt(tempB); //rt
            opcodeImm = Integer.parseInt(tempC , 16); //imm
            
            aBin = String.format("%5s", Integer.toBinaryString(opcodeA)).replace(' ', '0');
            bBin = String.format("%5s", Integer.toBinaryString(opcodeB)).replace(' ', '0');
            immBin = String.format("%16s", Integer.toBinaryString(opcodeImm)).replace(' ', '0');
            
            finalOp = opcodeBin + aBin + bBin + immBin;
             for(int i = 0; i < 8; i++)
            {
                toConv[i] = finalOp.substring(j, j+4);
                j += 4;
            }
            for(int i = 0; i < toConv.length; i++)
            {
                switch(toConv[i])
                {
                    case "0000" : hexOp += "0";
                        break;
                    case "0001" : hexOp += "1";
                        break;
                    case "0010" : hexOp += "2";
                        break;
                    case "0011" : hexOp += "3";
                        break;
                    case "0100" : hexOp += "4";
                        break;
                    case "0101" : hexOp += "5";
                        break;
                    case "0110" : hexOp += "6";
                        break;
                    case "0111" : hexOp += "7";
                        break;
                    case "1000" : hexOp += "8";
                        break;
                    case "1001" : hexOp += "9";
                        break;
                    case "1010" : hexOp += "A";
                        break;
                    case "1011" : hexOp += "B";
                        break;
                    case "1100" : hexOp += "C";
                        break;
                    case "1101" : hexOp += "D";
                        break;
                    case "1110" : hexOp += "E";
                        break;
                    case "1111" : hexOp += "F";
                        break;
                         
                }
                
            }
        }
        else if(breakCode[0].equals("DADDU"))
        {
        	j = 0;
            opcodeBin = "000000";
            String tempA = breakCode[1].substring(1);
            String tempB = breakCode[2].substring(1);
            String tempC = breakCode[3].substring(1);
            String sa = "00000";
            String func = "101101";
            
            opcodeA = Integer.parseInt(tempA); //rs
            opcodeB = Integer.parseInt(tempB); //rt
            opcodeImm = Integer.parseInt(tempC); //imm
            
            aBin = String.format("%5s", Integer.toBinaryString(opcodeA)).replace(' ', '0');
            bBin = String.format("%5s", Integer.toBinaryString(opcodeB)).replace(' ', '0');
            immBin = String.format("%5s", Integer.toBinaryString(opcodeImm)).replace(' ', '0');
            
            finalOp = opcodeBin + bBin + immBin + aBin + sa + func;
             for(int i = 0; i < 8; i++)
            {
                toConv[i] = finalOp.substring(j, j+4);
                j += 4;
            }
            for(int i = 0; i < toConv.length; i++)
            {
                switch(toConv[i])
                {
                    case "0000" : hexOp += "0";
                        break;
                    case "0001" : hexOp += "1";
                        break;
                    case "0010" : hexOp += "2";
                        break;
                    case "0011" : hexOp += "3";
                        break;
                    case "0100" : hexOp += "4";
                        break;
                    case "0101" : hexOp += "5";
                        break;
                    case "0110" : hexOp += "6";
                        break;
                    case "0111" : hexOp += "7";
                        break;
                    case "1000" : hexOp += "8";
                        break;
                    case "1001" : hexOp += "9";
                        break;
                    case "1010" : hexOp += "A";
                        break;
                    case "1011" : hexOp += "B";
                        break;
                    case "1100" : hexOp += "C";
                        break;
                    case "1101" : hexOp += "D";
                        break;
                    case "1110" : hexOp += "E";
                        break;
                    case "1111" : hexOp += "F";
                        break;
                         
                }
                
            }
            
        }
        else if(breakCode[0].equals("SLT"))
        {
        	j = 0;
            opcodeBin = "000000";
            String tempA = breakCode[1].substring(1);
            String tempB = breakCode[2].substring(1);
            String tempC = breakCode[3].substring(1);
            String sa = "00000";
            String func = "101010";
            
            opcodeA = Integer.parseInt(tempA); //rs
            opcodeB = Integer.parseInt(tempB); //rt
            opcodeImm = Integer.parseInt(tempC); //imm
            
            aBin = String.format("%5s", Integer.toBinaryString(opcodeA)).replace(' ', '0');
            bBin = String.format("%5s", Integer.toBinaryString(opcodeB)).replace(' ', '0');
            immBin = String.format("%5s", Integer.toBinaryString(opcodeImm)).replace(' ', '0');
            
            finalOp = opcodeBin + bBin + immBin + aBin + sa + func;
             for(int i = 0; i < 8; i++)
            {
                toConv[i] = finalOp.substring(j, j+4);
                j += 4;
            }
            for(int i = 0; i < toConv.length; i++)
            {
                switch(toConv[i])
                {
                    case "0000" : hexOp += "0";
                        break;
                    case "0001" : hexOp += "1";
                        break;
                    case "0010" : hexOp += "2";
                        break;
                    case "0011" : hexOp += "3";
                        break;
                    case "0100" : hexOp += "4";
                        break;
                    case "0101" : hexOp += "5";
                        break;
                    case "0110" : hexOp += "6";
                        break;
                    case "0111" : hexOp += "7";
                        break;
                    case "1000" : hexOp += "8";
                        break;
                    case "1001" : hexOp += "9";
                        break;
                    case "1010" : hexOp += "A";
                        break;
                    case "1011" : hexOp += "B";
                        break;
                    case "1100" : hexOp += "C";
                        break;
                    case "1101" : hexOp += "D";
                        break;
                    case "1110" : hexOp += "E";
                        break;
                    case "1111" : hexOp += "F";
                        break;
                         
                }
                
            }
        }
        else if(breakCode[0].equals("BC"))
        {
            opcodeBin = "110010";
            int found = 0;
            int countz = -1; //ito ung layo ng label from branch
            //found label breakCode[1]
            String temp = breakCode[1] + ":";
            int aaa = yeet.length;
            int count_2 = offset;
            boolean flag = false;
            boolean negative_flag = true;
            
            /* my code
            while(found == 0){
            	if(yeet[offset].equals(temp)){
            		found = 1;
            	}
            	else
            		countz++;
            	offset++;	
            }
            */
            
            //miko code
            while(found == 0){
            	if((offset != aaa)&&(!flag)){
            		if(yeet[offset].equals(temp)){
            			found = 1;
            		}
            		else
            			countz++;
            		offset++;	
            	}
            	else{
            		flag = true;
            		countz = -1;
            		while(negative_flag){
            			if(yeet[count_2].equals(temp)){
            				negative_flag = false;
            				found = 1;
            			}
            			else
            				countz++;
            			count_2--;
            		}
            	}
            }
            // e o c
            
            System.out.println(countz);
            
            
            immBin = String.format("%26s", Integer.toBinaryString(countz)).replace(' ', '0');
            finalOp = opcodeBin + immBin;
            
            
            for(int i = 0; i < 8; i++)
            {
                toConv[i] = finalOp.substring(j, j+4);
                System.out.println(toConv[i]);
                j += 4;

            }
            for(int i = 0; i < toConv.length; i++)
            {
                switch(toConv[i])
                {
                    case "0000" : hexOp += "0";
                        break;
                    case "0001" : hexOp += "1";
                        break;
                    case "0010" : hexOp += "2";
                        break;
                    case "0011" : hexOp += "3";
                        break;
                    case "0100" : hexOp += "4";
                        break;
                    case "0101" : hexOp += "5";
                        break;
                    case "0110" : hexOp += "6";
                        break;
                    case "0111" : hexOp += "7";
                        break;
                    case "1000" : hexOp += "8";
                        break;
                    case "1001" : hexOp += "9";
                        break;
                    case "1010" : hexOp += "A";
                        break;
                    case "1011" : hexOp += "B";
                        break;
                    case "1100" : hexOp += "C";
                        break;
                    case "1101" : hexOp += "D";
                        break;
                    case "1110" : hexOp += "E";
                        break;
                    case "1111" : hexOp += "F";
                        break;
                         
                }
                
            }
            
            
        }
        else if(breakCode[0].equals("BGEC"))
        {
            opcodeBin = "010110";
            int found = 0;
            int countz = -1; //ito ung layo ng label from branch
            //found label breakCode[1]
            String tempA = breakCode[1].substring(1);
            String tempB = breakCode[2].substring(1);
            String temp = breakCode[3] + ":";
            
            int aaa = yeet.length;
            int count_2 = offset;
            boolean flag = false;
            boolean negative_flag = true;
            
            /*
            while(found == 0){
            	if(yeet[offset].equals(temp)){
            		found = 1;
            	}
            	else
            		countz++;
            	offset++;	
            }
            */
            
            //miko code
            while(found == 0){
            	if((offset != aaa)&&(!flag)){
            		if(yeet[offset].equals(temp)){
            			found = 1;
            		}
            		else
            			countz++;
            		offset++;	
            	}
            	else{
            		flag = true;
            		countz = -1;
            		while(negative_flag){
            			if(yeet[count_2].equals(temp)){
            				negative_flag = false;
            				found = 1;
            			}
            			else
            				countz++;
            			count_2--;
            		}
            	}
            }
            // e o c
            
            System.out.println(countz);
            
            aBin = String.format("%5s", Integer.toBinaryString(opcodeA)).replace(' ', '0');
            bBin = String.format("%5s", Integer.toBinaryString(opcodeB)).replace(' ', '0');
            immBin = String.format("%16s", Integer.toBinaryString(countz)).replace(' ', '0');
            finalOp = opcodeBin + aBin + bBin + immBin;
            System.out.println(finalOp);
            
            for(int i = 0; i < 8; i++)
            {
                toConv[i] = finalOp.substring(j, j+4);
                j += 4;
            }
            for(int i = 0; i < toConv.length; i++)
            {
                switch(toConv[i])
                {
                    case "0000" : hexOp += "0";
                        break;
                    case "0001" : hexOp += "1";
                        break;
                    case "0010" : hexOp += "2";
                        break;
                    case "0011" : hexOp += "3";
                        break;
                    case "0100" : hexOp += "4";
                        break;
                    case "0101" : hexOp += "5";
                        break;
                    case "0110" : hexOp += "6";
                        break;
                    case "0111" : hexOp += "7";
                        break;
                    case "1000" : hexOp += "8";
                        break;
                    case "1001" : hexOp += "9";
                        break;
                    case "1010" : hexOp += "A";
                        break;
                    case "1011" : hexOp += "B";
                        break;
                    case "1100" : hexOp += "C";
                        break;
                    case "1101" : hexOp += "D";
                        break;
                    case "1110" : hexOp += "E";
                        break;
                    case "1111" : hexOp += "F";
                        break;
                         
                }
                
            }
            
            
        }
        else if(breakCode[0].equals("DAUI"))
        {
        	j = 0;
            opcodeBin = "011101";
            String tempA = breakCode[1].substring(1);
            String tempB = breakCode[2].substring(1);
            String tempC = breakCode[3].substring(1);
            
            opcodeA = Integer.parseInt(tempA); //rs
            opcodeB = Integer.parseInt(tempB); //rt
            opcodeImm = Integer.parseInt(tempC); //imm
            
            aBin = String.format("%5s", Integer.toBinaryString(opcodeA)).replace(' ', '0');
            bBin = String.format("%5s", Integer.toBinaryString(opcodeB)).replace(' ', '0');
            immBin = String.format("%16s", Integer.toBinaryString(opcodeImm)).replace(' ', '0');
            
            finalOp = opcodeBin + bBin + aBin + immBin;
             for(int i = 0; i < 8; i++)
            {
                toConv[i] = finalOp.substring(j, j+4);
                j += 4;
            }
            for(int i = 0; i < toConv.length; i++)
            {
                switch(toConv[i])
                {
                    case "0000" : hexOp += "0";
                        break;
                    case "0001" : hexOp += "1";
                        break;
                    case "0010" : hexOp += "2";
                        break;
                    case "0011" : hexOp += "3";
                        break;
                    case "0100" : hexOp += "4";
                        break;
                    case "0101" : hexOp += "5";
                        break;
                    case "0110" : hexOp += "6";
                        break;
                    case "0111" : hexOp += "7";
                        break;
                    case "1000" : hexOp += "8";
                        break;
                    case "1001" : hexOp += "9";
                        break;
                    case "1010" : hexOp += "A";
                        break;
                    case "1011" : hexOp += "B";
                        break;
                    case "1100" : hexOp += "C";
                        break;
                    case "1101" : hexOp += "D";
                        break;
                    case "1110" : hexOp += "E";
                        break;
                    case "1111" : hexOp += "F";
                        break;
                         
                }
                
            }
            
        }
        else if(breakCode.equals("NOP"))
        {
            finalOp = "00000000000000000000000000000000";
            hexOp = "00000000";
        }
        
        
        
        return hexOp;
    }
	
	public String pipeline(String yes, int offset, String[] yeet){
		
		
		return "test";
	}
	
	public void resetVar(){
			inputCode = "";
			breakCode = null;
			insError = 0;
			firstRegError = 0;
			thirdError = 0;
			secRegError = 0;
			branchError = 0;

			opcodeBin = "";
	        opcodeA = 0;
	        opcodeB = 0;
	        opcodeImm = 0;
	        finalOp = "";
	        hexOp = "";
	        aBin = ""; 
	        bBin = "";
	        immBin = "";
	        j = 0;
	        toConv = new String[8];
			
	}
	
	public void reset(){
		//textArea.setText("");
		textArea_1.setText("");
		textArea_2.setText("");
		resetVar();
	}
}
