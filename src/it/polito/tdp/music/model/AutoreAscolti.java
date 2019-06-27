package it.polito.tdp.music.model;

public class AutoreAscolti implements Comparable<AutoreAscolti>{
	
	private int id;
	private Artist artista;
	private int ascolti;
	
	public AutoreAscolti(int id, Artist artista, int ascolti) {
		
		this.id = id;
		this.artista = artista;
		this.ascolti = ascolti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Artist getArtista() {
		return artista;
	}

	public void setArtista(Artist artista) {
		this.artista = artista;
	}

	public int getAscolti() {
		return ascolti;
	}

	public void setAscolti(int ascolti) {
		this.ascolti = ascolti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutoreAscolti other = (AutoreAscolti) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(AutoreAscolti altro) {
		
		return this.ascolti-altro.getAscolti();
	}
	
	

}
