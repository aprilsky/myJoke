package joke.service;


import joke.domain.${domain};
import utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
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
    List<Object> listObj = new ArrayList<Object>();
    <#list   queryConditionMap?keys as mykey>
        <#assign mapUser=queryConditionMap[mykey] >
        if(${domain?lower_case}.get${mapUser}()!=null){
            builder.append(" and ${mykey} = ?");
            listObj.add(${domain?lower_case}.get${mapUser}());
        }
    </#list>
    Object[] args = listObj.toArray();

    builder.append(" order by ");
    if(page.getSortName()!=null){
        builder.append(page.getSortName()).append(" ").append(page.getSortOrder());
    }else{
        builder.append("${pkColumn}").append(" desc ");
    }
    if (page.isAutoPaging()) {
        builder.append(" limit ");
        builder.append(page.getStartRow());
        builder.append(",");
        builder.append(page.getPageSize());
    }
    if(page.isAutoCount()){
        String countSql = builder.substring(builder.indexOf("from"),builder.lastIndexOf("order by"));
        countSql =  "select count(*) "+countSql;
        int count = jdbcTemplate.queryForInt(countSql,args);
        page.setTotal(count);
    }
    List<${domain}> list = jdbcTemplate.query(builder.toString(),args,new BeanPropertyRowMapper<${domain}>(${domain}.class));
    page.setRows(list);
    return page;
}

}
