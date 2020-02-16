import java.util.*;

public class Single {
    private static Single instance = null;
    private Single(){}
    public static Single getInstance(){
        if(instance == null){
            instance = new Single();
        }
        return instance;
    }


    public Person loggedInUser;
    public List<Post> feed = new ArrayList<>();
    public List<Person> allUsers = new ArrayList<>();
    public Map<String, List<Post>> allPosts = new HashMap<>();
    public List<Goal> loggedInGoals = new ArrayList<>();

    public void init() {
        loggedInUser = new Person(
                "Ellen Wilson",
                "City Planner of the Shire",
                "The Shire, Middle Earth",
                "Age: 21-32",
                "I'm Ellen. I am the City Planner for The Shire Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris id quam feugiat, dictum magna pulvinar, molestie libero. Nunc porta quam quis sapien mattis, laoreet vulputate est luctus. Nunc a mauris arcu. Praesent efficitur efficitur lectus nec imperdiet. Proin et sapien at lectus interdum blandit. ",
                "Healthy Sleeping Habits, Marathon Training, Keto Dieting, Retirement Saving, Journaling"
        );
        addToAllUsers();
        addToAllPosts();
        addToGoals();
    }

    private void addToGoals() {
        loggedInGoals.add(new Goal(
                        "GOAL 1",
                        "January 7, 2020",
                        "Feb 1, 2020",
                        "MWF",
                        "This is a cool goal #1.",
                        new int[]{2,0,2,1,2,3,3}
                )
        );
        loggedInGoals.add(new Goal(
                        "GOAL 2",
                        "October 23, 2019",
                        "May 1, 2020",
                        "SMWF",
                        "This is a cool goal #2.",
                        new int[]{1,0,2,1,2,1,3}
                )
        );
        loggedInGoals.add(new Goal(
                        "GOAL 3",
                        "March 14, 2015",
                        "May 5, 2020",
                        "SMWF",
                        "This is a cool goal #3.",
                        new int[]{2,0,2,1,2,3,3}
                )
        );
        loggedInGoals.add(new Goal(
                        "GOAL 4",
                        "Feb 7, 2020",
                        "Feb 22, 2020",
                        "SMTWThFS",
                        "This is a cool goal #4.",
                        new int[]{1,2,1,1,2,3,3}
                )
        );
        loggedInGoals.add(new Goal(
                        "GOAL 5",
                        "March 7, 2019",
                        "April 1, 2020",
                        "MWThS",
                        "This is a cool goal #5.",
                new int[]{1,3,3,3,3,3,3}
                )
        );
        loggedInGoals.add(new Goal("a1","b1","c1","d1","e1",new int[]{1,3,3,3,3,3,3}));
        loggedInGoals.add(new Goal("a2","b2","c2","d2","e2",new int[]{1,3,3,3,3,3,3}));
        loggedInGoals.add(new Goal("a3","b3","c3","d3","e3",new int[]{1,3,3,3,3,3,3}));
        loggedInGoals.add(new Goal("a4","b4","c4","d4","e4",new int[]{1,3,3,3,3,3,3}));
        loggedInGoals.add(new Goal("a5","b5","c5","d5","e5",new int[]{1,3,3,3,3,3,3}));
    }

    private void addToAllPosts() {
        allPosts.put("Healthy Sleeping Habits", new ArrayList<>());
        allPosts.put("Marathon Training", new ArrayList<>());
        allPosts.put("Keto Dieting", new ArrayList<>());
        allPosts.put("Journaling", new ArrayList<>());
        allPosts.put("Meal Prepping", new ArrayList<>());

        allPosts.get("Healthy Sleeping Habits").add(new Post("Steve Fernandex", "Healthy Sleeping Habits", "Success!! I was able to sleep through the night last night!", 12152, 812));
        allPosts.get("Healthy Sleeping Habits").add(new Post("Glen Cummings", "Healthy Sleeping Habits", "Seeking advice: How do I restructure my sleep if I'm waking up every hour through the night? #exhausted #helpme", 12152, 812));
        allPosts.get("Healthy Sleeping Habits").add(new Post("Avery Matheson", "Healthy Sleeping Habits", "Celebrate with Avery! 6 day streak of successful sleeping! #celebrate #streak", 12152, 812));
        allPosts.get("Healthy Sleeping Habits").add(new Post("Simon Summer", "Healthy Sleeping Habits", "Seeking advice: How do I fix my sleep schedule after completely flipping it? Seems like I'm opposite of everyone around me. #sleep #isolated", 12152, 812));

        allPosts.get("Marathon Training").add(new Post("Glen Cummings", "Marathon Training", "Success!! I was able to sleep through the night last night!", 12152, 812));
        allPosts.get("Keto Dieting").add(new Post("Avery Matheson", "Keto Dieting", "Help motivate Avery! She is struggling with her dieting! #advice", 12152, 812));
        allPosts.get("Journaling").add(new Post("Simon Summer", "Journaling", "Seeking advice: Where should a newbie get a good bullet journal and pens? I want to start daily journaling, but I don't have the supplies!! #help", 12152, 812));
        allPosts.get("Meal Prepping").add(new Post("Julie Hancock", "Meal Prepping", "Seeking advice: Anyone know good meals for big groups (100-150 people)? ", 12152, 812));

//        for (int i = 0; i < 10; i++) {
//            allPosts.get("Healthy Sleeping Habits").add(new Post("Julie Hancock", "Healthy Sleeping Habits", "Cool post :-) #" + i, 12152, 812));
//            allPosts.get("Marathon Training").add(new Post("Julie Hancock", "Marathon Training", "Cool post :-)) #" + i, 12152, 812));
//            allPosts.get("Keto Dieting").add(new Post("Julie Hancock", "Keto Dieting", "Cool post :-))) #" + i, 12152, 812));
//            allPosts.get("Journaling").add(new Post("Julie Hancock", "Journaling", "Cool post :-)))) #" + i, 12152, 812));
//            allPosts.get("Meal Prepping").add(new Post("Julie Hancock", "Meal Prepping", "Cool post :-))))) #" + i, 12152, 812));
//        }

        for(Map.Entry<String, List<Post>> posts : allPosts.entrySet()){
            feed.addAll(posts.getValue());
        }
    }

    private void addToAllUsers() {
        allUsers.add(loggedInUser);
        allUsers.add(new Person("Steve Fernandex", "Software Engineer", "Austin, Texas", "Age: 21-32", "Hi! I'm Steve!", "Healthy Sleeping Habits"));
        allUsers.add(new Person("Glen Cummings", "Graphic Designed", "Denver, Colorado", "Age: 42-51", "Hi! I'm Glen!", "Healthy Sleeping Habits, Marathon Training"));
        allUsers.add(new Person("Avery Matheson", "Doctor", "Mesa, Arizona", "Age: 21-32", "Hi! I'm Avery!", "Healthy Sleeping Habits, Keto Dieting"));
        allUsers.add(new Person("Simon Summer", "Contractor", "London, England", "Age: 32-41", "Hi! I'm Simon!", "Healthy Sleeping Habits, Journaling"));
        allUsers.add(new Person("Julie Hancock", "Android Developer", "Lehi, UT Texas", "Age: 21-32", "Hi! I'm Julie!", "Healthy Sleeping Habits, Meal Prepping"));
    }


    public void updateGoal(Goal finalGoalBefore, Goal newGoal) {
//        finalGoalBefore = newGoal;
        for (int i = 0; i < loggedInGoals.size(); i++) {
            if(loggedInGoals.get(i).equals(finalGoalBefore)){
                loggedInGoals.remove(loggedInGoals.get(i));
                loggedInGoals.add(i, newGoal);
                return;
            }
        }
        throw new Error("goal didn't exist. idk how you got here my dude");
    }
}
