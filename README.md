# teamtoptrumps
Hey guys, first I will say thank u to Emily for create this repository. Now I will give a quick guide of “Git”. Hope it will helps.

**UPDATE** To test the DatabaseConnectionTest class from your own laptop:
  - Open PGAdmin and create a new server with the name localhost, username postgres and your password
  - On line 55 of the class, replace the content in the bracket with "jdbc:postgresql://localhost:5432/postgres", username, password"
  - When you run the class, enter postgres as your username and the password as your password. This should connect to the database.

How to clone: let us clone form the remote repository at the very start, we can use the following code:
  git clone https://github.com/emilymurphy2110/teamtoptrumps.git

How to create branch: We create and switch to our branch at local repository first, than push it to the remote repository.
  git checkout -b <yourname> --------> create and switch to the new branch
  git branch                 --------> check branches, the "*" will shows you which branch u r
  git push -u origin <yourname> --------> push your branch to the remote repository
  
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
!!!!!  You only work on your branch and push to your branch. Do not update the master branch. Never update the master branch!!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

Common codes
Add your files form Working Directory to the Stage----------------------------------------------------------------------------
      git add <yourfile>      e.g. git add README.txt  ---- You can add severl and commit them together
Highly recommend: always check status after u add and commit------------------------------------------------------------------
      git status
Commit files form Stage to your branch---- If the git_status shows everything is fine, you can commit by using this code------
      git commit -m "<please leave a note here. one day, you will me>"
Push files to the remote repository after u finish a work---------------------------------------------------------------------
      git push origin <yourname>
Revert to the last version----------------------------------------------------------------------------------------------------
      git reset --hard HEAD^
Revert to an old version------------------------------------------------------------------------------------------------------
      git reset --hard commit_id ---------> How to find the commit_id?
                                            git log --pretty=oneline
Pull from remote repository---------------------------------------------------------------------------------------------------
      git pull origin <branchname>



Well, those are all I can think of now. I will keep updating this txt. Love u guys 
  
