package com.ai.mapper;

import com.ai.entity.dto.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    @Select("SELECT * FROM account WHERE uid = #{uid}")
    Account findByUid(String uid);
    @Select("SELECT * FROM account WHERE role = #{role}")
    List<Account>findAllByRole(String role);

}
