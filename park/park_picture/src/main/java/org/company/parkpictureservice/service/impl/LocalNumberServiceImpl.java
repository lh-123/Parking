package org.company.parkpictureservice.service.impl;

import org.company.parkpictureservice.entity.LocalNumber;
import org.company.parkpictureservice.mapper.LocalNumberMapper;
import org.company.parkpictureservice.service.LocalNumberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 停车场地点 服务实现类
 * </p>
 *
 * @author lih
 * @since 2020-07-22
 */
@Service
public class LocalNumberServiceImpl extends ServiceImpl<LocalNumberMapper, LocalNumber> implements LocalNumberService {

}
