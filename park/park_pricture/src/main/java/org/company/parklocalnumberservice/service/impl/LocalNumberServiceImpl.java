package org.company.parklocalnumberservice.service.impl;

import org.company.parklocalnumberservice.entity.LocalNumber;
import org.company.parklocalnumberservice.mapper.LocalNumberMapper;
import org.company.parklocalnumberservice.service.LocalNumberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 停车场地点 服务实现类
 * </p>
 *
 * @author lih
 * @since 2020-08-23
 */
@Service
public class LocalNumberServiceImpl extends ServiceImpl<LocalNumberMapper, LocalNumber> implements LocalNumberService {

}
