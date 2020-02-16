public class Person {
    String profilePicURL;
    String name;
    String jobTitle;
    String location;
    String age;
    String description;
    String communityList;

    public Person(String name, String jobTitle, String location, String age, String description, String communityList) {
        this.profilePicURL = "/default";
        this.name = name;
        this.jobTitle = jobTitle;
        this.location = location;
        this.age = age;
        this.description = description;
        this.communityList = communityList;
    }

    public Person(String profilePicURL, String name, String jobTitle, String location, String age, String description, String communityList) {
        this.profilePicURL = profilePicURL;
        this.name = name;
        this.jobTitle = jobTitle;
        this.location = location;
        this.age = age;
        this.description = description;
        this.communityList = communityList;
    }
}
