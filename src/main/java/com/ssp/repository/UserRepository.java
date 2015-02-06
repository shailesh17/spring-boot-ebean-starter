package com.ssp.repository;

import org.springframework.stereotype.Repository;

import com.avaje.ebean.Expr;
import com.ssp.model.User;

/**
 * Handles generic user related DAO.
 * 
 * @author shailesh.patel
 */
@Repository
public class UserRepository extends BaseRepository<User, Long> {

    public UserRepository() {
        super(User.class);
    }

    /**
     * Fetches the user given the twitterId.
     * 
     * @param twitterId
     * @return
     */
    public User findByTwitterId(String twitterId) {
        return super.findSingleResult(Expr.eq("twitterId", twitterId));
    }
}
