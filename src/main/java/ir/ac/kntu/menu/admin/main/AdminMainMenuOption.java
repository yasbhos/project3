package ir.ac.kntu.menu.admin.main;

public enum AdminMainMenuOption {
    USERS, COURSES, CONTESTS, QUESTIONS, ACCOUNT, LOGOUT;

    public enum UsersSubMenu { LIST_OF_USERS, BACK }

    public enum CoursesSubMenu { ADD_COURSE, LIST_OF_COURSES, BACK }

    public enum ContestsSubMenu { ADD_CONTEST, LIST_OF_CONTESTS, BACK }

    public enum QuestionsSubMenu { LIST_OF_QUESTIONS, BACK }
}
