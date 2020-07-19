package org.company.parkservice.service.impl;

import org.company.parkservice.entity.Person;
import org.company.parkservice.mapper.PersonMapper;
import org.company.parkservice.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lih
 * @since 2020-07-20
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
