package lista_5;

import java.awt.*;
/**
 * Interfejs kszta³tów zawieraj¹cy metody potrzebne ka¿dej rysowanej figurze.
 * Implementuj¹ go: {@link Kolo}, {@link Prostokat}, {@link Wielokat}
 */
interface Figura extends Shape
{
	/**
	 * Zmienia w³aœciwoœci figury podczas jej tworzenia i rysowania;
	 * @param d nowy {@code Point}, który zmienia kszta³t figury
	 *  
	 */
	public void rysuj(Point d);
	/**
	 * Zmienia po³o¿enie figury.
	 * @param dx zmiana po³o¿enia wzglêdem osi x, o któr¹ nale¿y przesun¹æ figurê
	 * @param dy zmiana po³o¿enia wzglêdem osi y, o któr¹ nale¿y przesun¹æ figurê
	 */
	public void przesun(int dx, int dy);
	/**
	 * Wypisuje dane zwi¹zane z figur¹ w {@link Stopka#dane}
	 */
	public void wypiszDane();
	/**
	 * Przygotowuje informacje o figurze do zapisania.
	 * @return linia, która zostanie zapisana w pliku
	 */
	public String zapiszDane();
	/**
	 * Ustawia ca³¹ figurê korzystaj¹c z danych z pliku.
	 * @param argumenty tablica danych z odczytu, zawiera informacje determinuj¹ce figurê
	 */
	public void ustawZOdczytu(String[] argumenty);
	/**
	 * Aktualizuje pole kolor obecne w ka¿dej figurze.
	 * @param g2d grafika u¿ywana w {@link Wnetrze#paintComponent}
	 * @param kolor po¿¹dany kolor
	 * @see ustawKolor
	 */
	public void zmienKolor(Graphics2D g2d, Color kolor);
	/**
	 * Zmienia kolor grafiki na ten, który jest przechowywane w polu kolor.
	 * @param g2d grafika u¿ywana w {@link Wnetrze#paintComponent}
	 * @see zmienKolor
	 */
	public void ustawKolor(Graphics2D g2d);
	/**
	 * Powiêksza rozmiary figury.
	 * @param zwiekszenie wartoœæ, o któr¹ nale¿y zwiêkszyæ figurê przy skalowaniu
	 */
	public void zwieksz(int zwiekszenie);
}
