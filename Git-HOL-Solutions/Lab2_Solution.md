# Lab 2: Git Ignore

## Objectives
- Explain git ignore
- Explain how to ignore unwanted files using git ignore
- Implement git ignore command to ignore unwanted files and folders

## Solution Commands

```bash
mkdir log
touch log/app.log
touch error.log
git status
echo "*.log" >> .gitignore
echo "log/" >> .gitignore
git status
git add .gitignore
git commit -m "Added .gitignore to ignore log files and folders"
```
