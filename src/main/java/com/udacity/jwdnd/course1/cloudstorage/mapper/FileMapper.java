package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileDB;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    FileDB getFileById(Integer fileid);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    List<FileDB> getAllFilesForUser(Integer fileid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    int delete(@Param("fileId") Integer fileId);

    @Select("SELECT * FROM FILES")
    List<FileDB> getAllFiles();

    @Insert("INSERT INTO FILES (filename, contenttype, filedata, filesize, userid) VALUES(#{fileName}, #{contentType}, #{fileData}, #{fileSize}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(FileDB fileDB);
}

