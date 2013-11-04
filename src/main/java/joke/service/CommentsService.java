package joke.service;


import joke.domain.Comments;
import utils.Page;

import java.math.BigInteger;
import java.util.List;

/**
* @Author: caoxiao
* @Date: 12-11-21 下午9:27
*/
public interface CommentsService {

    int saveOrUpdateComments(Comments comments);

    int delete (int id);

    Page<Comments> listCommentsForPage(Page<Comments> commentsPage, Comments comments);

    List<Comments> listCommentsByArticleId(BigInteger articleId);
}
