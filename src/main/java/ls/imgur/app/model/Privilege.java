package ls.imgur.app.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "PRIVILEGES")
@Data
@NoArgsConstructor
@ToString(exclude = {"roles"})
public class Privilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRIV_ID")
	private Long privId;

	@Column(name = "PRIV_NAME", unique = true, nullable = false, length = 200)
	private String privName;

	@Column(name = "DETAILS", length = 500)
	private String details;

	@JsonBackReference
	@ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
	private Set<Role> roles = new HashSet<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((privId == null) ? 0 : privId.hashCode());
		result = prime * result + ((privName == null) ? 0 : privName.hashCode());
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
		Privilege other = (Privilege) obj;
		if (privId == null) {
			if (other.privId != null)
				return false;
		} else if (!privId.equals(other.privId))
			return false;
		if (privName == null) {
			if (other.privName != null)
				return false;
		} else if (!privName.equals(other.privName))
			return false;
		return true;
	}


}
