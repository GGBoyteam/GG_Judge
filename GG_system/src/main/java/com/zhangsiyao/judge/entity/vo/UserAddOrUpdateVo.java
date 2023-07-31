package com.zhangsiyao.judge.entity.vo;

import com.zhangsiyao.judge.entity.dao.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAddOrUpdateVo extends UserInfo implements Serializable {
}
