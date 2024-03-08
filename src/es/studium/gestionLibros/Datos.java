package es.studium.gestionLibros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datos
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/programacion";
	String login = "carlos";
	String password = "Studium2023;";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	Datos() {}

	public boolean conectar()
	{
		boolean conexionCorrecta = true;
		//Cargar el Driver
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}
		//Establecerlaconexiónconla base dedatos
		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			//System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}
	public void desconectar() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Error al cerrar " + e.toString());
		}
	}
	public boolean comprobarCredenciales(String usuario, String clave)
	{
		boolean credencialesCorrectas = true;
		String sentencia = "SELECT * FROM usuarios " + "WHERE nombreUsuario='"+usuario+"' " + "AND claveUsuario = ('"+clave+"');";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			if(!rs.next())
			{
				// Credencialesincorrectas
				credencialesCorrectas = false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}

		return credencialesCorrectas;
	}
	public boolean AltaPersona(String nombre, String apellido, String DNI, String correo)
	{
		boolean altaCorrecta = true;
		String sentencia = "INSERT INTO personas VALUES (null, '"+nombre+"', '"+apellido+"', '"+DNI+"', '"+correo+";)";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}

		return altaCorrecta;
	}
	public String damePersona() {
		String contenido = "";
		String sentencia = "SELECT * FROM personas";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia); while (rs.next())
			{
				contenido= contenido +rs.getString("nombrePersona")+ ", ";
				contenido= contenido +rs.getString("apellidoPersona")+ ", ";
				contenido= contenido +rs.getString("dniPersona")+ ", ";
				contenido= contenido +rs.getString("correoPersona")+ "\n";
			}	
		}
		catch (SQLException e) 
		{
			System.out.println("Error al realizar la consulta: " + e.toString());	
		}
		return contenido.toString();
	}
	public char dameTipo(String usuario) {
		String sentencia = "SELECT tipoUsuario FROM usuarios WHERE nombreUsuario = '"+usuario+"';";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			if (rs.next())
			{
				return(rs.getString("tipoUsuario").charAt(0));
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error en la sentencia SQL;" + e.toString());
		}
		return 0;
	}

}