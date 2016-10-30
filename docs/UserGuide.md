# User Guide
* [Introduction](#introduction)
* [Getting Started](#getting-started)
    * [Download](#download)
    * [Launch](#launch)
    * [Visual Introduction](#visual-introduction)
    * [Start using Agendum](#start-using-agendum)
* [Features](#features)
* [FAQ](#faq)
* [Command Summary](#command-summary)

&nbsp;

[comment]: # (@@author A0148095X)
## Introduction
Hi there! Do you have too many tasks and are unable to keep track of all of them? Are you looking for a hassle-free task manager which works swiftly?

Enter Agendum.

This task manager will assist you in completing all your tasks on time. It will automatically sort your tasks by date so you can always see the most urgent tasks standing out at the top of the list!

Agendum is simple, efficient, flexible and phenomenally keyboard friendly. With just one line of command, Agendum will carry out your wishes. You don’t ever have to worry about having to click multiple buttons and links. Agendum is even capable of supporting your own custom command words! This means that you can get things done even faster, your way.


&nbsp;


[comment]: # (@@author A0003878Y)
## Getting Started

### Download

1. Ensure that you have Java version `1.8.0_60` or above installed on your computer.
2. Download the latest `Agendum.jar` from [here](../../../releases).
<img src="images/userguide/releases.png" width="300"><br>
3. Copy the jar file to the folder that you intend to use as the root directory of Agendum.

### Launch

To launch Agendum, double-click on `Agendum.jar` to launch Agendum. Welcome! Here is the main window you will be greeted with. Initially the task panels are empty but fill them up with tasks soon.

<img src="images/userguide/launch.png" width="800"><br>

[comment]: # (@@author A013367E)
### Visual Introduction

Here is what Agendum may look like with some tasks added and completed.

<img src="images/userguide/mainui.png" width="800"><br>

Notice how Agendum has 3 panels: **"Do It Soon"**, **"Do It Anytime"** and **"Done"**.
* **"Do It Soon"** panel will show your **uncompleted** tasks with deadlines and events. Those tasks demand your attention at or by some specific time! Agendum has helpfully sorted these tasks by their deadline or happening time.
    * **Overdue** tasks _(e.g. buy Jim's present)_ will stand out in pink at the top of the list.
    * **Upcoming** tasks (happening/due within a week) _(e.g. attend yoga class)_ will stand out in yellow next.
* **"Do It Anytime"** panel will show your **uncompleted** tasks which you did not specify a deadline or happening time. Do these tasks anytime.
* **"Done"** panel will show all your completed tasks. To make it easier for you to keep track of what you have done recently, Agendum will always show the latest completed tasks at the top of the list.

Agendum will clearly display the name and time associated with each task. Notice that each task is displayed with a ID. For example, the task *drop by grocery store* has a ID *5* now. We will use this ID to refer to the task for some Agendum commands.

The **Command Box** is located at the top of Agendum. Enter your keyboard commands into the box!
Just in case, there is a **Status Bar** located at the bottom of Agendum. You can check today's date and time, where your Agendum's to-do list data is located and when was the data last saved.

Agendum also has a pretty **Help Window** which summarizes the commands you can use and pop-ups after each commands for you to review your changes.


[comment]: # (@@author A0148031R)
### Start using Agendum
*This is only a brief introduction and suggestion on how to get started with Agendum. Refer to our [Features](#features) section, for a more extensive coverage on what Agendum can do.*

**Step 1 - Get some help**

Feeling lost or clueless? To see a summary of Agendum commands, use the keyboard shortcut <kbd>F5</kbd> or type **`help`** to bring up the help screen as shown below.

**Step 2 - Add a task**

Perhaps, you can start by adding a task to your empty Agendum to-do list. For example, you might remember you have to return your library books. Type the following line in the command box:
`> add return library books`
Since you did not specify a time to return the books, Agendum will add this task to the **Do It Anytime** panel. The task *return library books* has a ID *1* now.

**Step 3 - Update your task (if needed)**

You might change your mind and want to update the details of the task. For example, you might only want to return a single book "Animal Farm" instead. Type the following line in the command box:
`> rename 1 return "Animal Farm"`
Agendum will promptly update the changes. What if you suddenly discover the book is due within a week? You will want to return "Animal Farm" by Friday night. To (re)schedule the task, type the following command:
`> schedule 1 by friday 8pm`
Since you will have to return your books by a specific time, Agendum will move this task to the **Do It Soon** panel.

**Step 4 - Mark a task as completed**

With the help of Agendum, you remembered to return "Animal Farm" punctually on Friday. Celebrate by marking the task as completed. Type the following line in the command box:
`> mark 1`
Agendum will move the task _return "Animal Farm"_ to the **Done** panel.

**Step 5 - Good to go**

Continue exploring Agendum. Add more tasks to your Agendum to-do list and try out the various convenient commands given in the next section. Do note that the ID of the task might change as new tasks are added, updated and marked. Agendum takes care of it for you but you should always refer to the current ID displayed.

Here is a **summary of all the visual changes** you should see at every step:
<img src="images/userguide/startAgendum.png" width="800"><br>

&nbsp;


[comment]: # (@@author A0003878Y)
## Features

### Commands

> **Here are some general things to note**
> * All command words are case-insensitive. e.g. `Add` will match `add`
> * Words enclosed in angle brackets, e.g.`<name>` are the parameters. You can freely decide what to replace them by.
> * Parameters with `...` after them can have multiple instances (separated by whitespace). For example, `<index>...` means that you can specify multiple indices such as `3 5 7`.

#### Viewing help : `help`

If you need some reminder or more information about the features available, you can use the `help` command.

Format: `help`  
> Help is also shown if an incorrect command is entered e.g. `run`


#### Adding a task: `add`

If you have a task to work on, add it to the Agendum!<br>
You can add a task with no start time or end time by typing the following command

Format: `add TASK_NAME`
> This will create a task without a start date/time or end date/time.

Examples:  

* `add Go to Cold Storage`
* `add Watch Star Wars`
* `add Read The Mythical Man-Month`

You can add a task which must be done by a certain deadline. You can specify the deadline after the keyword `by`.

Format: `add TASK_NAME [by DATE_TIME]`
> Date formats are not case-sensitive

Examples:  

* `add watch Star Wars by Fri`
* `add watch Star Wars by 9pm`
* `add watch Star Wars by next Wed`
* `add watch Star Wars by tonight`
* `add watch Star Wars by 10 Oct, 9.30pm`

If you need a task to be done within a specific date and time, you can specify the start and end time using `from` and `to`.

Format: `add TASK_NAME [from START_DATE_TIME to END_DATE_TIME] `
> If you specify the time but no day or date is given, the date of creation will be used.

Examples:

* `add movie marathon from today 12pm to friday 3pm`
* `add project meeting from 10 oct 12pm to 10 oct 2pm`
* `add clean my room from today 10pm to 12am`

The event “project meeting” will start at 12pm on 10 October and end at 2pm on 10 October.


[comment]: # (@@author A0133367E)
#### Renaming a task : `rename`

Agendum understands that plans and tasks change all the time. <br>
If you wish to update or enhance the description of a task, you can use the following command.

Format: `rename INDEX NEW_TASK_NAME`  

> * Renames the task at the specified `INDEX`.
> * Index refers to the index number shown in the most recent listing.
> * The index **must be a positive integer** 1, 2, 3, ...


> * Note that it is not possible to rename a task name to its original name and it might not be possible for the task to have the same name as another existing task

Examples:  

* `list` <br>
  `rename 2 Star Wars II` <br>
  Renames the 2nd task in the list to “Star Wars II”

* `find Star Trek`   <br>
  `rename 1 Star Wars II` <br>
  Renames the 1st task in the results of the `find` command to “Star Wars II”


#### Updating the date/time of a task : `schedule`

Agendum recognizes that your schedule might change, and therefore allows you to reschedule your tasks easily.<br>
If a task no longer has a deadline or specified time, you can remove the previous time restrictions by typing the following command:

Format: `schedule INDEX [NEW_DATE_TIME_RESTRICTIONS]`

> * Schedule the task at the specified `INDEX`.
> * The index refers to the index number shown in the most recent listing.
> * The index **must be a positive integer** 1, 2, 3, ...
> * The time description must follow the format given in the add command examples

Examples:  

* `list` <br>
  `schedule 4` <br>
  Removes the deadline and start and end date/time for task 4 on the list.

* `list` <br>
  `schedule 2 by Fri`<br>
  Removes the deadline and start and end date/time for task 2 and resets the deadline to the coming Friday (If the current day is Friday, it would be the following Friday).

* `list`<br>
  `schedule 3 from 1 Oct 7pm to 1 Oct 9.30pm`<br>
  Sets the start time of task 3 to 1 Oct 7pm and the end time to 1 Oct 9.30pm respectively


#### Marking a task as completed : `mark`

Have you completed a task? Well done! <br>
Now record this in Agendum by identifying the index of the task and type in the following command:

Format: `mark INDEX...`

> * Mark the task at the specified `INDEX`.
> * The index refers to the index number shown in the most recent listing.
> * The index **must be a positive integer** 1, 2, 3, ...
> * The index can be in any order.

Examples:  

* `list`<br>
  `mark 5`<br>
  Marks the 5nd task in the list. Task 5 will then be moved to the **"Done"** panel as described below<br>
  <img src="images/UiMarkTask.png" width="800"><br>


* `find Homework`<br>
  `mark 1`<br>
  Marks the 1st task in the list of results of the `find` command.

Sometimes, you might have had a productive day; Agendum saves you the hassle of marking multiple tasks one by one. <br>
To mark multiple tasks, try out any of the following examples:

* `list`<br>
  `mark 2 3 4`<br>
  `mark 2,3,4` <br>
  `mark 2-4` <br>
  Each of the above command will mark the 2nd, 3rd and 4th task as completed.  


#### Unmarking a task as completed : `unmark`

You might change your mind and want to continue working on a recently completed task.
To reflect these changes in Agendum, follow this command:

Format: `unmark INDEX...`

This works in the same way as the `mark` command. The tasks will then be moved to the **"Do It Soon"** or **"Do It Anytime"** panel accordingly. <br>


#### Deleting a task : `delete`

There are some tasks which will never get done and are perhaps no longer relevant. <br>
You can remove these tasks from the task list by using the following command:

Format: `delete INDEX...`  

> * Deletes the task at the specified `INDEX`.
> * The index refers to the index number shown in the most recent listing.
> * The index **must be a positive integer** 1, 2, 3, ...

Examples:  

* `list` <br>
  `delete 2` <br>
  Deletes the 2nd task in the task list.

* `find movie` <br>
  `delete 1` <br>
  Deletes the 1st task in the results of the `find` command.

You can also delete multiple tasks in the task list with a single command.

Examples:

* `list` <br>
  `delete 2 3 4` <br>
  `delete 2,3,4` <br>
  `delete 2-4` <br>
  Each of the above command will delete the 2nd, 3rd and 4th task in the task list.  


[comment]: # (@@author A0148031R)
#### Undo the last command : `undo`  

If you have accidentally made a mistake in the previous command, you can use 'undo' to remedy it.<br>
Multiple undo actions are also supported.

Format: `undo`

Examples:

* `add homework`<br>
  `undo`<br>
  The task "homework" which has been added previously, will be removed.


#### Finding tasks containing keywords: `find`

As your task list grows over time, it may become harder to locate a task.<br>
Fortunately, Agendum can search and bring up these tasks to you. Enter the following command: <br>
Format: `find KEYWORD``...`  

  > * The search is not case sensitive. e.g `assignment` will match `Assignment`
  > * The order of the keywords does not matter. e.g. `2 essay` will match `essay 2`
  > * Only the name is searched
  > * Only full words will be matched e.g. `work` will not match `homework`
  > * Tasks matching at least one keyword will be returned (i.e. `OR` search). e.g. `2103` will match `2101 and 2103 assignment`

Examples:  

* `find Dory` <br>
  Returns `Shark & Dory` and `dory`  

* `find Nemo Dory` <br>
  Returns all tasks that contain `Dory` or `Nemo`  


#### Retrieving task list : `list`

After you are done searching for tasks, you can use the following command to return to the default view of task lists: <br>
Format: `list`


[comment]: # (@@author A0148095X)
#### Creating an alias for a command : `alias`

Perhaps you want to type a command faster, or change the name of a command to suit your needs; <br>
fret not, Agendum allows you to define your own aliases for commands. <br>
You can use both new and old command aliases to carry out the same action.

Format: `alias ORIGINAL_COMMAND_NAME NEW_COMMAND_NAME`  

> * NEW_COMMAND_NAME must be a single word.
> * ORIGINAL_COMMAND_NAME must be a command word that is specified in the Command Summary section
> * When creating an alias for a command with a pre-existing alias, it can also be used to carry out that command.

Examples:

* `alias mark m` <br>
  you can now use`m` or `mark` to mark a task as completed.<br>
  `alias mark mk`<br>
  Now you can use `m`, `mk` or `mark` to mark a task.


#### Removing an alias command : `unalias`

Is a current alias inconvenient? Have you thought of a better one? <br>
Or perhaps you are thinking of using an alias for another command. <br>
To remove a previously defined alias, type:

Format: `unalias ALIAS_FOR_COMMAND_NAME`

> * ALIAS_FOR_COMMAND_NAME must be one of the user-defined command words.
> * Once it has been removed, you can still use the original command word or other unremoved aliases.

Examples:

Assume that mark has been aliased with `m` and `mk`.
* `unalias m`<br>
  `m` can no longer be used to mark tasks; now you can only use the original command `mark` or `mk` to mark a task as completed.

Similarly, you can also use the following command:
* `unalias mk`<br>
  `mk` can no longer be used to mark tasks; now you can only use the original command `mark` to mark a task as completed.


#### Specifying the data storage location : `store`

Are you considering moving Agendum’s data files to another file directory?
You might want to save your Agendum task list to a Cloud Storage service so you can easily access from another device.
Agendum offers you the flexibility in choosing where the task list data will be stored.
The task list data will be saved to the specific directory, and future data will be saved in that location.

Format: `store PATH_TO_FILE`

> * PATH_TO_FILE must be a valid path to a file on the local computer.
> * If a file at PATH_TO_FILE exists, it will be overriden.
> * The previous data storage file will not be deleted.

Examples:
* `store C:/Dropbox/mytasklist.xml`


#### Loading from another data storage location : `load`

After relocating Agendum’s data files, you might want to load that exact copy of Agendum’s task list from a certain location, or from a Cloud Storage service. Agendum also offers you the flexibility to choose which data files to import.

Format: `load PATH_TO_FILE`

> * PATH_TO_FILE must be a valid path to a file on the local computer.
> * Existing data will be saved and stored in the existing data storage location.
> * The task list in Agendum will be replaced by the loaded task list.
> * Future data will be stored in PATH_TO_FILE.

Examples:
* `load C:/Dropbox/mytasklist.xml`

#### Exiting the program : `exit`

Are you done with organizing your tasks? Well done! <br>
To leave Agendum, type `exit`.

Format: `exit`  


### Keyboard Shortcuts

1. Use the <kbd>UP ARROW</kbd> and <kbd>DOWN ARROW</kbd> to scroll through earlier commands.
2. If you are entering a new command, use the <kbd>DOWN ARROW</kbd> to instantly clear the command line.


### Saving the data

Agendum saves its data into the specified data storage location, or by default it saves into `todolist.xml`. This saving automatically happens whenever the task list is changed; There is no need to save manually.


&nbsp;


## FAQ

<html>
<dl>
   <dt> Q: How do I transfer my data to another computer? </dt>
   <dd> Firstly, take note of the data storage location that your current todo list is saved at. You can check this by looking at the            bottom-right of Agendum. Navigate to this location and copy the data file to a portable USB device or hard disk. Then, ensure            that you have installed Agendum in the other computer. Copy the data file from your device onto the other computer, preferrably          in the same folder as Agendum. Use the <code>load</code> command to load it into Agendum. </dd>

   <dt> Q: Why did Agendum complain about an invalid file directory? </dt>
   <dd> Check if the directory you wish to relocate to exists, or if you have enough administrator privileges. </dd>

   <dt> Q: Can Agendum remind me when my task is due soon? </dt>
   <dd> Agendum will always show the tasks that are due soon at the top of list. However, Agendum will not show you a reminder. </dd>

   <dt> Q: Why did Agendum reject my alias for a command? </dt>
   <dd> The short-hand command cannot be one of Agendum’s command keywords (e.g. add, delete) and cannot be concurrently used to alias            another command (e.g. m cannot be used for both mark and unmark). </dd>

</dl>
</html>

&nbsp;

[comment]: # (@@author A0133367E)
## Command Summary

Command  | Format  
:-------:| :--------
Add      | `add TASK_NAME` or `add TASK_NAME by DATE_TIME` or `add TASK_NAME from START_DATE_TIME to END_DATE_TIME`
Alias    | `alias ORIGINAL_COMMAND_NAME NEW_COMMAND_NAME`
Delete   | `delete INDEX...`
Exit     | `exit`
Find     | `find KEYWORD...`
Help     | `help`
List     | `list`
Load     | `load PATH_TO_FILE`
Mark     | `mark INDEX...`
Rename   | `rename INDEX NEW_NAME`
Schedule | `schedule INDEX` or `schedule INDEX by DATE_TIME` or `schedule INDEX from START_DATE_TIME to END_DATE_TIME`
Select   | `select INDEX`
Store    | `store PATH_TO_FILE`
Unalias  | `unalias NEW_COMMAND_NAME` or `unalias ORIGINAL_COMMAND_NAME`
Undo     | `undo`
Unmark   | `unmark INDEX...`

For a quick reference,
> * Words in `UPPER_CASE` are the parameters.
> * Parameters with `...` after them can have multiple instances (separated by whitespace).
