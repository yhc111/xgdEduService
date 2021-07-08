package com.xgd.manage_cms.dao;

import com.xgd.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/13 10:19
 */
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
    // 根据页面名称查询
    CmsPage findByPageName(String pageName);
    // 根据页面名称、站点ID、页面webpath查询
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);

}
