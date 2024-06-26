package kongnoodle.libraryPlatform.demo.feat.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Setter private String email;

	@Setter private String vendor;

	@Setter private String nickname;


	private double latitude; // 위도
	private double longitude; // 경도

	private String city;

	@Builder
	private Account(String email, String vendor, String nickname) {
		this.email = email;
		this.vendor = vendor;
		this.nickname = nickname;
	}
}
