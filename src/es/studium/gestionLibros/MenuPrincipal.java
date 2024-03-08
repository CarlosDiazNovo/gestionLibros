package es.studium.gestionLibros;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class MenuPrincipal implements WindowListener, ActionListener, ItemListener
{
	Frame ventana = new Frame("Menú Principal");
	MenuBar barraMenu = new MenuBar();
	Menu mnuPersona = new Menu("Personas");
	//MenuItem mniConsultaDepartamento = new MenuItem("Consulta");
	//MenuItem mniAltaDepartamento = new MenuItem("Alta");
	//MenuItem mniBajaDepartamento = new MenuItem("Baja");
	//MenuItem mniModificacionDepartamento = new MenuItem("Modificación");

	MenuItem mniConsultaPersona = new MenuItem("Consulta");
	MenuItem mniAltaPersona = new MenuItem("Alta");
	//MenuItem mniBajaEmpleado = new MenuItem("Baja");
	//MenuItem mniModificacionEmpleado = new MenuItem("Modificación");

	private char tipoUsuario;
	
	MenuPrincipal(char u)
	{
		tipoUsuario=u;
		
		ventana.setLayout(new FlowLayout());
		ventana.setMenuBar(barraMenu);
		ventana.addWindowListener(this);
		mniConsultaPersona.addActionListener(this);
		mniAltaPersona.addActionListener(this);
		
		mnuPersona.add(mniAltaPersona);
		if (tipoUsuario=='a')
		{
			mnuPersona.add(mniConsultaPersona);
		}
	
		barraMenu.add(mnuPersona);
		
		ventana.setSize(350,200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	public static void main(String[] args)
	{
		new Login();
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{

	}
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		if(actionEvent.getSource().equals(mniConsultaPersona))
		{
			new ConsultaPersona();
		}
		else if(actionEvent.getSource().equals(mniAltaPersona))
		{
			new AltaPersona();
		}
	}

	@Override
	public void windowOpened(WindowEvent e)
	{

	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e)
	{

	}

	@Override
	public void windowIconified(WindowEvent e)
	{

	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{

	}

	@Override
	public void windowActivated(WindowEvent e)
	{

	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{

	}

}