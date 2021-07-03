package com.hulunbuir.admin.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/5/31 17:40
 */
@Repository
public interface BuirUserRepository extends MongoRepository<BuirUser,String> {


}
