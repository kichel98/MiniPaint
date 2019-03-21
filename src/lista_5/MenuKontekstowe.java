package lista_5;

import javax.swing.*;
import java.awt.*;
/**
 * Implementacja menu kontekstowego s�u��cego do zmiany koloru figury.
 */
class MenuKontekstowe extends JPopupMenu
{	
	/**
	 * tablica przechowuj�ca mo�liwe do wybrania kolory
	 */
	public Color[] kolory = { Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA, new Color(210, 246, 255) }; 
	/**
	 * tablica przechowuj�ca przyciski s�u��ce do zmiany koloru
	 */
	public JMenuItem[] wypelnienia;
	/**
	 * Tworzy menu.
	 */
	public MenuKontekstowe()
	{
		wypelnienia = new JMenuItem[5];
		ustawWypelnienia();
	}
	/**
	 * tworzy JMenuItem, koloruje, ustawia wielko�� i dodaje je (indeksy z wypelnienia[] odpowiadaj� kolory[] od 0 do 4)
	 */
	private void ustawWypelnienia() 
	{
		for(int i = 0; i < 5; i++)
		{
			wypelnienia[i] = new JMenuItem();
			wypelnienia[i].setBackground(kolory[i]);
			wypelnienia[i].setPreferredSize(new Dimension(70, 20));
			add(wypelnienia[i]);
		}
	}
}
