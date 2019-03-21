package lista_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Panel po prawej strony, zawieraj¹cy przyciski s³u¿¹ce do tworzenia i edycji figur. 
 */
class PrawyBok extends JPanel implements ActionListener
{
	/**
	 * Przycisk, który prze³¹cza program w tryb tworzenia.
	 */
	static JToggleButton tworz;
	/**
	 * Przycisk, który prze³¹cza program w tryb tworzenia ko³a.
	 */
	static JToggleButton kolo;
	/**
	 * Przycisk, który prze³¹cza program w tryb tworzenia prostok¹ta.
	 */
	static JToggleButton prostokat;
	/**
	 * Przycisk, który prze³¹cza program w tryb tworzenia wielok¹ta.
	 */
	static JToggleButton wielokat;
	/**
	 * Przycisk, który prze³¹cza program w tryb edycji.
	 */
	static JToggleButton modyfikuj;
	/**
	 * Panel, w którym przechowywane s¹ przyciski odpowiadaj¹ce za tworzenie poszczegolnych figur - kolo, prostokat i wielokat.
	 */
	private JPanel figury;
	/**
	 * Odpowiada panelowi Wnêtrze, potrzebujemy to pole w celu wywo³ania metod {@link Wnetrze#usunWielokat()};
	 */
	private Wnetrze wnetrze;
	/**
	 * Powo³uje do ¿ycia obiekty, ustawia wygl¹d przycisków i ca³ego panelu.
	 * @param wnetrze panel, na którym rysujemy
	 */
	public PrawyBok(Wnetrze wnetrze)
	{
		this.wnetrze = wnetrze;
		setLayout(new BorderLayout());
		
		tworz = new JToggleButton("Twórz");
		figury = new JPanel();
		kolo = new JToggleButton("Ko³o");
		prostokat = new JToggleButton("Prostok¹t");
		wielokat = new JToggleButton("Wielok¹t");
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
	 * Obs³uguje wszystkie zdarzenia zwi¹zane z klikaniem i odklikaniem przycisków.
	 * Wywo³uje tak¿e metodê {@link Wnetrze#usunWielokat()}, jeœli ktoœ zmienia tryb przed skoñczeniem rysowania.
	 * 
	 * @param e zdarzenie - klikniêcie któregoœ z przycisków
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
