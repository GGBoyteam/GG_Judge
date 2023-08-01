package com.zhangsiyao.common.entity.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhangsiyao.common.entity.service.dao.Route;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteAddOrUpdateVo extends Route implements Serializable {

}
