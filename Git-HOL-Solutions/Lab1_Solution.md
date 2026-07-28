# Lab 1: Git Configuration and Setup

## Objectives
- Familiar with Git commands like git init, git status, git add, git commit, git push, and git pull.
- Setup your machine with Git Configuration.
- Integrate notepad++.exe to Git and make it a default editor.
- Add a file to source code repository.

## Solution Commands

```bash
git --version
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
git config --list
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
git config --global -e
mkdir GitDemo
cd GitDemo
git init
ls -a
echo "Welcome to Git" > welcome.txt
ls
cat welcome.txt
git status
git add welcome.txt
git commit -m "Added welcome.txt"
git status
git remote add origin <your_remote_repository_url>
git push -u origin master
```
