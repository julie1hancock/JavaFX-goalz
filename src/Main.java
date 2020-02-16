import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    Stage window;
    TextArea editGoalTitle;
    TextArea editStartDate;
    TextArea editEndDate;
    TextArea editFrequency;
    TextArea editDescription;
    TextArea editName;
    TextArea editJob;
    TextArea editLocation;
    TextArea editAge;
    TextArea editProfileDescription;
    TextArea editCommunities;


    public static void main(String[] args) {
        launch(args); // It calls start method defined bellow...
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Single.getInstance().init();
        window = primaryStage;
        window.setTitle("Goalz");
        setup(getHome(), "Home");
    }

    private void setup(GridPane body, String sHeader) {
        GridPane main = new GridPane();
        GridPane header = setupHeader(sHeader);
        main.add(header, 0,0);
        main.add(body, 0,1);

        ScrollPane scrollPane = new ScrollPane(main);
        scrollPane.setFitToHeight(true);

        BorderPane borderPane = new BorderPane(scrollPane);
        borderPane.setPadding(new Insets(15));
        borderPane.setTop(main);

        Scene scene = new Scene(borderPane, 1000, 800);
        window.setScene(scene);
        window.show();
    }

    private GridPane setupHeader(String title) {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(new Text(title), 0,0);
        Button home = new Button("Home");
        home.setOnAction((event) ->{
            setup(getHome(), "Home");
            System.out.println("home");
        });
        Button addGoal = new Button("Add Goal");
        addGoal.setOnAction((event) ->{
            setup(getSingleGoalEdit(null), "Add Goal");
            System.out.println("addgoal");
        });
        Button myGoals = new Button("My Goals");
        myGoals.setOnAction((event) ->{
            setup(getMyGoals(), "My Goals");
            System.out.println("mygoals");
        });
        Button feed = new Button("Feed");
        feed.setOnAction((event) ->{
            setup(getFeed(), "Feed");
            System.out.println("feed");
        });
        Button profile = new Button("Profile");
        profile.setOnAction((event) ->{
            setup(getProfile(Single.getInstance().loggedInUser), "Profile");
            System.out.println("profile");
        });
        grid.add(home, 0,1);
        grid.add(addGoal, 1,1);
        grid.add(myGoals, 2,1);
        grid.add(feed, 3,1);
        grid.add(profile, 4,1);

        return grid;
    }

    private GridPane getMyGoals() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        List<Goal> goals = Single.getInstance().loggedInGoals;

        for (int i = 0; i < goals.size(); i++) {
            grid.add(newGoalRow(goals.get(i)), 0,i);
        }
        return grid;
    }

    private GridPane newGoalRow(Goal goal) {
        GridPane grid = new GridPane();

        grid.add(new Text("Goal Title: "),0,0);
        grid.add(new Text("Start Date: "),0,1);
        grid.add(new Text("End Date: "),0,2);
        grid.add(new Text("Frequency: "),0,3);
        grid.add(new Text("Description: "),0,4);

        grid.add(new Text(goal.goalTitle),1,0);
        grid.add(new Text(goal.startDate),1,1);
        grid.add(new Text(goal.endDate),1,2);
        grid.add(new Text(goal.frequency),1,3);
        grid.add(new Text(goal.description),1,4);
        Button edit = new Button("Edit");
        edit.setOnAction((event) ->{
            setup(getSingleGoalEdit(goal), "Edit Goal");
        });

        grid.add(edit,0,5);

        Line line = new Line();
        line.setStartX(0);
        line.setEndX(700);

        grid.add(line,0,6,2,1);
        return grid;
    }

    private GridPane getSingleGoalEdit(Goal goal) {
        boolean isNewGoal = false;
        Goal goalBefore = null;
        if(goal == null) {
            isNewGoal = true;
            goal = new Goal();
        }
        else {
            goalBefore = goal.copy();
        }
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(new Text("Goal Title:"), 0,0);
        editGoalTitle = new TextArea(goal.goalTitle);
        editGoalTitle.setPrefRowCount(1);
        editGoalTitle.setPromptText("marathon training");
        grid.add(editGoalTitle, 1,0);

        grid.add(new Text("Start Date:"), 0,1);
        editStartDate = new TextArea(goal.startDate);
        editStartDate.setPrefRowCount(1);
        editStartDate.setPromptText("January 17, 2020");
        grid.add(editStartDate, 1,1);

        grid.add(new Text("End Date:"), 0,2);
        editEndDate = new TextArea(goal.endDate);
        editEndDate.setPrefRowCount(1);
        editEndDate.setPromptText("Feb 16, 2021");
        grid.add(editEndDate, 1,2);

        grid.add(new Text("Frequency:"), 0,3);
        editFrequency = new TextArea(goal.frequency);
        editFrequency.setPrefRowCount(1);
        editFrequency.setPromptText("Select a frequency");
        grid.add(editFrequency, 1,3);

        grid.add(new Text("Description:"), 0,4);
        editDescription = new TextArea(goal.description);
        editDescription.setPrefRowCount(1);
        editDescription.setPromptText("This goal is to...");
        grid.add(editDescription, 1,4);

        Button saveGoal = new Button("Save");
        boolean finalIsNewGoal = isNewGoal;
        Goal finalGoalBefore = goalBefore;
        saveGoal.setOnAction((event) ->{
            Goal newGoal = new Goal(
                    editGoalTitle.getText(),
                    editStartDate.getText(),
                    editEndDate.getText(),
                    editFrequency.getText(),
                    editDescription.getText()
            );

            if(finalIsNewGoal){
                Single.getInstance().loggedInGoals.add(newGoal);
            }
            else{
                Single.getInstance().updateGoal(finalGoalBefore, newGoal);
            }

            setup(getHome(), "Home");
        });
        grid.add(saveGoal, 0,5);
        return grid;
    }

    //TODO
//    private GridPane getCommunityFeed(String communityName){
//        return null;
//    }

    private GridPane getFeed() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        List<Post> posts = Single.getInstance().feed;
        for (int i = 0; i < posts.size(); i++) {
            grid.add(newPostRow(posts.get(i)), 0, i);
        }
        return grid;
    }

    private GridPane newPostRow(Post post) {
        GridPane grid = new GridPane();
        grid.add(new Circle(10,10,40),0,0,1,3);
        grid.add(new Text(post.name + " - " + post.communityName),1,1);
        grid.add(new Text(post.message),1,2); //wrap
        GridPane likeCommentGrid = new GridPane();
        Button likeButton = new Button("LIKE");
        likeButton.setOnAction((event) ->{
            post.likeCount++;
        });
        likeCommentGrid.add(likeButton,0,0);
        likeCommentGrid.add(new Text(post.likeCount+""),1,0);
//        Button commentButton = new Button("COMMENT");
//        commentButton.setOnAction((event) ->{
//            //TODO
//            post.commentCount++;
//        });
//        likeCommentGrid.add(commentButton,2,0);
//        likeCommentGrid.add(new Text(post.commentCount+""),3,0);
        grid.add(likeCommentGrid,1,3);

        Line line = new Line();
        line.setStartX(0);
        line.setEndX(700);

        grid.add(line,0,4,4,1);

        return grid;
    }

    private GridPane getProfile(Person p) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(new Text(p.name), 0, 1);

        GridPane subheader = new GridPane();
        subheader.add(new Text(p.jobTitle), 0,0);
        subheader.add(new Text(" - "), 1,0);
        subheader.add(new Text(p.location), 2,0);
        subheader.add(new Text(" - "), 3,0);
        subheader.add(new Text(p.age), 4,0);
        grid.add(subheader, 0, 2);

        Text description = new Text(p.description);
        description.setWrappingWidth(800);
        grid.add(description, 0,3);


        GridPane communityGrid = new GridPane();
        communityGrid.add(new Text("Communities: "),0,0);
        communityGrid.add(new Text(p.communityList),1,0);
        grid.add(communityGrid, 0,4);

        Button editProfile = new Button("Edit Profile");
        editProfile.setOnAction((event) ->{
            setup(getEditProfile(p), "EditProfile");
        });
        grid.add(editProfile, 0,5);
        return grid;
    }

    private GridPane getEditProfile(Person p) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //TODO: edit picture

        grid.add(new Text("Name: "), 0, 0);
        editName = new TextArea(p.name);
        editName.setPrefRowCount(1);
        grid.add(editName, 1, 0);

        grid.add(new Text("Job Title: "), 0, 1);
        editJob = new TextArea(p.jobTitle);
        editJob.setPrefRowCount(1);
        grid.add(editJob, 1, 1);

        grid.add(new Text("Location: "), 0, 2);
        editLocation = new TextArea(p.location);
        editLocation.setPrefRowCount(1);
        grid.add(editLocation, 1, 2);

        grid.add(new Text("Age: "), 0, 3);
        editAge = new TextArea(p.age);
        editAge.setPrefRowCount(1);
        grid.add(editAge, 1, 3);

        grid.add(new Text("Description: "), 0, 4);
        editProfileDescription = new TextArea(p.description);
        editProfileDescription.setPrefRowCount(1);
        grid.add(editProfileDescription, 1, 4);

        grid.add(new Text("Communities: "), 0, 5);
        editCommunities = new TextArea(p.communityList);
        editCommunities.setPrefRowCount(1);
        grid.add(editCommunities, 1, 5);

        Button saveProfile = new Button("Save");
        saveProfile.setOnAction((event) ->{
            Person person = new Person("",editName.getText(),editJob.getText(),editLocation.getText(),editAge.getText(),editProfileDescription.getText(), editCommunities.getText());
            Single.getInstance().loggedInUser = person;
            setup(getProfile(person), "Profile");
        });
        grid.add(saveProfile, 0,6);
        return grid;
    }

    private GridPane getHome(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(new Text("Welcome to Goalz! This is for tracking and reporting goals!"),0,0);
        return grid;
    }
}