package joke.service;


import joke.domain.${domain};
import utils.Page;

import java.util.List;

/**
* @Author: caoxiao
* @Date: 12-11-21 下午9:27
*/
public interface ${domain}Service {

    int saveOrUpdate${domain}(${domain} ${domain?lower_case});

    int delete (int id);

    Page<${domain}> list${domain}ForPage(Page<${domain}> ${domain?lower_case}Page, ${domain} ${domain?lower_case});

}
