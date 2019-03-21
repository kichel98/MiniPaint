package lista_5;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 * Najwa�niejsza klasa w programie - zajmuje si� rysowaniem i wszystkimi akcjami zwi�zanymi z dzia�alno�ci� u�ytkownika w obr�bie rysowania.
 * Jest to panel znajduj�cy si� w centrum programu.
 * Ma w sobie jedn� klas� wewn�trzn� {@link MojMouseAdapter}.
 *
 * @see ActionListener
 */
@SuppressWarnings("serial")
class Wnetrze extends JPanel implements ActionListener
{
	/**
	 * ArrayList, kt�ra ma w sobie wszystkie powsta�e figury.
	 * @see Figura
	 */
	private ArrayList<Figura> figury;
	/**
	 * Figura, na rzecz kt�rej wywo�ano menu z kolorami.
	 */
	private Figura figuraMenu;
	/**
	 * Obecnie rysowany wielok�t.
	 */
	private Wielokat wielokatRys;
	/**
	 * Menu pozwalaj�ce na zmian� kolor�w.
	 */
	private MenuKontekstowe menu;
	/**
	 * G��wny obiekt klasy {@code Graphics2D}, pozwalaj�cy nam na rysowanie.
	 */
	private Graphics2D g2d;
	/**
	 * {@code true} - je�li jaki� wielok�t jest obecnie rysowany, {@code false} - je�li �aden wielok�t nie jest obecnie rysowany
	 */
	public boolean czyWielokat = false; 
	/**
	 * Powo�uje do �ycia obiekty i dodaje {@code MojMouseAdapter}.
	 */
	public Wnetrze()
	{
		figury = new ArrayList<Figura>();
		menu = new MenuKontekstowe();
		MojMouseAdapter mojMouseAdapter = new MojMouseAdapter();
		addMouseListener(mojMouseAdapter);
		addMouseMotionListener(mojMouseAdapter);	
		addMouseWheelListener(mojMouseAdapter);
   		for(int i = 0; i < 5; i++)
		{
			menu.wypelnienia[i].addActionListener(Wnetrze.this);
		}
	}
	/**
	 * Usuwa wielok�t z {@link figury} i z ekranu.
	 */
	public void usunWielokat()
	{
		figury.remove(wielokatRys);
		repaint();
		czyWielokat = false;
	}
	/**
	 * Metoda odpowiadaj�ca za rysowanie, rysuje po kolei wszystkie figury znajduj�ce si� w {@link figury}.
	 * Je�li wielok�t jest w trakcie rysowania, to rysuje jedynie kszta�t, po zako�czeniu wype�nia go.
	 */
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g2d = (Graphics2D) g.create(); 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        for(Figura figura: figury)
        {
        	figura.ustawKolor(g2d);
        	if(figura == wielokatRys)
        	{
        		g2d.draw(figura);	
        		if(czyWielokat == false)
        		{
            		g2d.fill(figura);
        		}
        	}
        	else
        	{
        		g2d.fill(figura);
        	}
        }
        g.dispose();
    }
	/**
	 * Zmienia kolor figury.
	 */
	@Override
    public void actionPerformed(ActionEvent e) 
    {
		for(int i = 0; i < 5; i++) //  zmiana koloru
		{
			if(e.getSource() == menu.wypelnienia[i])
			{
		    	figuraMenu.zmienKolor(g2d, menu.kolory[i]);
				repaint();
		    	return ;
			}
		}
    }
	/**
	 * Zapisuje obecny stan figur do pliku tekstowego.
	 * @throws IOException wyj�tek, mog�cy wyst�pi� przy braku mo�liwo�ci zapisu do pliku
	 */
    public void zapisz() throws IOException 
    {
		  FileWriter fileWriter = new FileWriter("nowy.txt");
		  BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		  try 
		  {
			  for(Figura figura: figury)
			  {
			      bufferedWriter.write(figura.zapiszDane());
			      bufferedWriter.newLine();
			  }
		  } 
		  finally 
		  {
		    bufferedWriter.close();
		  }
    }
	/**
	 * Najpierw czy�ci panel, a nast�pnie odczytuje stan figur z pliku tekstowego. 
	 * @throws IOException wyj�tek, mog�cy wyst�pi� przy braku mo�liwo�ci odczytu z pliku
	 */
    public void odczyt() throws IOException
    {
    	figury.removeAll(figury);
    	repaint();
    	czyWielokat = false;
    	  FileReader fileReader = new FileReader("nowy.txt");
    	  BufferedReader bufferedReader = new BufferedReader(fileReader);
    	  
    	  String textLine = bufferedReader.readLine();
    	  if(textLine == null)
    		  throw new IOException();
    	  do 
    	  {
    		String[] argumenty = textLine.split("\\s+");
    		if(argumenty[0].equals(""))
    			throw new IOException();
    		else if(argumenty[0].equals("kolo"))
    	    {
    	    	Kolo kolo;
    	    	kolo = new Kolo(new Point(Integer.parseInt(argumenty[2]), Integer.parseInt(argumenty[3])));
    	    	figury.add(kolo);
    	    	kolo.ustawZOdczytu(argumenty);
    	    }
    	    else if(argumenty[0].equals("prostokat"))
    	    {
    	    	Prostokat prostokat;
    	    	prostokat = new Prostokat(new Point(Integer.parseInt(argumenty[2]), Integer.parseInt(argumenty[3])));
    	    	figury.add(prostokat);
    	    	prostokat.ustawZOdczytu(argumenty);
    	    }
    	    else if(argumenty[0].equals("wielokat"))
    	    {
    	    	Wielokat wielokat;
    	    	wielokat = new Wielokat(new Point(Integer.parseInt(argumenty[2]), Integer.parseInt(argumenty[2 + ((argumenty.length-2)/2)])));
    	    	figury.add(wielokat);
    	    	wielokat.ustawZOdczytu(argumenty);
    	    }
    	    textLine = bufferedReader.readLine();
    	  } while(textLine != null);
    	  repaint();
    	  bufferedReader.close();

    }
    /**
     * Obs�uguje wszystkie zdarzenia zwi�zane z myszk�.
     */
    class MojMouseAdapter extends MouseAdapter 
    {
    	/**
    	 * Figura, kt�ra jest aktualnie rysowana. Pole inicjalizowane przy wci�ni�ciu myszy, wykorzystywane przy przeci�gni�ciu(tworzeniu figury).
    	 */
    	private Figura figuraRys;
    	/**
    	 * Figura, kt�ra jest aktualnie przesuwana. Pole inicjalizowane przy wci�ni�ciu myszy, wykorzystywane przy przeci�gni�ciu (przesuwaniu figury).
    	 */
    	private Figura figuraPrzes;
    	/**
    	 * Punkt, w kt�rym znajduje si� kursor myszy podczas przesuwania figury.
    	 */
    	private Point przesuwanie;
    	/**
    	 * {@code true} - je�li klikni�ta zosta�a cho� jedna figura, {@code false} - je�li klikni�ta zosta�a pusta przestrze�
    	 */
    	private boolean czyWewnatrz = false;
    	/**
    	 * W trybie tworzenia tworzy ko�a i prostok�ty, w trybie edycji przygotowuje przesuwanie.
    	 * @param e zdarzenie zwi�zane z wci�ni�ciem myszy
    	 */
    	@Override
    	public void mousePressed(MouseEvent e) 
    	{
    		/***** TWORZENIE ****/
    		if(PrawyBok.tworz.isSelected() == true)
    		{
    			if(PrawyBok.kolo.isSelected() == true)
    			{
    				figuraRys = new Kolo(e.getPoint());
    				figury.add(figuraRys);
    			}
    			if(PrawyBok.prostokat.isSelected() == true)
    			{
    				figuraRys = new Prostokat(e.getPoint());
    				figury.add(figuraRys);
    			}
    		}
    		/*******/
    		/****** PRZESUWANIE *******/
    		else
    		{       		
    			przesuwanie = e.getPoint();
    			for(int i = figury.size() - 1; i >= 0; i--)
    			{
    				if(figury.get(i).contains(przesuwanie) == true)
    				{
    					figuraPrzes = figury.get(i);
    					czyWewnatrz = true; 
    					return ;
    				}
    			}
    			czyWewnatrz = false; 
    		} 
    		/*********/
    	}
    	/**
    	 * W trybie tworzenia pozwala rozszerza� figur�, w trybie edycji przesuwa j� i uruchamia wypisywanie danych w {@link Stopka#dane}.
    	 * @param e zdarzenie zwi�zane z przesuni�ciem myszy
    	 */
    	@Override
    	public void mouseDragged(MouseEvent e)
    	{
    		/***** TWORZENIE - ROZSZERZANIE ****/
    		if(PrawyBok.tworz.isSelected() == true) // tworzenie
    		{
    			if(PrawyBok.kolo.isSelected() == true) // mo�na zlikwidowa� te ify? !!! zobaczy si� po zrobieniu wielok�ta
    			{
    				Point d = e.getPoint();
    	    		figuraRys.rysuj(d);
    				repaint();
    			}
    			if(PrawyBok.prostokat.isSelected() == true)
    			{
    				Point d = e.getPoint();
    	    		figuraRys.rysuj(d);
    				repaint();
    			}
    		}
    		/*******/
    		/****** PRZESUWANIE - CIAG DALSZY *******/
    		else 
    		{
    			if(czyWewnatrz == true) 
    			{
    				int dx = 0, dy = 0;
        			dx = e.getX() - przesuwanie.x;
        			dy = e.getY() - przesuwanie.y;
    				figuraPrzes.przesun(dx, dy);
    				figuraPrzes.wypiszDane();
    				przesuwanie.x += dx;
    				przesuwanie.y += dy;
    				repaint();
    			}
    			
    		}
    		/*********/
    	}
    	/**
    	 * W trybie tworzenia tworzy wielok�t.
    	 * W trybie edycji uruchamia metod� s�u��c� do tworzenia menu.
    	 * Najpierw wywo�uje si� {@link mouseReleased}, nast�pnie {@code mouseClicked}.
    	 * @param e zdarzenie zwi�zane z klikni�ciem przycisku myszy
    	 */
    	@Override
        public void mouseClicked(MouseEvent e)  
        {
    		if(PrawyBok.modyfikuj.isSelected() == true)	
    			tworzMenu(e);
    		else if(PrawyBok.wielokat.isSelected() == true)
    		{
    			/********** TWORZENIE WIELOKATA ************/ // 
    			if(czyWielokat == false)
    			{
    				wielokatRys = new Wielokat(e.getPoint());
    				figury.add(wielokatRys);
    				czyWielokat = true;
    			}
    			else
    			{
    				wielokatRys.rysuj(e.getPoint());
    				if(SwingUtilities.isRightMouseButton(e))
    				{
    					czyWielokat = false;
        				PrawyBok.wielokat.setSelected(false);
        				PrawyBok.modyfikuj.setEnabled(true);
    				}
    				repaint();
    			}
    			/*******************************************/ // 
    		}
        }
    	/**
    	 * W trybie tworzenia sprawia, �e przyciski si� odklikuj� lub blokuj�, by nie dosz�o do konflikt�w.
    	 * W trybie edycji tworzy menu.
    	 * @param e zdarzenie zwi�zane ze zwolnieniem przycisku myszy
    	 */
    	@Override
        public void mouseReleased(MouseEvent e) //najpierw wywo�uje si� released, pozniej clicked
        {
        	Stopka.dane.setText("Dane: ");
        	czyWewnatrz = false; 
        	if(PrawyBok.modyfikuj.isSelected() == true)	
            	tworzMenu(e);
        	else
        	{
        		if(PrawyBok.kolo.isSelected())
        		{
    				PrawyBok.kolo.setSelected(false);
    				PrawyBok.modyfikuj.setEnabled(true);
        		}
        		else if(PrawyBok.prostokat.isSelected())
        		{
    				PrawyBok.prostokat.setSelected(false);
    				PrawyBok.modyfikuj.setEnabled(true);
        		}
        		else if(PrawyBok.wielokat.isSelected())
            	{
    				PrawyBok.modyfikuj.setEnabled(false);
            	}
        	}
        }
    	/**
    	 * Dla ka�dej figury, kt�ra zawiera punkt, w kt�rym obecnie znajduje si� przycisk myszy, wywo�uje metod� {@link Figura#zwieksz(int)}
    	 * @param e zdarzenie zwi�zane z poruszeniem scrolla 
    	 */
    	@Override
        public void mouseWheelMoved(MouseWheelEvent e) 
        {
        	if(PrawyBok.modyfikuj.isSelected() == true)	
        	{
        		for(Figura figura: figury)
        		{
        			if(figura.contains(e.getPoint()) && e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
        			{
        				figura.zwieksz(e.getWheelRotation() * 5);
        			}
        		}
        		repaint();
        	}
        }
        /**
         * Sprawdza, czy nale�y stworzy� menu, je�li tak - robi to.
         * @param e zdarzenie zwi�zane z klikni�ciem na terenie panelu
         */
        private void tworzMenu(MouseEvent e)
        {
            if (e.isPopupTrigger()) 
            {
        		for(int i = figury.size() - 1; i >= 0; i--)
        		{
        			if(figury.get(i).contains(e.getPoint()))
        			{
        				figuraMenu = figury.get(i);
                        menu.show(Wnetrze.this, e.getX(), e.getY()); // pierwszy argument to panel, klasa zewnetrzna, przy samym this to bylby MojMouseAdapter
        				return ;
        			}
        		}
            }
        }
    }
}
