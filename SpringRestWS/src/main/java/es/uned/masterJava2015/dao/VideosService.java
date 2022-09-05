package es.uned.masterJava2015.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.uned.masterJava2015.domain.Videos;
import es.uned.masterJava2015.utility.DBUtility;

@Service("VideosService")
public class VideosService {
	
	private Connection connection;

	public VideosService() {
		connection = DBUtility.getConnection();
		 
	}	
	

	
	public Videos getVideoById(String id) {
		
		Videos video = new Videos();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from videos where  id=?");
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				video.setid(id);
				video.setid_canal(rs.getString("id_canal"));
				video.settitulo(rs.getString("titulo"));
				video.setdescripcion(rs.getString("descripcion"));
				video.setfecha_publicacion(rs.getString("fecha_publicacion"));
				video.setthumbnail(rs.getString("thumbnail"));
			}else{
				video.setid(id);
				video.setid_canal("Video no registrado en la BD local");
				video.settitulo("Video no registrado en la BD local");
				video.setdescripcion("Video no registrado en la BD local");
				video.setfecha_publicacion("Video no registrado en la BD local");
				video.setthumbnail("Video no registrado en la BD local");
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} catch (java.lang.NullPointerException e){
			System.out.println("NO HAY CONEXION A LA BD!!!!");
		}
		return video;
	}

}

