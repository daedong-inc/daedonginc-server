package com.daedonginc.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daedonginc.entity.admin.AdminEntity;

/**
 * @author domo
 * Created on 2023/03/28
 */
public interface AdminJpaRepository extends JpaRepository<AdminEntity, Long>, AdminRepository {
}
