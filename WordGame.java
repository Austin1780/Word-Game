/*
	Austin Smith
	"WordGame.java" Applet
	AP Comp Sci 2H
	May 13th, 2005
*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component.*;
import java.awt.Container.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class WordGame extends Applet implements ActionListener
  // This allows you to play Hang-Man
{
  // These are the various variable values, buttons, and fonts.
  Button[] button = new Button[26];
  Button newGame = new Button("New Game");
  Button levels[] = new Button[3];
  Font Font1 = new Font("Helvetica", Font.BOLD, 72);
  Font Font2 = new Font("Helvetica", Font.PLAIN, 14);
  Font Font3 = new Font("Helvetica", Font.BOLD, 48);
  Font Font4 = new Font("Helvetica", Font.BOLD, 20);
  Font Font5 = new Font("Helvetica", Font.BOLD, 100);
  Image imgBackground;
  Image[] flag = new Image[193];
  Image[] map = new Image[193];
  boolean firstTime = true;
  boolean easy = true;
  boolean medium = false;
  boolean hard = false;
  int mistakes = 0;
  int sss = 0;
  int ss = 0;
  // These are the letters.
  char letters1[] = {'A','B','C','D','E','F','G','H','I','J',
                     'K','L','M','N','O','P','Q','R','S','T',
                     'U','V','W','X','Y','Z'};
  // This is the array of words in the game.
  String word[] = {"AFGHANISTAN","ALBANIA","ALGERIA","ANDORRA","ANGOLA",
  				   "ANTIGUA AND BARBUDA","ARGENTINA","ARMENIA",
  				   "AUSTRALIA","AUSTRIA","AZERBAIJAN","BAHAMAS",
  				   "BAHRAIN","BANGLADESH","BARBADOS","BELARUS",
  				   "BELGIUM","BELIZE","BENIN","BHUTAN","BOLIVIA",
  				   "BOSNIA AND HERZEGOVINA","BOTSWANA","BRAZIL",
  				   "BRUNEI","BULGARIA","BURKINA FASO","BURUNDI",
  				   "CAMBODIA","CAMEROON","CANADA","CAPE VERDE",
  				   "CENTRAL AFRICAN REPUBLIC","CHAD","CHILE","CHINA",
  				   "COLOMBIA","COMOROS","DEMOCRATIC REPUBLIC OF CONGO",
  				   "REPUBLIC OF CONGO","COSTA RICA","IVORY COAST",
  				   "CROATIA","CUBA","CYPRUS","CZECH REPUBLIC","DENMARK",
  				   "DJIBOUTI","DOMINICA","DOMINICAN REPUBLIC",
  				   "EAST TIMOR","ECUADOR","EGYPT","EL SALVADOR",
  				   "EQUATORIAL GUINEA","ERITREA","ESTONIA","ETHIOPIA",
  				   "FIJI","FINLAND","FRANCE","GABON","GAMBIA","GEORGIA",
  				   "GERMANY","GHANA","GREECE","GRENADA","GUATEMALA",
  				   "GUINEA","GUINEA BISSAU","GUYANA","HAITI","HONDURAS",
  				   "HUNGARY","ICELAND","INDIA","INDONESIA","IRAN",
  				   "IRAQ","IRELAND","ISRAEL","ITALY","JAMAICA","JAPAN",
  				   "JORDAN","KAZAKHSTAN","KENYA","KIRIBATI",
  				   "NORTH KOREA","SOUTH KOREA","KUWAIT","KYRGYZSTAN",
  				   "LAOS","LATVIA","LEBANON","LESOTHO","LIBERIA",
  				   "LIBYA","LIECHTENSTEIN","LITHUANIA","LUXEMBOURG",
  				   "MACEDONIA","MADAGASCAR","MALAWI","MALAYSIA",
  				   "MALDIVES","MALI","MALTA","MARSHALL ISLANDS",
  				   "MAURITANIA","MAURITIUS","MEXICO","MICRONESIA",
  				   "MOLDOVA","MONACO","MONGOLIA","MOROCCO",
  				   "MOZAMBIQUE","MYANMAR","NAMIBIA","NAURU","NEPAL",
  				   "NETHERLANDS","NEW ZEALAND","NICARAGUA","NIGER",
  				   "NIGERIA","NORWAY","OMAN","PAKISTAN","PALAU","PANAMA",
  				   "PAPUA NEW GUINEA","PARAGUAY","PERU","PHILIPPINES",
  				   "POLAND","PORTUGAL","QATAR","ROMANIA","RUSSIA",
  				   "RWANDA","SAINT KITTS AND NEVIS","SAINT LUCIA",
  				   "SAINT VINCENT AND THE GRENADINES","SAMOA",
  				   "SAN MARINO","SAO TOME AND PRINCIPE",
  				   "SAUDI ARABIA","SENEGAL","SERBIA AND MONTENEGRO",
  				   "SEYCHELLES","SIERRA LEONE","SINGAPORE","SLOVAKIA",
  				   "SLOVENIA","SOLOMON ISLANDS","SOMALIA","SOUTH AFRICA",
  				   "SPAIN","SRI LANKA","SUDAN","SURINAME","SWAZILAND",
  				   "SWEDEN","SWITZERLAND","SYRIA","TAIWAN","TAJIKISTAN",
  				   "TANZANIA","THAILAND","TOGO","TONGA",
  				   "TRINIDAD AND TOBAGO","TUNISIA","TURKEY",
  				   "TURKMENISTAN","TUVALU","UGANDA","UKRAINE",
  				   "UNITED ARAB EMIRATES","UNITED KINGDOM",
  				   "UNITED STATES","URUGUAY","UZBEKISTAN","VANUATU",
  				   "VATICAN CITY","VENEZUELA","VIETNAM","YEMEN",
  				   "ZAMBIA","ZIMBABWE"};
  public void init()
    // This adds the text fields and buttons.
  {
  	for(int i = 0; i < word.length; i++)
  	{
  	  flag[i] = getImage(getDocumentBase(), "Flags\\" + word[i]+".jpg");
  	  map[i] = getImage(getDocumentBase(), "Maps\\" + word[i] + ".jpg");
  	}
    add(newGame);
    newGame.addActionListener(this);
    for (int i = 0; i < 26; ++i)
  	{
  	  button[i] = new Button("");
  	  add(button[i]);
  	  button[i].addActionListener(this);
  	}
  	for (int i = 0; i < 3; ++i)
  	{
  	  levels[i] = new Button("");
  	  add(levels[i]);
  	  levels[i].addActionListener(this);
  	}
  }
  public void paint (Graphics gr)
    // This paints the various graphics in the program by
    // going to different mehtods.
  {
  	levels[0].setLabel("Easy");
  	levels[0].setLocation(50,475);
  	levels[0].setSize(40,20);
  	levels[1].setLabel("Medium");
  	levels[1].setLocation(200,475);
  	levels[1].setSize(50,20);
  	levels[2].setLabel("Hard");
  	levels[2].setLocation(350,475);
  	levels[2].setSize(40,20);
  	gr.setColor(Color.blue);
  	gr.setFont(Font5);
  	gr.drawString("LOADING...",50,250);
  	gr.setColor(Color.black);
  	imgBackground = getImage(getDocumentBase(), "gallows.jpg");
  	gr.drawImage(imgBackground, 0, 0, 800, 500, this);
  	// This loop sets all the letter buttons.
  	int loc = 5;
  	for (int i = 0; i < 26; ++i)
  	{
  	  button[i].setEnabled(true);
  	  button[i].setVisible(true);
  	  button[i].setLocation(loc, 400);
  	  button[i].setLabel("" + letters1[i]);
  	  loc = loc + 20;
  	}
  	// This draws the other strings.
  	gr.setFont(Font1);
  	gr.setColor(Color.black);
  	gr.drawString("Hang-Man!", 75, 65);
  	gr.setFont(Font4);
  	gr.drawString("Countries of the World", 150, 100);
  	newGame.setLocation(10, 430);
  	// This gets a random word and finds its value and length.
  	int num = (int)(Math.random() * word.length);
  	sss = num - 1;
  	ss = word[sss].length();
  	if(easy)
  	{
  	  gr.drawImage(flag[sss],300,200,150,100,this);
  	  gr.drawImage(map[sss],500,200,250,115,this);
  	}
  	if(medium)
  	{
  	  gr.drawImage(flag[sss],300,200,150,100,this);
  	}
  	// This loop sets all the spaces _.
  	mistakes = 0;
  	int length = 0;
  	int locat = 25;
  	// This loop puts a space for every letter in the word.
  	for(int j = 0; j < ss; ++j)
  	{
  	  if ((word[sss].charAt(j)) != ' ')
	  {
	    gr.drawString("_", locat, 175);
	  }
	  locat = locat + 25;
	}
  return;
  }
  public void youLose(Graphics gr)
    // This happens if you lose.
  {
  	gr.setFont(Font3);
  	gr.setColor(Color.red);
  	gr.drawString("You Lose!", 150, 150);
  	gr.setFont(Font2);
  	gr.setColor(Color.black);
  	gr.drawString("The country was:",170,220);
  	gr.setFont(Font3);
  	gr.setColor(Color.blue);
  	gr.drawString("" + word[sss],110,260);
  	for(int i = 0; i < 26; ++i)
  	{
  	  button[i].setEnabled(false);
  	}
  }
  public void actionPerformed(ActionEvent e)
    // This happens if the button is clicked.
  {
  	Object source = e.getSource();

  	// This loop goes through all the letter buttons.
  	for(int i = 0; i < 26; ++i)
	{
	  if (source == button[i])
	  {
	  	button[i].setEnabled(false);
	  	int location = 0;
	  	for(int j = 0; j < ss; ++j)
	  	{
	  	  location = location + 25;
	  	  if ((word[sss].charAt(j)) == letters1[i])
	  	  // This places a letter if it is in the word.
	  	  {
	  	    Graphics gr = this.getGraphics();
	  	    gr.drawString("" + (word[sss].charAt(j)), location, 175);
	  	  }
	  	}
	  	if ((word[sss].indexOf(letters1[i])) == -1)
	  	// This draws the mistakes out.
	  	{
	  	 mistakes = mistakes + 1;
	  	  Graphics gr = this.getGraphics();
          switch(mistakes)
          {
            case 1: gr.drawOval(50, 225, 50, 50);
            	    break;
            case 2: gr.drawLine(75, 275, 75, 325);
            		break;
            case 3: gr.drawLine(75, 300, 50, 275);
            		break;
            case 4: gr.drawLine(75, 300, 100, 275);
            		break;
            case 5: gr.drawLine(75, 325, 50, 375);
            		break;
            case 6: gr.drawLine(75, 325, 100, 375);
            		youLose(gr);
            		break;
          }
	  	}
	  }
	}
	if(source == levels[0])
	{
	  easy = true;
	  medium = false;
	  hard = false;
	}
	if(source == levels[1])
	{
	  easy = false;
	  medium = true;
	  hard = false;
	}
	if(source == levels[2])
	{
	  easy = false;
	  medium = false;
	  hard = true;
	}
	if (source == newGame)
	{
	  for(int i = 0; i < 26; ++i)
	  {
	    button[i].setEnabled(true);
	  }
	  repaint();
	}
	return;
  }
}
