package com.dragon;

import com.dragon.entity.user.UserEntity;
import com.dragon.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoShopWebApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    @Test
    public void saveUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("chenlong");
        userEntity.setSalt(RandomStringUtils.randomAlphabetic(10));
        String password = userEntity.getSalt() + "/" + "123456";
        userEntity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userRepository.save(userEntity);
    }

    @Test
    public void saveRedis(){
        Optional<UserEntity> byId = userRepository.findById("402880e46e77c104016e77c1102a0000");
        if (byId.isPresent()){
            UserEntity userEntity = byId.get();
            redisTemplate.opsForValue().set("USER:"+userEntity.getId(),userEntity);

            UserEntity getUserEntity = (UserEntity)redisTemplate.opsForValue().get("USER:"+userEntity.getId());
            System.out.println(getUserEntity);
        }
    }
}
