package joke.service;


import joke.domain.${domain};
import utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import utils.Constant;
import utils.Page;

import java.util.List;

/**
* @Author: caoxiao
* @Date: 12-11-21 下午9:27
*/
@Service
@Transactional
public class ${domain}ServiceImpl extends BaseService implements ${domain}Service{

public int saveOrUpdate${domain}(${domain} ${domain?lower_case}){
    return saveOrUpdateEntity(${domain?lower_case});
}

public int delete (int id){
    return 0;
}

//默认猜一个外键
public Page<${domain}> list${domain}ForPage(Page<${domain}> page, ${domain} ${domain?lower_case}){
    StringBuilder builder = new StringBuilder();
    builder.append("select * from ${tableName} where 1=1 ");
    <#list queryConditions as con>
        builder.append(" and ${con} = ?");
    </#list>
    Object[] args = {};
    if(page.getSortName()!=null){
        builder.append(" order by ").append(page.getSortName()).append(" ").append(page.getSortOrder());
    }
    if (page.isAutoPaging()) {
        builder.append("limit ");
        builder.append(page.getStartRow());
        builder.append(",");
        builder.append(page.getPageSize());
    }
    if(page.isAutoCount()){
        int count = jdbcTemplate.queryForInt("select count(*) from ${tableName} where article_status = ? ");
        page.setTotal(count);
    }
    List<${domain}> list = jdbcTemplate.query(builder.toString(),new BeanPropertyRowMapper<${domain}>(${domain}.class));
    page.setRows(list);
    return page;
}

}
