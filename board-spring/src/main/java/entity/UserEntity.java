package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User") //Entity이름 지정
@Table(name = "User")
public class UserEntity {
    @Id //userEmail을 primary key로 사용
    private String userEmail;   //Snake Case -> Camel Case로
    private String userPassword;
    private String userNickname;
    private String userPhoneNumber;
    private String userAddress;
    private String userProfile;
}
