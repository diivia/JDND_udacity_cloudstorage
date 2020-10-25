package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {


    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNoteById(Integer noteid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getAllNotesForUser(Integer userid);

    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    int delete(@Param("noteId") Integer noteId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Insert("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription}, userid=#{userId} WHERE noteid = #{noteId}")
    int update(@Param("noteId") Integer noteId,
               @Param("noteTitle") String noteTitle,
               @Param("noteDescription") String noteDescription,
               @Param("userId") String userId);
}
