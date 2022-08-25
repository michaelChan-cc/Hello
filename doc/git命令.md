1. # 查看

```bash

# 当前状态，在那个分支上               On branch b_cc     Your branch is up to date with 'origin/b_cc'.
git status
# 查看本地分支
git branch
# 查看远程分支
git branch -r
# 查看所有分支
git branch -a 


# 切换分支
git checkout dev_linxiao   




```

2. # 提交内容

```bash
# 拉取
git pull origin master

# 提交
git add .
git commit -m "ltc采集程序"
git push   根据其提示输入命令
```



2. # 分支

```bash
#二、本地创建新分支
git branch [分支名称]
#三、切换到新分支
git checkout [分支名称]
#四、创建+切换分支
git checkout -b [分支名称]

#其中：git checkout -b [分支名称]相当于两步
git branch [分支名称]
git checkout [分支名称]

#五、将新分支推送到github
git push origin [分支名称]


#六：删除本地分支  -D 强制删除，不管是否merge
git branch -d [分支名称]
git branch -D [分支名称]   
#七、删除github 远方分支  其中：分支前面：代表删除
git push origin :[branch name]

#八：git 提交本地代码至新分支 (4步走：#1.切换到新分支#2.添加本地需要提交的代码#3.提交本地代码#4.push到git仓库)
git checkout [分支名称]
git add .
git commit -m "修改说明"
git push origin [分支名称]

#git删除远程分支
git push origin --delete 分支名称
#git重命名分支
git branch -m 原分支名 新分支名
#git重命名当前分支
git branch -m 新分支名
#git推送当前分支到远程（远程没有该分支）
git push --set-upstream origin  xxx分支
```

