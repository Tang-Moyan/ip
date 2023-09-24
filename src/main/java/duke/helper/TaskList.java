package duke.helper;

public class TaskList {
    private Task[] userList;
    private int userListPointer;




    public TaskList(Task[] userList, int userListPointer) {
        this.userList = userList;
        this.userListPointer = userListPointer;

    }

    public TaskList() {
        this.userList = new Task[100];
        this.userListPointer = 0;
    }

    public String addTask(Task task) {
        userList[userListPointer] = task;
        userListPointer++;
        String message = "Got it. I've added this task:\n" + task.display();
        message = message + "\nNow you have " + userListPointer + " tasks in the list.";
        return message;


    }

    public String deleteTask(int position) {

        if (position < 0 || position >= userListPointer) {
            return ("Invalid index.");
        } else {

            String message = "Noted. I've removed this task:" + userList[position].display();


            Task[] newUserList = new Task[100];

            for (int a = 0, k = 0; a < userListPointer; a++) {

                // if the index is
                // the removal element index
                if (a == position) {
                    continue;
                }

                // if the index is not
                // the removal element index
                newUserList[k++] = userList[a];
            }

            userListPointer--;

            userList = newUserList;

            message = message + "\nNow you have " + userListPointer + " tasks in the list.";
            return message;

        }
    }

    public String markTask(int position) {
        if (position < 0 || position >= userListPointer) {
            return "Invalid index.";
        } else {

            userList[position].markDone();

            assert userList[position].getCompletionStatus(); //target task should be marked as done

            return "Following task is marked as done:\n" + position + ". "
                    + userList[position].display();

        }
    }

    public String unmarkTask(int position) {
        if (position < 0 || position >= userListPointer) {
            return "Invalid index.";
        } else {

            userList[position].unmarkDone();

            assert !userList[position].getCompletionStatus();//target task should be marked as undone

            return "Following task is marked as undone:\n" + position + ". "
                    + userList[position].display();

        }
    }

    public Task[] getUserList() {
        return userList;
    }

    public int getUserListPointer() {
        return userListPointer;
    }

    /**
     * Return the String containing all the information in the list.
     * @return String representation of the content in the list.
     */
    public String displayList() {
        if (userListPointer < 1) {
            return "No items in the list yet";
        } else {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < userListPointer; i++) {
                int num = i + 1;
                message.append(num).append(". ").append(userList[i].display()).append("\n");


            }
            return message.toString();
        }
    }

    public String findTask(String keyWords) {
        if (userListPointer < 1) {
            return "No items in the list yet";
        } else {
            int num = 1;
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < userListPointer; i++) {
                if (userList[i].getTaskName().toLowerCase().contains(keyWords.toLowerCase())) {
                    message.append(num).append(". ").append(userList[i].display()).append("\n");
                    num++;
                }

            }
            if (num == 1) {
                return "No matches found";
            } else {
                return "Here are the matching tasks in your list:\n" + message;
            }

        }
    }

    public String showTaskStatics() {
        if (userListPointer < 1) {
            return "No items in the list yet";
        } else {
            int numTodo = 0;
            int numDeadLine = 0;
            int numEvent = 0;

            for (int i = 0; i < userListPointer; i++) {
                int type = userList[i].getTypeOfTask();
                if (type == 1) {
                    numTodo++;
                }
                if (type == 2) {
                    numDeadLine++;
                }
                if (type == 3) {
                    numEvent++;
                }



            }

            assert numTodo <= userListPointer;//number of todo tasks should not exceed the total number of tasks
            assert numDeadLine <= userListPointer;//number of deadline tasks should not exceed the total number of tasks
            assert numEvent <= userListPointer;//number of event tasks should not exceed the total number of tasks

            return "You have:\n" + "~ " + numTodo + " todo.\n" + "~ " + numDeadLine + " deadline.\n"
                    + "~ " + numEvent + " event.\n";



        }
    }
}
