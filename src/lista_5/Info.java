
package lista_5;

import javax.swing.*;

/**
 * Klasa zawieraj�ca informacyjne okno dialogowe.
 */
class Info extends JFrame
{
	/**
	 * okno dialogowe z informacjami
	 */
	private JDialog dialog;
	/**
	 * {@code JTextArea}, do kt�rej wpisujemy tre�� informacji
	 */
	private JTextArea tresc;
	/**
	 * Tworzy okno dialogowe i wype�nia je tre�ci�.
	 */
	public Info()
	{
		dialog = new JDialog(this, "Informacje", true);
		tresc = new JTextArea("Autor: Piotr Andrzejewski\nWersja: 2.3\nProgram stworzony w celach edukacyjnych.\n\nObs�uga: program dzia�a w dw�ch trybach - Tw�rz i Modyfikuj. \n"
				+ "Prze��czanie mi�dzy nimi jest mo�liwe w prawej cz�ci ekranu. \nRysowanie figur odbywa si� jedynie po wybraniu odpowiedniej figury w trybie Tw�rz.\n"
				+ "Tworzenie wielok�ta odbywa si� za pomoc� dodawania punkt�w lewym przyciskiem myszy,\nw celu zako�czenia rysowania nale�y doda� punkt prawym przyciskiem.\n"
				+ "Zmiana rozmiaru oraz przesuwanie figur dost�pne s� jedynie w trybie Modyfikuj.\n"
				+ "Mo�liwy jest r�wnie� zapis rozmieszczenia i parametr�w figur, jak r�wnie� ich odczyt z pliku.\nS�u�� do tego przyciski Zapisz oraz Odczyt umieszczone w "
				+ "prawym dolnym rogu programu.\nZmiana koloru jest mo�liwa w trybie Modyfikuj po klikni�ciu prawym przyciskiem na figur�.");
		dialog.setSize(520, 300);
		dialog.add(tresc);
		dialog.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
