package lista_5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Dolny panel panelu {@link Wyglad}.
 */
class Stopka extends JPanel implements ActionListener
{
	/**
	 * Miejsce, w kt�rym wypisywane s� informacje o figurze w trakcie przesuwania.
	 */
	static JTextArea dane; 
	/**
	 * Panel z przyciskami, umieszczony w prawym dolnym rogu.
	 */
	private JPanel rog;
	/**
	 * Przycisk uruchamiaj�cy informacyjne okno dialogowe.
	 * @see Info
	 */
	private JButton info;
	/**
	 * Przycisk rozpoczynaj�cy zapisywanie do pliku.
	 * @see Wnetrze#zapisz()
	 */
	private JButton zapisz;
	/**
	 * Przycisk rozpoczynaj�cy odczytywanie z pliku.
	 * @see Wnetrze#odczyt()
	 */
	private JButton odczyt;
	/**
	 * Odpowiada panelowi Wn�trze, potrzebujemy to pole w celu wywo�ania metod {@link zapisz} i {@link odczyt};
	 */
	private Wnetrze wnetrze;
	/**
	 * Tworzymy stopk�, ustawiamy jej parametry i wygl�d.
	 * @param wnetrze panel, na kt�rym rysujemy
	 */
	public Stopka(Wnetrze wnetrze)
	{
		this.wnetrze = wnetrze;
		setLayout(new BorderLayout());
		dane = new JTextArea("Dane: ");
		rog = new JPanel();
		zapisz = new JButton("Zapisz");
		odczyt = new JButton("Odczyt");
		info = new JButton("Info");
		info.addActionListener(this);
		zapisz.addActionListener(this);
		odczyt.addActionListener(this);
		dane.setPreferredSize(new Dimension(382, 0));
		setMinimumSize(new Dimension(0, 50));
		rog.setPreferredSize(new Dimension(90, 100));
		setBorder(BorderFactory.createLineBorder(Color.black));
		add(dane, BorderLayout.WEST);
		rog.add(info);
		rog.add(zapisz);
		rog.add(odczyt);
		add(rog, BorderLayout.EAST);
	}
	/**
	 * Obs�uguje zdarzenia, tworzy obiekt klasy {@link Info}, uruchamia funkcj� {@link Wnetrze#zapisz()}
	 * lub {@link Wnetrze#odczyt()} (w zale�no�ci od zdarzenia).
	 * @param e zdarzenie - przyci�ni�cie przycisku info, zapisz lub odczyt
	 * @see Wnetrze
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == info)
		{
			new Info();
		}
		else if(e.getSource() == zapisz)
		{
			try
			{
				wnetrze.zapisz();
			}
			catch (IOException ex)
			{
				dane.setText("nieudany zapis");
			}
		}
		else if(e.getSource() == odczyt)
		{
			try
			{
				wnetrze.odczyt();
			}
			catch (IOException ex)
			{
				dane.setText("nieudany odczyt");
			}
		}
	}
}
