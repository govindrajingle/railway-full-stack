package govind.railway;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createUser(UserEntity user) {

        if (userRepository.existsByMobile(user.getMobile())) {
            throw new RuntimeException("User with mobile number " + user.getMobile() + " already exists");
        }

        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public UserEntity updateUser(UUID id, UserEntity updatedUser) {

        UserEntity existingUser = getUserById(id);

        if (!existingUser.getMobile().equals(updatedUser.getMobile()) && userRepository.existsByMobile(updatedUser.getMobile())) {
            throw new RuntimeException("User with mobile number " + updatedUser.getMobile() + " already exists");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setPosition(updatedUser.getPosition());
        existingUser.setMobile(updatedUser.getMobile());

        return userRepository.save(existingUser);
    }

    public void deleteUser(UUID id) {
        UserEntity user = getUserById(id);
        userRepository.delete(user);
    }
}

