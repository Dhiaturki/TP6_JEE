package metier;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Genre implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_genre;
	private String genre_name;
	@OneToMany(mappedBy = "genre")
	private List<Film> genre;

	public Genre() {
		super();
	}

	public Genre(String nomCat) {
		super();
		this.genre_name = nomCat;
	}

	public Long getIdCat() {
		return id_genre;
	}

	public void setIdCat(Long idCat) {
		this.id_genre = idCat;
	}

	public String getNomCat() {
		return genre_name;
	}

	public void setNomCat(String nomCat) {
		this.genre_name = nomCat;
	}

	public List<Film> getProduits() {
		return genre;
	}

	public void setProduits(List<Film> produits) {
		this.genre = produits;
	}
}