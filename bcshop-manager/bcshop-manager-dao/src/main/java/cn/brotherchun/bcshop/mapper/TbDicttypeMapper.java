package cn.brotherchun.bcshop.mapper;

import cn.brotherchun.bcshop.pojo.TbDicttype;
import cn.brotherchun.bcshop.pojo.TbDicttypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDicttypeMapper {
    int countByExample(TbDicttypeExample example);

    int deleteByExample(TbDicttypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbDicttype record);

    int insertSelective(TbDicttype record);

    List<TbDicttype> selectByExample(TbDicttypeExample example);

    TbDicttype selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbDicttype record, @Param("example") TbDicttypeExample example);

    int updateByExample(@Param("record") TbDicttype record, @Param("example") TbDicttypeExample example);

    int updateByPrimaryKeySelective(TbDicttype record);

    int updateByPrimaryKey(TbDicttype record);
}