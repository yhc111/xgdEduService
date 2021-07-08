package com.xgd.filesystem.dao;

import com.xgd.framework.domain.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Administrator.
 */
public interface FileSystemRepository extends MongoRepository<FileSystem,String> {
}
