package lista_5;

import javax.swing.*;
import java.awt.*;
/**
 *  Klasa, ktorej obiekt jest panelem 
 *  calkowicie wypelniajacym okno.
 *  @see Paint
 */
class Wyglad extends JPanel
{	
	Stopka stopka;
	PrawyBok prawyBok;
	Wnetrze wnetrze;
	public Wyglad()
	{
		wnetrze = new Wnetrze();
		stopka = new Stopka(wnetrze); // przekazujemy stopce wnetrze, potrzebuje go przy zapisie i odczycie
		prawyBok = new PrawyBok(wnetrze); // przekazujemy prawemu bokowi wnetrze, potrzebuje go do usuwania wielok¹ta
		
		setLayout(new BorderLayout());
		add(wnetrze);
		add(stopka, BorderLayout.SOUTH);
		add(prawyBok, BorderLayout.EAST);
	}
}
/**
 *  Glowna klasa programu graficznego.
 *  Wiecej informacji ogolnych w Info.
 *  @see Info
 *  @author Piotr Andrzejewski
 *  @version 3.2
 */
public class Painty extends JFrame
{
	public Painty()
	{
		super("Painty");
		Wyglad wyglad = new Wyglad();
        add(wyglad);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
	}
	public static void main(String[] args) 
	{
		Painty glowny = new Painty();
		glowny.setVisible(true);
	}
}
