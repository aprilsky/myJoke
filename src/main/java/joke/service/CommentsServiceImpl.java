package joke.service;


import joke.domain.Comments;
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
public class CommentsServiceImpl extends BaseService implements CommentsService{

    public int saveOrUpdateComments(Comments comments){
        return saveOrUpdateEntity(comments);
    }

    public int delete (int id){
        return 0;
    }

    //默认猜一个外键
    public Page<Comments> listCommentsForPage(Page<Comments> page, Comments comments){
        StringBuilder builder = new StringBuilder();
        builder.append("select * from t_comments where 1=1 ");

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
            int count = jdbcTemplate.queryForInt("select count(*) from t_comments where  ");
            page.setTotal(count);
        }
        List<Comments> list = jdbcTemplate.query(builder.toString(),new BeanPropertyRowMapper<Comments>(Comments.class));
        page.setRows(list);
        return page;
    }

}
