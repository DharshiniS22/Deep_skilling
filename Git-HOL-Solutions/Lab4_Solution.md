# Lab 4: Conflict Resolution

## Objectives
- Explain how to resolve the conflict during merge.
- Implement conflict resolution when multiple users are updating the trunk (or master) in such a way that it results into a conflict with the branch's modification.

## Solution Commands

```bash
git checkout master
git status
git checkout -b GitWork
echo "<hello>GitWork</hello>" > hello.xml
git add hello.xml
git commit -m "Added hello.xml in GitWork branch"
git checkout master
echo "<hello>master</hello>" > hello.xml
git add hello.xml
git commit -m "Added hello.xml in master branch"
git log --oneline --graph --decorate --all
git diff master..GitWork
git difftool master..GitWork
git merge GitWork
cat hello.xml
git mergetool
git commit -m "Resolved merge conflict between master and GitWork"
git status
echo "*.orig" >> .gitignore
git add .gitignore
git commit -m "Ignore mergetool backup files"
git branch
git branch -d GitWork
git log --oneline --graph --decorate
```
