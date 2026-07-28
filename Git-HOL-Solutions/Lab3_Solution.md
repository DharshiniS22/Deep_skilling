# Lab 3: Branching and Merging

## Objectives
- Explain branching and merging
- Explain about creating a branch request in GitLab
- Explain about creating a merge request in GitLab
- Construct a branch, do some changes in the branch, and merge it with master (or trunk)

## Solution Commands

```bash
git branch GitNewBranch
git branch -a
git checkout GitNewBranch
echo "This is content in the new branch" > branch_file.txt
git add branch_file.txt
git commit -m "Added branch_file.txt to GitNewBranch"
git status
git checkout master
git diff master..GitNewBranch
git difftool master..GitNewBranch
git merge GitNewBranch
git log --oneline --graph --decorate
git branch -d GitNewBranch
git status
```
