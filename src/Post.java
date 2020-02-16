public class Post {
    String name;
    String communityName;
    String message;
    int likeCount;
    int commentCount;

    public Post(String name, String communityName, String message, int likeCount, int commentCount) {
        this.name = name;
        this.communityName = communityName;
        this.message = message;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}