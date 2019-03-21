package lista_5;

import java.awt.*;
import java.awt.geom.*;

/**
 * Klasa reprezentuj¹ca wielok¹t.
 * @see Figura
 */
class Wielokat extends Polygon implements Figura
{
	/**
	 * {@code Color}, domyœlnie ustawiony na {@code Color.ORANGE}
	 */
	private Color kolor = Color.CYAN;
	/**
	 * Minimalna szerokoœæ, która jest nieprzekraczalna przy zmniejszaniu.
	 */
	private final int min_width = 100;
	/**
	 * Minimalna wysokoœæ, która jest nieprzekraczalna przy zmniejszaniu.
	 */
	private final int min_height = 100;
	/**
	 * Tworzy Wielokat, ustalaj¹c wspó³rzêdne pierwszego punktu oraz aktualizuj¹c prostok¹t okalaj¹cy.
	 * @param d pierwszy punkt
	 */
	public Wielokat(Point d)
	{
		rysuj(d);
		naprawBounds(xpoints, ypoints, npoints);
	}
	/**
	 * {@inheritDoc}
	 */
	public void rysuj(Point d)
	{
		addPoint(d.x, d.y);
	}
	/**
	 * {@inheritDoc}
	 */
	public void przesun(int dx, int dy)
	{
		bounds.x += dx;
		bounds.y += dy;
		for(int i = 0; i < xpoints.length; i++)
		{
			xpoints[i] += dx;
			ypoints[i] += dy;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	public void wypiszDane()
	{
		Stopka.dane.setText("Dane: typ = wielokat, x = " + bounds.x + ", y = " + bounds.y + ", dl = " + bounds.width + ", szer = " + bounds.height);
	}
	/**
	 * {@inheritDoc}
	 */
	public String zapiszDane()
	{
		String napis;
		napis = "wielokat " + kolor.getRGB() + " ";
		for(int i = 0; i < npoints; i++)
		{
			napis += xpoints[i] + " ";
		}
		for(int i = 0; i < npoints; i++)
		{
			napis += ypoints[i] + " ";
		}
		return napis;
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
	 * {@inheritDoc}
	 */
	public void ustawZOdczytu(String[] argumenty)
	{
		kolor = new Color(Integer.parseInt(argumenty[1]));
		for(int i = 2; i < (2 + ((argumenty.length-2)/2)); i++)
		{
			addPoint(Integer.parseInt(argumenty[i]), Integer.parseInt(argumenty[i + ((argumenty.length-2)/2)]));
		}
	}
	/**
	 * {@inheritDoc}, pamiêtaj¹c o wartoœciach minimalnych.
	 * @see min_width
	 * @see min_height
	 */
	public void zwieksz(int zwiekszenie) 
	{
		int dx = 0, dy = 0;
		if(zwiekszenie > 0)
		{
			dx = (int)(xpoints[0] * 1.2f) - xpoints[0];
			dy = (int)(ypoints[0] * 1.2f) - ypoints[0];
			for(int i = 0; i < npoints; i++)
			{
				xpoints[i] *= 1.2f;
				ypoints[i] *= 1.2f;
				xpoints[i] -= dx;
				ypoints[i] -= dy;
			}
		}
		else if(bounds.width > min_width && bounds.height > min_height)
		{
			dx = (int)(xpoints[0] * 0.8f) - xpoints[0];
			dy = (int)(ypoints[0] * 0.8f) - ypoints[0];
			for(int i = 0; i < npoints; i++)
			{
				xpoints[i] *= 0.8f;
				ypoints[i] *= 0.8f;
				xpoints[i] -= dx;
				ypoints[i] -= dy;
			}
		}
		naprawBounds(xpoints, ypoints, npoints);
	}
	/**
	 * Aktualizuje prostok¹t okalaj¹cy wielok¹t.
	 * @param xpoints tablica wartoœci odciêtych punktów wielok¹ta{@link xpoints}
	 * @param ypoints tablica wartoœci rzêdnych punktów wielok¹ta {@link ypoints}
	 * @param npoints liczba punktów wielok¹ta{@link npoints}
	 */
	private void naprawBounds(int xpoints[], int ypoints[], int npoints) 
	{
        int boundsMinX = Integer.MAX_VALUE;
        int boundsMinY = Integer.MAX_VALUE;
        int boundsMaxX = Integer.MIN_VALUE;
        int boundsMaxY = Integer.MIN_VALUE;

        for (int i = 0; i < npoints; i++) 
        {
            int x = xpoints[i];
            boundsMinX = Math.min(boundsMinX, x);
            boundsMaxX = Math.max(boundsMaxX, x);
            int y = ypoints[i];
            boundsMinY = Math.min(boundsMinY, y);
            boundsMaxY = Math.max(boundsMaxY, y);
        }
        bounds = new Rectangle(boundsMinX, boundsMinY,
                               boundsMaxX - boundsMinX,
                               boundsMaxY - boundsMinY);
    }
}