package lista_5;

import java.awt.*;
import java.awt.geom.*;

/**
 * Klasa reprezentuj�ca ko�o.
 * @see Figura
 */
class Kolo extends Ellipse2D.Float implements Figura 
{
	/**
	 * wyjsciowy {@code Point}, lewy g�rny r�g okalaj�cego prostok�ta, niezmienny przy rysowaniu
	 */
	private Point wyjsciowy; 
	/**
	 * {@code Color}, domy�lnie ustawiony na {@code Color.GRAY}
	 */
	private Color kolor = Color.GRAY; // kolor
	/**
	 * Minimalna szeroko��, kt�ra jest nieprzekraczalna przy zmniejszaniu.
	 */
	private final int min_width = 20;
	/**
	 * Minimalna wysoko��, kt�ra jest nieprzekraczalna przy zmniejszaniu.
	 */
	private final int min_height = 20;
	/**
	 * Tworzy Kolo, ustalaj�c wsp�rz�dne punktu wyjsciowego.
	 * @param d punkt wyj�ciowy
	 * @see wyjsciowy
	 */
	public Kolo(Point d)
	{
		wyjsciowy = d;
		x = d.x;
		y = d.y;
	}
	/**
	 * {@inheritDoc}
	 * @param zmienny prawy dolny r�g prostok�ta
	 */
	public void rysuj(Point zmienny)
	{
		setFrameFromDiagonal(wyjsciowy, min(wyjsciowy, zmienny)); // ustawia prostok�tny(kwadratowy) obszar, na kt�rym tworzy si� ko�o
	}
	/**
	 * {@inheritDoc}
	 */
	public void przesun(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	/**
	 * {@inheritDoc}, pami�taj�c o warto�ciach minimalnych.
	 * @see min_width
	 * @see min_height
	 */
	public void zwieksz(int zwiekszenie)
	{
		if(zwiekszenie > 0)
		{
			width += zwiekszenie;
			height += zwiekszenie;
		}
		else if(zwiekszenie < 0 && width > min_width && height > min_height)
		{
			width += zwiekszenie;
			height += zwiekszenie;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public void ustawZOdczytu(String[] argumenty)
	{
		kolor = new Color(Integer.parseInt(argumenty[1]));
		width = Integer.parseInt(argumenty[4]);
		height = Integer.parseInt(argumenty[5]);
	}
	/**
	 * {@inheritDoc}
	 */
	public void wypiszDane()
	{
		Stopka.dane.setText("Dane: typ = ko�o, x = " + x + ", y = " + y + ", dl = " + width + ", szer = " + height);
	}
	/**
	 * {@inheritDoc}
	 */
	public String zapiszDane()
	{
		return "kolo " + kolor.getRGB() + " " + (int)x + " " + (int)y + " " + (int)width + " " + (int)height;
	}
	/**
	 * {@inheritDoc}
	 */
	public void zmienKolor(Graphics2D g2d, Color kolor)
	{
		this.kolor = kolor;
	}
	/**
	 * {@inheritDoc}
	 */
	public void ustawKolor(Graphics2D g2d)
	{
		g2d.setPaint(kolor);
	}
	/**
	 * Podaje nam punkt, kt�ry powinien by� prawym dolnym rogiem tak, aby figura by�a ko�em, a nie elips�.
	 * @param wyjsciowy {@code Point}, od kt�rego zaczynamy rysowanie, lewy g�rny r�g
	 * @param zmienny  {@code Point}, na kt�rym ko�czymy rysowanie, prawy dolny r�g
	 * @return {@code Point}, kt�ry powinien by� jako zmienny
	 */
	private Point min(Point wyjsciowy, Point zmienny) // zmiana z elips na ko�a 
	{
		if(wyjsciowy.x < zmienny.x && wyjsciowy.y > zmienny.y) // kursor w prawo i g�r� (I �wiartka)
		{
			zmienny.x = wyjsciowy.x - Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
			zmienny.y = wyjsciowy.y + Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
		}
		else if(wyjsciowy.x > zmienny.x && wyjsciowy.y < zmienny.y) // kursor w lewo i d� (III �wiartka)
		{
			zmienny.x = wyjsciowy.x + Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
			zmienny.y = wyjsciowy.y - Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
		}
		else // III i IV �wiartka
		{
			zmienny.x = wyjsciowy.x + Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
			zmienny.y = wyjsciowy.y + Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);			
		}
		return zmienny;
	}
}
