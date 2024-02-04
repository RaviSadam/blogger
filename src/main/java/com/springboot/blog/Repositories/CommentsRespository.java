package com.springboot.blog.Repositories;


public interface CommentsRespository{
    
    // @Transactional
    // @Modifying
    // @Query(value="INSERT INTO comments(content,posted_date,blogid,username) VALUES(:content,:postedDate,:blogid,:username)",nativeQuery = true)
    // public void addComment(@Param("content")String content,@Param("postedDate")Date date,@Param("blogid") String blogid,@Param("username")String username);

    // @Query(value="SELECT content AS comment, posted_date AS date, username AS username, blogid AS blogId FROM comments WHERE blogid=:blogid LIMIT 10",nativeQuery = true)
    // public List<CommentDto> getComments(@Param("blogid") String blogid);

}
