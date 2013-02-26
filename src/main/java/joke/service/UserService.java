package joke.service;

import joke.domain.User;

/**
 * @Author: caoxiao
 * @Date: 12-11-21 下午9:27
 */
public interface UserService {

    int registerUser(User user);

    User loginUser(User user);

    void saveOrUpdateUser(User user);
}
