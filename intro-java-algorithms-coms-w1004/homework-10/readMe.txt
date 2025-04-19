Invalid Login IP Address Documenter

The following program takes two command line arguments: the name of a single log file and the name of an output file. The former has the login information of users valid and invalid, and the latter has the list of IP addresses, the login attempts of which failed three or more times. 

The program saves every line in the scanned input file that contains the String “Invalid” in array lists. It then takes the IP addresses appearing in those lines and saves them into another array list. Those IP that appear three or more times are saved into yet another array list. Then those IP addresses are printed within an output file viewable by the user.

The try and catch methods handle exceptions i.e. when the input and/or output file(s) do(es) not exist.