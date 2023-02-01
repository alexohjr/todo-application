package com.heoch.todoapplication.persistence;

import com.heoch.todoapplication.model.QUserEntity;
import com.heoch.todoapplication.model.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public String getUserNameByUserEmail(String email) {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory
                .select(qUser.userName)
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();

    }

    @Override
    public UserEntity getUserDTOByUserEmail(String email) {
        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory
                .selectFrom(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();
    }


}
