package joke.service;


import joke.domain.Comments;
import utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.math.BigInteger;
import java.util.ArrayList;
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
    List<Object> listObj = new ArrayList<Object>();
    if(comments.getCommentId()!=null){
        builder.append(" and comment_id = ?");
        listObj.add(comments.getCommentId());
    }
    if(comments.getArticleId()!=null){
        builder.append(" and article_id = ?");
        listObj.add(comments.getArticleId());
    }
    if(comments.getUserId()!=null){
        builder.append(" and user_id = ?");
        listObj.add(comments.getUserId());
    }
    Object[] args = listObj.toArray();

    builder.append(" order by ");
    if(page.getSortName()!=null){
        builder.append(page.getSortName()).append(" ").append(page.getSortOrder());
    }else{
        builder.append("comment_id").append(" desc ");
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
    List<Comments> list = jdbcTemplate.query(builder.toString(),args,new BeanPropertyRowMapper<Comments>(Comments.class));
    page.setRows(list);
    return page;
}

    public List<Comments> listCommentsByArticleId(BigInteger articleId) {
        Page<Comments> page = new Page<Comments>();
        page.setAutoCount(false);
        page.setAutoPaging(false);
        page.setSortName("comment_date");
        Comments comments = new Comments();
        comments.setArticleId(articleId);
        Page<Comments> pages = listCommentsForPage(page,comments);
        return pages.getRows();
    }

}
