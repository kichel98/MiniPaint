
package lista_5;

import javax.swing.*;

/**
 * Klasa zawieraj¹ca informacyjne okno dialogowe.
 */
class Info extends JFrame
{
	/**
	 * okno dialogowe z informacjami
	 */
	private JDialog dialog;
	/**
	 * {@code JTextArea}, do której wpisujemy treœæ informacji
	 */
	private JTextArea tresc;
	/**
	 * Tworzy okno dialogowe i wype³nia je treœci¹.
	 */
	public Info()
	{
		dialog = new JDialog(this, "Informacje", true);
		tresc = new JTextArea("Autor: Piotr Andrzejewski\nWersja: 2.3\nProgram stworzony w celach edukacyjnych.\n\nObs³uga: program dzia³a w dwóch trybach - Twórz i Modyfikuj. \n"
				+ "Prze³¹czanie miêdzy nimi jest mo¿liwe w prawej czêœci ekranu. \nRysowanie figur odbywa siê jedynie po wybraniu odpowiedniej figury w trybie Twórz.\n"
				+ "Tworzenie wielok¹ta odbywa siê za pomoc¹ dodawania punktów lewym przyciskiem myszy,\nw celu zakoñczenia rysowania nale¿y dodaæ punkt prawym przyciskiem.\n"
				+ "Zmiana rozmiaru oraz przesuwanie figur dostêpne s¹ jedynie w trybie Modyfikuj.\n"
				+ "Mo¿liwy jest równie¿ zapis rozmieszczenia i parametrów figur, jak równie¿ ich odczyt z pliku.\nS³u¿¹ do tego przyciski Zapisz oraz Odczyt umieszczone w "
				+ "prawym dolnym rogu programu.\nZmiana koloru jest mo¿liwa w trybie Modyfikuj po klikniêciu prawym przyciskiem na figurê.");
		dialog.setSize(520, 300);
		dialog.add(tresc);
		dialog.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
