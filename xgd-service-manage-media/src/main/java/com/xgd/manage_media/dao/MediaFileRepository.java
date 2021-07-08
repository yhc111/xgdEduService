package com.xgd.manage_media.dao;

import com.xgd.framework.domain.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MediaFileRepository extends MongoRepository<MediaFile,String> {
}
