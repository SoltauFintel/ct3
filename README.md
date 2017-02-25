# Generic clipboard text transformation tool "ct3"

This is a little application with a JavaFX GUI and one button.
If the button is pressed it gets the text from clipboard, executes script.groovy and copies the result to the clipboard.
By modifying script.groovy this tool can do any text transformation without the need to build the application.
The Groovy 2.4 script gets the input by the variable called 'text'. The script can do output by throwing exceptions.
On success the window background becomes green. Otherwise it becomes red and displays the error message.
