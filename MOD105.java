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

public class MOD105 {
	
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
    private JScrollPane scrollPane_6;
    private JPanel panel_11;
    private JScrollPane scrollPane_7;
    private JTable table;

    
    //module 2
    public static DefaultTableModel model = new DefaultTableModel(reg, cols);
    
    //memory locs
    public static String memorylocs[][] = {
    		{"1000", "0000000000"},	{"1004", "0000000000"},
    		{"1008", "0000000000"}, {"100C", "0000000000"},
    		{"1010", "0000000000"},	{"1014", "0000000000"},
    		{"1018", "0000000000"}, {"101C", "0000000000"},
    };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MOD105 window = new MOD105();
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
	public MOD105() {
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
							henlo = wopCode(temp[q], q, temp);
							textArea_2.setText(textArea_2.getText() + "line # " + q + " - " + temp[q] + ": " + henlo + "\n\n");
						}
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
	
		table = new JTable(model);
		table.setRowHeight(30);
		scrollPane_5.setViewportView(table);
		
		panel_10 = new JPanel();
		panel_10.setLayout(null);
		tabbedPane_1.addTab("memory", null, panel_10, null);
		
		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(15, 16, 589, 499);
		panel_10.add(scrollPane_6);
		
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
		txtpnAvailableInstructions.setText("available instructions:\r\n\r\nLD\r\nSD\r\nDADDIU\r\nDADDU\r\nSLT\r\nNOP\r\nBC\r\nBGEC\r\nDAUI\r\n\r\navailable registers:\r\nR0-R31");
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
	        	if(breakCode.length == 1)
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
            while(found == 0){
            	if(yeet[offset].equals(temp)){
            		found = 1;
            	}
            	else
            		countz++;
            	offset++;	
            }
            
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
            while(found == 0){
            	if(yeet[offset].equals(temp)){
            		found = 1;
            	}
            	else
            		countz++;
            	offset++;	
            }
            
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
