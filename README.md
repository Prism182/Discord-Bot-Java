Hi! This discord bot is made for a privately owned server for me and my friends!
It boasts the main function of being able to create a category, role and a set of channels from a command and delete them from another!

If you would like to use this bot or adapt parts of the code, please feel free! all my code is free use, everything can be found freely on the internet.
If you would like to use this bot as is, refer to the next part.

When you open the "Main.java" file you will see the following code:

![image](https://github.com/user-attachments/assets/356471db-2f2b-42d9-9d7c-6e0c55f782d6)


Instead of pasting my bot's token here I have opted to use an enviromental variable, this means it does not leak a bot token online.
To use your own token you can paste the token into a the string variable in place of "dotenv.get("DISCORD_BOT_TOKEN")" like as follows.
Replace "YOUR BOT TOKEN HERE" with your bot token.

![image](https://github.com/user-attachments/assets/c4345859-96e3-46f1-8ba6-200b720aa646)


or you can create a file in the main folder (Discord-Bot-Java-main in this case) called ".env".
(Do not put anything before the .env i.e. [example.env] as it will not work).
This file will house your enviromental variables, and you should create a single line like as follows, replace "YOUR BOT TOKEN HERE" with your bot token. 

![image](https://github.com/user-attachments/assets/8d836ec5-f71f-42fe-88f7-b3b9e998eaff)
 

![image](https://github.com/user-attachments/assets/f30bd972-0087-4d94-aed1-0f45542ed90f)


