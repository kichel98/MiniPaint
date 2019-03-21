package lista_5;

import java.awt.*;
import java.awt.geom.*;

/**
 * Klasa reprezentuj¹ca ko³o.
 * @see Figura
 */
class Kolo extends Ellipse2D.Float implements Figura 
{
	/**
	 * wyjsciowy {@code Point}, lewy górny róg okalaj¹cego prostok¹ta, niezmienny przy rysowaniu
	 */
	private Point wyjsciowy; 
	/**
	 * {@code Color}, domyœlnie ustawiony na {@code Color.GRAY}
	 */
	private Color kolor = Color.GRAY; // kolor
	/**
	 * Minimalna szerokoœæ, która jest nieprzekraczalna przy zmniejszaniu.
	 */
	private final int min_width = 20;
	/**
	 * Minimalna wysokoœæ, która jest nieprzekraczalna przy zmniejszaniu.
	 */
	private final int min_height = 20;
	/**
	 * Tworzy Kolo, ustalaj¹c wspó³rzêdne punktu wyjsciowego.
	 * @param d punkt wyjœciowy
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
	 * @param zmienny prawy dolny róg prostok¹ta
	 */
	public void rysuj(Point zmienny)
	{
		setFrameFromDiagonal(wyjsciowy, min(wyjsciowy, zmienny)); // ustawia prostok¹tny(kwadratowy) obszar, na którym tworzy siê ko³o
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
	 * {@inheritDoc}, pamiêtaj¹c o wartoœciach minimalnych.
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
		Stopka.dane.setText("Dane: typ = ko³o, x = " + x + ", y = " + y + ", dl = " + width + ", szer = " + height);
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
	 * Podaje nam punkt, który powinien byæ prawym dolnym rogiem tak, aby figura by³a ko³em, a nie elips¹.
	 * @param wyjsciowy {@code Point}, od którego zaczynamy rysowanie, lewy górny róg
	 * @param zmienny  {@code Point}, na którym koñczymy rysowanie, prawy dolny róg
	 * @return {@code Point}, który powinien byæ jako zmienny
	 */
	private Point min(Point wyjsciowy, Point zmienny) // zmiana z elips na ko³a 
	{
		if(wyjsciowy.x < zmienny.x && wyjsciowy.y > zmienny.y) // kursor w prawo i górê (I æwiartka)
		{
			zmienny.x = wyjsciowy.x - Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
			zmienny.y = wyjsciowy.y + Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
		}
		else if(wyjsciowy.x > zmienny.x && wyjsciowy.y < zmienny.y) // kursor w lewo i dó³ (III æwiartka)
		{
			zmienny.x = wyjsciowy.x + Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
			zmienny.y = wyjsciowy.y - Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
		}
		else // III i IV æwiartka
		{
			zmienny.x = wyjsciowy.x + Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);
			zmienny.y = wyjsciowy.y + Math.min(zmienny.x - wyjsciowy.x, zmienny.y - wyjsciowy.y);			
		}
		return zmienny;
	}
}
