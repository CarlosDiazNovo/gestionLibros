package es.studium.gestionLibros;


import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaPersona implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Alta de Personas");
	Label lblnombre = new Label ("Nombre de la Persona");
	TextField txtNombre = new TextField(20);
	Label lblapellido = new Label ("Apellido de la Persona");
    TextField txtApellido = new TextField(20);
    Label lbldni = new Label ("DNI de la Persona");
    TextField txtDNI = new TextField(20);
    Label lblcorreo = new Label ("Correo de la Persona");
    TextField txtCorreo = new TextField(20);

	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");

	Datos datos = new Datos();
	Dialog mensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("Persona creada correctamente");

	AltaPersona()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(new Label("Nombre:"));
		ventana.add(txtNombre);
		ventana.add(new Label("Apellido:"));
		ventana.add(txtApellido);
		ventana.add(new Label("DNI:"));
		ventana.add(txtDNI);
		ventana.add(new Label("Correo electrónico:"));
		ventana.add(txtCorreo);
		
		btnAceptar.addActionListener(this);
		ventana.add(btnAceptar);
		
		btnLimpiar.addActionListener(this);
		ventana.add(btnLimpiar);

		ventana.setSize(400,250);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

	}
	public void windowActivated(WindowEvent windowEvent){}
	public void windowClosed(WindowEvent windowEvent) {}
	public void windowClosing(WindowEvent windowEvent)
	{
		if(mensaje.isActive())
		{
			mensaje.setVisible(false);
			txtNombre.setText("");
			txtApellido.setText("");
			txtDNI.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
		}

		else
		{
			ventana.setVisible(false);
		}
	}
	public void windowDeactivated(WindowEvent windowEvent) {}
	public void windowDeiconified(WindowEvent windowEvent) {}
	public void windowIconified(WindowEvent windowEvent) {}
	public void windowOpened(WindowEvent windowEvent) {}
	public void actionPerformed(ActionEvent actionEvent)
	{
		if(actionEvent.getSource().equals(btnLimpiar))
		{
			txtNombre.setText("");
			txtApellido.setText("");
			txtDNI.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
		}
		
		else if(actionEvent.getSource().equals(btnAceptar))
		{
			datos.conectar();
			boolean altaCorrecta = datos.AltaPersona(txtNombre.getText(), txtApellido.getText(), txtDNI.getText(), txtCorreo.getText());
			mensaje.setLayout(new FlowLayout());
			mensaje.addWindowListener(this);
			mensaje.setSize(250,70);
			mensaje.setResizable(false);
			mensaje.setLocationRelativeTo(null);
			if(altaCorrecta == false)
			{
				lblMensaje.setText("Se ha producido un error");
			}
			else
			{
				lblMensaje.setText("Persona creada correctamente");
			}
			mensaje.add(lblMensaje);
			mensaje.setVisible(true);
			datos.desconectar();
		}
	}
}