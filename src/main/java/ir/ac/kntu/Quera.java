package ir.ac.kntu;

import java.util.ArrayList;

import ir.ac.kntu.db.*;
import ir.ac.kntu.menu.login.LoginMenu;
import ir.ac.kntu.model.*;

public class Quera {
    private LoginMenu loginMenu;

    public void start() {
        initial();
        loginMenu.menu();
    }

    private void initial() {
        AdminDB adminDB = new AdminDB(new ArrayList<>());
        User admin = new User("Hossein", "funlife", "2126",
                "yasbolaghi.sharrahi@gmail.com", "09184878759", "5710111326");
        adminDB.addAdmin(admin);

        UserDB userDB = new UserDB(new ArrayList<>());
        User user = new User("Mahdi", "react", "1379",
                "niati@gmail.com", "09334969659", "0710020650");
        userDB.addUser(user);

        CourseDB courseDB = new CourseDB(new ArrayList<>());
        DateTime startDate = new DateTime(1400, 11, 18, 0, 1, 1);
        Course course = new Course(user, "AP", "KNTU", startDate, Course.CourseStatus.OPEN_PRIVATE,
                "2126", "Advanced Programming with Java language");
        courseDB.addCourse(course);

        ContestDB contestDB = new ContestDB(new ArrayList<>());
        startDate = new DateTime(1401, 4, 15, 0, 1, 1);
        DateTime endDate = new DateTime(1401, 4, 18, 0, 1, 1);
        Contest normalContest = new NormalContest(admin, "CodeCup4", startDate,
                endDate, new ArrayList<>(), true);
        contestDB.addContest(normalContest);

        QuestionDB questionDB = new QuestionDB(new ArrayList<>());
        Question question = new ChoiceOneQuestion("Test", 25,
                "Select the word that is not one of the OOP pillars:\nA)interface \nB) inheritance\nC) encapsulation\nD) polymorphism",
                Question.QuestionType.CHOICE_ONE, Question.QuestionLevel.EASY, ChoiceOneQuestion.Choices.A);

        questionDB.addQuestion(question);

        loginMenu = new LoginMenu(adminDB, userDB, courseDB, contestDB, questionDB);
    }
}
