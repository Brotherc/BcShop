package cn.brotherchun.bcshop.mapper;

import cn.brotherchun.bcshop.pojo.TbDictinfo;
import cn.brotherchun.bcshop.pojo.TbDictinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDictinfoMapper {
    int countByExample(TbDictinfoExample example);

    int deleteByExample(TbDictinfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbDictinfo record);

    int insertSelective(TbDictinfo record);

    List<TbDictinfo> selectByExample(TbDictinfoExample example);

    TbDictinfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbDictinfo record, @Param("example") TbDictinfoExample example);

    int updateByExample(@Param("record") TbDictinfo record, @Param("example") TbDictinfoExample example);

    int updateByPrimaryKeySelective(TbDictinfo record);

    int updateByPrimaryKey(TbDictinfo record);
}