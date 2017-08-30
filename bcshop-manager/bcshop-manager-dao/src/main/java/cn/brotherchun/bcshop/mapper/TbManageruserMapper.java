package cn.brotherchun.bcshop.mapper;

import cn.brotherchun.bcshop.pojo.TbManageruser;
import cn.brotherchun.bcshop.pojo.TbManageruserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbManageruserMapper {
    int countByExample(TbManageruserExample example);

    int deleteByExample(TbManageruserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbManageruser record);

    int insertSelective(TbManageruser record);

    List<TbManageruser> selectByExample(TbManageruserExample example);

    TbManageruser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbManageruser record, @Param("example") TbManageruserExample example);

    int updateByExample(@Param("record") TbManageruser record, @Param("example") TbManageruserExample example);

    int updateByPrimaryKeySelective(TbManageruser record);

    int updateByPrimaryKey(TbManageruser record);
}