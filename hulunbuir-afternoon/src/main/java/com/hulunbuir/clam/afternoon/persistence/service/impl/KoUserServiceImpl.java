package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.hulunbuir.clam.afternoon.persistence.entity.KoUser;
import com.hulunbuir.clam.afternoon.persistence.mapper.KoUserMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IKoUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020-01-17
 */
@Service
public class KoUserServiceImpl extends ServiceImpl<KoUserMapper, KoUser> implements IKoUserService {

}
