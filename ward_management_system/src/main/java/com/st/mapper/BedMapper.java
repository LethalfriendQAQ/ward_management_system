package com.st.mapper;

import com.st.bean.Bed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BedMapper {
    //添加
    int insert (Bed b);
    //根据ID删除
    int delete(Long bid);
    int deleteByWnumber(String wnumber);
    //修改
    int update(Bed b);
    int updateWnumberByOldwnumber(@Param("oldWnumber") String oldWnumber, @Param("newWnumber") String newWnumber);
    int updatePnoAndNnoByBnumber(@Param("pno") String pno, @Param("nno") String nno, @Param("bnumber") String bnumber);
    int updateBnumber(@Param("oldBnumber") String oldBnumber, @Param("newBnumber") String newBnumber);

    //查询所有病床
    List<Bed> selectAll();
    //根据病床号查询
    Bed selectByBnumber(String bnumber);
    List<Bed> selectByWnumber(String wnumber);
    Bed selectByBid(Long bid);
    List<Bed> selectFreeBedsByWnumber(@Param("number") String wnumber);

    //Bed selectWnumberByBid(Long bid);
}
