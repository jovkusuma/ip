import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final String bye = " Bye. Hope to see you again soon!";
    private int count = 0;
    private static final String LINE = "____________________________________________________________";
    private static final String message = LINE + "\n"
                + " Hello! I'm ChatBot\n"
                + " What can I do for you?\n"
                + LINE + "\n";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> tasksList = new ArrayList<>();
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
//        String bye = " Bye. Hope to see you again soon!";
//        String message = "____________________________________________________________\n"
//                + " Hello! I'm ChatBot\n"
//                + " What can I do for you?\n"
//                + "____________________________________________________________\n";
//        ArrayList<Task> tasksList = new ArrayList<>();
//        int count = 0;
//
//        System.out.println(message);

//        while(true) {
//            try {
//                String input = scanner.nextLine();
//                System.out.println("____________________________________________________________");
//                if (input.equalsIgnoreCase("bye")) {
//                    System.out.println(bye);
//                    System.out.println("____________________________________________________________");
//                    break;
//
//                } else if (input.equalsIgnoreCase("list")) {
//                    System.out.println("Here are the tasks in your list:");
//                    if (count == 0) {
//                        System.out.println("\t You currently have no tasks!");
//                    } else {
//                        for (int i = 0; i < count; i++) {
//                            System.out.println((i + 1) + "." + tasksList.get(i).toString());
//                        }
//                    }
//
//                    System.out.println("____________________________________________________________");
//                } else if (input.startsWith("mark")) {
//                    tasksList.get(Integer.parseInt(input.replace("mark ", "")) - 1).markDone();
//                } else if (input.startsWith("unmark")) {
//                    tasksList.get(Integer.parseInt(input.replace("unmark ", "")) - 1).unmarkDone();
//                } else {
//                    if(input.equalsIgnoreCase("todo")) {
//                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
//                    } else if(input.equalsIgnoreCase("deadline")) {
//                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
//                    } else if(input.equalsIgnoreCase("event")) {
//                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
//                    }
//
//                    if (input.startsWith("todo")) {
//                        tasksList.add(new Todo(input.replace("todo ", "")));
//                        count++;
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + tasksList.get(count - 1).toString());
//                        System.out.println("Now you have " + count + " tasks in the list");
//                        System.out.println("____________________________________________________________");
//
//                    } else if (input.startsWith("event")) {
//                        String[] s = input.replace("event ", "").split(" /from | /to");
//                        tasksList.add(new Event(s[0], s[1], s[2]));
//                        count++;
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + tasksList.get(count - 1).toString());
//                        System.out.println("Now you have " + count + " tasks in the list");
//                        System.out.println("____________________________________________________________");
//
//                    } else if (input.startsWith("deadline")) {
//                        String[] s = input.replace("deadline ", "").split(" /by ");
//                        tasksList.add(new Deadline(s[0], s[1]));
//                        count++;
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + tasksList.get(count - 1).toString());
//                        System.out.println("Now you have " + count + " tasks in the list");
//                        System.out.println("____________________________________________________________");
//                    } else if(input.startsWith("delete")) {
//                        System.out.println("____________________________________________________________");
//                        System.out.println("Noted. I've removed this task:");
//                        System.out.println("\t" + tasksList.get(Integer.parseInt(input.replace("delete ", "")) - 1));
//                        count--;
//                        System.out.println("Now you have " + count + " tasks in the list.");
//                        System.out.println("____________________________________________________________");
//                        tasksList.remove(Integer.parseInt(input.replace("delete ", "")) - 1);
//                    } else {
//                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    }
//                }
//            } catch (DukeException e) {
//                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//            }
//        }

    public void taskPrint(String input) {
        System.out.println(LINE + "\n"
                + "Got it. I've added this task" + "\n"
                + input + "\n" + "Now you have " + this.count
                + " tasks in this list." + "\n" + LINE);
    }

    public void deletePrint(String input) {
        System.out.println(LINE + "\n"
                + "Noted. I've removed this task: " + "\n"
                + input + "\n" + "Now you have " + this.count
                + " tasks in this list." + "\n" + LINE);
    }

    public void todo(String input) {
        tasksList.add(new Todo(input.replace("todo", "")));
        count++;
        taskPrint(tasksList.get(count - 1).toString());
    }
    public void deadline(String input) {
        String[] s = input.replace("deadline ", "").split(" /by ");
        tasksList.add(new Deadline(s[0], s[1]));
        count++;
        taskPrint(tasksList.get(count - 1).toString());
    }
    public void event(String input) {
        String[] s = input.replace("event ", "").split(" /from | /to");
        tasksList.add(new Event(s[0], s[1], s[2]));
        count++;
        taskPrint(tasksList.get(count - 1).toString());
    }

    public void delete(String input) {
        int i = Integer.parseInt(input.replace("delete ", "")) - 1;
        taskPrint(tasksList.get(i).toString());
        count--;
        tasksList.remove(i);
    }

    public void listOfTasks() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + tasksList.get(i).toString());
        }
    }

    public void run() {
        System.out.println(message);

        while(true) {
            try {
                String input = scanner.nextLine();
                System.out.println(LINE);
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(bye);
                    System.out.println(LINE);
                    break;

                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    if (count == 0) {
                        System.out.println("\t You currently have no tasks!");
                    } else {
                        listOfTasks();
                    }
                } else if (input.startsWith("mark")) {
                    int i = Integer.parseInt(input.replace("mark ", "")) - 1;
                    tasksList.get(i).markDone();
                } else if (input.startsWith("unmark")) {
                    int i = Integer.parseInt(input.replace("unmark ", "")) - 1;
                    tasksList.get(i).unmarkDone();
                } else {
                    if (input.equalsIgnoreCase("todo")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else if (input.equalsIgnoreCase("deadline")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    } else if (input.equalsIgnoreCase("event")) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }

                    if (input.startsWith("todo")) {
                        todo(input);
                    } else if (input.startsWith("event")) {
                        event(input);
                    } else if (input.startsWith("deadline")) {
                        deadline(input);
                    } else if (input.startsWith("delete")) {
                        delete(input);
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (Exception e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}

//                    if(input.equalsIgnoreCase("todo")) {
//                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
//                    } else if(input.equalsIgnoreCase("deadline")) {
//                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
//                    } else if(input.equalsIgnoreCase("event")) {
//                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
//                    }
//
//                    if (input.startsWith("todo")) {
//                        tasksList.add(new Todo(input.replace("todo ", "")));
//                        count++;
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + tasksList.get(count - 1).toString());
//                        System.out.println("Now you have " + count + " tasks in the list");
//                        System.out.println("____________________________________________________________");
//
//                    } else if (input.startsWith("event")) {
//                        String[] s = input.replace("event ", "").split(" /from | /to");
//                        tasksList.add(new Event(s[0], s[1], s[2]));
//                        count++;
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + tasksList.get(count - 1).toString());
//                        System.out.println("Now you have " + count + " tasks in the list");
//                        System.out.println("____________________________________________________________");
//
//                    } else if (input.startsWith("deadline")) {
//                        String[] s = input.replace("deadline ", "").split(" /by ");
//                        tasksList.add(new Deadline(s[0], s[1]));
//                        count++;
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + tasksList.get(count - 1).toString());
//                        System.out.println("Now you have " + count + " tasks in the list");
//                        System.out.println("____________________________________________________________");
//                    } else if(input.startsWith("delete")) {
//                        System.out.println("____________________________________________________________");
//                        System.out.println("Noted. I've removed this task:");
//                        System.out.println("\t" + tasksList.get(Integer.parseInt(input.replace("delete ", "")) - 1));
//                        count--;
//                        System.out.println("Now you have " + count + " tasks in the list.");
//                        System.out.println("____________________________________________________________");
//                        tasksList.remove(Integer.parseInt(input.replace("delete ", "")) - 1);
//                    } else {
//                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    }
//                }
//            } catch (DukeException e) {
//                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//            }
//        }
//    }
//}