package com.hulunbuir.admin.mongodb;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * explain: 测试mongodb的curd
 * </p>
 *
 * @author wangjunming
 * @since 2021/5/31 17:48
 */
@Service
public class BuirUserService {

    @Autowired
    private BuirUserRepository userRepository;

    /**
     * 增加
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUser(BuirUser user) {
        BuirUser save = userRepository.save(user);
        return true;
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delUser(String userId) {
        userRepository.deleteById(userId);
        return true;
    }

    /**
     * 修改
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(BuirUser user) {
        Optional<BuirUser> buirUser = userRepository.findById(user.getId());
        boolean present = buirUser.isPresent();
        if (present) {
            BuirUser userGet = buirUser.get();
            BeanUtils.copyProperties(user, userGet);
            return true;
        }
        return false;
    }

    /**
     * 查询-主要为分页模糊搜索-参考：
     * https://blog.csdn.net/qq_38288606/article/details/78673528
     * https://blog.csdn.net/zhengchaowei5286860/article/details/99834606
     * https://www.kanzhun.com/jiaocheng/528965.html
     * 重点参考：
     * https://blog.csdn.net/qq_30054997/article/details/79420141
     * https://www.cnblogs.com/rulian/p/6533109.html
     */
    public Page<BuirUser> selectPageUser(BuirUser user, Integer pageSize, Integer pageNo) {
        Sort statusSort = Sort.by(Sort.Direction.DESC, "status");
        PageRequest pageRequest = PageRequest.of(pageSize, pageNo,statusSort);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                //开启模糊搜索
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                //name开启 “开始匹配” 方式
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::startsWith)
                .withMatcher("status",ExampleMatcher.GenericPropertyMatcher::contains)
                .withIgnorePaths("id")
                ;
        Example<BuirUser> example = Example.of(user,matcher);
        return userRepository.findAll(example,pageRequest);
    }

}
