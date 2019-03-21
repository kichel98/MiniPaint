package lista_5;

import java.awt.*;
import java.awt.geom.*;

/**
 * Klasa reprezentuj¹ca prostok¹t.
 * @see Figura
 */
class Prostokat extends Rectangle2D.Float implements Figura
{
	/**
	 * wyjsciowy {@code Point}, lewy górny róg prostok¹ta, niezmienny przy rysowaniu
	 */
	private Point wyjsciowy;
	/**
	 * {@code Color}, domyœlnie ustawiony na {@code Color.ORANGE}
	 */
	private Color kolor = Color.ORANGE;
	/**
	 * Minimalna szerokoœæ, która jest nieprzekraczalna przy zmniejszaniu.
	 */
	private final int min_width = 20;
	/**
	 * Minimalna wysokoœæ, która jest nieprzekraczalna przy zmniejszaniu.
	 */
	private final int min_height = 20;
	/**
	 * Tworzy Prostokat, ustalaj¹c wspó³rzêdne punktu wyjsciowego.
	 * @param d punkt wyjœciowy
	 * @see wyjsciowy
	 */
	public Prostokat(Point d)
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
		setFrameFromDiagonal(wyjsciowy, zmienny);
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
	public void wypiszDane()
	{
		Stopka.dane.setText("Dane: typ = prostok¹t, x = " + x + ", y = " + y + ", dl = " + width + ", szer = " + height);
	}
	/**
	 * {@inheritDoc}
	 */
	public String zapiszDane()
	{
		return "prostokat " + kolor.getRGB() + " " + (int)x + " " + (int)y + " " + (int)width + " " + (int)height;
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
}
