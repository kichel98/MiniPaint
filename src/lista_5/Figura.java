package lista_5;

import java.awt.*;
/**
 * Interfejs kszta�t�w zawieraj�cy metody potrzebne ka�dej rysowanej figurze.
 * Implementuj� go: {@link Kolo}, {@link Prostokat}, {@link Wielokat}
 */
interface Figura extends Shape
{
	/**
	 * Zmienia w�a�ciwo�ci figury podczas jej tworzenia i rysowania;
	 * @param d nowy {@code Point}, kt�ry zmienia kszta�t figury
	 *  
	 */
	public void rysuj(Point d);
	/**
	 * Zmienia po�o�enie figury.
	 * @param dx zmiana po�o�enia wzgl�dem osi x, o kt�r� nale�y przesun�� figur�
	 * @param dy zmiana po�o�enia wzgl�dem osi y, o kt�r� nale�y przesun�� figur�
	 */
	public void przesun(int dx, int dy);
	/**
	 * Wypisuje dane zwi�zane z figur� w {@link Stopka#dane}
	 */
	public void wypiszDane();
	/**
	 * Przygotowuje informacje o figurze do zapisania.
	 * @return linia, kt�ra zostanie zapisana w pliku
	 */
	public String zapiszDane();
	/**
	 * Ustawia ca�� figur� korzystaj�c z danych z pliku.
	 * @param argumenty tablica danych z odczytu, zawiera informacje determinuj�ce figur�
	 */
	public void ustawZOdczytu(String[] argumenty);
	/**
	 * Aktualizuje pole kolor obecne w ka�dej figurze.
	 * @param g2d grafika u�ywana w {@link Wnetrze#paintComponent}
	 * @param kolor po��dany kolor
	 * @see ustawKolor
	 */
	public void zmienKolor(Graphics2D g2d, Color kolor);
	/**
	 * Zmienia kolor grafiki na ten, kt�ry jest przechowywane w polu kolor.
	 * @param g2d grafika u�ywana w {@link Wnetrze#paintComponent}
	 * @see zmienKolor
	 */
	public void ustawKolor(Graphics2D g2d);
	/**
	 * Powi�ksza rozmiary figury.
	 * @param zwiekszenie warto��, o kt�r� nale�y zwi�kszy� figur� przy skalowaniu
	 */
	public void zwieksz(int zwiekszenie);
}
