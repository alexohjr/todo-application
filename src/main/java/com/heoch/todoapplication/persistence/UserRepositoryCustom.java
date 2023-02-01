package com.heoch.todoapplication.persistence;

import com.heoch.todoapplication.model.UserEntity;

public interface UserRepositoryCustom {

    String getUserNameByUserEmail(final String email);

    UserEntity getUserDTOByUserEmail(final String email);
}
