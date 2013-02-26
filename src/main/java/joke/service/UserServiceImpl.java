package joke.service;

import joke.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: caoxiao
 * @Date: 12-11-21 下午7:00
 */
@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {



    @Override
    public int registerUser(User user) {
        if(user==null || StringUtils.isEmpty(user.getUserEmail()) || StringUtils.isEmpty(user.getPassWord())||StringUtils.isEmpty(user.getUserName())){
            return 0;
        }
        return saveOrUpdateEntity(user);
    }

    public User loginUser(User user) {
        String sql = "select * from t_user where (user_email = ? or user_name = ?) and pass_word = ?";
        List<User> list  = jdbcTemplate.query(sql,new Object[]{user.getUserEmail(),user.getUserEmail(),user.getPassWord()},new BeanPropertyRowMapper<User>(User.class));
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public void saveOrUpdateUser(User user) {
        saveOrUpdateEntity(user);
    }

}
