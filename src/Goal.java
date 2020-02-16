import java.util.Objects;

public class Goal {
    String goalTitle;
    String startDate;
    String endDate;
    String frequency;
    String description;

    public Goal(String goalTitle, String startDate, String endDate, String frequency, String description) {
        this.goalTitle = goalTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.description = description;
    }

    public Goal() {
        String goalTitle = "";
        String startDate = "";
        String endDate = "";
        String frequency = "";
        String description = "";
    }

    public Goal copy() {
        Goal g = new Goal();
        g.goalTitle = this.goalTitle;
        g.startDate = this.startDate;
        g.endDate = this.endDate;
        g.frequency = this.frequency;
        g.description = this.description;
        return g;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Objects.equals(goalTitle, goal.goalTitle) &&
                Objects.equals(startDate, goal.startDate) &&
                Objects.equals(endDate, goal.endDate) &&
                Objects.equals(frequency, goal.frequency) &&
                Objects.equals(description, goal.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalTitle, startDate, endDate, frequency, description);
    }
}
