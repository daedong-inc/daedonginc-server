package com.daedonginc.repository.admin;

import java.util.Optional;

import com.daedonginc.entity.admin.AdminEntity;

/**
 * @author domo
 * Created on 2023/03/28
 */
public interface AdminRepository {
	Optional<AdminEntity> findByNameAndPassword(String name, String password);
}
