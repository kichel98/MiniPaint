package lista_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Panel po prawej strony, zawieraj�cy przyciski s�u��ce do tworzenia i edycji figur. 
 */
class PrawyBok extends JPanel implements ActionListener
{
	/**
	 * Przycisk, kt�ry prze��cza program w tryb tworzenia.
	 */
	static JToggleButton tworz;
	/**
	 * Przycisk, kt�ry prze��cza program w tryb tworzenia ko�a.
	 */
	static JToggleButton kolo;
	/**
	 * Przycisk, kt�ry prze��cza program w tryb tworzenia prostok�ta.
	 */
	static JToggleButton prostokat;
	/**
	 * Przycisk, kt�ry prze��cza program w tryb tworzenia wielok�ta.
	 */
	static JToggleButton wielokat;
	/**
	 * Przycisk, kt�ry prze��cza program w tryb edycji.
	 */
	static JToggleButton modyfikuj;
	/**
	 * Panel, w kt�rym przechowywane s� przyciski odpowiadaj�ce za tworzenie poszczegolnych figur - kolo, prostokat i wielokat.
	 */
	private JPanel figury;
	/**
	 * Odpowiada panelowi Wn�trze, potrzebujemy to pole w celu wywo�ania metod {@link Wnetrze#usunWielokat()};
	 */
	private Wnetrze wnetrze;
	/**
	 * Powo�uje do �ycia obiekty, ustawia wygl�d przycisk�w i ca�ego panelu.
	 * @param wnetrze panel, na kt�rym rysujemy
	 */
	public PrawyBok(Wnetrze wnetrze)
	{
		this.wnetrze = wnetrze;
		setLayout(new BorderLayout());
		
		tworz = new JToggleButton("Tw�rz");
		figury = new JPanel();
		kolo = new JToggleButton("Ko�o");
		prostokat = new JToggleButton("Prostok�t");
		wielokat = new JToggleButton("Wielok�t");
		modyfikuj = new JToggleButton("Modyfikuj", true);
		setPreferredSize(new Dimension(100, 0));
		add(tworz, BorderLayout.NORTH);
		figury.setLayout(new BoxLayout(figury, BoxLayout.Y_AXIS));
		kolo.setMaximumSize(new Dimension(100, 30));
		prostokat.setMaximumSize(new Dimension(100, 30));
		wielokat.setMaximumSize(new Dimension(100, 30));
		kolo.setAlignmentX(Component.CENTER_ALIGNMENT);
		prostokat.setAlignmentX(Component.CENTER_ALIGNMENT);
		wielokat.setAlignmentX(Component.CENTER_ALIGNMENT);
		figury.add(kolo);
		figury.add(prostokat);
		figury.add(wielokat);
		figury.setVisible(false);
		tworz.addActionListener(this);
		kolo.addActionListener(this);
		prostokat.addActionListener(this);
		wielokat.addActionListener(this);
		modyfikuj.addActionListener(this);
		add(figury, BorderLayout.CENTER);
		add(modyfikuj, BorderLayout.SOUTH);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	/**
	 * Obs�uguje wszystkie zdarzenia zwi�zane z klikaniem i odklikaniem przycisk�w.
	 * Wywo�uje tak�e metod� {@link Wnetrze#usunWielokat()}, je�li kto� zmienia tryb przed sko�czeniem rysowania.
	 * 
	 * @param e zdarzenie - klikni�cie kt�rego� z przycisk�w
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{ 
		if(e.getSource() == tworz)
		{
			modyfikuj.setEnabled(true);
			if(tworz.isSelected() == false)
			{
				if(wnetrze.czyWielokat == true)
				{
					wnetrze.usunWielokat();
				}
				modyfikuj.setSelected(true);
				figury.setVisible(false);
			}
			else
			{
				modyfikuj.setSelected(false);
				figury.setVisible(true);
				kolo.setSelected(false);
				prostokat.setSelected(false);
				wielokat.setSelected(false);	
			}
		}
		else if(e.getSource() == modyfikuj)
		{
			if(modyfikuj.isSelected() == false)
			{
				tworz.setSelected(true);
				figury.setVisible(true);
				kolo.setSelected(false);
				prostokat.setSelected(false);
				wielokat.setSelected(false);	
				
			}
			else
			{
				tworz.setSelected(false);
				figury.setVisible(false);
			}
		}
		else if(e.getSource() == kolo)
		{
			if(kolo.isSelected())
				modyfikuj.setEnabled(false);
			else
				modyfikuj.setEnabled(true);
			
			if(wnetrze.czyWielokat == true)
			{
				wnetrze.usunWielokat();
			}
			prostokat.setSelected(false);
			wielokat.setSelected(false);
			
		}
		else if(e.getSource() == prostokat)
		{
			if(prostokat.isSelected())
				modyfikuj.setEnabled(false);
			else
				modyfikuj.setEnabled(true);
			
			if(wnetrze.czyWielokat == true)
			{
				wnetrze.usunWielokat();
			}
			kolo.setSelected(false);
			wielokat.setSelected(false);
		}
		else if(e.getSource() == wielokat)
		{
			if(wielokat.isSelected())
				modyfikuj.setEnabled(false);
			else
			{
				modyfikuj.setEnabled(true);
			}
			kolo.setSelected(false);
			prostokat.setSelected(false);
		}
	}
}
