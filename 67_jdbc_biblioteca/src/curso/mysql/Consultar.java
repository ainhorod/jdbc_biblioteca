package curso.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Consultar {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
	
		//Con Statement
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from libros");

		while (rs.next()) {

			System.out.println(" titulo: " + rs.getString("titulo") + ", autor: " + rs.getString("autor") + ", precio: " + rs.getFloat("precio")
					+ ", fechapublicacion: " + rs.getDate("fechapublicacion"));

		}
		
		int insertar = stmt.executeUpdate("insert into libros(titulo, autor, precio, fechapublicacion) values('El Quijote', 'Miguel de Cervantes', 15.95, '1990-05-25'  )");
		System.out.println("Fila insertada " + insertar);
		System.out.println("--------------------------------");
		
		//PreparedStatement ahora
		
			PreparedStatement pstmt = conn.prepareStatement("select * from libros where titulo = ?");
			pstmt.setString(1, "El Quijote");
			ResultSet rs1 = pstmt.executeQuery();
		
		while (rs1.next()) {

			System.out.println(" titulo: " + rs1.getString("titulo") + ", autor: " + rs1.getString("autor") + ", precio: " + rs1.getFloat("precio")
					+ ", fechapublicacion: " + rs1.getDate("fechapublicacion"));

		}
		
		System.out.println("Fila insertada con prepared Statement " + insertar);
		System.out.println("--------------------------------");
		
		//Siguiente ejercicio con callablestatement
		
			CallableStatement cstmt = conn.prepareCall("{call listaLibrosPorAutor(?)}");
			cstmt.setString(1, "Miguel de Cervantes");
			ResultSet rs2 = cstmt.executeQuery();
			
			while (rs2.next()) {

				System.out.println(" titulo: " + rs2.getString("titulo") + ", autor: " + rs2.getString("autor") + ", precio: " + rs2.getFloat("precio")
						+ ", fechapublicacion: " + rs2.getDate("fechapublicacion"));

			}
			
			System.out.println("Fila insertada con callable Statement " + insertar);
			System.out.println("--------------------------------");
		
			
	}

}
