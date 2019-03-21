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
 * Najwa¿niejsza klasa w programie - zajmuje siê rysowaniem i wszystkimi akcjami zwi¹zanymi z dzia³alnoœci¹ u¿ytkownika w obrêbie rysowania.
 * Jest to panel znajduj¹cy siê w centrum programu.
 * Ma w sobie jedn¹ klasê wewnêtrzn¹ {@link MojMouseAdapter}.
 *
 * @see ActionListener
 */
@SuppressWarnings("serial")
class Wnetrze extends JPanel implements ActionListener
{
	/**
	 * ArrayList, która ma w sobie wszystkie powsta³e figury.
	 * @see Figura
	 */
	private ArrayList<Figura> figury;
	/**
	 * Figura, na rzecz której wywo³ano menu z kolorami.
	 */
	private Figura figuraMenu;
	/**
	 * Obecnie rysowany wielok¹t.
	 */
	private Wielokat wielokatRys;
	/**
	 * Menu pozwalaj¹ce na zmianê kolorów.
	 */
	private MenuKontekstowe menu;
	/**
	 * G³ówny obiekt klasy {@code Graphics2D}, pozwalaj¹cy nam na rysowanie.
	 */
	private Graphics2D g2d;
	/**
	 * {@code true} - jeœli jakiœ wielok¹t jest obecnie rysowany, {@code false} - jeœli ¿aden wielok¹t nie jest obecnie rysowany
	 */
	public boolean czyWielokat = false; 
	/**
	 * Powo³uje do ¿ycia obiekty i dodaje {@code MojMouseAdapter}.
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
	 * Usuwa wielok¹t z {@link figury} i z ekranu.
	 */
	public void usunWielokat()
	{
		figury.remove(wielokatRys);
		repaint();
		czyWielokat = false;
	}
	/**
	 * Metoda odpowiadaj¹ca za rysowanie, rysuje po kolei wszystkie figury znajduj¹ce siê w {@link figury}.
	 * Jeœli wielok¹t jest w trakcie rysowania, to rysuje jedynie kszta³t, po zakoñczeniu wype³nia go.
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
	 * @throws IOException wyj¹tek, mog¹cy wyst¹piæ przy braku mo¿liwoœci zapisu do pliku
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
	 * Najpierw czyœci panel, a nastêpnie odczytuje stan figur z pliku tekstowego. 
	 * @throws IOException wyj¹tek, mog¹cy wyst¹piæ przy braku mo¿liwoœci odczytu z pliku
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
     * Obs³uguje wszystkie zdarzenia zwi¹zane z myszk¹.
     */
    class MojMouseAdapter extends MouseAdapter 
    {
    	/**
    	 * Figura, która jest aktualnie rysowana. Pole inicjalizowane przy wciœniêciu myszy, wykorzystywane przy przeci¹gniêciu(tworzeniu figury).
    	 */
    	private Figura figuraRys;
    	/**
    	 * Figura, która jest aktualnie przesuwana. Pole inicjalizowane przy wciœniêciu myszy, wykorzystywane przy przeci¹gniêciu (przesuwaniu figury).
    	 */
    	private Figura figuraPrzes;
    	/**
    	 * Punkt, w którym znajduje siê kursor myszy podczas przesuwania figury.
    	 */
    	private Point przesuwanie;
    	/**
    	 * {@code true} - jeœli klikniêta zosta³a choæ jedna figura, {@code false} - jeœli klikniêta zosta³a pusta przestrzeñ
    	 */
    	private boolean czyWewnatrz = false;
    	/**
    	 * W trybie tworzenia tworzy ko³a i prostok¹ty, w trybie edycji przygotowuje przesuwanie.
    	 * @param e zdarzenie zwi¹zane z wciœniêciem myszy
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
    	 * W trybie tworzenia pozwala rozszerzaæ figurê, w trybie edycji przesuwa j¹ i uruchamia wypisywanie danych w {@link Stopka#dane}.
    	 * @param e zdarzenie zwi¹zane z przesuniêciem myszy
    	 */
    	@Override
    	public void mouseDragged(MouseEvent e)
    	{
    		/***** TWORZENIE - ROZSZERZANIE ****/
    		if(PrawyBok.tworz.isSelected() == true) // tworzenie
    		{
    			if(PrawyBok.kolo.isSelected() == true) // mo¿na zlikwidowaæ te ify? !!! zobaczy siê po zrobieniu wielok¹ta
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
    	 * W trybie tworzenia tworzy wielok¹t.
    	 * W trybie edycji uruchamia metodê s³u¿¹c¹ do tworzenia menu.
    	 * Najpierw wywo³uje siê {@link mouseReleased}, nastêpnie {@code mouseClicked}.
    	 * @param e zdarzenie zwi¹zane z klikniêciem przycisku myszy
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
    	 * W trybie tworzenia sprawia, ¿e przyciski siê odklikuj¹ lub blokuj¹, by nie dosz³o do konfliktów.
    	 * W trybie edycji tworzy menu.
    	 * @param e zdarzenie zwi¹zane ze zwolnieniem przycisku myszy
    	 */
    	@Override
        public void mouseReleased(MouseEvent e) //najpierw wywo³uje siê released, pozniej clicked
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
    	 * Dla ka¿dej figury, która zawiera punkt, w którym obecnie znajduje siê przycisk myszy, wywo³uje metodê {@link Figura#zwieksz(int)}
    	 * @param e zdarzenie zwi¹zane z poruszeniem scrolla 
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
         * Sprawdza, czy nale¿y stworzyæ menu, jeœli tak - robi to.
         * @param e zdarzenie zwi¹zane z klikniêciem na terenie panelu
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
