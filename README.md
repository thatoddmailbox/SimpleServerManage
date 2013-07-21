SimpleServerManage is a plugin for Bukkit servers.


Currently, it adds two commands:

- /shutdown [seconds]
  This command will display a message saying the server will shut down, with a count down for number of seconds specified.
  When the countdown is done, the server will stop.
  Permission ```simpleservermanage.shutdown``` is required to use this command.
- /restart [seconds]
  This command will display a message saying the server will restart, with a count down for number of seconds specified.
  When the countdown is done, the server will STOP.
  Permission ```simpleservermanage.restart``` is required to use this command.

SimpleServerManage also needs the Vault plugin.