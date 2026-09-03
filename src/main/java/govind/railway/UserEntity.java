package govind.railway;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "uk_user_mobile", columnNames = "mobile")})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Position is required")
    @Size(max = 30, message = "Position cannot exceed 30 characters")
    @Column(nullable = false, length = 30)
    private String position;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
    @Column(nullable = false, unique = true, length = 10)
    private String mobile;
}
