package it.polito.tdp.music.db;

import it.polito.tdp.music.model.Artist;
import it.polito.tdp.music.model.AutoreAscolti;
import it.polito.tdp.music.model.City;
import it.polito.tdp.music.model.Country;
import it.polito.tdp.music.model.Listening;
import it.polito.tdp.music.model.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

public class MusicDAO {
	
	public List<Country> getAllCountries(Map<Integer,Country>m) {
		final String sql = "SELECT id, country FROM country" ;
		
		List<Country> countries = new LinkedList<Country>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				Country c=new Country(res.getInt("id"), res.getString("country"));
				countries.add(c) ;
				m.put(res.getInt("id"), c);
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return countries ;
		
	}
	
	public List<City> getAllCities() {
		final String sql = "SELECT id, city FROM city" ;
		
		List<City> cities = new LinkedList<City>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				cities.add( new City(res.getInt("id"), res.getString("city"))) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return cities ;
		
	}

	
	public List<Artist> getAllArtists(Map<Integer,Artist>m) {
		final String sql = "SELECT id, artist FROM artist" ;
		
		List<Artist> artists = new LinkedList<Artist>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				Artist a= new Artist(res.getInt("id"), res.getString("artist"));
				artists.add(a) ;
				m.put(a.getId(), a);
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return artists ;
		
	}

	public List<Track> getAllTracks() {
		final String sql = "SELECT id, track FROM track" ;
		
		List<Track> tracks = new LinkedList<Track>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				tracks.add( new Track(res.getInt("id"), res.getString("track"))) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return tracks ;
		
	}
	
	public List<Listening> getAllListenings() {
		final String sql = "SELECT id, userid, month, weekday, longitude, latitude, countryid, cityid, artistid, trackid FROM listening" ;
		
		List<Listening> listenings = new LinkedList<Listening>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				listenings.add( new Listening(res.getLong("id"), res.getLong("userid"), res.getInt("month"), res.getInt("weekday"),
						res.getDouble("longitude"), res.getDouble("latitude"), res.getInt("countryid"), res.getInt("cityid"),
						res.getInt("artistid"), res.getInt("trackid"))) ;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
		return listenings ;
		
	}
	
	public List<AutoreAscolti> getAutoriAscolti(int month,Map<Integer,Artist>m) {
		final String sql = "SELECT l.artistid,COUNT(l.id),l.country FROM listening AS l WHERE l.MONTH= ? GROUP BY l.artistid" ;
		
		List<AutoreAscolti> list = new LinkedList<AutoreAscolti>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, month);
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				AutoreAscolti a=new AutoreAscolti(res.getInt("artistid"),m.get(res.getInt("artistid")),res.getInt("COUNT(l.id)"));
				list.add(a);
			}
			
			conn.close() ;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null ;
		}
		
		return list ;
		
	}

	public List<Country> getTopCountry(int month,Map<Integer,Country>m) {
		final String sql = "SELECT DISTINCT countryid FROM listening WHERE artistid= ? " ;
		
		List<Country> list = new LinkedList<Country>() ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, month);
			
			ResultSet res = st.executeQuery() ;
			
			while( res.next() ) {
				list.add(m.get(res.getInt("countryid")));
			}
			
			conn.close() ;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null ;
		}
		
		return list ;
		
	}

	public int getPeso(Country c,Country m) {
		final String sql = "SELECT COUNT(l1.artistid) FROM listening AS l1, listening AS l2 WHERE l1.countryid= ? AND l2.countryid= ? AND l1.artistid=l2.artistid GROUP BY l1.artistid " ;
		
		int peso=0;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, c.getId());
			st.setInt(2, m.getId());
			
			ResultSet res = st.executeQuery() ;
			
			if( res.next() ) {
				peso=res.getInt("COUNT(artistid)");
			}
			
			conn.close() ;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return -1 ;
		}
		
		return 0 ;
		
	}
	
	public LatLng getLatLngCountry(Country c) {
		final String sql = "SELECT AVG(latitude),AVG(longitude) FROM listening WHERE countryid= ? " ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, c.getId());
		
			ResultSet res = st.executeQuery() ;	
			
			if( res.next() ) {
				LatLng l=new LatLng(res.getLong("AVG(latitude)"),res.getLong("AVG(longitude)"));
				return l;
			}
			
			conn.close() ;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null ;
		}
		
		return null ;
		
	}
	
	/*public static void main(String[] args) {
		MusicDAO dao = new MusicDAO() ;
		
		List<Country> countries = dao.getAllCountries() ;
		//System.out.println(countries) ;
		
		List<City> cities = dao.getAllCities() ;
		//System.out.println(cities) ;
		
		List<Artist> artists = dao.getAllArtists() ;
		
		List<Track> tracks = dao.getAllTracks() ;
		
		List<Listening> listenings = dao.getAllListenings() ;



		System.out.format("Loaded %d countries, %d cities, %d artists, %d tracks, %d listenings\n", 
				countries.size(), cities.size(), artists.size(), tracks.size(), listenings.size()) ;
	}*/

}
