package es.studium.gestionLibros;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ConsultaPersona implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Listado de Personas");

	TextArea listado = new TextArea(5,40);
	Button btnVolver = new Button("Volver");
	Datos datos = new Datos();

	ConsultaPersona()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);

		// Conectar BD
		datos.conectar();
		listado.append(datos.damePersona());
		datos.desconectar();

		ventana.add(listado);
		ventana.add(btnVolver);
		btnVolver.addActionListener(this);
		ventana.setSize(350,200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	public void windowActivated(WindowEvent windowEvent){}
	public void windowClosed(WindowEvent windowEvent) {}
	public void windowClosing(WindowEvent windowEvent)
	{
		ventana.setVisible(false);
	}
	public void windowDeactivated(WindowEvent windowEvent) {}
	public void windowDeiconified(WindowEvent windowEvent) {}
	public void windowIconified(WindowEvent windowEvent) {}
	public void windowOpened(WindowEvent windowEvent) {}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnVolver) {
            ventana.dispose(); 
        }
    }

    public static void main(String[] args) {
        new ConsultaPersona();
}
}