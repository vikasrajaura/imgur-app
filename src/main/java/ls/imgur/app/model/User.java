package ls.imgur.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@ToString()
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long userId;

	@NotBlank
	@Size(min = 3, max = 20, message = "{0} must be between {2} and {1} characters")
	@Column(name = "USERNAME", unique = true, nullable = false, length = 20)
	private String userName;

	@NotBlank
	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;

	@NotBlank
	@Email
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Size(max = 20, message = "{0} can't exceed {1} characters")
	@Column(name = "LAST_NAME", nullable = true)
	private String lastName;


	@Column(name = "GENDER")
	private String gender;

	@JsonManagedReference
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name = "users_roles_map", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Column(name = "LAST_LOGIN", nullable = true)
	private Timestamp lastLogin;

	@Column(name = "IS_ACT_EXPIRED")
	private boolean isAccountExpired;

	@Column(name = "IS_ACT_LOCKED")
	private boolean isAccountLocked;

	@Column(name = "IS_CRED_EXPIRED")
	private boolean isCredentialsExpired;

	@Column(name = "IS_ENABLED")
	private boolean isEnabled = true;

	@Transient
	private List<Long> roleIds;



	public User(Long userId, String userName, String password, String firstName, String middleName, String lastName,
			String email, String gender, Set<Role> roles, Timestamp lastLogin, boolean isAccountExpired,
			boolean isAccountLocked, boolean isCredentialsExpired, boolean isEnabled, Long branchId, List<Long> roleIds) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.roles = roles;
		this.lastLogin = lastLogin;
		this.isAccountExpired = isAccountExpired;
		this.isAccountLocked = isAccountLocked;
		this.isCredentialsExpired = isCredentialsExpired;
		this.isEnabled = isEnabled;
		this.roleIds = roleIds;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return userId.equals(user.userId) && userName.equals(user.userName) && email.equals(user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userName, email);
	}

	public String getFullName() {
		if (StringUtils.isEmpty(middleName))
			return firstName + " " + lastName;
		else
			return firstName + " " + middleName + " " + lastName;
	}


}
